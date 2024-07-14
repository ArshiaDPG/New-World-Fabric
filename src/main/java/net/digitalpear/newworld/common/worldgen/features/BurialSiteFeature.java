package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.digitalpear.newworld.common.blocks.TombstoneBlock;
import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BurialSiteFeature extends Feature<DefaultFeatureConfig> {

    public BurialSiteFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin().down();
        Random random = context.getRandom();

        int width = world.getRandom().nextBetween(5, 10);

        BlockState bricks = Blocks.DEEPSLATE_BRICKS.getDefaultState();
        BlockState crackedBricks = Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState();
        BlockState tiles = Blocks.DEEPSLATE_TILES.getDefaultState();
        BlockState crackedTiles = Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState();

        List<BlockPos> towerPositions = new ArrayList<>();

        for (BlockPos pos : BlockPos.iterate(origin.add(-width, 1, -width), origin.add(width, 5, width))) {
            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                return false;
            }
        }
        for (BlockPos pos : BlockPos.iterate(origin.add(-width, 0, -width), origin.add(width, 0, width))){
            if (pos.getX() == origin.getX() || pos.getZ() == origin.getZ()){
                placeBlock(world, pos, bricks, crackedBricks);
            }
            else{
                placeBlock(world, pos, tiles, crackedTiles);
            }
        }

        for (int i = -width; i <= width; i += 3){
            towerPositions.add(origin.add(-width, 0, -i));
            towerPositions.add(origin.add(width, 0, i));
            towerPositions.add(origin.add(i, 0, -width));
            towerPositions.add(origin.add(-i, 0, width));

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
        placeBlock(world, origin.up(), NWBlocks.TOMBSTONE.getDefaultState());

        towerPositions.forEach(currentPos -> {
            if (random.nextFloat() < 0.4){
                makeTower(world, currentPos);
            }
        });

        return true;
    }

    private static void makeTower(StructureWorldAccess world, BlockPos pos){
        int length = world.getRandom().nextBetween(4, 7);

        for (BlockPos currentPos : BlockPos.iterate(pos.up(), pos.up(length))){
            placeBlock(world, currentPos, Blocks.POLISHED_DEEPSLATE_WALL.getDefaultState());
        }
        placeBlock(world, pos.up(length+1), Blocks.REDSTONE_LAMP.getDefaultState());
        placeBlock(world, pos.up(length+2), Blocks.SCULK_SENSOR.getDefaultState().with(Properties.WATERLOGGED, true));

        Direction.stream().filter(direction -> direction.getAxis() != Direction.Axis.Y).forEach(direction -> {
            placeBlock(world, pos.up(length+2).add(direction.getVector()), Blocks.DEEPSLATE_BRICKS.getDefaultState());
            placeBlock(world, pos.up(length+1).add(direction.getVector()), Blocks.DEEPSLATE_BRICK_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP));
        });
    }

    private static void generateTombstone(StructureWorldAccess world, BlockPos pos) {
        placeBlock(world, pos, NWBlocks.TOMBSTONE.getDefaultState().with(TombstoneBlock.FACING, Direction.byId(world.getRandom().nextBetween(2, 5))));
        world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent((blockEntity) ->
                blockEntity.setLootTable(LootTables.UNDERWATER_RUIN_SMALL_CHEST, pos.asLong()));
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
