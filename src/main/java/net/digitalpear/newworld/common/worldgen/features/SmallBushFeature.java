package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SmallBushFeature extends Feature<SmallBushFeatureConfig> {
    public SmallBushFeature(Codec<SmallBushFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SmallBushFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        SmallBushFeatureConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        world.setBlockState(pos.down(), config.dirtProvider.get(random, pos.down()), Block.NOTIFY_ALL);
        world.setBlockState(pos, config.trunkProvider.get(random, pos), Block.NOTIFY_ALL);

        int size = 1 + random.nextInt(1);
        float f = (float)(size + size + size) * 0.333f + 0.5f;
        for (BlockPos blockPos2 : BlockPos.iterate(pos.add(-size, -size, -size), pos.add(size, size, size))) {
            if (!(blockPos2.getSquaredDistance(pos) <= (double)(f * f))) continue;
            if (world.getBlockState(blockPos2).isAir()){
                world.setBlockState(blockPos2, random.nextFloat() > 0.2 ? config.foliageProvider.get(random, blockPos2) : config.rareFoliageProvider.get(random, blockPos2), Block.NOTIFY_ALL);
            }
        }
        return true;
    }
}
