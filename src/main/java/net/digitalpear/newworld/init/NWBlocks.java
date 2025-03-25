package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.blocks.MossSproutsBlock;
import net.digitalpear.newworld.common.blocks.TombstoneBlock;
import net.digitalpear.newworld.init.data.StoneSet;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.worldgen.NWSaplingGenerators;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
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

    private static RegistryKey<Block> keyOf(String block){
        return RegistryKey.of(RegistryKeys.BLOCK, Newworld.id(block));
    }
    private static Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = createBlockWithoutItem(blockID, factory, settings);
        Items.register(block);
        return block;
    }
    private static Block createBlockWithoutItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        return Blocks.register(keyOf(blockID), factory, settings);
    }

    public static final Woodset FIR = new Woodset(Newworld.id("fir"), MapColor.DEEPSLATE_GRAY, MapColor.SPRUCE_BROWN, new Woodset.Settings.Builder().leaveSoundGroup(BlockSoundGroup.AZALEA_LEAVES));

    public static final Block FIR_SAPLING = createBlockWithItem("fir_sapling", settings -> new SaplingBlock(NWSaplingGenerators.FIR, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_FIR_SAPLING = createBlockWithoutItem("potted_fir_sapling", settings -> new FlowerPotBlock(NWBlocks.FIR_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING));

    public static final StoneSet LOAM = new StoneSet(Newworld.id("loam"), AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2f, 3f).sounds(BlockSoundGroup.PACKED_MUD))
            .stairs().slab().wall();
    public static final StoneSet LOAM_BRICKS = new StoneSet(Newworld.id("loam_brick"), AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2f, 3f).sounds(BlockSoundGroup.MUD_BRICKS))
            .stairs().slab().wall();

    public static final StoneSet LOAM_TILES = new StoneSet(Newworld.id("loam_tile"), AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2f, 3f).sounds(BlockSoundGroup.MUD_BRICKS))
            .stairs().slab().wall();

    public static final Block TOMBSTONE = createBlockWithoutItem("tombstone", TombstoneBlock::new, AbstractBlock.Settings.create().mapColor(Blocks.DEEPSLATE.getDefaultMapColor()).strength(0.7f, 1200).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.POLISHED_DEEPSLATE).suffocates((state, world, pos) -> false).nonOpaque());

    public static final Block MOSS_SPROUTS = createBlockWithItem("moss_sprouts", MossSproutsBlock::new, AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).sounds(BlockSoundGroup.MOSS_CARPET).mapColor(Blocks.MOSS_BLOCK.getDefaultMapColor()));

    public static final Block POTTED_POINTED_DRIPSTONE = createBlockWithoutItem("potted_pointed_dripstone", settings -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING));

    public static void init(){
        Woodset.addToBuildingTab(Items.SPRUCE_BUTTON, FIR);
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LEAVES, FIR.getLeaves());
            entries.addAfter(Items.SPRUCE_SAPLING, FIR_SAPLING);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.MUD, LOAM.getBase());
            entries.addAfter(Items.MOSS_CARPET, MOSS_SPROUTS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.MUD_BRICK_WALL, LOAM.getBase(), LOAM.getStairs(), LOAM.getWall(),
                    LOAM.getSlab(), LOAM_BRICKS.getBase(), LOAM_BRICKS.getStairs(), LOAM_BRICKS.getWall(),
                    LOAM_BRICKS.getSlab(), LOAM_TILES.getBase(), LOAM_TILES.getStairs(), LOAM_TILES.getWall(), LOAM_TILES.getSlab()
            );
        });
    }
}
