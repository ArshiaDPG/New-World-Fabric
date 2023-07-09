package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class NWRecipeGen extends FabricRecipeProvider {
    public NWRecipeGen(FabricDataOutput output) {
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
    }

}
