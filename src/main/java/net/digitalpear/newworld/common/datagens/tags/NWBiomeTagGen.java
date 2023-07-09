package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.data.tags.NWBiomeTags;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class NWBiomeTagGen extends FabricTagProvider<Biome> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public NWBiomeTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(NWBiomeTags.BURIED_BUNKER_HAS_STRUCTURE)
                .add(BiomeKeys.PLAINS)
                .add(BiomeKeys.MEADOW);
        getOrCreateTagBuilder(BiomeTags.IS_MOUNTAIN).add(NWBiomes.WOODED_MEADOW);
        getOrCreateTagBuilder(BiomeTags.VILLAGE_TAIGA_HAS_STRUCTURE).add(NWBiomes.WOODED_MEADOW);
        getOrCreateTagBuilder(BiomeTags.MINESHAFT_HAS_STRUCTURE).add(NWBiomes.WOODED_MEADOW);
    }
}
