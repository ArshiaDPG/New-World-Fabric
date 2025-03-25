package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.AxisRotation;

public class NWModelProvider extends FabricModelProvider {

    public NWModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        NWBlocks.FIR.fullWoodset(blockStateModelGenerator);
        blockStateModelGenerator.registerFlowerPotPlant(NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerItemModel(NWBlocks.FIR_SAPLING);

        blockStateModelGenerator.registerCubeAllModelTexturePool(NWBlocks.LOAM.getBase()).family(NWBlocks.LOAM.getBlockFamily());
        blockStateModelGenerator.registerCubeAllModelTexturePool(NWBlocks.LOAM_BRICKS.getBase()).family(NWBlocks.LOAM_BRICKS.getBlockFamily());
        blockStateModelGenerator.registerCubeAllModelTexturePool(NWBlocks.LOAM_TILES.getBase()).family(NWBlocks.LOAM_TILES.getBlockFamily());

        generateDripstonePot(blockStateModelGenerator);
        registerTombstone(blockStateModelGenerator);

        blockStateModelGenerator.registerTintableCross(NWBlocks.MOSS_SPROUTS, BlockStateModelGenerator.CrossType.NOT_TINTED);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(NWItems.ANCIENT_MATTOCK, Models.HANDHELD);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, Models.GENERATED);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, Models.GENERATED);

        itemModelGenerator.register(NWItems.ILLAGER_TOME, Models.GENERATED);

        itemModelGenerator.register(NWItems.JEB_BOOK, Models.GENERATED);
    }





    public final void registerTombstone(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerParentedItemModel(NWBlocks.TOMBSTONE, Newworld.id("block/tombstone_north"));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(NWBlocks.TOMBSTONE).with(BlockStateVariantMap.models(Properties.CRACKED, Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING).generate((cracked, blockFace, horizontalFacing) ->{


            String wallName = blockFace == BlockFace.WALL ? "_" + blockFace.asString() + "_" :  "_";
            Identifier tombstone = Newworld.id("block/tombstone").withSuffixedPath(wallName).withSuffixedPath(horizontalFacing.asString());

            WeightedVariant blockStateVariant = BlockStateModelGenerator.createWeightedVariant(tombstone);
            if (blockFace == BlockFace.CEILING){
                blockStateVariant.apply(ModelVariantOperator.ROTATION_X.withValue(AxisRotation.R180));
            }

            return blockStateVariant;
        })));
    }

    public void generateDripstonePot(BlockStateModelGenerator blockStateModelGenerator){
        WeightedVariant identifier = BlockStateModelGenerator.createWeightedVariant(BlockStateModelGenerator.CrossType.NOT_TINTED.getFlowerPotCrossModel().upload(NWBlocks.POTTED_POINTED_DRIPSTONE, TextureMap.of(TextureKey.PLANT, TextureMap.getId(Blocks.POINTED_DRIPSTONE).withSuffixedPath("_up_tip")), blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(NWBlocks.POTTED_POINTED_DRIPSTONE, identifier));

    }
}
