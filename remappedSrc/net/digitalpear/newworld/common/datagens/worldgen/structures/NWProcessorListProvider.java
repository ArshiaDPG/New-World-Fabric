package net.digitalpear.newworld.common.datagens.providers.worldgen.structures;

import net.digitalpear.newworld.init.worldgen.structures.NWProcessorLists;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.processor.StructureProcessorList;

import java.util.concurrent.CompletableFuture;

public class NWProcessorListProvider extends FabricDynamicRegistryProvider {

    public NWProcessorListProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        NWProcessorLists.processorLists.forEach(biomeRegistryKey -> add(registries, entries, biomeRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructureProcessorList> biome) {
        final RegistryWrapper.Impl<StructureProcessorList> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.PROCESSOR_LIST);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/processor_list";
    }
}