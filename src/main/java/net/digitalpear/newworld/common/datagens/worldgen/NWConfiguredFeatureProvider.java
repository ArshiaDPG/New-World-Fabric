package net.digitalpear.newworld.common.datagens.worldgen;

import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class NWConfiguredFeatureProvider extends FabricDynamicRegistryProvider {

    public NWConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, NWConfiguredFeatures.FIR);
        add(registries, entries, NWConfiguredFeatures.FALLEN_FIR_LOG);
        add(registries, entries, NWConfiguredFeatures.FIR_BEES);
        add(registries, entries, NWConfiguredFeatures.FIR_BEES_002);
        add(registries, entries, NWConfiguredFeatures.GLOW_LICHEN_WOODED_MEADOW);
        add(registries, entries, NWConfiguredFeatures.FIR_MEADOW);
        add(registries, entries, NWConfiguredFeatures.FIR_SPAWN);
        add(registries, entries, NWConfiguredFeatures.FIR_TAIGA);
        add(registries, entries, NWConfiguredFeatures.GROWN_FIR);
        add(registries, entries, NWConfiguredFeatures.GROWN_FIR_BEES);
        add(registries, entries, NWConfiguredFeatures.GROWN_FIR_BEES_002);
        add(registries, entries, NWConfiguredFeatures.MOSS_CARPET_WOODED_MEADOW);
        add(registries, entries, NWConfiguredFeatures.PATCH_BERRY_WOODED_MEADOW);
    }
    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<ConfiguredFeature<?, ?>> resourceKey) {
        RegistryWrapper.Impl<ConfiguredFeature<?, ?>> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "worldgen/configured_feature";
    }
}