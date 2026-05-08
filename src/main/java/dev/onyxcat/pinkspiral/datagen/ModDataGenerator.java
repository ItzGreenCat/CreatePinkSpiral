package dev.onyxcat.pinkspiral.datagen;

import dev.onyxcat.pinkspiral.PinkSpiral;
import dev.onyxcat.pinkspiral.recipe.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = PinkSpiral.MODID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        String modid = PinkSpiral.MODID;

        generator.addProvider(event.includeServer(), new ModMillingRecipeGen(output, registries, modid));
        generator.addProvider(event.includeServer(), new ModMixingRecipeGen(output, registries, modid));
        generator.addProvider(event.includeServer(), new ModFillingRecipeGen(output, registries, modid));
        generator.addProvider(event.includeServer(), new ModPressingRecipeGen(output, registries, modid));
        generator.addProvider(event.includeServer(), new ModDeployingRecipeGen(output, registries, modid));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, registries));

        generator.addProvider(event.includeClient(), new MergingLanguageProvider(output, modid,"en_us"));
    }
}