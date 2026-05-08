package dev.onyxcat.pinkspiral.fluids;

import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import dev.onyxcat.pinkspiral.PinkSpiral;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class ModFluid {
    public static final FluidEntry<BaseFlowingFluid.Flowing> SOYBEAN_OIL = PinkSpiral.REGISTRATE
            .fluid("soybean_oil",
                    ResourceLocation.fromNamespaceAndPath(PinkSpiral.MODID, "block/soybean_oil_still"),
                    ResourceLocation.fromNamespaceAndPath(PinkSpiral.MODID, "block/soybean_oil_flow"))
            .lang("Soybean Oil")
            .block()
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .noLootTable()
                    .noCollission())
            .build()
            .register();
    public static final FluidEntry<BaseFlowingFluid.Flowing> CRUDE_FERMENTED_ACID = PinkSpiral.REGISTRATE
            .fluid("crude_fermented_acid",
                    ResourceLocation.fromNamespaceAndPath(PinkSpiral.MODID, "block/crude_fermented_acid_still"),
                    ResourceLocation.fromNamespaceAndPath(PinkSpiral.MODID, "block/crude_fermented_acid_flow"))
            .lang("Crude Fermented Acid")
            .block()
            .properties(p -> p.mapColor(MapColor.COLOR_YELLOW)
                    .noLootTable()
                    .noCollission())
            .build()
            .register();
    public static void register(){
        System.out.println("[PinkSpiral] Loaded Mod Fluids");
    }
}
