package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

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
        int logLength = context.getRandom().nextInt(2,5);

        if ((structureWorldAccess.isSkyVisible(blockPos)) || structureWorldAccess.getBlockState(blockPos.down()).isSolidBlock(structureWorldAccess, blockPos)) {
            if (!structureWorldAccess.getBlockState(blockPos.down()).isOf(Blocks.WATER) && !structureWorldAccess.getBlockState(blockPos.down()).isOf(Blocks.LAVA)) {
                placeBlock(logLength, 0, structureWorldAccess, blockPos, direction, singleStateFeatureConfig.state);
            }
        }
        return true;
    }

    //Place log
    public static void placeBlock(int maxLength, int currentLoop, StructureWorldAccess structureWorldAccess, BlockPos blockPos, Direction direction, BlockState state){
        if (currentLoop <= maxLength){
            structureWorldAccess.setBlockState(getLogPlacement(structureWorldAccess, blockPos), state.with(PillarBlock.AXIS, direction.getAxis()), Block.NO_REDRAW);
            placeBlock(maxLength, currentLoop + 1, structureWorldAccess, blockPos.offset(direction), direction, state);
        }
    }


    public static BlockPos getLogPlacement(StructureWorldAccess world, BlockPos pos){
        if (isAir(world, pos.down())){
            getLogPlacement(world, pos.down());
        }
        return pos;
    }


    //This is supposed to stop it from having vertical direction, but it doesn't work
    public static Direction getDirection(Random random){
        Direction output = Direction.random(random);
        if (output.getAxis().isVertical()){
            getDirection(random);
        }else {
            return output;
        }
        return output;
    }
}
