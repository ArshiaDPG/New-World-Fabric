package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryWrapper;

import java.util.Arrays;

public class NWRecipeGenerator extends RecipeGenerator {

    RegistryEntryLookup<Item> lookup = registries.getOrThrow(Registries.ITEM.getKey());
    public NWRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {

        NWBlocks.FIR.generateRecipes(this, lookup, exporter, NWItemTags.FIR_LOGS);
//        offerPlanksRecipe(NWBlocks.FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
//        createStairsRecipe(NWBlocks.FIR_STAIRS, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB, NWBlocks.FIR_PLANKS);
//        createFenceRecipe(NWBlocks.FIR_FENCE, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        createFenceGateRecipe(NWBlocks.FIR_FENCE_GATE, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        offerBarkBlockRecipe(NWBlocks.FIR_WOOD, NWBlocks.FIR_LOG);
//        offerBarkBlockRecipe(NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
//        offerBoatRecipe(NWItems.FIR_BOAT, NWBlocks.FIR_PLANKS);
//        offerChestBoatRecipe(NWItems.FIR_CHEST_BOAT, NWItems.FIR_BOAT);
//        offerHangingSignRecipe(NWItems.FIR_HANGING_SIGN, NWBlocks.STRIPPED_FIR_LOG);
//        createSignRecipe(NWItems.FIR_SIGN, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        createDoorRecipe(NWBlocks.FIR_DOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        createTrapdoorRecipe(NWBlocks.FIR_TRAPDOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        ShapelessRecipeJsonBuilder.create(lookup, RecipeCategory.REDSTONE, NWBlocks.FIR_BUTTON.asItem()).input(Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(RecipeGenerator.hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
//        offerPressurePlateRecipe(NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);
        offerBoatRecipe(NWItems.FIR_BOAT, NWBlocks.FIR.getPlanks());
        offerChestBoatRecipe(NWItems.FIR_CHEST_BOAT, NWItems.FIR_BOAT);

        makeTemplateRecipe(exporter, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT);

        offerUpgradeRecipe(exporter, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.ANCIENT_MATTOCK);
        offerSmithingTemplateCopyingRecipe(NWItems.MATTOCK_CRAFTING_TEMPLATE, Items.COBBLED_DEEPSLATE);

        createStoneSetRecipes(exporter, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
        createStoneSetRecipes(exporter, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
        createStoneSetRecipes(exporter, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_BRICKS), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_TILES), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_TILES), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, 2);
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(NWBlocks.LOAM_TILES), RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL);


        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, 4).input('#', NWBlocks.LOAM)
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(NWBlocks.LOAM), conditionsFromItem(NWBlocks.LOAM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, 4).input('#', NWBlocks.LOAM_BRICKS)
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(NWBlocks.LOAM_BRICKS), conditionsFromItem(NWBlocks.LOAM_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM, 4).input('C', Items.CLAY).input('D', Items.DIRT)
                .pattern("CD")
                .pattern("DC")
                .criterion(hasItem(NWBlocks.LOAM_BRICKS), conditionsFromItem(NWBlocks.LOAM_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(lookup, RecipeCategory.COMBAT, NWBlocks.TOMBSTONE)
                .input('D', Items.DIAMOND)
                .input('S', Items.POLISHED_DEEPSLATE)
                .input('A', NWItems.ILLAGER_TOME)
                .input('T', NWBlocks.TOMBSTONE)

                .pattern("SDS")
                .pattern("SAS")
                .pattern("STS")
                .showNotification(true)
                .criterion("has_tombstone_or_arcane_tome", conditionsFromItemPredicates(ItemPredicate.Builder.create().items(lookup, NWItems.ILLAGER_TOME, NWBlocks.TOMBSTONE).build()))
                .offerTo(exporter);
    }

    public void createStoneSetRecipes(RecipeExporter exporter, ItemConvertible base, ItemConvertible stairs, ItemConvertible slab, ItemConvertible wall){
        createStairsRecipe(stairs, Ingredient.ofItems(base)).criterion(hasItem(base), conditionsFromItem(base)).offerTo(exporter);
        offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, slab, base);
        offerWallRecipe(RecipeCategory.BUILDING_BLOCKS, wall, base);
    }
    public void makeTemplateRecipe(RecipeExporter exporter, Item fullTemplate, Item... pieces){
        ShapelessRecipeJsonBuilder recipeJsonBuilder = ShapelessRecipeJsonBuilder.create(lookup, RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE.getDefaultStack())
                .criterion("has_" + Registries.ITEM.getId(fullTemplate).getPath() + "_piece",
                        RecipeGenerator.conditionsFromItemPredicates(ItemPredicate.Builder.create().items(lookup, pieces).build()));

        Arrays.stream(pieces).toList().forEach(recipeJsonBuilder::input);

        recipeJsonBuilder.offerTo(exporter, Registries.ITEM.getId(fullTemplate).withSuffixedPath("_from_piece_combination").getPath());
    }

    public void offerUpgradeRecipe(RecipeExporter exporter, Item template, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template), Ingredient.ofItems(Items.STICK), Ingredient.ofItems(Items.FLINT), RecipeCategory.TOOLS, result).criterion(hasItem(template), conditionsFromItem(template)).offerTo(exporter, getItemPath(result) + "_smithing");
    }
}
