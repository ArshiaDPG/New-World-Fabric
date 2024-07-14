package net.digitalpear.newworld.mixin;


import net.digitalpear.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.NWStats;
import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerInventory.class)
public abstract class PlayerDeathMixin {
    @Shadow @Final public PlayerEntity player;

    @Shadow @Final private List<DefaultedList<ItemStack>> combinedInventory;

    @Inject(method = "dropAll", at = @At("HEAD"))
    private void method(CallbackInfo ci){
        World world = player.getWorld();
        BlockPos pos = getValidPos(world, player.getBlockPos());

        if (pos != null && hasTombstoneInInventory()) {
            if (!world.getBlockState(pos).isOf(NWBlocks.TOMBSTONE)){
                world.playSound(player, pos, world.getBlockState(pos).getSoundGroup().getBreakSound(), SoundCategory.BLOCKS);
                world.setBlockState(pos, NWBlocks.TOMBSTONE.getDefaultState().with(Properties.CRACKED, true));
            }
            world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent(tombstoneBlockEntity -> {
                player.increaseStat(NWStats.TOMBSTONE_ACTIVATION, 1);

                boolean decrementedTombstone = false;

                for (DefaultedList<ItemStack> itemStacks : combinedInventory) {
                    for (int i = 0; i < itemStacks.size(); ++i) {
                        ItemStack itemStack = itemStacks.get(i);
                        /*
                            Remove a single tombstone from the player's inventory.
                         */
                        if (itemStack.isOf(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone) {
                            itemStack.decrement(1);
                            decrementedTombstone = true;
                        }

                        placeOrDropStack(tombstoneBlockEntity, itemStack);
                        itemStacks.set(i, ItemStack.EMPTY);
                    }
                }

            });
        }
    }

    @Unique
    private void placeOrDropStack(TombstoneBlockEntity tombstoneBlockEntity, ItemStack currentStack){
        int compatibleSlot = tombstoneBlockEntity.getCompatibleSlot(currentStack);

        if (!currentStack.isEmpty() && compatibleSlot >= 0){
            ItemStack tombstoneStack = tombstoneBlockEntity.getStack(compatibleSlot);
            /*
                If slot is empty.
             */
            if (tombstoneStack.isEmpty()){
                tombstoneBlockEntity.setStack(compatibleSlot, currentStack);
            }
            /*
                If slot is not empty but is compatible.
             */
            else if (ItemStack.areItemsAndComponentsEqual(tombstoneStack, currentStack)){
                if (tombstoneStack.getCount() + currentStack.getCount() > tombstoneStack.getMaxCount()){
                    tombstoneStack.setCount(tombstoneStack.getMaxCount());
                    currentStack.setCount(tombstoneStack.getCount() + currentStack.getCount() - tombstoneStack.getMaxCount());

                    /*
                        If there is more left over then try to place it in another slot.
                     */
                    placeOrDropStack(tombstoneBlockEntity, currentStack.copyWithCount(tombstoneStack.getCount() + currentStack.getCount() - tombstoneStack.getMaxCount()));
                    currentStack.copyAndEmpty();

                }
                else{
                    tombstoneStack.setCount(tombstoneStack.getCount() + currentStack.getCount());
                }
            }
        }
        else{
            this.player.dropStack(currentStack);
        }
    }

    @Unique
    private boolean hasTombstoneInInventory(){
        for (DefaultedList<ItemStack> itemStacks : combinedInventory) {
            if (itemStacks.stream().anyMatch(stack -> stack.isOf(NWBlocks.TOMBSTONE.asItem()))){
                return true;
            }
        }
        return false;
    }
    @Unique
    private BlockPos getValidPos(World world, BlockPos pos){
        BlockPos finalPos = pos;
        while (finalPos.getY() < pos.getY() + 6){
            if (world.getBlockState(finalPos).isIn(NWBlockTags.TOMBSTONE_REPLACEABLE) || world.getBlockState(finalPos).isAir()){
                return finalPos;
            }
            finalPos = finalPos.up();
        }
        return null;
    }
}
