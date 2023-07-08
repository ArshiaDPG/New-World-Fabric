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
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class NWPlacedFeatures {
    public static final RegistryKey<PlacedFeature> FALLEN_FIR_LOG= RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("fallen_fir_log"));

    public static final RegistryKey<PlacedFeature> FIR_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("fir_checked"));
    public static final RegistryKey<PlacedFeature> FIR_BEES_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("fir_bees_checked"));
    public static final RegistryKey<PlacedFeature> GROWN_FIR_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("grown_fir_checked"));
    public static final RegistryKey<PlacedFeature> GROWN_FIR_BEES_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("grown_fir_bees_checked"));


    public static final RegistryKey<PlacedFeature> TREES_FIR = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("trees_fir"));
    public static final RegistryKey<PlacedFeature> TREES_FIR_SCARCE= RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("trees_fir_scarce"));
    public static final RegistryKey<PlacedFeature> TREES_FIR_MEADOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("trees_fir_meadow"));

    public static final RegistryKey<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("glow_lichen_wooded_meadow"));
    public static final RegistryKey<PlacedFeature> MOSS_CARPET_WOODED_MEADOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("moss_carpet_wooded_meadow"));
    public static final RegistryKey<PlacedFeature> PATCH_BERRY_WOODED_MEADOW = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Newworld.id("patch_berry_bush_wooded_meadow"));


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
        RegistryEntry<ConfiguredFeature<?, ?>> patchMossCarpet = holderGetter.getOrThrow(NWConfiguredFeatures.MOSS_CARPET_WOODED_MEADOW);
        RegistryEntry<ConfiguredFeature<?, ?>> patchLichen = holderGetter.getOrThrow(NWConfiguredFeatures.GLOW_LICHEN_WOODED_MEADOW);




        PlacedFeatures.register(featureRegisterable, FIR_CHECKED, fir, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, FIR_BEES_CHECKED, firBees, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, GROWN_FIR_CHECKED, grownFir, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));
        PlacedFeatures.register(featureRegisterable, GROWN_FIR_BEES_CHECKED, grownFirBees, PlacedFeatures.wouldSurvive(NWBlocks.FIR_SAPLING));

        PlacedFeatures.register(featureRegisterable, TREES_FIR, firSpawn, VegetationPlacedFeatures.treeModifiers(PlacedFeatures.createCountExtraModifier(3, 0.1f, 5)));
        PlacedFeatures.register(featureRegisterable, TREES_FIR_SCARCE, firTaiga, VegetationPlacedFeatures.treeModifiers(RarityFilterPlacementModifier.of(1)));
        PlacedFeatures.register(featureRegisterable, TREES_FIR_MEADOW, firMeadow, VegetationPlacedFeatures.treeModifiers(RarityFilterPlacementModifier.of(40)));

        PlacedFeatures.register(featureRegisterable,FALLEN_FIR_LOG, fallenFirLog, List.of(RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));

        PlacedFeatures.register(featureRegisterable,PATCH_BERRY_WOODED_MEADOW, patchBerryBush, List.of(RarityFilterPlacementModifier.of(12), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        PlacedFeatures.register(featureRegisterable,MOSS_CARPET_WOODED_MEADOW, patchMossCarpet, List.of(CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        PlacedFeatures.register(featureRegisterable,GLOW_LICHEN_WOODED_MEADOW, patchLichen, List.of(CountPlacementModifier.of(UniformIntProvider.create(104, 157)), PlacedFeatures.BOTTOM_TO_TOP_RANGE, RarityFilterPlacementModifier.of(2), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, 30, 300), BiomePlacementModifier.of()));
    }


    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, TREES_FIR_SCARCE);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, TREES_FIR_MEADOW);
    }
}
