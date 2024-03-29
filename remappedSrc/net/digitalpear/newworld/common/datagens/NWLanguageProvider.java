package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWEntityTypes;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.woodset.Woodset;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Arrays;

public class NWLanguageProvider extends FabricLanguageProvider {
    public NWLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        makeWoodTranslations(translationBuilder,NWBlocks.FIR);

        makeTranslation(translationBuilder, NWBlocks.FIR_SAPLING);
        makeTranslation(translationBuilder, NWItems.ANCIENT_MATTOCK);

        makeTranslation(translationBuilder, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, "Template Fragment", "Mattock Head");
        makeTranslation(translationBuilder, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, "Template Fragment", "Mattock Shaft");

        makeSmithingTemplateTranslation(translationBuilder, NWItems.MATTOCK_CRAFTING_TEMPLATE, "mattock_crafting", "Stick", "Flint");

        makeTranslation(translationBuilder, NWBlocks.LOAM);
        makeTranslation(translationBuilder, NWBlocks.LOAM_STAIRS);
        makeTranslation(translationBuilder, NWBlocks.LOAM_SLAB);
        makeTranslation(translationBuilder, NWBlocks.LOAM_WALL);
        makeTranslation(translationBuilder, NWBlocks.LOAM_BRICKS);
        makeTranslation(translationBuilder, NWBlocks.LOAM_BRICK_STAIRS);
        makeTranslation(translationBuilder, NWBlocks.LOAM_BRICK_SLAB);
        makeTranslation(translationBuilder, NWBlocks.LOAM_BRICK_WALL);
        makeTranslation(translationBuilder, NWBlocks.LOAM_TILES);
        makeTranslation(translationBuilder, NWBlocks.LOAM_TILE_STAIRS);
        makeTranslation(translationBuilder, NWBlocks.LOAM_TILE_SLAB);
        makeTranslation(translationBuilder, NWBlocks.LOAM_TILE_WALL);

        makeTranslation(translationBuilder, NWItems.AUTOMATON_SPAWN_EGG);
        makeTranslation(translationBuilder, NWEntityTypes.AUTOMATON);

        translationBuilder.add(NWItems.FIR_BOAT, "Fir Boat");
        translationBuilder.add(NWItems.FIR_CHEST_BOAT, "Fir Boat with Chest");

        translationBuilder.add("advancements.story.collect_ancient_mattock.title", "Renaissance Tool");
        translationBuilder.add("advancements.story.collect_ancient_mattock.description", "Discover an Ancient Mattock.");


        NWBiomes.biomes.forEach(biomeRegistryKey -> makeBiomeTranslation(translationBuilder, biomeRegistryKey));
    }

    public void makeWoodTranslations(TranslationBuilder translationBuilder, Woodset woodset){
        woodset.getRegisteredBlocksList().forEach(block -> {
            if (!(block instanceof AbstractSignBlock)){
                translationBuilder.add(block, formatString(Registries.BLOCK.getId(block).getPath()));
            }
        });
        woodset.getRegisteredItemsList().forEach(item -> translationBuilder.add(item, formatString(Registries.ITEM.getId(item).getPath())));
    }

    public static void makeSmithingTemplateTranslation(TranslationBuilder translationBuilder, Item template, String templateId, String appliesTo, String ingredient){
        translationBuilder.add(template, "Smithing Template");
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".additions_slot_description", "Add " + ingredient);
        translationBuilder.add("item.newworld.smithing_template." + templateId +".applies_to", appliesTo);
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".base_slot_description", "Add " + appliesTo);
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".ingredients", ingredient);
        translationBuilder.add("upgrade.newworld." + templateId, formatString(templateId));
    }

    public static void makeTranslation(TranslationBuilder translationBuilder, Item item, String name, String desc){
        translationBuilder.add(item, name);
        translationBuilder.add(item.getTranslationKey() + ".desc", desc);
    }

    public static void makeTranslation(TranslationBuilder translationBuilder, Item itemConvertible){
        translationBuilder.add(itemConvertible, formatString(Registries.ITEM.getId(itemConvertible).getPath()));
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, Block itemConvertible){
        translationBuilder.add(itemConvertible, formatString(Registries.BLOCK.getId(itemConvertible).getPath()));
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, EntityType entity){
        translationBuilder.add(entity, formatString(Registries.ENTITY_TYPE.getId(entity).getPath()));
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
