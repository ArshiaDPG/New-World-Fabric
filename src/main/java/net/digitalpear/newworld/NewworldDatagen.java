package net.digitalpear.newworld;

import net.digitalpear.newworld.common.datagens.*;
import net.digitalpear.newworld.common.datagens.tags.NWBiomeTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWBlockTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWEntityTypeTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWItemTagProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWBiomeProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWConfiguredFeatureProvider;
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

        pack.addProvider(NWItemTagProvider::new);
        pack.addProvider(NWBlockTagProvider::new);
        pack.addProvider(NWBiomeTagProvider::new);
        pack.addProvider(NWEntityTypeTagProvider::new);

        pack.addProvider(NWBlockLootTableProvider::new);
        pack.addProvider(NWChestLootTableProvider::new);
        pack.addProvider(NWModelProvider::new);
        pack.addProvider(NWRecipeProvider::new);
        pack.addProvider(NWLanguageProvider::new);
        pack.addProvider(NWAdvancementProvider::new);

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
