package dev.onyxcat.pinkspiral.datagen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;

public class MergingLanguageProvider extends LanguageProvider {
    protected final String locale;
    protected final Path existingFilePath;

    public MergingLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.locale = locale;
        this.existingFilePath = output.getOutputFolder()
                .resolve("assets")
                .resolve(modid)
                .resolve("lang")
                .resolve(locale + ".json");
    }

    @Override
    protected void addTranslations() {
        loadExistingTranslations();
        add("itemGroup.pinkspiral.tab", "Create: Pink Spiral");
        add("msg.pinkspiral.hormone_overload","You eat too much Meowgynova in one day");
        add("msg.pinkspiral.monitor_report_header","Hormone Monitor");
        add("msg.pinkspiral.current_concentration","Estradiol Concentration");
        add("msg.pinkspiral.today_dose","Today Dose");
        add("msg.pinkspiral.effect_strength_reduction","Strength Reduction");
        add("msg.pinkspiral.effect_precision_increase","Precision Increase");
    }

    private void loadExistingTranslations() {
        File file = existingFilePath.toFile();
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                json.entrySet().forEach(entry -> {
                    add(entry.getKey(), entry.getValue().getAsString());
                });
            } catch (Exception e) {
                System.err.println("无法读取现有的语言文件进行合并: " + e.getMessage());
            }
        }
    }
}