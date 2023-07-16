package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
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




        if (!world.getBlockState(pos.up()).isAir() && !world.getBlockState(pos.up(2)).isAir()) {
            return false;
        }

        Random random = context.getRandom();
        int range = random.nextBetween(3, 6);
        float f = (float)(range * 3) * 0.333f + 0.5f;

        int blocksPlaced = 0;

        BlockState state = random.nextFloat() >= 0.8 ? Blocks.POWDER_SNOW.getDefaultState() : Blocks.SNOW_BLOCK.getDefaultState();

        for (BlockPos currentPos : BlockPos.iterate(pos.add(-range, -range, -range), pos.add(range, range, range))) {
            if ((currentPos.getSquaredDistance(pos) <= (double)(f * f)) && shouldPlaceSnowBlock(random, currentPos, world)) {
                world.setBlockState(currentPos, state, Block.NOTIFY_ALL);
                blocksPlaced++;
            }
        }

        return blocksPlaced > 0;
    }

    private boolean shouldPlaceSnowBlock(Random random, BlockPos currentPos, StructureWorldAccess world) {
        for (BlockPos pos : BlockPos.iterate(currentPos.add(-2, -2, -2), currentPos.add(2, 2, 2))) {
            if (world.getBlockState(pos).getFluidState().isIn(FluidTags.LAVA)){
                return false;
            }
        }
        return random.nextInt(9) < 7
                && currentPos.getY() < world.getTopY()
                && currentPos.getY() > world.getBottomY()
                && world.getBlockState(currentPos).isOf(Blocks.CALCITE);
    }
}
