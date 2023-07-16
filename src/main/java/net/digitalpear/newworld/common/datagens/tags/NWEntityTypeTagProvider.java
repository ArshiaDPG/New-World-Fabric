package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWEntityTypes;
import net.digitalpear.newworld.init.data.tags.NWEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class NWEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public NWEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENTITY_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(NWEntityTypeTags.AUTOMATONS_WILL_IGNORE)
                .add(EntityType.GHAST)
                .add(NWEntityTypes.AUTOMATON)
                .add(EntityType.ENDER_DRAGON)
                .add(EntityType.CREEPER);
    }

}
