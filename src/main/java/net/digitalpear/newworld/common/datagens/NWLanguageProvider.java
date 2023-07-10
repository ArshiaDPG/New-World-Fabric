package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

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

        translationBuilder.add(NWBlocks.FIR_SAPLING, "Fir Sapling");

        translationBuilder.add(NWItems.ANCIENT_MATTOCK, "Ancient Mattock");

        translationBuilder.add(NWItems.FIR_BOAT, "Fir Boat");
        translationBuilder.add(NWItems.FIR_CHEST_BOAT, "Fir Boat with Chest");

        translationBuilder.add("advancements.story.collect_ancient_mattock.title", "Renaissance Tool");
        translationBuilder.add("advancements.story.collect_ancient_mattock.description", "Discover an Ancient Mattock.");

//        translationBuilder.add("biome.newworld.wooded_meadow", "Wooded Meadow");

        makeBiomeTranslation(translationBuilder, NWBiomes.WOODED_MEADOW);
    }


    public static void makeBiomeTranslation(TranslationBuilder translationBuilder, RegistryKey<Biome> biomeRegistryKey){
        Identifier identifier = biomeRegistryKey.getValue();
        translationBuilder.add("biome" + "." + identifier.getNamespace() + "." + identifier.getPath(), formatString(identifier.getPath()));
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
