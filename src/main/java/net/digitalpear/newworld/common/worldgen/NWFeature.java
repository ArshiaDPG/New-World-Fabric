package net.digitalpear.newworld.common.worldgen;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.features.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class NWFeature {
    public static final Feature<SingleStateFeatureConfig> FALLEN_LOG = register("fallen_log", new FallenLogFeature(SingleStateFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> LOAM_SNOW = register("loam_snow", new LoamSnowFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<SmallBushFeatureConfig> SMALL_BUSH = register("small_bush", new SmallBushFeature(SmallBushFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> BURIAL_SITE = register("burial_site", new BurialSiteFeature(DefaultFeatureConfig.CODEC));


    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, Newworld.id(name), feature);
    }

    public static void init() {
    }
}
