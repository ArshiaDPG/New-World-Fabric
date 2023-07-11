package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.woodset.Woodset;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
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
        itemModelGenerator.register(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT, Models.GENERATED
        );
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
    public final void registerPearBlock(BlockStateModelGenerator blockStateModelGenerator, Block pearBlock) {
        Identifier outerID = Models.TEMPLATE_SINGLE_FACE.upload(pearBlock, TextureMap.texture(pearBlock), blockStateModelGenerator.modelCollector);
        Identifier insideID = Models.TEMPLATE_SINGLE_FACE.upload(pearBlock, "_inside", TextureMap.texture(getId(pearBlock, "_inside")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(pearBlock).with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID)).with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.DOWN, true), BlockStateVariant.create().put(VariantSettings.MODEL, outerID).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)).with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID)).with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.DOWN, false), BlockStateVariant.create().put(VariantSettings.MODEL, insideID).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false)));
        blockStateModelGenerator.registerParentedItemModel(pearBlock, TexturedModel.CUBE_ALL.upload(pearBlock, "_inventory", blockStateModelGenerator.modelCollector));
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
