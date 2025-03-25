package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.data.tags.NWItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class NWItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public NWItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, new NWBlockTagProvider(output, completableFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addMineableAxe(NWBlocks.FIR, NWItemTags.FIR_LOGS);

        getOrCreateTagBuilder(NWItemTags.ANCIENT_TOOL_MATERIALS).add(Items.FLINT);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(NWItemTags.FIR_LOGS);

        getOrCreateTagBuilder(ItemTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.asItem());

        getOrCreateTagBuilder(ItemTags.STAIRS).add(NWBlocks.LOAM.getStairs().asItem(), NWBlocks.LOAM_BRICKS.getStairs().asItem(), NWBlocks.LOAM_TILES.getStairs().asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(NWBlocks.LOAM.getSlab().asItem(), NWBlocks.LOAM_BRICKS.getSlab().asItem(), NWBlocks.LOAM_TILES.getSlab().asItem());
        getOrCreateTagBuilder(ItemTags.WALLS).add(NWBlocks.LOAM.getWall().asItem(), NWBlocks.LOAM_BRICKS.getWall().asItem(), NWBlocks.LOAM_TILES.getWall().asItem());

        getOrCreateTagBuilder(ItemTags.BOOKSHELF_BOOKS).add(NWItems.ILLAGER_TOME);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK);
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK);
    }
    private void addMineableAxe(Woodset woodset, TagKey<Item> logs){
        getOrCreateTagBuilder(logs).add(woodset.getLog().asItem()).add(woodset.getStrippedLog().asItem());
        if (woodset.notBambooVariant()){
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

        getOrCreateTagBuilder(ItemTags.BOATS).add(woodset.getBoatItem());
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodset.getChestBoatItem());
    }
}

