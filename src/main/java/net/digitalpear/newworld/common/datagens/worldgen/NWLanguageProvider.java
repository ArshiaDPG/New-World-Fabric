package net.digitalpear.newworld.common.datagens.worldgen;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.registry.Registries;

public class NWLanguageProvider extends FabricLanguageProvider {
    public NWLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        NWBlocks.FIR.getRegisteredBlocksList().forEach(block -> {
            if (!(block instanceof AbstractSignBlock)){
                translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath()));
            }
        });
        NWBlocks.FIR.getRegisteredItemsList().forEach(item -> {
                translationBuilder.add(item, formatString(Registries.ITEM.getId(item).getPath()));
        });
        translationBuilder.add(NWItems.FIR_BOAT, "Fir Boat");
        translationBuilder.add(NWItems.FIR_CHEST_BOAT, "Fir Boat with Chest");
    }

    public static String formatString(String input) {
        String[] words = input.split("_"); // Split the input string at underscores
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter of each word and append it to the result
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    // Append the rest of the word (excluding the first letter)
                    result.append(word.substring(1));
                }
                result.append(" "); // Append a space after each word
            }
        }

        return result.toString().trim(); // Trim any leading/trailing spaces and return the formatted string
    }
}
