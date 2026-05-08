package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.CompletableFuture;

public class ModMillingRecipeGen extends MillingRecipeGen {
    public ModMillingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries, defaultNamespace);
    }
    GeneratedRecipe SOY_FLOUR = create("soy_flour_milling", b -> b
        .duration(100)
        .require(ModItem.SOYBEAN.get())
        .output(new ProcessingOutput(new ItemStack(ModItem.SOY_FLOUR.get(),1), 1.0f))
        .output(new ProcessingOutput(new ItemStack(ModItem.SOY_FLOUR.get(),1),0.3f))
    );
}