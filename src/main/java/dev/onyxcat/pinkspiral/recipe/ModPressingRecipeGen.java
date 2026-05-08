package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class ModPressingRecipeGen extends PressingRecipeGen {
    public ModPressingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries, defaultNamespace);
    }
    GeneratedRecipe VALERIC_ACID_POWDER = create("valeric_acid_powder", b -> b
            .require(ModItem.VALERIC_ACID_CRYSTAL.get())
            .output(ModItem.VALERIC_ACID_POWDER.get())
    );
    GeneratedRecipe ESTRADIOL = create("estradiol", b -> b
            .require(ModItem.STEROL_PRECURSOR.get())
            .output(ModItem.ESTRADIOL.get())
    );
}