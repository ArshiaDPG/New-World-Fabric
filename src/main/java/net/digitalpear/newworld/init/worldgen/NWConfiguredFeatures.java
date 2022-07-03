package net.digitalpear.newworld.init.worldgen;

import com.google.common.collect.ImmutableList;
import net.digitalpear.newworld.init.NWBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class NWConfiguredFeatures {


    private static final BeehiveTreeDecorator BEES = new BeehiveTreeDecorator(1.0F);
    private static final BeehiveTreeDecorator BEES_02 = new BeehiveTreeDecorator(0.02F);

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PLANTED_FIR_TREE = ConfiguredFeatures.register("planted_fir_tree", Feature.TREE, fir().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PLANTED_FIR_TREE_BEES = ConfiguredFeatures.register("planted_fir_tree_bees", Feature.TREE, fir().decorators(List.of(BEES_02)).build());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FIR_TREE = ConfiguredFeatures.register("fir_tree", Feature.TREE, fir().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FIR_TREE_BEES = ConfiguredFeatures.register("fir_tree_bees", Feature.TREE, fir().decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)), BEES)).build());
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_BERRY_BUSH_WOODED_MEADOW = ConfiguredFeatures.register("patch_berry_bush_wooded_meadow", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SWEET_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 2))), List.of(Blocks.GRASS_BLOCK), 60));
    public static final RegistryEntry<ConfiguredFeature<GlowLichenFeatureConfig, ?>> GLOW_LICHEN_WOODED_MEADOW = ConfiguredFeatures.register("glow_lichen_wooded_meadow", Feature.GLOW_LICHEN, new GlowLichenFeatureConfig(20, false, true, true, 0.5f, RegistryEntryList.of(Block::getRegistryEntry, NWBlocks.FIR_LOG)));

    public static final RegistryEntry<PlacedFeature> FIR_CHECKED = PlacedFeatures.register("fir_checked", NWConfiguredFeatures.FIR_TREE, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING));
    public static final RegistryEntry<PlacedFeature> FIR_BEES_CHECKED = PlacedFeatures.register("fir_bees_02_checked", NWConfiguredFeatures.FIR_TREE_BEES, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING));


    private static TreeFeatureConfig.Builder fir() {
        return (new TreeFeatureConfig.Builder(BlockStateProvider.of(NWBlocks.FIR_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.of(NWBlocks.FIR_LEAVES), new SpruceFoliagePlacer(UniformIntProvider.create(1, 3), UniformIntProvider.create(0, 1), UniformIntProvider.create(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).ignoreVines();
    }
    public static <FC extends FeatureConfig, F extends Feature<FC>> RandomPatchFeatureConfig createRandomPatchFeatureConfig(F feature, FC config, List<Block> predicateBlocks, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(feature, config, predicateBlocks, tries);
    }

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> FIR_SPAWN =
            ConfiguredFeatures.register("fir_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(FIR_BEES_CHECKED, 0.06f)),
                            FIR_CHECKED));
}
