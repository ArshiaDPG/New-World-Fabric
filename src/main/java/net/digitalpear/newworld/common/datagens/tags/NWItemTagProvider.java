package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

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
        addMineableAxe(NWBlocks.FIR, NWItemTags.FIR_LOGS);

        getOrCreateTagBuilder(NWItemTags.ANCIENT_TOOL_MATERIALS).add(Items.FLINT);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(NWItemTags.FIR_LOGS);

        getOrCreateTagBuilder(ItemTags.BOATS).add(NWItems.FIR_BOAT);
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(NWItems.FIR_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.asItem());


        getOrCreateTagBuilder(ItemTags.STAIRS).add(NWBlocks.LOAM_STAIRS.asItem(), NWBlocks.LOAM_BRICK_STAIRS.asItem(), NWBlocks.LOAM_TILE_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(NWBlocks.LOAM_SLAB.asItem(), NWBlocks.LOAM_BRICK_SLAB.asItem(), NWBlocks.LOAM_TILE_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WALLS).add(NWBlocks.LOAM_WALL.asItem(), NWBlocks.LOAM_BRICK_WALL.asItem(), NWBlocks.LOAM_TILE_WALL.asItem());

        getOrCreateTagBuilder(ItemTags.BOOKSHELF_BOOKS).add(NWItems.ILLAGER_TOME);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK);
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK);
    }
    private void addMineableAxe(Woodset woodset, TagKey<Item> logs){
        getOrCreateTagBuilder(logs).add(woodset.getLog().asItem()).add(woodset.getStrippedLog().asItem());
        if (!woodset.isBambooVariant()){
            getOrCreateTagBuilder(logs).add(woodset.getWood().asItem()).add(woodset.getStrippedWood().asItem());
        }
        if (woodset.isOverworldTreeWood()){
            getOrCreateTagBuilder(ItemTags.LEAVES).add(woodset.getLeaves().asItem());
        }
        getOrCreateTagBuilder(ItemTags.PLANKS).add(woodset.getPlanks().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(woodset.getStairs().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(woodset.getSlab().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(woodset.getFence().asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(woodset.getFenceGate().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(woodset.getDoor().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(woodset.getTrapDoor().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(woodset.getPressurePlate().asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS).add(woodset.getButton().asItem());
        getOrCreateTagBuilder(ItemTags.SIGNS).add(woodset.getSignItem());
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(woodset.getHangingSignItem());
    }
}

