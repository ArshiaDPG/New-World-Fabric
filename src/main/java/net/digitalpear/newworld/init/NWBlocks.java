package net.digitalpear.newworld.init;

import gg.moonflower.pollen.api.block.PollinatedStandingSignBlock;
import gg.moonflower.pollen.api.block.PollinatedWallSignBlock;
import net.digitalpear.newworld.NewWorld;
import net.digitalpear.newworld.common.blocks.NWSignTypes;
import net.digitalpear.newworld.common.worldgen.tree.FirSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;


@SuppressWarnings("unused")
public class NWBlocks {
    public static MapColor TOP_COLOR = MapColor.SPRUCE_BROWN;
    public static MapColor SIDE_COLOR = MapColor.DEEPSLATE_GRAY;




    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(NewWorld.MOD_ID, blockID), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Block createBlockWithItem(String blockID, Block block, ItemGroup group){
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(NewWorld.MOD_ID, blockID), block);
    }

    private static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(NewWorld.MOD_ID, blockID), block);
    }

    private static PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }

    private static LeavesBlock createLeavesBlock(BlockSoundGroup soundGroup) {
        return new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(soundGroup).nonOpaque().allowsSpawning(NWBlocks::canSpawnOnLeaves).suffocates(NWBlocks::never).blockVision(NWBlocks::never));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockView blockView, BlockPos pos) {
        return false;
    }

    private static AbstractBlock.Settings createWoodBlock(Block base,MapColor mapColor) {
        return AbstractBlock.Settings.copy(base).mapColor(mapColor);
    }

    public static final Block FIR_LOG = createBlockWithItem("fir_log", createLogBlock(TOP_COLOR, SIDE_COLOR), ItemGroup.BUILDING_BLOCKS);
    public static final Block FIR_WOOD = createBlockWithItem("fir_wood", createLogBlock(SIDE_COLOR, SIDE_COLOR), ItemGroup.BUILDING_BLOCKS);
    public static final Block STRIPPED_FIR_LOG = createBlockWithItem("stripped_fir_log", createLogBlock(TOP_COLOR, TOP_COLOR), ItemGroup.BUILDING_BLOCKS);
    public static final Block STRIPPED_FIR_WOOD = createBlockWithItem("stripped_fir_wood", createLogBlock(TOP_COLOR, TOP_COLOR), ItemGroup.BUILDING_BLOCKS);

    public static final Block FIR_PLANKS = createBlockWithItem("fir_planks", new Block(createWoodBlock(Blocks.OAK_PLANKS, TOP_COLOR)), ItemGroup.BUILDING_BLOCKS);
    public static final Block FIR_STAIRS = createBlockWithItem("fir_stairs", new StairsBlock(FIR_PLANKS.getDefaultState(), createWoodBlock(Blocks.OAK_PLANKS, TOP_COLOR)), ItemGroup.BUILDING_BLOCKS);
    public static final Block FIR_SLAB = createBlockWithItem("fir_slab", new SlabBlock(createWoodBlock(Blocks.OAK_PLANKS, TOP_COLOR)), ItemGroup.BUILDING_BLOCKS);

    public static final Block FIR_DOOR = createBlockWithItem("fir_door", new DoorBlock(createWoodBlock(Blocks.OAK_DOOR, TOP_COLOR)), ItemGroup.REDSTONE);
    public static final Block FIR_TRAPDOOR = createBlockWithItem("fir_trapdoor", new TrapdoorBlock(createWoodBlock(Blocks.OAK_TRAPDOOR, TOP_COLOR)), ItemGroup.REDSTONE);

    public static final Block FIR_BUTTON = createBlockWithItem("fir_button", new WoodenButtonBlock(createWoodBlock(Blocks.OAK_BUTTON, SIDE_COLOR)), ItemGroup.REDSTONE);
    public static final Block FIR_PRESSURE_PLATE = createBlockWithItem("fir_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, createWoodBlock(Blocks.OAK_BUTTON, SIDE_COLOR)), ItemGroup.REDSTONE);

    public static final Block FIR_FENCE = createBlockWithItem("fir_fence", new FenceBlock(createWoodBlock(Blocks.OAK_FENCE, TOP_COLOR)), ItemGroup.DECORATIONS);
    public static final Block FIR_FENCE_GATE = createBlockWithItem("fir_fence_gate", new FenceGateBlock(createWoodBlock(Blocks.OAK_FENCE, TOP_COLOR)), ItemGroup.REDSTONE);

    public static final Block FIR_LEAVES = createBlockWithItem("fir_leaves", createLeavesBlock(BlockSoundGroup.GRASS), ItemGroup.DECORATIONS);
    public static final Block FIR_SAPLING = createBlockWithItem("fir_sapling", new SaplingBlock(new FirSaplingGenerator(),FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);

    public static final Block FIR_WALL_SIGN_BLOCK = createBlockWithoutItem("fir_wall_sign", new PollinatedWallSignBlock(createWoodBlock(Blocks.OAK_WALL_SIGN, TOP_COLOR), NWSignTypes.FIR));
    public static final Block FIR_SIGN_BLOCK = createBlockWithoutItem("fir_sign", new PollinatedStandingSignBlock(createWoodBlock(Blocks.OAK_SIGN, TOP_COLOR), NWSignTypes.FIR));

    public static final Block POTTED_FIR_SAPLING = createBlockWithoutItem("potted_fir_sapling", new FlowerPotBlock(NWBlocks.FIR_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ACACIA_SAPLING)));

    public static Block FIR_BOOKSHELF;


    public static void init(){
        if (FabricLoader.getInstance().isModLoaded("charm")){
            FIR_BOOKSHELF = createBlockWithItem("fir_bookshelf", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD).mapColor(TOP_COLOR)), ItemGroup.DECORATIONS);
        }

    }
}
