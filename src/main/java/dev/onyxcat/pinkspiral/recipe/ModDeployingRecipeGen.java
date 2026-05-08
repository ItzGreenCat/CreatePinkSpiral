package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import dev.onyxcat.pinkspiral.fluids.ModFluid;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import java.util.concurrent.CompletableFuture;

public class ModDeployingRecipeGen extends DeployingRecipeGen {
    public ModDeployingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String defaultNamespace) {
        super(output, registries, defaultNamespace);
    }
    GeneratedRecipe ESTRADIOL_VALERATE_ASSEMBLY = create("estradiol_valerate_assembly", b -> b
            .require(ModItem.ESTRADIOL.get())
            .require(ModItem.VALERIC_ACID_BOTTLE.get())
            .output(ModItem.ESTRADIOL_VALERATE.get(), 1)
    );
}