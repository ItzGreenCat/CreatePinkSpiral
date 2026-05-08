package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import dev.onyxcat.pinkspiral.fluids.ModFluid;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.concurrent.CompletableFuture;

public class ModMixingRecipeGen extends MixingRecipeGen {
    public ModMixingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries, defaultNamespace);
    }

    GeneratedRecipe SOY_OIL = create("soy_oil_extraction", b -> b
        .require(ModItem.SOY_FLOUR.get())
        .output((Fluid) ModFluid.SOYBEAN_OIL.getSource(), 125)
        .requiresHeat(HeatCondition.HEATED)
        .duration(20)
    );
    GeneratedRecipe STEROL_DISTILLATION = create("sterol_distillation", b -> b
            .duration(800) // 蒸馏过程很慢，给它一个较长的加工时间
            .require(ModFluid.SOYBEAN_OIL.getSource(), 1000) // 输入一桶大豆油
            // 产出固体甾醇粉末
            .output(ModItem.STEROL.get(), 1)
            .output(0.1f, ModItem.STEROL.get(), 1)
            .output(0.01f, ModItem.STEROL.get(), 2)
            // 核心逻辑：必须使用蓝火（模拟分馏塔的高温环境）
            .requiresHeat(HeatCondition.SUPERHEATED)
    );
    GeneratedRecipe CRUDE_FERMENTED_ACID_SUGAR = create("crude_fernebted_acid_extraction_sugar", b -> b
            .require(Items.SUGAR)
            .require(Items.BONE_MEAL)
            .withFluidIngredients(SizedFluidIngredient.of(Fluids.WATER,500))
            .output((Fluid) ModFluid.CRUDE_FERMENTED_ACID.getSource(), 500)
            .requiresHeat(HeatCondition.HEATED)
            .duration(350)
    );
    GeneratedRecipe CRUDE_FERMENTED_ACID_BEETROOT = create("crude_fernebted_acid_extraction_beetroot", b -> b
            .require(Items.BEETROOT)
            .require(Items.BONE_MEAL)
            .withFluidIngredients(SizedFluidIngredient.of(Fluids.WATER,250))
            .output((Fluid) ModFluid.CRUDE_FERMENTED_ACID.getSource(), 250)
            .requiresHeat(HeatCondition.HEATED)
            .duration(350)
    );
    GeneratedRecipe STEROL_PRECURSOR = create("sterol_precursor", b -> b
            .require(ModItem.STEROL)
            .require(Items.REDSTONE)
            .withFluidIngredients(SizedFluidIngredient.of(Fluids.WATER,500))
            .output(ModItem.STEROL_PRECURSOR.get(), 1)
            .requiresHeat(HeatCondition.HEATED)
            .duration(350)
    );
}