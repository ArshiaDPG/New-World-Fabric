package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.woodset.Woodset;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class NWModelProvider extends FabricModelProvider {


    public NWModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        fullWoodset(blockStateModelGenerator, NWBlocks.FIR);
        blockStateModelGenerator.registerFlowerPotPlant(NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        makeStoneModels(blockStateModelGenerator, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
        makeStoneModels(blockStateModelGenerator, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
        makeStoneModels(blockStateModelGenerator, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);


        generateDripstonePot(blockStateModelGenerator);
        registerTombstone(blockStateModelGenerator);
    }




    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(NWItems.FIR_SIGN, Models.GENERATED);
        itemModelGenerator.register(NWItems.FIR_HANGING_SIGN, Models.GENERATED);
        itemModelGenerator.register(NWItems.FIR_BOAT, Models.GENERATED);
        itemModelGenerator.register(NWItems.FIR_CHEST_BOAT, Models.GENERATED);

        itemModelGenerator.register(NWItems.ANCIENT_MATTOCK, Models.HANDHELD);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, Models.GENERATED);
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, Models.GENERATED);

        itemModelGenerator.register(NWItems.ILLAGER_TOME, Models.GENERATED);
    }

    public static void fullWoodset(BlockStateModelGenerator blockStateModelGenerator, Woodset woodset){
        makeStuff(blockStateModelGenerator, woodset);
        blockStateModelGenerator.registerDoor(woodset.getDoor());
        blockStateModelGenerator.registerTrapdoor(woodset.getTrapDoor());
        makeParticles(blockStateModelGenerator, woodset.getPlanks(), woodset.getSign(), woodset.getWallSign());
        makeParticles(blockStateModelGenerator, woodset.getStrippedLog(), woodset.getHangingSign(), woodset.getHangingWallSign());

        if (woodset.isNormalWood()){
            blockStateModelGenerator.registerSimpleCubeAll(woodset.getLeaves());
        }

        if (!woodset.getWoodPreset().equals(Woodset.WoodPreset.BAMBOO)){
            blockStateModelGenerator.registerLog(woodset.getLog()).log(woodset.getLog()).wood(woodset.getWood());
            blockStateModelGenerator.registerLog(woodset.getStrippedLog()).log(woodset.getStrippedLog()).wood(woodset.getStrippedWood());
        }
        else{
            blockStateModelGenerator.registerLog(woodset.getLog()).uvLockedLog(woodset.getLog());
            blockStateModelGenerator.registerLog(woodset.getStrippedLog()).uvLockedLog(woodset.getStrippedLog());

        }
    }

//    public static final Model TOMBSTONE = block("template_tombstone", TextureKey.TEXTURE, TextureKey.PARTICLE);
//
//    private static Model block(String parent, TextureKey... requiredTextureKeys) {
//        return new Model(Optional.of(new Identifier(Newworld.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
//    }

    public final void registerTombstone(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerParentedItemModel(NWBlocks.TOMBSTONE, Newworld.id("block/tombstone_north"));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(NWBlocks.TOMBSTONE).coordinate(BlockStateVariantMap.create(Properties.CRACKED, Properties.WALL_MOUNT_LOCATION, Properties.HORIZONTAL_FACING).register((cracked, blockFace, horizontalFacing) ->{
            BlockStateVariant blockStateVariant = BlockStateVariant.create();

            String wallName = blockFace == WallMountLocation.WALL ? "_" + blockFace.asString() + "_" :  "_";
            Identifier tombstone = Newworld.id("block/tombstone").withSuffixedPath(wallName).withSuffixedPath(horizontalFacing.asString());

            if (blockFace == WallMountLocation.CEILING){
                blockStateVariant.put(VariantSettings.X, VariantSettings.Rotation.R180);
            }

            return blockStateVariant.put(VariantSettings.MODEL, tombstone);
        })));
    }

    public void generateDripstonePot(BlockStateModelGenerator blockStateModelGenerator){
        Identifier identifier = BlockStateModelGenerator.TintType.NOT_TINTED.getFlowerPotCrossModel().upload(NWBlocks.POTTED_POINTED_DRIPSTONE, TextureMap.of(TextureKey.PLANT, TextureMap.getId(Blocks.POINTED_DRIPSTONE).withSuffixedPath("_up_tip")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(NWBlocks.POTTED_POINTED_DRIPSTONE, identifier));

    }
    public void makeStoneModels(BlockStateModelGenerator blockStateModelGenerator, Block block, Block stairs, Block slab, Block wall){
        blockStateModelGenerator.registerSimpleCubeAll(block);
        createStairs(blockStateModelGenerator, block, stairs);
        createSlab(blockStateModelGenerator, block, slab);
        createWall(blockStateModelGenerator, block, wall);
    }

    public static void makeStuff(BlockStateModelGenerator blockStateModelGenerator, Woodset woodset){
        makeStuff(blockStateModelGenerator, woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(), woodset.getFence(), woodset.getFenceGate(), woodset.getButton(), woodset.getPressurePlate());
    }
    public static void makeStuff(BlockStateModelGenerator blockStateModelGenerator, Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block button, Block pressurePlate){
        blockStateModelGenerator.registerSimpleCubeAll(planks);
        createStairs(blockStateModelGenerator, planks, stairs);
        createSlab(blockStateModelGenerator, planks, slab);
        makeButton(blockStateModelGenerator, planks, button);
        makePressurePlate(blockStateModelGenerator, planks, pressurePlate);
        fence(blockStateModelGenerator, planks, fence);
        fenceGate(blockStateModelGenerator, planks, fenceGate);
    }

    public static void makeParticles(BlockStateModelGenerator blockStateModelGenerator, Block particle, Block sign, Block wallSign){
        Identifier identifier = Models.PARTICLE.upload(sign, TextureMap.particle(new Identifier(Newworld.MOD_ID, "block/" + Registries.BLOCK.getId(particle).getPath())), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(sign, identifier));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSign, identifier));
    }

    public static void createStairs(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block stairs){
        Identifier STAIRS = Models.STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier INNER_STAIRS = Models.INNER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier OUTER_STAIRS = Models.OUTER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
    }
    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab){
        Identifier SLAB = Models.SLAB.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier SLAB_TOP = Models.SLAB_TOP.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab,
                SLAB, SLAB_TOP, getId(textureBase)));
    }
    public static void makeButton(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block button){
        Identifier BUTTON = Models.BUTTON.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_PRESSED = Models.BUTTON_PRESSED.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_INVENTORY = Models.BUTTON_INVENTORY.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(button,
                BUTTON, BUTTON_PRESSED));
        blockStateModelGenerator.registerParentedItemModel(button.asItem(), BUTTON_INVENTORY);
    }

    public static void makePressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block plate){
        Identifier PRESSURE_PLATE_DOWN = Models.PRESSURE_PLATE_DOWN.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier PRESSURE_PLATE_UP = Models.PRESSURE_PLATE_UP.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(plate,
                PRESSURE_PLATE_UP, PRESSURE_PLATE_DOWN));
    }
    public static void fence(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block fenceBlock) {
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(fenceBlock, identifier3);
    }
    public static void fenceGate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block fenceGateBlock) {
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4, true));
    }
    public static void createWall(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block wallBlock) {
        Identifier identifier = Models.TEMPLATE_WALL_POST.upload(wallBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.WALL_INVENTORY.upload(wallBlock, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(wallBlock, identifier4);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, identifier, identifier2, identifier3));
    }


    public static Identifier getId(Block block) {
        Identifier identifier = Registries.BLOCK.getId(block);
        return identifier.withPrefixedPath("block/");
    }
    public static Identifier getId(Block block, String suffix) {
        Identifier identifier = Registries.BLOCK.getId(block).withSuffixedPath(suffix);
        return identifier.withPrefixedPath("block/");
    }
    public static Identifier getId(String prefix, Block block) {
        Identifier identifier = Registries.BLOCK.getId(block).withPrefixedPath(prefix);
        return identifier.withPrefixedPath("block/");
    }
}
