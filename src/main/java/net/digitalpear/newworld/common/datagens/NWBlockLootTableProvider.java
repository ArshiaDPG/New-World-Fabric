package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.item.Items;

public class NWBlockLootTableProvider extends FabricBlockLootTableProvider {
    public NWBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(NWBlocks.FIR_PLANKS);
        addDrop(NWBlocks.FIR_STAIRS);
        addDrop(NWBlocks.FIR_SLAB, slabDrops(NWBlocks.FIR_SLAB));
        addDrop(NWBlocks.FIR_FENCE);
        addDrop(NWBlocks.FIR_FENCE_GATE);
        addDrop(NWBlocks.FIR_BUTTON);
        addDrop(NWBlocks.FIR_PRESSURE_PLATE);
        addDrop(NWBlocks.FIR_LOG);
        addDrop(NWBlocks.FIR_WOOD);
        addDrop(NWBlocks.STRIPPED_FIR_LOG);
        addDrop(NWBlocks.STRIPPED_FIR_WOOD);
        addDrop(NWBlocks.FIR_TRAPDOOR);
        addDrop(NWBlocks.FIR_DOOR, doorDrops(NWBlocks.FIR_DOOR));
        addDrop(NWBlocks.FIR_LEAVES, leavesDrops(NWBlocks.FIR_LEAVES, NWBlocks.FIR_SAPLING, VanillaBlockLootTableGenerator.SAPLING_DROP_CHANCE));


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
}


