package dev.onyxcat.pinkspiral;

import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.onyxcat.pinkspiral.blocks.ModBlock;
import dev.onyxcat.pinkspiral.fluids.ModFluid;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(PinkSpiral.MODID)
public class PinkSpiral {
    public static final String MODID = "createpinkspiral";
    public static final DeferredRegister<CreativeModeTab> TABS_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PinkSpiral.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS_REGISTER.register("pink_spiral",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pinkspiral.tab"))
                    .icon(() -> new ItemStack(Items.PINK_WOOL))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItem.SOYBEAN_OIL_BUCKET.get());
                        output.accept(ModItem.CRUDE_FERMENTED_ACID_BUCKET.get());
                    })
                    .build());

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public PinkSpiral(IEventBus modEventBus) {
        TABS_REGISTER.register(modEventBus);
        REGISTRATE.defaultCreativeTab(MAIN_TAB.getKey());
        REGISTRATE.registerEventListeners(modEventBus);

        ModBlock.register();
        ModFluid.register();
        ModItem.register();

    }
}