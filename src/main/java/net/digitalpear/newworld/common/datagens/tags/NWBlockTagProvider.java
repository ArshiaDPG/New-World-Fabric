package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class NWBlockTagProvider extends FabricTagProvider<Block> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public NWBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .forceAddTag(NWBlockTags.FIR_LOGS)
                .add(NWBlocks.FIR_PLANKS)
                .add(NWBlocks.FIR_STAIRS)
                .add(NWBlocks.FIR_SLAB)
                .add(NWBlocks.FIR_FENCE)
                .add(NWBlocks.FIR_FENCE_GATE)
                .add(NWBlocks.FIR_DOOR)
                .add(NWBlocks.FIR_TRAPDOOR)
                .add(NWBlocks.FIR_PRESSURE_PLATE)
                .add(NWBlocks.FIR_BUTTON)
                .add(NWBlocks.FIR_SIGN)
                .add(NWBlocks.FIR_WALL_SIGN)
                .add(NWBlocks.FIR_HANGING_SIGN)
                .add(NWBlocks.FIR_HANGING_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(NWBlocks.TOMBSTONE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL,
                NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL,
                NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL
        );
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(NWBlocks.FIR_LEAVES);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(NWBlockTags.FIR_LOGS);
        getOrCreateTagBuilder(BlockTags.PLANKS).add(NWBlocks.FIR_PLANKS);
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(NWBlocks.FIR_STAIRS);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(NWBlocks.FIR_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(NWBlocks.FIR_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(NWBlocks.FIR_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(NWBlocks.FIR_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(NWBlocks.FIR_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.LEAVES).add(NWBlocks.FIR_LEAVES);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(NWBlocks.FIR_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.BUTTONS).add(NWBlocks.FIR_BUTTON);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(NWBlocks.FIR_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(NWBlocks.FIR_SIGN);
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.ALL_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGN).add(NWBlocks.FIR_HANGING_WALL_SIGN);
        getOrCreateTagBuilder(BlockTags.SAPLINGS).add(NWBlocks.FIR_SAPLING);
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(NWBlocks.POTTED_FIR_SAPLING).add(NWBlocks.POTTED_POINTED_DRIPSTONE);

        getOrCreateTagBuilder(BlockTags.STAIRS).add(NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_TILE_STAIRS);
        getOrCreateTagBuilder(BlockTags.SLABS).add(NWBlocks.LOAM_SLAB, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_TILE_SLAB);
        getOrCreateTagBuilder(BlockTags.WALLS).add(NWBlocks.LOAM_WALL, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_TILE_WALL);

        getOrCreateTagBuilder(NWBlockTags.MATTOCK_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE)
                .forceAddTag(BlockTags.HOE_MINEABLE)
                .forceAddTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE);

        getOrCreateTagBuilder(NWBlockTags.FIR_LOGS)
                .add(NWBlocks.FIR_LOG)
                .add(NWBlocks.FIR_WOOD)
                .add(NWBlocks.STRIPPED_FIR_WOOD)
                .add(NWBlocks.STRIPPED_FIR_LOG);

        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(NWBlocks.LOAM);

        getOrCreateTagBuilder(NWBlockTags.SMALL_BUSH_PLANTABLE).forceAddTag(BlockTags.DIRT).add(Blocks.MUD).add(Blocks.CLAY).forceAddTag(BlockTags.LUSH_GROUND_REPLACEABLE);

        getOrCreateTagBuilder(NWBlockTags.TOMBSTONE_REPLACEABLE)
                .forceAddTag(BlockTags.CORAL_PLANTS)
                .forceAddTag(BlockTags.SMALL_FLOWERS)
                .forceAddTag(BlockTags.LEAVES)
                .forceAddTag(BlockTags.FIRE)
                .forceAddTag(BlockTags.CROPS)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.WATER)
                .add(Blocks.SEAGRASS)
                .add(Blocks.TALL_SEAGRASS)
                .add(Blocks.CRIMSON_ROOTS)
                .add(Blocks.WARPED_ROOTS)
                .add(Blocks.HANGING_ROOTS)
        ;

    }

}
