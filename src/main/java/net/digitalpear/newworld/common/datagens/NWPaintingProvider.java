package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWPaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class NWPaintingProvider extends FabricDynamicRegistryProvider {
    public NWPaintingProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        NWPaintingVariants.paintings.forEach(paintingVariantRegistryKey -> add(wrapperLookup, entries, paintingVariantRegistryKey));
    }
    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PaintingVariant> resourceKey) {
        RegistryWrapper.Impl<PaintingVariant> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.PAINTING_VARIANT);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "painting_variant";
    }
}
