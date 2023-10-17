package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Arrays;

public class BurialSiteFeature extends Feature<DefaultFeatureConfig> {

    public BurialSiteFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin().down();
        int width = 12;
        BlockState bricks = Blocks.DEEPSLATE_BRICKS.getDefaultState();
        BlockState crackedBricks = Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState();
        BlockState tiles = Blocks.DEEPSLATE_TILES.getDefaultState();
        BlockState crackedTiles = Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState();

        for (BlockPos pos : BlockPos.iterate(origin.add(-width, 1, -width), origin.add(width, 5, width))) {
            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                return false;
            }
        }
        for (BlockPos pos : BlockPos.iterate(origin.add(-width, 0, -width), origin.add(width, 0, width))){
            if (pos.getX() == origin.getX() || pos.getZ() == origin.getZ()){
                placeBlock(world, pos, bricks, crackedBricks, Blocks.AIR.getDefaultState());
            }
            else{
                placeBlock(world, pos, tiles, crackedTiles, Blocks.AIR.getDefaultState());
            }
        }

        for (int i = -width; i <= width; i += 3){
            makeTower(world, origin.add(-width, 0, -i));
            makeTower(world, origin.add(width, 0, i));
            makeTower(world, origin.add(-i, 0, width));
            makeTower(world, origin.add(i, 0, -width));

            if (Math.abs(i) < width){
                for (int k = -width; k < width; k += 3) {
                    BlockPos currentPos = origin.add(-i, 1, -k);
                    generateTombstone(world, currentPos, 0.3f);
                    if (world.getRandom().nextFloat() > 0.6){
                        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                                registry.getEntry(UndergroundConfiguredFeatures.SCULK_PATCH_DEEP_DARK)).ifPresent((reference) ->
                                reference.value().generate(world, context.getGenerator(), world.getRandom(), currentPos));
                    }
                }
            }
        }


        return true;
    }

    private static void makeTower(StructureWorldAccess world, BlockPos pos){
        for (BlockPos currentPos : BlockPos.iterate(pos.up(), pos.up(4))){
            placeBlock(world, currentPos, Blocks.POLISHED_DEEPSLATE_WALL.getDefaultState());

        }
        placeBlock(world, pos.up(5), Blocks.REDSTONE_LAMP.getDefaultState());
        placeBlock(world, pos.up(6), Blocks.SCULK_SENSOR.getDefaultState());
    }

    private static void generateTombstone(StructureWorldAccess world, BlockPos pos) {
        placeBlock(world, pos, NWBlocks.TOMBSTONE.getDefaultState());
        world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent((blockEntity) ->
                blockEntity.setLootTable(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY, pos.asLong()));
    }
    private static void generateTombstone(StructureWorldAccess world, BlockPos pos, float chance) {
        if (world.getRandom().nextFloat() < chance){
            generateTombstone(world, pos);
        }
    }

    private static void placeBlock(StructureWorldAccess world, BlockPos pos, BlockState... states){
        if (world.getBlockState(pos).isAir()){
            world.setBlockState(pos, Arrays.stream(states).toList().get(world.getRandom().nextInt(Arrays.stream(states).toList().size())), 3);
        }
    }
}
