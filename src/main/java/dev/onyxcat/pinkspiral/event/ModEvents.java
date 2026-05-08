package dev.onyxcat.pinkspiral.event;

import dev.onyxcat.pinkspiral.PinkSpiral;
import dev.onyxcat.pinkspiral.items.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = PinkSpiral.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // 1. 判定是不是草
        if (event.getState().is(Blocks.SHORT_GRASS)) {
            LevelAccessor level = event.getLevel();

            if (!level.isClientSide()) {
                // 2. 10% 掉落概率判定 (0.1)
                // level.getRandom() 比 Math.random() 更符合 MC 的随机风格
                if (level.getRandom().nextFloat() < 0.1f) {
                    BlockPos pos = event.getPos();
                    // 3. 掉落大豆
                    Block.popResource((Level) level, pos, new ItemStack(ModItem.SOYBEAN.get()));
                }
            }
        }
    }
}