package net.digitalpear.newworld.common.datagens.loot;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.StoneSet;
import net.digitalpear.newworld.init.data.Woodset;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.loottable.vanilla.VanillaBlockLootTableGenerator;
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

        makeStoneLoot(NWBlocks.LOAM);
        makeStoneLoot(NWBlocks.LOAM_TILES);
        makeStoneLoot(NWBlocks.LOAM_BRICKS);

        addDrop(NWBlocks.TOMBSTONE);

        addDrop(NWBlocks.POTTED_POINTED_DRIPSTONE, pottedPlantDrops(Items.POINTED_DRIPSTONE));

        addDrop(NWBlocks.MOSS_SPROUTS, dropsWithShears(NWBlocks.MOSS_SPROUTS));
    }

    public void makeStoneLoot(StoneSet stoneSet){
        for (Block block : stoneSet.iterate()){
            if (block instanceof SlabBlock){
                addDrop(block, slabDrops(block));
            }
            else{
                addDrop(block);
            }
        }
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

        if (woodset.getWoodsetSettings().hasMosaic()) {
            addDrop(woodset.getMosaic());
            addDrop(woodset.getMosaicStairs());
            addDrop(woodset.getMosaicSlab(), slabDrops(woodset.getMosaic()));
        }
        if (woodset.notBambooVariant()){
            addDrop(woodset.getWood());
            addDrop(woodset.getStrippedLog());
            addDrop(woodset.getStrippedWood());
        }
        
        addDrop(woodset.getTrapDoor());
        addDrop(woodset.getDoor(), doorDrops(woodset.getDoor()));
        if (woodset.isOverworldTreeWood()){
            addDrop(woodset.getLeaves(), leavesDrops(woodset.getLeaves(), sapling, VanillaBlockLootTableGenerator.SAPLING_DROP_CHANCE));
            addDrop(sapling);
        }
        addDrop(woodset.getSign());
        addDrop(woodset.getHangingSign());
    }
}


