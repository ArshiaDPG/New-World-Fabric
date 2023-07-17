package net.digitalpear.newworld.common.datagens.worldgen.structures;

import net.digitalpear.newworld.init.worldgen.structures.NWStructureSets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.StructureSet;

import java.util.concurrent.CompletableFuture;

public class NWStructureSetProvider extends FabricDynamicRegistryProvider {

    public NWStructureSetProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        NWStructureSets.structureSets.forEach(biomeRegistryKey -> add(registries, entries, biomeRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructureSet> biome) {
        final RegistryWrapper.Impl<StructureSet> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.STRUCTURE_SET);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/structure_set";
    }
}