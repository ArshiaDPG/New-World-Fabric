package net.digitalpear.newworld.init.worldgen;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.NWOverworldBiomeCreator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

public class NWBiomes {

    public static List<RegistryKey<Biome>> biomes = new ArrayList<>();
    public static RegistryKey<Biome> createBiomeKey(String id){
        RegistryKey<Biome> registryKey = RegistryKey.of(RegistryKeys.BIOME, new Identifier(Newworld.MOD_ID, id));
        biomes.add(registryKey);
        return registryKey;
    }

    public static final RegistryKey<Biome> WOODED_MEADOW = createBiomeKey("wooded_meadow");
    public static final RegistryKey<Biome> ENFOLDED_SCRAPYARD = createBiomeKey("enfolded_scrapyard");

    public static void bootstrap(Registerable<Biome> bootstapContext) {
        RegistryEntryLookup<PlacedFeature> placeddFeatureHolder = bootstapContext.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> configuredWorldCarverHolderGetter = bootstapContext.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);


        bootstapContext.register(WOODED_MEADOW, NWOverworldBiomeCreator.createWoodedMeadow(placeddFeatureHolder, configuredWorldCarverHolderGetter));
        bootstapContext.register(ENFOLDED_SCRAPYARD, NWOverworldBiomeCreator.createEnfoldedScrapyard(placeddFeatureHolder, configuredWorldCarverHolderGetter));
    }

    public static void init(){
    }
}
