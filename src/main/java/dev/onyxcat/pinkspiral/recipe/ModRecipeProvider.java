package dev.onyxcat.pinkspiral.recipe;

import com.simibubi.create.AllItems;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.MEOWGYNOVA_BOX.get())
                .pattern(" P ")
                .pattern("PWP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('W', Items.PINK_WOOL)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.MEOWGYNOVA.get())
                .pattern("PPP")
                .pattern("PWP")
                .pattern("PPP")
                .define('P', ModItem.ESTRADIOL_VALERATE.get())
                .define('W', ModItem.MEOWGYNOVA_BOX.get())
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.HORMONE_MONITOR.get())
                .pattern(" G ")
                .pattern("ETE")
                .pattern(" P ")
                .define('G', Items.GLASS_PANE)
                .define('E', AllItems.ELECTRON_TUBE.get())
                .define('T', Items.COMPASS)
                .define('P', AllItems.COPPER_SHEET.get())
                .unlockedBy("has_hormone_medicine", has(ModItem.MEOWGYNOVA.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItem.MEOWGYNOVA_CORE.get())
                .pattern("PRP")
                .pattern("DMD")
                .pattern("QGQ")
                .define('P',Items.PISTON)
                .define('R',Items.REDSTONE)
                .define('D',AllItems.ELECTRON_TUBE)
                .define('M',ModItem.MEOWGYNOVA)
                .define('Q',AllItems.POLISHED_ROSE_QUARTZ)
                .define('G',AllItems.GOLDEN_SHEET)
                .unlockedBy("has_meowgynova_with_package", has(ModItem.MEOWGYNOVA.get()))
                .save(output);

    }
}