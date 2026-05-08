package dev.onyxcat.pinkspiral.blocks;

import com.tterrag.registrate.util.entry.BlockEntry;
import dev.onyxcat.pinkspiral.PinkSpiral;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ModBlock {

    public static final BlockEntry<SoybeanCropBlock> SOY_BLOCK = PinkSpiral.REGISTRATE.block("soybean_crop", SoybeanCropBlock::new)
            .properties(p -> p.noCollission().mapColor(MapColor.PLANT).instabreak().pushReaction(PushReaction.DESTROY))
            .loot((lt,block) -> lt.add(block,lt.createCropDrops(block, ModItem.SOYBEAN.get(),ModItem.SOYBEAN.get(),LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(block)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(CropBlock.AGE, 7)))))
            .blockstate((c, p) -> {
                // 自动生成 0-7 级的作物模型
                // p.models().cross(...) 会生成类似原版小麦那种十字交叉的模型
                for (int age = 0; age <= 7; age++) {
                    p.getVariantBuilder(c.get())
                            .partialState().with(SoybeanCropBlock.AGE, age)
                            .modelForState().modelFile(
                                    p.models().cross("soy_crop_age" + age, p.modLoc("block/soy_crop_age" + age))
                            ).addModel();
                }
            })
            .register();
    public static void register(){
        System.out.println("[PinkSpiral] Loaded Mod Blocks");
    }
}
