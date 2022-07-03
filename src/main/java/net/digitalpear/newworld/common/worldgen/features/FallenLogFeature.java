package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
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
        int logLength = context.getRandom().nextInt(7);

        if (!structureWorldAccess.isSkyVisible(blockPos) &&
                structureWorldAccess.getBlockState(blockPos.down()) != Blocks.GRASS_BLOCK.getDefaultState() &&
                structureWorldAccess.getBlockState(blockPos.down()) != Blocks.PODZOL.getDefaultState()) {
            return false;
        }
        else {
            for (int i = 0; i > 4; i++) {
                    structureWorldAccess.setBlockState(getLogPlacement(structureWorldAccess, blockPos), singleStateFeatureConfig.state.with(PillarBlock.AXIS, direction.getAxis()), Block.NO_REDRAW);
                    blockPos.offset(direction);
            }
            return true;
        }
    }


    public static BlockPos getLogPlacement(StructureWorldAccess world, BlockPos pos){
        if (isAir(world, pos.down())){
            getLogPlacement(world, pos.down());
        }
        return pos;
    }



    public static Direction getDirection(Random random){
        Direction output = Direction.random(random);
        if (output != Direction.DOWN && output != Direction.UP){
            return output;
        }
        else{
            getDirection(random);
        }
        return output;
    }
}
