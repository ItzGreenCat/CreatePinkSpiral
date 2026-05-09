package dev.onyxcat.pinkspiral.event;

import dev.onyxcat.pinkspiral.PinkSpiral;
import dev.onyxcat.pinkspiral.blocks.ModBlock;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.UUID;

@EventBusSubscriber(modid = PinkSpiral.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // 1. 判定是不是草
        if (event.getState().is(Blocks.SHORT_GRASS)) {
            LevelAccessor level = event.getLevel();

            if (!level.isClientSide()) {
                // 2. 10% 掉落概率判定 (0.1)
                // level.getRandom() 比 Math.random() 更符合 MC 的随机风格
                if (level.getRandom().nextFloat() < 0.1f) {
                    BlockPos pos = event.getPos();
                    // 3. 掉落大豆
                    Block.popResource((Level) level, pos, new ItemStack(ModItem.SOYBEAN.get()));
                }
            }
        }
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // 这一行是解决黑框的关键
            ItemBlockRenderTypes.setRenderLayer(ModBlock.SOY_BLOCK.get(), RenderType.cutout());
        });
    }
    @SubscribeEvent
    public static void onEatingMeowgynova(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (event.getItem().getItem() == ModItem.MEOWGYNOVA.get()) {
            Level level = player.level();

            if (!level.isClientSide) {
                CompoundTag data = player.getPersistentData();
                long currentDay = level.getGameTime() / 24000;
                long lastDoseDay = data.getLong("last_dose_day");
                int dailyCount = data.getInt("daily_dose_count");

                if (currentDay > lastDoseDay) {
                    dailyCount = 0;
                    data.putLong("last_dose_day", currentDay);
                }

                dailyCount++;
                data.putInt("daily_dose_count", dailyCount);

                if (dailyCount > 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0));
                    player.displayClientMessage(Component.translatable("msg.pinkspiral.hormone_overload")
                            .withStyle(ChatFormatting.RED), true);
                }

                float currentConcentration = data.getFloat("meowgynova_concentration");
                float newConcentration = Math.min(100f, currentConcentration + 8f);
                data.putFloat("meowgynova_concentration", newConcentration);
                data.putLong("last_dose_tick", level.getGameTime());

                syncHRTEffects(player, newConcentration);
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(ServerTickEvent.Post event) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;

        if (server.getTickCount() % 1200 == 0) {

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                CompoundTag data = player.getPersistentData();

                if (data.contains("meowgynova_concentration")) {
                    float currentLevel = data.getFloat("meowgynova_concentration");

                    if (currentLevel > 0) {
                        float decayRate = 0.5f;
                        float newLevel = Math.max(0, currentLevel - decayRate);

                        data.putFloat("meowgynova_concentration", newLevel);

                        syncHRTEffects(player, newLevel);
                    }
                }
            }
        }
    }
    private static final ResourceLocation STRENGTH_REDUCTION_ID = ResourceLocation.fromNamespaceAndPath("pinkspiral", "hormone_strength_reduction");
    private static final ResourceLocation PRECISION_INCREASE_ID = ResourceLocation.fromNamespaceAndPath("pinkspiral", "hormone_precision_increase");

    private static void syncHRTEffects(LivingEntity entity, float concentration) {
        if (entity.level().isClientSide) return;

        if (concentration < 1.0f) {
            removeHormoneAttributes(entity);
            return;
        }
        if (concentration >= 1.0f) {
            applyTimedEffect(entity, MobEffects.DAMAGE_RESISTANCE, 0, 24000);
        }

        if (concentration >= 20.0f) {
            updateAttribute(entity, Attributes.ATTACK_DAMAGE, STRENGTH_REDUCTION_ID, -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        if (concentration >= 50.0f) {
            applyTimedEffect(entity, MobEffects.REGENERATION, 0, 24000);
            updateAttribute(entity, Attributes.ATTACK_SPEED, PRECISION_INCREASE_ID, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
        if (concentration >= 80.0f) {
            applyTimedEffect(entity, MobEffects.REGENERATION, 1, 24000);
            applyTimedEffect(entity, MobEffects.ABSORPTION, 0, 24000);
        }
    }

    private static void applyTimedEffect(LivingEntity entity, Holder<MobEffect> effect, int amplifier, int duration) {
        MobEffectInstance current = entity.getEffect(effect);
        if (current == null || current.getDuration() < 200) {
            entity.addEffect(new MobEffectInstance(effect, duration, amplifier, true, false, true));
        }
    }
    private static void updateAttribute(LivingEntity entity, Holder<Attribute> attribute, ResourceLocation id, float value, AttributeModifier.Operation op) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            if (!instance.hasModifier(id)) {
                instance.addTransientModifier(new AttributeModifier(id, (double) value, op));
            }
        }
    }
    private static void removeHormoneAttributes(LivingEntity entity) {
        AttributeInstance attack = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance speed = entity.getAttribute(Attributes.ATTACK_SPEED);
        if (attack != null) {
            attack.removeModifier(STRENGTH_REDUCTION_ID);
        }
        if (speed != null) {
            speed.removeModifier(PRECISION_INCREASE_ID);
        }
    }
    @SubscribeEvent
    public static void onUseMonitor(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem() == ModItem.HORMONE_MONITOR.get()) {
            Player player = event.getEntity();
            if (!player.level().isClientSide) {
                CompoundTag data = player.getPersistentData();
                float concentration = data.getFloat("meowgynova_concentration");
                int dailyDose = data.getInt("daily_dose_count");

                // 发送详细的本地化报告
                player.sendSystemMessage(Component.translatable("msg.pinkspiral.monitor_report_header")
                        .withStyle(ChatFormatting.LIGHT_PURPLE));

// 2. 浓度拼接：[语言键内容] + " " + [数值]
                player.sendSystemMessage(Component.translatable("msg.pinkspiral.current_concentration")
                        .append(" " + String.format("%.2f", concentration) + "%"));

// 3. 今日剂量拼接
                player.sendSystemMessage(Component.translatable("msg.pinkspiral.today_dose")
                        .append(" " + dailyDose + " / 4"));

// --- 属性变化描述 ---
                if (concentration >= 20.0f) {
                    player.sendSystemMessage(Component.translatable("msg.pinkspiral.effect_strength_reduction")
                            .append(" " + "(-15%)") // 你也可以在这里拼接具体的百分比数值
                            .withStyle(ChatFormatting.GRAY));
                }
                if (concentration >= 50.0f) {
                    player.sendSystemMessage(Component.translatable("msg.pinkspiral.effect_precision_increase")
                            .append(" " + "(+10%)")
                            .withStyle(ChatFormatting.GOLD));
                }
            }
        }
    }
}