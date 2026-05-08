package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import dev.onyxcat.pinkspiral.fluids.ModFluid;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.concurrent.CompletableFuture;

public class ModFillingRecipeGen extends FillingRecipeGen {
    public ModFillingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries, defaultNamespace);
    }
    GeneratedRecipe VALERIC_ACID_CRYSTAL = create("valeric_acid_crystal",b -> b
            .require(Items.BLAZE_POWDER)
            .withFluidIngredients(SizedFluidIngredient.of(ModFluid.CRUDE_FERMENTED_ACID.get().getSource(), 500)) // Spout 里的液体
            .output(ModItem.VALERIC_ACID_CRYSTAL.get(), 1));
}