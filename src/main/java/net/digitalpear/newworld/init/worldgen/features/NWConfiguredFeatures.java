package net.digitalpear.newworld.init.worldgen.features;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.NWFeature;
import net.digitalpear.newworld.init.NWBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.ArrayList;
import java.util.List;

public class NWConfiguredFeatures {
    public static List<RegistryKey<ConfiguredFeature<?, ?>>> features = new ArrayList<>();

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id){
        RegistryKey<ConfiguredFeature<?, ?>> registryKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Newworld.MOD_ID, id));
        features.add(registryKey);
        return registryKey;
    }

    private static final BeehiveTreeDecorator BEES = new BeehiveTreeDecorator(1.0F);
    private static final BeehiveTreeDecorator BEES_02 = new BeehiveTreeDecorator(0.02F);
    public static List<Block> VALID_BERRY_BUSH_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK);
    public static List<Block> VALID_MOSS_CARPET_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK, Blocks.STONE, Blocks.COBBLESTONE, Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.MOSSY_COBBLESTONE);


    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_FIR_LOG = of("fallen_fir_log");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR = of("fir");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_BEES = of("fir_bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_BEES_002 = of("fir_bees_002");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_MEADOW = of("fir_meadow");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_SPAWN = of("fir_spawn");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_TAIGA = of("fir_taiga");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOW_LICHEN_WOODED_MEADOW = of("glow_lichen_wooded_meadow");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSS_CARPET_WOODED_MEADOW = of("moss_carpet_wooded_meadow");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_BERRY_WOODED_MEADOW = of("patch_berry_bush_wooded_meadow");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GROWN_FIR = of("grown_fir");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GROWN_FIR_BEES = of("grown_fir_bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GROWN_FIR_BEES_002 = of("grown_fir_bees_002");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LUSH_CAVE_MUD_PATCH = of("lush_cave_mud_patch");


    /*
        Scrapyard Features
     */

    public static final RegistryKey<ConfiguredFeature<?, ?>> LOAM_PATCH_CEILING = of("loam_patch_ceiling");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RegistryEntryLookup<PlacedFeature> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        RegistryEntry<PlacedFeature> firChecked = registryEntryLookup.getOrThrow(NWPlacedFeatures.FIR_CHECKED);
        RegistryEntry<PlacedFeature> firBeesChecked = registryEntryLookup.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED);
        RegistryEntry<PlacedFeature> grownFirChecked = registryEntryLookup.getOrThrow(NWPlacedFeatures.GROWN_FIR_CHECKED);
        RegistryEntry<PlacedFeature> grownFirBeesChecked = registryEntryLookup.getOrThrow(NWPlacedFeatures.GROWN_FIR_BEES_CHECKED);



        ConfiguredFeatures.register(featureRegisterable, FALLEN_FIR_LOG, NWFeature.FALLEN_LOG, new SingleStateFeatureConfig(NWBlocks.FIR_LOG.getDefaultState()));


        ConfiguredFeatures.register(featureRegisterable, GROWN_FIR, Feature.TREE, grownFirConfig().build());
        ConfiguredFeatures.register(featureRegisterable, GROWN_FIR_BEES, Feature.TREE, grownFirConfig().decorators(List.of(BEES)).build());
        ConfiguredFeatures.register(featureRegisterable, GROWN_FIR_BEES_002, Feature.TREE, grownFirConfig().decorators(List.of(BEES_02)).build());
        ConfiguredFeatures.register(featureRegisterable, FIR, Feature.TREE, naturalFirConfig().build());
        ConfiguredFeatures.register(featureRegisterable, FIR_BEES, Feature.TREE, naturalFirConfig().decorators(List.of(BEES)).build());
        ConfiguredFeatures.register(featureRegisterable, FIR_BEES_002, Feature.TREE, naturalFirConfig().decorators(List.of(BEES_02)).build());


        ConfiguredFeatures.register(featureRegisterable, PATCH_BERRY_WOODED_MEADOW, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SWEET_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 2))), VALID_BERRY_BUSH_BLOCKS, 60));
        ConfiguredFeatures.register(featureRegisterable, MOSS_CARPET_WOODED_MEADOW, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.MOSS_CARPET.getDefaultState())), VALID_MOSS_CARPET_BLOCKS, 60));
        ConfiguredFeatures.register(featureRegisterable,GLOW_LICHEN_WOODED_MEADOW, Feature.MULTIFACE_GROWTH, new MultifaceGrowthFeatureConfig((MultifaceGrowthBlock) Blocks.GLOW_LICHEN,20, true, true, true, 0.5f, RegistryEntryList.of(Block::getRegistryEntry, NWBlocks.FIR_LOG, Blocks.MOSSY_COBBLESTONE)));
        ConfiguredFeatures.register(featureRegisterable,FIR_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(firBeesChecked, 0.06f)), firChecked));
        ConfiguredFeatures.register(featureRegisterable,FIR_TAIGA, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(grownFirBeesChecked, 0.0f)), grownFirChecked));
        ConfiguredFeatures.register(featureRegisterable,FIR_MEADOW, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(grownFirBeesChecked, 1.0f)), grownFirChecked));


        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        ConfiguredFeatures.register(featureRegisterable, LOAM_PATCH_CEILING, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.of(NWBlocks.LOAM), PlacedFeatures.createEntry(configuredFeatureRegistryEntryLookup.getOrThrow(VegetationConfiguredFeatures.VINES)), VerticalSurfaceType.CEILING, UniformIntProvider.create(1, 2), 0.0F, 5, 0.08F, UniformIntProvider.create(4, 7), 0.3F));

        TagMatchRuleTest ruleTest = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);

        ConfiguredFeatures.register(featureRegisterable, LUSH_CAVE_MUD_PATCH, Feature.ORE, new OreFeatureConfig(ruleTest, Blocks.MUD.getDefaultState(), 64));
    }

    private static TreeFeatureConfig.Builder naturalFirConfig() {
        return grownFirConfig().decorators(List.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL))));
    }

    private static TreeFeatureConfig.Builder grownFirConfig() {
        return new TreeFeatureConfig.Builder(

                BlockStateProvider.of(NWBlocks.FIR_LOG),
                new StraightTrunkPlacer(6, 1, 2),

                BlockStateProvider.of(NWBlocks.FIR_LEAVES),
                new SpruceFoliagePlacer(
                        UniformIntProvider.create(1, 3),
                        UniformIntProvider.create(0, 1),
                        UniformIntProvider.create(3, 4)),
                new TwoLayersFeatureSize(2, 0, 2))
                .ignoreVines();
    }

    public static void init() {
    }
}
