package dev.onyxcat.pinkspiral.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MeowgynovaItem extends Item {
    public MeowgynovaItem(Properties properties) {
        super(properties.stacksTo(1).durability(8));
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // 不要在这里直接用 super 的返回值，因为它会尝试 shrink 你的物品
        if (entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                // 正确逻辑：增加损耗值（Damage）
                int newDamage = stack.getDamageValue() + 1;

                if (newDamage < stack.getMaxDamage()) {
                    // 如果损耗还没到上限，更新损耗值并保留物品
                    stack.setDamageValue(newDamage);
                } else {
                    // 如果损耗达到 8（吃完了），返回空盒
                    return new ItemStack(ModItem.MEOWGYNOVA_BOX.get());
                }
            }
        }

        // 关键：返回当前的 stack 实例，阻止系统将其删除
        return stack;
    }
}
