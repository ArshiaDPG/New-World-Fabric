package net.digitalpear.newworld.common.blocks;

import com.google.common.collect.ImmutableMap;
import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TombstoneBlock extends BlockWithEntity implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty CRACKED = Properties.CRACKED;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockFace> FACE = Properties.BLOCK_FACE;

    protected static final Map<Direction, VoxelShape> directionToShapeMap = ImmutableMap.of(
            Direction.DOWN, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
            Direction.UP, Block.createCuboidShape(0.0, 7.0, 0.0, 16.0, 16.0, 16.0),
            Direction.SOUTH, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 9.0),
            Direction.NORTH, Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 16.0),
            Direction.EAST, Block.createCuboidShape(0.0, 0.0, 0.0, 9.0, 16.0, 16.0),
            Direction.WEST, Block.createCuboidShape(7.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    );

    public TombstoneBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(FACE, BlockFace.FLOOR).with(FACING, Direction.NORTH).with(CRACKED, false));
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(CRACKED) ? BlockSoundGroup.DEEPSLATE_TILES : super.getSoundGroup(state);
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof Inventory) {
                ItemScatterer.spawn(world, pos, (Inventory)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACE) == BlockFace.FLOOR){
            return directionToShapeMap.get(Direction.DOWN);
        }
        else if (state.get(FACE) == BlockFace.CEILING){
            return directionToShapeMap.get(Direction.UP);
        }
        return directionToShapeMap.get(state.get(FACING));
    }


    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        if(state.get(CRACKED)){
            return Collections.emptyList();
        }
        return super.getDroppedStacks(state, builder);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        FluidState fluidState = world.getFluidState(blockPos);
        Direction direction = ctx.getSide();
        BlockFace face = getFaceFromDirection(direction);

        return super.getPlacementState(ctx)
                .with(WATERLOGGED, fluidState.isOf(Fluids.WATER)).with(FACE, face)
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public BlockFace getFaceFromDirection(Direction direction){
        if (direction == Direction.UP){
            return BlockFace.FLOOR;
        }
        else if (direction == Direction.DOWN){
            return BlockFace.CEILING;
        }
        else{
            return BlockFace.WALL;
        }
    }


    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACE, FACING, CRACKED);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TombstoneBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
