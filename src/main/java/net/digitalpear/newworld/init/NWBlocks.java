package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.blocks.MossSproutsBlock;
import net.digitalpear.newworld.common.blocks.TombstoneBlock;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.worldgen.NWSaplingGenerators;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;


@SuppressWarnings("unused")
public class NWBlocks {

    public static BlockItem createCustomBlockItem(String blockID, Block block, Item.Settings item){
        return Registry.register(Registries.ITEM, Newworld.id(blockID), new BlockItem(block, item));
    }
    public static BlockItem createBlockItem(String blockID, Block block){
        return createCustomBlockItem(blockID, block, new Item.Settings());
    }
    private static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, Newworld.id(blockID), block);
    }

    private static Block createBlockWithCustomItem(String blockID, Block block, Item.Settings item){
        createCustomBlockItem(blockID, block, item);
        return createBlockWithoutItem(blockID, block);
    }


    private static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return createBlockWithoutItem(blockID, block);
    }





    public static final Woodset FIR = new Woodset(Newworld.id("fir"), MapColor.DEEPSLATE_GRAY, MapColor.SPRUCE_BROWN, BlockSoundGroup.AZALEA_LEAVES);

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

    public static final Block FIR_SAPLING = createBlockWithItem("fir_sapling", new SaplingBlock(NWSaplingGenerators.FIR, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_FIR_SAPLING = createBlockWithoutItem("potted_fir_sapling", new FlowerPotBlock(NWBlocks.FIR_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING)));

    public static final Block FIR_SIGN = FIR.getSign();
    public static final Block FIR_WALL_SIGN = FIR.getWallSign();

    public static final Block FIR_HANGING_SIGN = FIR.getHangingSign();
    public static final Block FIR_HANGING_WALL_SIGN = FIR.getWallHangingSign();

    public static final Block LOAM = createBlockWithItem("loam", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2f, 3f).sounds(BlockSoundGroup.PACKED_MUD)));
    public static final Block LOAM_STAIRS = createBlockWithItem("loam_stairs", new StairsBlock(LOAM.getDefaultState(), AbstractBlock.Settings.copy(LOAM)));
    public static final Block LOAM_SLAB = createBlockWithItem("loam_slab", new SlabBlock(AbstractBlock.Settings.copy(LOAM)));
    public static final Block LOAM_WALL = createBlockWithItem("loam_wall", new WallBlock(AbstractBlock.Settings.copy(LOAM)));

    public static final Block LOAM_BRICKS = createBlockWithItem("loam_bricks", new Block(AbstractBlock.Settings.copy(LOAM).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block LOAM_BRICK_STAIRS = createBlockWithItem("loam_brick_stairs", new StairsBlock(LOAM_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LOAM_BRICKS)));
    public static final Block LOAM_BRICK_SLAB = createBlockWithItem("loam_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LOAM_BRICKS)));
    public static final Block LOAM_BRICK_WALL = createBlockWithItem("loam_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LOAM_BRICKS)));

    public static final Block LOAM_TILES = createBlockWithItem("loam_tiles", new Block(AbstractBlock.Settings.copy(LOAM).sounds(BlockSoundGroup.MUD_BRICKS)));
    public static final Block LOAM_TILE_STAIRS = createBlockWithItem("loam_tile_stairs", new StairsBlock(LOAM_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LOAM_TILES)));
    public static final Block LOAM_TILE_SLAB = createBlockWithItem("loam_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(LOAM_TILES)));
    public static final Block LOAM_TILE_WALL = createBlockWithItem("loam_tile_wall", new WallBlock(AbstractBlock.Settings.copy(LOAM_TILES)));

    public static final Block MOSS_SPROUTS = createBlockWithItem("moss_sprouts", new MossSproutsBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).offset(AbstractBlock.OffsetType.XZ).sounds(BlockSoundGroup.MOSS_CARPET)));

    public static final Block TOMBSTONE = createBlockWithoutItem("tombstone", new TombstoneBlock(AbstractBlock.Settings.create().mapColor(Blocks.DEEPSLATE.getDefaultMapColor()).strength(0.7f, 1200).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.POLISHED_DEEPSLATE).suffocates((state, world, pos) -> false).nonOpaque()));

    /*
        Experimental blocks
     */
    public static final Block POTTED_POINTED_DRIPSTONE = createBlockWithoutItem("potted_pointed_dripstone", new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING)));

    public static void init(){
        FIR.addToBuildingTab(Items.SPRUCE_BUTTON);
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LEAVES, FIR_LEAVES);
            entries.addAfter(Items.SPRUCE_SAPLING, FIR_SAPLING);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.MUD, LOAM);
            entries.addAfter(Items.SHORT_GRASS, NWBlocks.MOSS_SPROUTS);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.MUD_BRICK_WALL, LOAM, LOAM_STAIRS, LOAM_WALL,
                    LOAM_SLAB, LOAM_BRICKS, LOAM_BRICK_STAIRS, LOAM_BRICK_WALL,
                    LOAM_BRICK_SLAB, LOAM_TILES, LOAM_TILE_STAIRS, LOAM_TILE_SLAB, LOAM_TILE_WALL
            );
        });
    }
}
