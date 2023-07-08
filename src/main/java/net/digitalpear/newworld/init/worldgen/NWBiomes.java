package net.digitalpear.newworld.init.worldgen;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.NWOverworldBiomes;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

public class NWBiomes {

    public static RegistryKey<Biome> createBiomeKey(String id){
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(Newworld.MOD_ID, id));
    }

    public static final RegistryKey<Biome> WOODED_MEADOW = createBiomeKey("wooded_meadow");

    public static void bootstrap(Registerable<Biome> bootstapContext) {
        RegistryEntryLookup<PlacedFeature> placeddFeatureHolder = bootstapContext.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> configuredWorldCarverHolderGetter = bootstapContext.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);


        bootstapContext.register(WOODED_MEADOW, NWOverworldBiomes.createWoodedMeadow(placeddFeatureHolder, configuredWorldCarverHolderGetter));
    }

    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.TREES_FIR_MEADOW);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.TREES_FIR_SCARCE);
    }
}
