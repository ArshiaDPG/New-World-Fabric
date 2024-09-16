package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.BiFunction;
import java.util.function.Function;


@SuppressWarnings("unused")
public class NWBlocks {

    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings().fireproof();
        return createBlockItem(id, block, factory, settings);
    }
    public static Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(block, settings.registryKey(NWItems.keyOf(id)));
        return Registry.register(Registries.ITEM, NWItems.keyOf(id), item);
    }
    private static Block createBlockWithItem(String blockID, AbstractBlock.Settings settings){
        return createBlockWithItem(blockID, Block::new, settings);
    }
    private static Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Newworld.id(blockID))));
        createBlockItem(blockID, block, BlockItem::new);
        return createBlockWithoutItem(blockID, factory, settings);
    }
    private static Block createBlockWithoutItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Newworld.id(blockID))));
        return Registry.register(Registries.BLOCK, Newworld.id(blockID), block);
    }


    public static final Woodset FIR = new Woodset(Newworld.id("fir"), MapColor.DEEPSLATE_GRAY, MapColor.SPRUCE_BROWN, BlockSoundGroup.AZALEA_LEAVES);

    public static final Block FIR_SAPLING = createBlockWithItem("fir_sapling", settings -> new SaplingBlock(NWSaplingGenerators.FIR, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_FIR_SAPLING = createBlockWithoutItem("potted_fir_sapling", settings -> new FlowerPotBlock(NWBlocks.FIR_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING));

    public static final Block LOAM = createBlockWithItem("loam", AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2f, 3f).sounds(BlockSoundGroup.PACKED_MUD));
    public static final Block LOAM_STAIRS = createBlockWithItem("loam_stairs", settings -> new StairsBlock(LOAM.getDefaultState(), settings), AbstractBlock.Settings.copy(LOAM));
    public static final Block LOAM_SLAB = createBlockWithItem("loam_slab", SlabBlock::new, AbstractBlock.Settings.copy(LOAM));
    public static final Block LOAM_WALL = createBlockWithItem("loam_wall", WallBlock::new, AbstractBlock.Settings.copy(LOAM));

    public static final Block LOAM_BRICKS = createBlockWithItem("loam_bricks", AbstractBlock.Settings.copy(LOAM).sounds(BlockSoundGroup.MUD_BRICKS));
    public static final Block LOAM_BRICK_STAIRS = createBlockWithItem("loam_brick_stairs", settings -> new StairsBlock(LOAM_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(LOAM_BRICKS));
    public static final Block LOAM_BRICK_SLAB = createBlockWithItem("loam_brick_slab", SlabBlock::new, AbstractBlock.Settings.copy(LOAM_BRICKS));
    public static final Block LOAM_BRICK_WALL = createBlockWithItem("loam_brick_wall", WallBlock::new, AbstractBlock.Settings.copy(LOAM_BRICKS));

    public static final Block LOAM_TILES = createBlockWithItem("loam_tiles", AbstractBlock.Settings.copy(LOAM).sounds(BlockSoundGroup.MUD_BRICKS));
    public static final Block LOAM_TILE_STAIRS = createBlockWithItem("loam_tile_stairs", settings -> new StairsBlock(LOAM_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(LOAM_TILES));
    public static final Block LOAM_TILE_SLAB = createBlockWithItem("loam_tile_slab", SlabBlock::new, AbstractBlock.Settings.copy(LOAM_TILES));
    public static final Block LOAM_TILE_WALL = createBlockWithItem("loam_tile_wall", WallBlock::new, AbstractBlock.Settings.copy(LOAM_TILES));

    public static final Block TOMBSTONE = createBlockWithoutItem("tombstone", TombstoneBlock::new, AbstractBlock.Settings.create().mapColor(Blocks.DEEPSLATE.getDefaultMapColor()).strength(0.7f, 1200).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.POLISHED_DEEPSLATE).suffocates((state, world, pos) -> false).nonOpaque());

    public static final Block MOSS_SPROUTS = createBlockWithItem("moss_sprouts", ShortPlantBlock::new, AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).sounds(BlockSoundGroup.MOSS_CARPET).mapColor(Blocks.MOSS_BLOCK.getDefaultMapColor()));
    /*
        Experimental blocks
     */
    public static final Block POTTED_POINTED_DRIPSTONE = createBlockWithoutItem("potted_pointed_dripstone", settings -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING));

    public static void init(){
        Woodset.addToBuildingTab(Items.SPRUCE_BUTTON, FIR);
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LEAVES, FIR.getLeaves());
            entries.addAfter(Items.SPRUCE_SAPLING, FIR_SAPLING);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(Items.MUD, LOAM));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.MUD_BRICK_WALL, LOAM, LOAM_STAIRS, LOAM_WALL,
                    LOAM_SLAB, LOAM_BRICKS, LOAM_BRICK_STAIRS, LOAM_BRICK_WALL,
                    LOAM_BRICK_SLAB, LOAM_TILES, LOAM_TILE_STAIRS, LOAM_TILE_SLAB, LOAM_TILE_WALL
            );
        });
    }
}
