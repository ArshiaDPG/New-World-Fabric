package net.digitalpear.newworld.common.datagens.loot;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.woodset.Woodset;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class NWBlockLootTableProvider extends FabricBlockLootTableProvider {
    public NWBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        makeWoodLoot(NWBlocks.FIR, NWBlocks.FIR_SAPLING);

        addDrop(NWBlocks.LOAM);
        addDrop(NWBlocks.LOAM_STAIRS);
        addDrop(NWBlocks.LOAM_SLAB, slabDrops(NWBlocks.LOAM_SLAB));
        addDrop(NWBlocks.LOAM_WALL);
        addDrop(NWBlocks.LOAM_BRICKS);
        addDrop(NWBlocks.LOAM_BRICK_STAIRS);
        addDrop(NWBlocks.LOAM_BRICK_SLAB, slabDrops(NWBlocks.LOAM_BRICK_SLAB));
        addDrop(NWBlocks.LOAM_BRICK_WALL);
        addDrop(NWBlocks.LOAM_TILES);
        addDrop(NWBlocks.LOAM_TILE_STAIRS);
        addDrop(NWBlocks.LOAM_TILE_SLAB, slabDrops(NWBlocks.LOAM_TILE_SLAB));
        addDrop(NWBlocks.LOAM_TILE_WALL);

        addDrop(NWBlocks.TOMBSTONE);

        addDrop(NWBlocks.POTTED_POINTED_DRIPSTONE, pottedPlantDrops(Items.POINTED_DRIPSTONE));
    }
    public void makeWoodLoot(Woodset woodset, Block sapling){
        addDrop(woodset.getPlanks());
        addDrop(woodset.getStairs());
        addDrop(woodset.getSlab(), slabDrops(woodset.getSlab()));
        addDrop(woodset.getFence());
        addDrop(woodset.getFenceGate());
        addDrop(woodset.getButton());
        addDrop(woodset.getPressurePlate());
        addDrop(woodset.getLog());

        if (woodset.getWoodPreset() == Woodset.WoodPreset.BAMBOO) {
            addDrop(woodset.getMosaic());
            addDrop(woodset.getMosaicStairs());
            addDrop(woodset.getMosaicSlab(), slabDrops(woodset.getMosaic()));
        }
        else{
            addDrop(woodset.getWood());
            addDrop(woodset.getStrippedLog());
            addDrop(woodset.getStrippedWood());
        }
        
        addDrop(woodset.getTrapDoor());
        addDrop(woodset.getDoor(), doorDrops(woodset.getDoor()));
        if (woodset.isNormalWood()){
            addDrop(woodset.getLeaves(), leavesDrops(woodset.getLeaves(), sapling, VanillaBlockLootTableGenerator.SAPLING_DROP_CHANCE));
            addDrop(sapling);
        }
        addDrop(woodset.getSign());
        addDrop(woodset.getHangingSign());
    }
}


