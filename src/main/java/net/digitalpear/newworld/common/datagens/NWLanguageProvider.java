package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.NWStats;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class NWLanguageProvider extends FabricLanguageProvider {


    public NWLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void makeWoodTranslations(TranslationBuilder translationBuilder, Woodset woodset){
        woodset.getRegisteredBlocksList().forEach(block -> {
            if (!(block instanceof AbstractSignBlock)){
                makeTranslation(translationBuilder, block);
            }
        });
        woodset.getRegisteredItemsList().forEach(item -> makeTranslation(translationBuilder, item));
    }

    public static void makeSmithingTemplateTranslation(TranslationBuilder translationBuilder, Item template, String templateId, String appliesTo, String ingredient){
        translationBuilder.add(template, "Smithing Template");
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".additions_slot_description", "Add " + ingredient);
        translationBuilder.add("item.newworld.smithing_template." + templateId +".applies_to", appliesTo);
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".base_slot_description", "Add " + appliesTo);
        translationBuilder.add("item.newworld.smithing_template." + templateId + ".ingredients", ingredient);
        translationBuilder.add("upgrade.newworld." + templateId, formatString(templateId));
    }
    public static void makeTemplateFragmentTranslation(TranslationBuilder translationBuilder, Item item, String desc){
        translationBuilder.add(item,"Template Fragment");
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
    public static void makeTranslation(TranslationBuilder translationBuilder, Identifier identifier){
        translationBuilder.add(identifier, formatString(identifier.getPath()));
    }


    public static void makeBiomeTranslation(TranslationBuilder translationBuilder, RegistryKey<Biome> biomeRegistryKey){
        Identifier identifier = biomeRegistryKey.getValue();
        translationBuilder.add("biome" + "." + identifier.getNamespace() + "." + identifier.getPath(), formatString(identifier.getPath()));
    }
    public static void makeStatusEffectTranslation(TranslationBuilder translationBuilder, RegistryEntry<StatusEffect> effect){
        String name = effect.getIdAsString().split(":")[1];
        translationBuilder.add("effect." + Newworld.MOD_ID + "." + name, formatString(name));
        makePotionTranslation(translationBuilder, name);
    }
    public static void makePotionTranslation(TranslationBuilder translationBuilder, String name){
        translationBuilder.add("item.minecraft.potion.effect." + name, "Potion of " + formatString(name));
        translationBuilder.add("item.minecraft.splash_potion.effect." + name, "Splash Potion of " + formatString(name));
        translationBuilder.add("item.minecraft.lingering_potion.effect." + name, "Lingering Potion of " + formatString(name));
        translationBuilder.add("item.minecraft.tipped_arrow.effect." + name, "Arrow of " + formatString(name));
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

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        makeWoodTranslations(translationBuilder, NWBlocks.FIR);

        makeTranslation(translationBuilder, NWBlocks.FIR_SAPLING);
        makeTranslation(translationBuilder, NWItems.ANCIENT_MATTOCK);

        makeTemplateFragmentTranslation(translationBuilder, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, "Mattock Head");
        makeTemplateFragmentTranslation(translationBuilder, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, "Mattock Shaft");

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


        makeTranslation(translationBuilder, NWBlocks.TOMBSTONE);
        makeTranslation(translationBuilder, NWItems.ILLAGER_TOME);

        makeTranslation(translationBuilder, NWItems.JEB_BOOK);
        translationBuilder.add(NWItems.FIR_BOAT, "Fir Boat");
        translationBuilder.add(NWItems.FIR_CHEST_BOAT, "Fir Boat with Chest");

        translationBuilder.add("advancements.adventure.collect_ancient_mattock.title", "Renaissance Tool");
        translationBuilder.add("advancements.adventure.collect_ancient_mattock.description", "Craft an Ancient Mattock using template fragments.");

        translationBuilder.add("advancements.adventure.collect_template_fragment.title", "A Piece of History");
        translationBuilder.add("advancements.adventure.collect_template_fragment.description", "Discover an ancient crafting template fragment.");

        translationBuilder.add(NWStats.TOMBSTONE_ACTIVATION, "Tombstones Activated");


        NWBiomes.biomes.forEach(biomeRegistryKey -> makeBiomeTranslation(translationBuilder, biomeRegistryKey));
    }
}
