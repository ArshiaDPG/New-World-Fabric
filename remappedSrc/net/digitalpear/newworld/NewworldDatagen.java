package net.digitalpear.newworld;

import net.digitalpear.newworld.common.datagens.*;
import net.digitalpear.newworld.common.datagens.tags.NWBiomeTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWBlockTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWEntityTypeTagProvider;
import net.digitalpear.newworld.common.datagens.tags.NWItemTagProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWBiomeProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWConfiguredFeatureProvider;
import net.digitalpear.newworld.common.datagens.worldgen.NWPlacedFeatureProvider;
import net.digitalpear.newworld.common.datagens.worldgen.structures.NWProcessorListProvider;
import net.digitalpear.newworld.common.datagens.worldgen.structures.NWStructurePoolProvider;
import net.digitalpear.newworld.common.datagens.worldgen.structures.NWStructureProvider;
import net.digitalpear.newworld.common.datagens.worldgen.structures.NWStructureSetProvider;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.digitalpear.newworld.init.worldgen.structures.NWProcessorLists;
import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.digitalpear.newworld.init.worldgen.structures.NWStructurePools;
import net.digitalpear.newworld.init.worldgen.structures.NWStructureSets;
import net.digitalpear.newworld.init.worldgen.structures.NWStructures;
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

        pack.addProvider(NWProcessorListProvider::new);
        pack.addProvider(NWStructureSetProvider::new);
        pack.addProvider(NWStructureProvider::new);
        pack.addProvider(NWStructurePoolProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, NWConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, NWPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, NWBiomes::bootstrap);

        registryBuilder.addRegistry(RegistryKeys.PROCESSOR_LIST, NWProcessorLists::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, NWStructureSets::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE, NWStructures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, NWStructurePools::bootstrap);
    }
}
