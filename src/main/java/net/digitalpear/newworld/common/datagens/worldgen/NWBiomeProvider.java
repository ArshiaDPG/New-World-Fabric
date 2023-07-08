package net.digitalpear.newworld.common.datagens.worldgen;

import net.digitalpear.newworld.common.worldgen.NWOverworldBiomes;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class NWBiomeProvider extends FabricDynamicRegistryProvider {


    public NWBiomeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, NWBiomes.WOODED_MEADOW);
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Biome> biome) {
        final RegistryWrapper.Impl<Biome> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.BIOME);

        entries.add(biome, biomeRegistry.getOrThrow(biome).value());
    }

    @Override
    public String getName() {
        return "worldgen/biome";
    }
}