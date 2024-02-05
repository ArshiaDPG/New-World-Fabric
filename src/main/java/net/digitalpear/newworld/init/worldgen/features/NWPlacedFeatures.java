package net.digitalpear.newworld.init.worldgen.features;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.treeModifiers;

public class NWPlacedFeatures {

    public static List<RegistryKey<PlacedFeature>> features = new ArrayList<>();

    public static RegistryKey<PlacedFeature> of(String id){
        RegistryKey<PlacedFeature> registryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Newworld.MOD_ID, id));
        features.add(registryKey);
        return registryKey;
    }

    public static final RegistryKey<PlacedFeature> FALLEN_FIR_LOG= of("fallen_fir_log");

    public static final RegistryKey<PlacedFeature> FIR_CHECKED = of("fir_checked");
    public static final RegistryKey<PlacedFeature> FIR_BEES_CHECKED = of("fir_bees_checked");
    public static final RegistryKey<PlacedFeature> GROWN_FIR_CHECKED = of("grown_fir_checked");
    public static final RegistryKey<PlacedFeature> GROWN_FIR_BEES_CHECKED = of("grown_fir_bees_checked");


    public static final RegistryKey<PlacedFeature> TREES_FIR = of("trees_fir");
    public static final RegistryKey<PlacedFeature> TREES_FIR_SCARCE= of("trees_fir_scarce");
    public static final RegistryKey<PlacedFeature> TREES_FIR_MEADOW = of("trees_fir_meadow");

    public static final RegistryKey<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW = of("glow_lichen_wooded_meadow");
    public static final RegistryKey<PlacedFeature> PATCH_BERRY_WOODED_MEADOW = of("patch_berry_bush_wooded_meadow");
    public static final RegistryKey<PlacedFeature> PATCH_FERN_WOODED_MEADOW = of("patch_fern_wooded_meadow");




    public static final RegistryKey<PlacedFeature> LOAM_PATCH_CEILING = of("loam_patch_ceiling");
    public static final RegistryKey<PlacedFeature> LOAM_ORE = of("loam_ore");
    public static final RegistryKey<PlacedFeature> LOAM_SNOW = of("loam_snow");
    public static final RegistryKey<PlacedFeature> CALCITE_PATCH = of("calcite_patch");

    public static final RegistryKey<PlacedFeature> BIRCH_CHERRY_GROVE = of("birch_cherry_grove");
    public static final RegistryKey<PlacedFeature> LUSH_CAVE_MUD_PATCH = of("lush_cave_mud_patch");
    public static final RegistryKey<PlacedFeature> LUSH_CAVE_LOAM_ORE = of("lush_cave_loam_ore");
//    public static final RegistryKey<PlacedFeature> AZALEA_BUSH = of("azalea_bush");

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> holderGetter = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry<ConfiguredFeature<?, ?>> fallenFirLog = holderGetter.getOrThrow(NWConfiguredFeatures.FALLEN_FIR_LOG);

        RegistryEntry<ConfiguredFeature<?, ?>> fir = holderGetter.getOrThrow(NWConfiguredFeatures.FIR);
        RegistryEntry<ConfiguredFeature<?, ?>> firBees = holderGetter.getOrThrow(NWConfiguredFeatures.FIR_BEES);
        RegistryEntry<ConfiguredFeature<?, ?>> grownFir = holderGetter.getOrThrow(NWConfiguredFeatures.GROWN_FIR);
        RegistryEntry<ConfiguredFeature<?, ?>> grownFirBees = holderGetter.getOrThrow(NWConfiguredFeatures.GROWN_FIR_BEES);

        RegistryEntry<ConfiguredFeature<?, ?>> firSpawn = holderGetter.getOrThrow(NWConfiguredFeatures.FIR_SPAWN);
        RegistryEntry<ConfiguredFeature<?, ?>> firTaiga = holderGetter.getOrThrow(NWConfiguredFeatures.FIR_TAIGA);
        RegistryEntry<ConfiguredFeature<?, ?>> firMeadow = holderGetter.getOrThrow(NWConfiguredFeatures.FIR_MEADOW);


        RegistryEntry<ConfiguredFeature<?, ?>> patchBerryBush = holderGetter.getOrThrow(NWConfiguredFeatures.PATCH_BERRY_WOODED_MEADOW);
        RegistryEntry<ConfiguredFeature<?, ?>> patchLichen = holderGetter.getOrThrow(NWConfiguredFeatures.GLOW_LICHEN_WOODED_MEADOW);

        RegistryEntry<ConfiguredFeature<?, ?>> birchTall = holderGetter.getOrThrow(VegetationConfiguredFeatures.BIRCH_TALL);
        RegistryEntry<ConfiguredFeature<?, ?>> lushCaveMud = holderGetter.getOrThrow(NWConfiguredFeatures.LUSH_CAVE_MUD_PATCH);
//        RegistryEntry<ConfiguredFeature<?, ?>> azaleaBush = holderGetter.getOrThrow(NWConfiguredFeatures.AZALEA_BUSH);

        RegistryEntry<ConfiguredFeature<?, ?>> loamPatchCeiling = holderGetter.getOrThrow(NWConfiguredFeatures.LOAM_PATCH_CEILING);
        RegistryEntry<ConfiguredFeature<?, ?>> loamOre = holderGetter.getOrThrow(NWConfiguredFeatures.LOAM_ORE);
        RegistryEntry<ConfiguredFeature<?, ?>> loamSnow = holderGetter.getOrThrow(NWConfiguredFeatures.LOAM_SNOW);
        RegistryEntry<ConfiguredFeature<?, ?>> calcitePatch = holderGetter.getOrThrow(NWConfiguredFeatures.CALCITE_PATCH);
        RegistryEntry<ConfiguredFeature<?, ?>> fernPatch = holderGetter.getOrThrow(VegetationConfiguredFeatures.PATCH_TAIGA_GRASS);





        PlacedFeatures.register(featureRegisterable, FIR_CHECKED, fir, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, FIR_BEES_CHECKED, firBees, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, GROWN_FIR_CHECKED, grownFir, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, GROWN_FIR_BEES_CHECKED, grownFirBees, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));

        PlacedFeatures.register(featureRegisterable, TREES_FIR, firSpawn, treeModifiers(PlacedFeatures.createCountExtraModifier(3, 0.1f, 5)));
        PlacedFeatures.register(featureRegisterable, TREES_FIR_SCARCE, firTaiga, treeModifiers(RarityFilterPlacementModifier.of(1)));
        PlacedFeatures.register(featureRegisterable, TREES_FIR_MEADOW, firMeadow, treeModifiers(RarityFilterPlacementModifier.of(40)));

        PlacedFeatures.register(featureRegisterable,FALLEN_FIR_LOG, fallenFirLog, List.of(RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));

        PlacedFeatures.register(featureRegisterable,PATCH_BERRY_WOODED_MEADOW, patchBerryBush, List.of(RarityFilterPlacementModifier.of(12), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        PlacedFeatures.register(featureRegisterable,GLOW_LICHEN_WOODED_MEADOW, patchLichen, List.of(CountPlacementModifier.of(UniformIntProvider.create(104, 157)), PlacedFeatures.BOTTOM_TO_TOP_RANGE, RarityFilterPlacementModifier.of(2), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, 30, 300), BiomePlacementModifier.of()));



        PlacedFeatures.register(featureRegisterable, LOAM_PATCH_CEILING, loamPatchCeiling, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, LOAM_ORE, loamOre, CountPlacementModifier.of(250), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(32)), BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, LOAM_SNOW, loamSnow, CountPlacementModifier.of(8), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(32)), BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, CALCITE_PATCH, calcitePatch, CountPlacementModifier.of(100), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, PATCH_FERN_WOODED_MEADOW, fernPatch, modifiers(4));


        /*
            Vanilla Biome Features
         */
        PlacedFeatures.register(featureRegisterable,BIRCH_CHERRY_GROVE, birchTall, treeModifiers(RarityFilterPlacementModifier.of(3)));
        PlacedFeatures.register(featureRegisterable, LUSH_CAVE_MUD_PATCH, lushCaveMud, CountPlacementModifier.of(35), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0)), BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, LUSH_CAVE_LOAM_ORE, loamOre, CountPlacementModifier.of(27), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0)), BiomePlacementModifier.of());
//        PlacedFeatures.register(featureRegisterable, AZALEA_BUSH, azaleaBush, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 5), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiers(int count) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }


    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, TREES_FIR_SCARCE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, TREES_FIR_MEADOW);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.CHERRY_GROVE), GenerationStep.Feature.VEGETAL_DECORATION, BIRCH_CHERRY_GROVE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, LUSH_CAVE_MUD_PATCH);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, LUSH_CAVE_LOAM_ORE);
//        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, AZALEA_BUSH);
    }


}
