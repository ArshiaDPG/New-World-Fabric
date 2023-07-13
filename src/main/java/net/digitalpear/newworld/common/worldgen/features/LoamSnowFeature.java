package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class LoamSnowFeature extends Feature<DefaultFeatureConfig> {
    public LoamSnowFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        int range = 4;
        int blocksPlaced = 0;
        float f = (float)(range * 3) * 0.333f + 0.5f;

        for (BlockPos currentPos : BlockPos.iterate(pos.add(-range, -range, -range), pos.add(range, range, range))) {
            if ((currentPos.getSquaredDistance(pos) <= (double)(f * f)) && shouldPlaceSnowBlock(random, currentPos, world)) {
                placeSnowBlock(random, currentPos, world);
                blocksPlaced++;
            }
        }


        return blocksPlaced > 0;
    }

    private boolean shouldPlaceSnowBlock(Random random, BlockPos currentPos, StructureWorldAccess world) {
        return random.nextInt(10) < 7
                && currentPos.getY() < world.getTopY()
                && currentPos.getY() > world.getBottomY()
                && world.getBlockState(currentPos).isAir()
                && world.getBlockState(currentPos.down()).isSideSolidFullSquare(world, currentPos, Direction.UP);
    }

    private void placeSnowBlock(Random random, BlockPos currentPos, StructureWorldAccess world) {
        int layers = random.nextInt(8) + 1;
        if (layers >= 8) {
            world.setBlockState(currentPos, Blocks.POWDER_SNOW.getDefaultState(), Block.NOTIFY_ALL);
        } else {
            world.setBlockState(currentPos, Blocks.SNOW.getDefaultState().with(SnowBlock.LAYERS, layers), SnowBlock.NOTIFY_ALL);
        }
    }
}
