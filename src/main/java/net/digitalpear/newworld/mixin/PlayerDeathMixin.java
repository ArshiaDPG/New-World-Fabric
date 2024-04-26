package net.digitalpear.newworld.mixin;


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

                if (tombstoneBlockEntity.getInvStackList().stream().anyMatch(ItemStack::isEmpty)) {

                    boolean decrementedTombstone = false;

                    for (DefaultedList<ItemStack> itemStacks : combinedInventory) {
                        for (int i = 0; i < ((List<ItemStack>) itemStacks).size(); ++i) {
                            ItemStack itemStack = ((List<ItemStack>) itemStacks).get(i);
                            /*
                                Remove a single tombstone from the player's inventory.
                             */
                            if (itemStack.isOf(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone) {
                                itemStack.decrement(1);
                                decrementedTombstone = true;
                            }

                            if (!itemStack.isEmpty()) {
                                int compatibleSlot = tombstoneBlockEntity.getCompatibleSlot(itemStack);
                                ItemStack tombstoneStack = tombstoneBlockEntity.getStack(compatibleSlot);

                                /*
                                    Place the stack inside the tombstone.
                                 */
                                if (compatibleSlot != -1){
                                    if (tombstoneStack.isEmpty()){
                                        tombstoneBlockEntity.setStack(compatibleSlot, itemStack);
                                    }
//                                    else if (ItemStack.canCombine(tombstoneStack, itemStack)){
//                                        if (tombstoneStack.getCount() + itemStack.getCount() > tombstoneStack.getMaxCount()){
//                                            tombstoneStack.setCount(tombstoneStack.getMaxCount());
//                                            itemStack.setCount(tombstoneStack.getCount() + itemStack.getCount() - tombstoneStack.getMaxCount());
//                                            if (tombstoneBlockEntity.getEmptySlot() != -1){
//                                                tombstoneBlockEntity.setStack(tombstoneBlockEntity.getEmptySlot(), itemStack);
//                                            }
//                                            else{
//                                                this.player.dropItem(itemStack, true, false);
//                                            }
//
//                                        }
//                                        else{
//                                            tombstoneStack.setCount(tombstoneStack.getCount() + itemStack.getCount());
//                                        }
//                                    }
                                }
                                else {
                                    this.player.dropItem(itemStack, true, false);
                                }
                                ((List<ItemStack>) itemStacks).set(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            });
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
