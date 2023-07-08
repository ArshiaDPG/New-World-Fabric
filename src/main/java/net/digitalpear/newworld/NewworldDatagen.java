package net.digitalpear.newworld;

import net.digitalpear.newworld.common.datagens.NWBlockLootTableGen;
import net.digitalpear.newworld.common.datagens.NWBlockModelGen;
import net.digitalpear.newworld.common.datagens.NWRecipeGen;
import net.digitalpear.newworld.common.datagens.tags.NWBiomeTagGen;
import net.digitalpear.newworld.common.datagens.tags.NWBlockTagGen;
import net.digitalpear.newworld.common.datagens.tags.NWItemTagGen;
import net.digitalpear.newworld.common.datagens.worldgen.NWBiomeProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWConfiguredFeatureProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWLanguageProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWPlacedFeatureProvider;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class NewworldDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(NWItemTagGen::new);
        pack.addProvider(NWBlockTagGen::new);
        pack.addProvider(NWBiomeTagGen::new);

        pack.addProvider(NWBlockLootTableGen::new);
        pack.addProvider(NWBlockModelGen::new);
        pack.addProvider(NWRecipeGen::new);
        pack.addProvider(NWLanguageProvider::new);

        pack.addProvider(NWConfiguredFeatureProvider::new);
        pack.addProvider(NWPlacedFeatureProvider::new);
        pack.addProvider(NWBiomeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, NWConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, NWPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, NWBiomes::bootstrap);
    }
}
