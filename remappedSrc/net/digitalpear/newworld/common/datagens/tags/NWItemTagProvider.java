package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class NWItemTagProvider extends FabricTagProvider<Item> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public NWItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(NWItemTags.FIR_LOGS);
        getOrCreateTagBuilder(ItemTags.PLANKS).add(NWBlocks.FIR_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(NWBlocks.FIR_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(NWBlocks.FIR_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(NWBlocks.FIR_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(NWBlocks.FIR_FENCE_GATE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(NWBlocks.FIR_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(NWBlocks.FIR_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ItemTags.LEAVES).add(NWBlocks.FIR_LEAVES.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(NWBlocks.FIR_PRESSURE_PLATE.asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS).add(NWBlocks.FIR_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.BOATS).add(NWItems.FIR_BOAT);
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(NWItems.FIR_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.asItem());

        getOrCreateTagBuilder(NWItemTags.FIR_LOGS)
                .add(NWBlocks.FIR_LOG.asItem())
                .add(NWBlocks.FIR_WOOD.asItem())
                .add(NWBlocks.STRIPPED_FIR_WOOD.asItem())
                .add(NWBlocks.STRIPPED_FIR_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.STAIRS).add(NWBlocks.LOAM_STAIRS.asItem(), NWBlocks.LOAM_BRICK_STAIRS.asItem(), NWBlocks.LOAM_TILE_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(NWBlocks.LOAM_SLAB.asItem(), NWBlocks.LOAM_BRICK_SLAB.asItem(), NWBlocks.LOAM_TILE_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WALLS).add(NWBlocks.LOAM_WALL.asItem(), NWBlocks.LOAM_BRICK_WALL.asItem(), NWBlocks.LOAM_TILE_WALL.asItem());
    }
}

