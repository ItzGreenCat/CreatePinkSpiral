package dev.onyxcat.pinkspiral.items;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.onyxcat.pinkspiral.PinkSpiral;
import dev.onyxcat.pinkspiral.blocks.ModBlock;
import dev.onyxcat.pinkspiral.fluids.ModFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

public class ModItem {
    public static final ItemEntry<ItemNameBlockItem> SOYBEAN = PinkSpiral.REGISTRATE.item("soybean", p -> new ItemNameBlockItem(ModBlock.SOY_BLOCK.get(),p))
            .lang("SoyBean")
            .model((c, p) -> p.generated(c))
            .tag(Tags.Items.SEEDS)
            .register();
    public static final ItemEntry<Item> SOY_FLOUR = PinkSpiral.REGISTRATE
            .item("soy_flour", Item::new)
            .lang("Soy Flour").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> STEROL = PinkSpiral.REGISTRATE
            .item("sterol",Item::new)
            .lang("Sterol").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<BucketItem> SOYBEAN_OIL_BUCKET = PinkSpiral.REGISTRATE
            .item("soybean_oil_bucket",p -> new BucketItem(ModFluid.SOYBEAN_OIL.get().getSource(),p))
            .lang("Soy Oil Bucket").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<BucketItem> CRUDE_FERMENTED_ACID_BUCKET = PinkSpiral.REGISTRATE
            .item("crude_fermented_acid_bucket",p -> new BucketItem(ModFluid.CRUDE_FERMENTED_ACID.get().getSource(),p))
            .lang("Crude Fermented Acid").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> VALERIC_ACID_CRYSTAL = PinkSpiral.REGISTRATE
            .item("valeric_acid_crystal",Item::new)
            .lang("Valeric Acid Crystal").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> VALERIC_ACID_BOTTLE = PinkSpiral.REGISTRATE
            .item("valeric_acid_bottle",Item::new)
            .lang("Valeric Acid Bottle").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> VALERIC_ACID_POWDER = PinkSpiral.REGISTRATE
            .item("valeric_acid_powder",Item::new).model((c,p)-> p.generated(c))
            .lang("Valeric Acid Powder")
            .register();
    public static final ItemEntry<Item> STEROL_PRECURSOR = PinkSpiral.REGISTRATE
            .item("sterol_precursor",Item::new)
            .lang("Sterol Precursor").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> ESTRADIOL = PinkSpiral.REGISTRATE
            .item("estradiol",Item::new)
            .lang("Estradiol").model((c,p) -> p.generated(c))
            .register();
    public static final ItemEntry<Item> ESTRADIOL_VALERATE = PinkSpiral.REGISTRATE
            .item("estradiol_valerate",Item::new)
            .lang("Estradiol Valerate").model((c,p) -> p.generated(c))
            .register();
    public static void register(){
        System.out.println("[PinkSpiral] Loaded Mod Items");
    }
}
