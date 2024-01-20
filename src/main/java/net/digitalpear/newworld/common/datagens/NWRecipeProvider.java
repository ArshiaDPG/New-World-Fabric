package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;

import java.util.Arrays;
import java.util.function.Consumer;

public class NWRecipeProvider extends FabricRecipeProvider {
    public NWRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerPlanksRecipe(exporter, NWBlocks.FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
        createStairsRecipe(NWBlocks.FIR_STAIRS, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB, NWBlocks.FIR_PLANKS);
        createFenceRecipe(NWBlocks.FIR_FENCE, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        createFenceGateRecipe(NWBlocks.FIR_FENCE_GATE, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        offerBarkBlockRecipe(exporter, NWBlocks.FIR_WOOD, NWBlocks.FIR_LOG);
        offerBarkBlockRecipe(exporter, NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
        offerBoatRecipe(exporter, NWItems.FIR_BOAT, NWBlocks.FIR_PLANKS);
        offerChestBoatRecipe(exporter, NWItems.FIR_CHEST_BOAT, NWItems.FIR_BOAT);
        offerHangingSignRecipe(exporter, NWItems.FIR_HANGING_SIGN, NWBlocks.STRIPPED_FIR_LOG);
        createSignRecipe(NWItems.FIR_SIGN, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        createDoorRecipe(NWBlocks.FIR_DOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        createTrapdoorRecipe(NWBlocks.FIR_TRAPDOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, NWBlocks.FIR_BUTTON).input(Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasItem(NWBlocks.FIR_PLANKS), conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);
        offerPressurePlateRecipe(exporter, NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);

        makeTemplateRecipe(exporter, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT);

        offerUpgradeRecipe(exporter, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.ANCIENT_MATTOCK);
        offerSmithingTemplateCopyingRecipe(exporter, NWItems.MATTOCK_CRAFTING_TEMPLATE, Items.COBBLED_DEEPSLATE);

        createStoneSetRecipes(exporter, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
        createStoneSetRecipes(exporter, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
        createStoneSetRecipes(exporter, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);

        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM);

        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_BRICKS);

        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILES);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILES);
        RecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, 4).input('#', NWBlocks.LOAM)
                .pattern("##")
                .pattern("##")
                .criterion(RecipeProvider.hasItem(NWBlocks.LOAM), RecipeProvider.conditionsFromItem(NWBlocks.LOAM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, 4).input('#', NWBlocks.LOAM_BRICKS)
                .pattern("##")
                .pattern("##")
                .criterion(RecipeProvider.hasItem(NWBlocks.LOAM_BRICKS), RecipeProvider.conditionsFromItem(NWBlocks.LOAM_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM, 4).input('C', Items.CLAY).input('D', Items.DIRT)
                .pattern("CD")
                .pattern("DC")
                .criterion(RecipeProvider.hasItem(NWBlocks.LOAM_BRICKS), RecipeProvider.conditionsFromItem(NWBlocks.LOAM_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, NWBlocks.TOMBSTONE)
                .input('D', Items.DIAMOND)
                .input('S', Items.POLISHED_DEEPSLATE)
                .input('A', NWItems.ILLAGER_TOME)
                .input('T', NWBlocks.TOMBSTONE)

                .pattern("SDS")
                .pattern("SAS")
                .pattern("STS")
                .showNotification(true)
                .criterion("has_tombstone_or_arcane_tome", conditionsFromItemPredicates(ItemPredicate.Builder.create().items(NWItems.ILLAGER_TOME, NWBlocks.TOMBSTONE).build()))
                .offerTo(exporter);



    }

    public void createStoneSetRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible base, ItemConvertible stairs, ItemConvertible slab, ItemConvertible wall){
        createStairsRecipe(stairs, Ingredient.ofItems(base)).criterion(hasItem(base), conditionsFromItem(base)).offerTo(exporter);
        offerSlabRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, slab, base);
        offerWallRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, wall, base);

    }
    public void makeTemplateRecipe(Consumer<RecipeJsonProvider> exporter, Item fullTemplate, Item... pieces){
        ShapelessRecipeJsonBuilder recipeJsonBuilder = ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE)
                .criterion("has_" + Registries.ITEM.getId(fullTemplate).getPath() + "_piece",
                        conditionsFromItemPredicates(ItemPredicate.Builder.create().items(pieces).build()));

        Arrays.stream(pieces).toList().forEach(recipeJsonBuilder::input);

        recipeJsonBuilder.offerTo(exporter, Registries.ITEM.getId(fullTemplate).withSuffixedPath("_from_piece_combination"));
    }

    public static void offerUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item template, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template), Ingredient.ofItems(Items.STICK), Ingredient.ofItems(Items.FLINT), RecipeCategory.TOOLS, result).criterion(hasItem(template), conditionsFromItem(template)).offerTo(exporter, getItemPath(result) + "_smithing");
    }
}
