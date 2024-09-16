package net.digitalpear.newworld.common.datagens.providers.worldgen.structures;

import net.digitalpear.newworld.init.worldgen.structures.NWStructurePools;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.pool.StructurePool;

import java.util.concurrent.CompletableFuture;

public class NWStructurePoolProvider extends FabricDynamicRegistryProvider {

    public NWStructurePoolProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        NWStructurePools.structurePools.forEach(biomeRegistryKey -> add(registries, entries, biomeRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<StructurePool> biome) {
        final RegistryWrapper.Impl<StructurePool> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/template_pool";
    }
}