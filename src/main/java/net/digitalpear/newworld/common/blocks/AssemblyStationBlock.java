package net.digitalpear.newworld.common.blocks;

import net.digitalpear.newworld.common.entities.automaton.AutomatonEntity;
import net.digitalpear.newworld.init.NWEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AssemblyStationBlock extends Block {
    public static final BooleanProperty FAULTY = BooleanProperty.of("faulty");

    public AssemblyStationBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FAULTY, false));
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) {
            return;
        }
        if (world.isReceivingRedstonePower(pos)) {
            world.scheduleBlockTick(pos, this, 4);
        }
    }
    public boolean isAcceptableStack(ItemStack stack){
        return stack.isOf(Items.IRON_BLOCK);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isAcceptableStack(player.getStackInHand(hand)) && state.get(FAULTY)){
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE.value(), SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(pos, state);
            world.setBlockState(pos, state.with(FAULTY, false));
            player.swingHand(hand);
            if (!player.isCreative()){
                player.getStackInHand(hand).decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        else{
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos) && !state.get(FAULTY)) {
            assembleAutomaton(world, pos.up());
            world.setBlockState(pos, state.with(FAULTY, true));
        }
    }

    public void assembleAutomaton(World world, BlockPos blockPos){
        AutomatonEntity automatonEntity = new AutomatonEntity(NWEntityTypes.AUTOMATON, world);
        automatonEntity.setPos(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
        world.spawnEntity(automatonEntity);
        world.addBlockBreakParticles(blockPos, Blocks.ANVIL.getDefaultState());
        world.playSound(null, blockPos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FAULTY);
    }
    
}
