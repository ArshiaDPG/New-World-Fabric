package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class NWBlockLootTableGen extends FabricBlockLootTableProvider {
    public NWBlockLootTableGen(FabricDataOutput dataOutput) {
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
        addDrop(NWBlocks.FIR_LEAVES, leavesDrops(NWBlocks.FIR_LEAVES, NWBlocks.FIR_SAPLING));
    }
}


