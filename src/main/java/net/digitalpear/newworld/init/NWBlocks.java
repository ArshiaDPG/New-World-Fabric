package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.tree.FirSaplingGenerator;
import net.digitalpear.newworld.init.woodset.Woodset;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


@SuppressWarnings("unused")
public class NWBlocks {
    public static MapColor TOP_COLOR = MapColor.SPRUCE_BROWN;
    public static MapColor SIDE_COLOR = MapColor.DEEPSLATE_GRAY;




    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Newworld.MOD_ID, blockID), new BlockItem(block, new Item.Settings()));
    }

    private static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(Newworld.MOD_ID, blockID), block);
    }

    private static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(Newworld.MOD_ID, blockID), block);
    }

    public static final Woodset FIR = new Woodset(Newworld.id("fir"), TOP_COLOR, SIDE_COLOR, Woodset.WoodPreset.DEFAULT);

    public static final Block FIR_LOG = FIR.getLog();
    public static final Block FIR_WOOD = FIR.getWood();
    public static final Block STRIPPED_FIR_LOG = FIR.getStrippedLog();
    public static final Block STRIPPED_FIR_WOOD = FIR.getStrippedWood();

    public static final Block FIR_PLANKS = FIR.getPlanks();
    public static final Block FIR_STAIRS = FIR.getStairs();
    public static final Block FIR_SLAB = FIR.getSlab();

    public static final Block FIR_DOOR = FIR.getDoor();
    public static final Block FIR_TRAPDOOR = FIR.getTrapDoor();

    public static final Block FIR_BUTTON = FIR.getButton();
    public static final Block FIR_PRESSURE_PLATE = FIR.getPressurePlate();

    public static final Block FIR_FENCE = FIR.getFence();
    public static final Block FIR_FENCE_GATE = FIR.getFenceGate();

    public static final Block FIR_LEAVES = FIR.getLeaves();

    public static final Block FIR_SAPLING = createBlockWithItem("fir_sapling", new SaplingBlock(new FirSaplingGenerator(),AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_FIR_SAPLING = createBlockWithoutItem("potted_fir_sapling", new FlowerPotBlock(NWBlocks.FIR_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING)));

    public static final Block FIR_SIGN = FIR.getSign();
    public static final Block FIR_WALL_SIGN = FIR.getWallSign();

    public static final Block FIR_HANGING_SIGN = FIR.getHangingSign();
    public static final Block FIR_HANGING_WALL_SIGN = FIR.getHangingWallSign();

    public static void init(){
        Woodset.addToBuildingTab(Items.SPRUCE_BUTTON, FIR);
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LEAVES, FIR_LEAVES);
            entries.addAfter(Items.SPRUCE_SAPLING, FIR_SAPLING);
        });

    }
}
