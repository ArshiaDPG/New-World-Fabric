package net.digitalpear.newworld.common.worldgen;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.features.FallenLogFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class NWFeature {
    public static final Feature<SingleStateFeatureConfig> FALLEN_LOG = register("fallen_log", new FallenLogFeature(SingleStateFeatureConfig.CODEC));


    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, Newworld.id(name), feature);
    }

    public static void init() {
    }
}
