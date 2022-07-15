package net.digitalpear.newworld.init.worldgen;

import net.digitalpear.newworld.NewWorld;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

public class NWPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> FIR_PLACED = PlacedFeatures.register(NewWorld.getId("fir_placed"),
            NWConfiguredFeatures.FIR_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(3, 0.1f, 5)));

    public static final RegistryEntry<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW = PlacedFeatures.register(NewWorld.getId("glow_lichen_wooded_meadow"),
            NWConfiguredFeatures.GLOW_LICHEN_WOODED_MEADOW, CountPlacementModifier.of(UniformIntProvider.create(104, 157)),
            PlacedFeatures.BOTTOM_TO_TOP_RANGE, SquarePlacementModifier.of(),
            SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG,
                    Integer.MIN_VALUE, -13),
            BiomePlacementModifier.of());




    public static final RegistryEntry<PlacedFeature> PATCH_BERRY_WOODED_MEADOW = PlacedFeatures.register(NewWorld.getId("patch_berry_wooded_meadow"), NWConfiguredFeatures.PATCH_BERRY_BUSH_WOODED_MEADOW, RarityFilterPlacementModifier.of(12), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> MOSS_CARPET_WOODED_MEADOW = PlacedFeatures.register(NewWorld.getId("moss_carpet_wooded_meadow"), NWConfiguredFeatures.MOSS_CARPET_BUSH_WOODED_MEADOW, RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());



}
