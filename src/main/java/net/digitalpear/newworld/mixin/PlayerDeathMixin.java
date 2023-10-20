package net.digitalpear.newworld.mixin;


import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerDeathMixin {
    @Shadow @Final public PlayerEntity player;

    @Shadow @Final private List<DefaultedList<ItemStack>> combinedInventory;

    @Inject(method = "dropAll", cancellable = true, at = @At("HEAD"))
    private void method(CallbackInfo ci){
        World world = player.getWorld();
        BlockPos pos = getValidPos(world, player.getBlockPos());

        if (pos != null && hasTombstoneInInventory()) {
            if (!world.getBlockState(pos).isOf(NWBlocks.TOMBSTONE)){
                world.setBlockState(pos, NWBlocks.TOMBSTONE.getDefaultState());
            }
            world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent(tombstoneBlockEntity -> {
                if (!tombstoneBlockEntity.getInvStackList().stream().noneMatch(ItemStack::isEmpty)) {
                    boolean decrementedTombstone = false;
                    int tombstoneIndex = 0;
                    for (DefaultedList<ItemStack> itemStacks : combinedInventory) {


                        List<ItemStack> list = itemStacks;
                        for (int i = 0; i < list.size(); ++i) {
                            ItemStack itemStack = list.get(i);
                            if (!itemStack.isEmpty()) {
                                if (tombstoneIndex < tombstoneBlockEntity.getInvStackList().size()) {
                                    if (itemStack.isOf(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone) {
                                        itemStack.decrement(1);
                                        decrementedTombstone = true;
                                    } else if (tombstoneBlockEntity.getInvStackList().get(tombstoneIndex).isEmpty()) {
                                        tombstoneBlockEntity.setStack(tombstoneIndex, itemStack);
                                        tombstoneIndex++;
                                    }
                                } else {
                                    this.player.dropItem(itemStack, true, false);
                                }
                                list.set(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            });

        }
    }

    private boolean hasTombstoneInInventory(){
        for (DefaultedList<ItemStack> itemStacks : combinedInventory) {
            if (itemStacks.stream().anyMatch(stack -> stack.isOf(NWBlocks.TOMBSTONE.asItem()))){
                return true;
            }
        }
        return false;

    }
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
