package dev.onyxcat.pinkspiral.recipe;

import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.VALERIC_ACID_BOTTLE.get())
                .requires(ModItem.VALERIC_ACID_POWDER.get())
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_valeric_powder", has(ModItem.VALERIC_ACID_POWDER.get()))
                .save(output);
    }
}