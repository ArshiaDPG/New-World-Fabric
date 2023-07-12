package net.digitalpear.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SmallBushFeatureConfig implements FeatureConfig {
    public static final Codec<SmallBushFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            (BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider")).forGetter(config -> config.trunkProvider),
            (BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider")).forGetter(config -> config.foliageProvider),
            (BlockStateProvider.TYPE_CODEC.fieldOf("rare_foliage_provider")).forGetter(config -> config.rareFoliageProvider),
            (BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider")).forGetter(config -> config.dirtProvider)).apply(instance, SmallBushFeatureConfig::new));
    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider foliageProvider;
    public final BlockStateProvider rareFoliageProvider;
    public final BlockStateProvider dirtProvider;

    public SmallBushFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider rareFoliageProvider, BlockStateProvider dirtProvider) {
        this.trunkProvider = trunkProvider;
        this.foliageProvider = foliageProvider;
        this.rareFoliageProvider = rareFoliageProvider;
        this.dirtProvider = dirtProvider;
    }

    public SmallBushFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, BlockStateProvider rareFoliageProvider) {
        this.trunkProvider = trunkProvider;
        this.foliageProvider = foliageProvider;
        this.rareFoliageProvider = rareFoliageProvider;
        this.dirtProvider = BlockStateProvider.of(Blocks.DIRT);
    }
    public SmallBushFeatureConfig(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) {
        this.trunkProvider = trunkProvider;
        this.foliageProvider = foliageProvider;
        this.rareFoliageProvider = foliageProvider;
        this.dirtProvider = BlockStateProvider.of(Blocks.DIRT);
    }
}
