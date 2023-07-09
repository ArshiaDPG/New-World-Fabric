package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.digitalpear.newworld.init.NWBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class FallenLogFeature extends Feature<SingleStateFeatureConfig> {


    public FallenLogFeature(Codec<SingleStateFeatureConfig> configCodec) {
        super(configCodec);
    }


    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        Direction direction = getDirection(context.getRandom());
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        SingleStateFeatureConfig singleStateFeatureConfig = context.getConfig();
        int logLength = 4 + context.getRandom().nextBetween(2, 4);

        if ((structureWorldAccess.isSkyVisible(blockPos)) || structureWorldAccess.getBlockState(blockPos.down()).isSolidBlock(structureWorldAccess, blockPos)) {
            if (!structureWorldAccess.getBlockState(blockPos.down()).isOf(Blocks.WATER) && !structureWorldAccess.getBlockState(blockPos.down()).isOf(Blocks.LAVA)) {
                placeBlock(logLength, 0, structureWorldAccess, blockPos, direction, singleStateFeatureConfig.state);
                return true;
            }
        }
        return false;
    }

    //Place log
    public static void placeBlock(int maxLength, int currentLoop, StructureWorldAccess structureWorldAccess, BlockPos blockPos, Direction direction, BlockState state){
        if (currentLoop <= maxLength){
            generateLog(structureWorldAccess, blockPos, state, direction);
            placeBlock(maxLength, currentLoop + 1, structureWorldAccess, blockPos.offset(direction), direction, state);
        }
    }
    public static void generateLog(StructureWorldAccess world, BlockPos pos, BlockState state, Direction direction){
        for (int j = 10; j >= 0; --j) {
            if (!world.getBlockState(pos.down()).isAir() && !world.getBlockState(pos).isOf(NWBlocks.FIR_LOG)){
                world.setBlockState(pos, state.with(PillarBlock.AXIS, direction.getAxis()), Block.NOTIFY_ALL);
            }
            else{
                break;
            }
        }
    }

    public static Direction getDirection(Random random){
        Direction direction;
        do {
            direction = Direction.random(random);
        }
        while (direction.getAxis().isVertical());

        return direction;
    }
}
