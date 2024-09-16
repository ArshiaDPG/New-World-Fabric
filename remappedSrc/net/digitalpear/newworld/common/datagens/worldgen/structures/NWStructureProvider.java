package net.digitalpear.newworld.common.datagens.providers.worldgen.structures;

import net.digitalpear.newworld.init.worldgen.structures.NWStructures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class NWStructureProvider extends FabricDynamicRegistryProvider {

    public NWStructureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        NWStructures.structures.forEach(biomeRegistryKey -> add(registries, entries, biomeRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Structure> biome) {
        final RegistryWrapper.Impl<Structure> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.STRUCTURE);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/structure";
    }
}