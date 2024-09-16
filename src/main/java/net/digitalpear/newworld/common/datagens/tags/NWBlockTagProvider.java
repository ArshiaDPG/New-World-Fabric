package net.digitalpear.newworld.common.datagens.tags;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.Woodset;
import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

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
        addMineableAxe(NWBlocks.FIR, NWBlockTags.FIR_LOGS);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(NWBlocks.TOMBSTONE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL,
                NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL,
                NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL
        );

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(NWBlockTags.FIR_LOGS);

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
    private void addMineableAxe(Woodset woodset, TagKey<Block> logs){
        woodset.getRegisteredBlocksList().forEach(block -> {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
        });
        getOrCreateTagBuilder(logs).add(woodset.getLog()).add(woodset.getStrippedLog());
        if (!woodset.isBambooVariant()){
            getOrCreateTagBuilder(logs).add(woodset.getWood()).add(woodset.getStrippedWood());
        }
        if (woodset.isOverworldTreeWood()){
            getOrCreateTagBuilder(BlockTags.LEAVES).add(woodset.getLeaves());
        }
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).forceAddTag(logs);
        getOrCreateTagBuilder(BlockTags.PLANKS).add(woodset.getPlanks());
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(woodset.getStairs());
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(woodset.getSlab());
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(woodset.getFence());
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(woodset.getFenceGate());
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(woodset.getDoor());
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(woodset.getTrapDoor());
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodset.getPressurePlate());
        getOrCreateTagBuilder(BlockTags.BUTTONS).add(woodset.getButton());
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(woodset.getWallSign());
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(woodset.getSign());
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(woodset.getHangingSign());
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(woodset.getWallHangingSign());
        getOrCreateTagBuilder(BlockTags.ALL_HANGING_SIGNS).add(woodset.getHangingSign()).add(woodset.getWallHangingSign());
    }

}
