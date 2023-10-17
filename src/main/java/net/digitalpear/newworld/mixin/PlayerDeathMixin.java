package net.digitalpear.newworld.mixin;


import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
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
        boolean canProceed = false;
        World world = player.getWorld();
        BlockPos pos = player.getBlockPos();
        for (DefaultedList<ItemStack> itemStacks : combinedInventory) {
            if (itemStacks.stream().anyMatch(stack -> stack.isOf(NWBlocks.TOMBSTONE.asItem()))){
                canProceed = true;
                break;
            }
        }

        if (!canProceed) {
            ci.cancel();
        }
        else{
            if (!world.getBlockState(pos).isOf(NWBlocks.TOMBSTONE)){
                world.setBlockState(pos, NWBlocks.TOMBSTONE.getDefaultState());
            }
            world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent(tombstoneBlockEntity -> {
                if (tombstoneBlockEntity.getInvStackList().stream().noneMatch(stack -> stack.isEmpty())){
                    ci.cancel();
                }
                boolean decrementedTombstone = false;
                int tombstoneIndex = 0;
                for (DefaultedList<ItemStack> itemStacks : combinedInventory) {


                    List<ItemStack> list = itemStacks;
                    for(int i = 0; i < list.size(); ++i) {
                        ItemStack itemStack = list.get(i);
                        if (!itemStack.isEmpty()) {
                            if (tombstoneIndex < tombstoneBlockEntity.getInvStackList().size()){
                                if (itemStack.isOf(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone){
                                    itemStack.decrement(1);
                                    decrementedTombstone = true;
                                }
                                else if (tombstoneBlockEntity.getInvStackList().get(tombstoneIndex).isEmpty()){
                                    tombstoneBlockEntity.setStack(tombstoneIndex, itemStack);
                                    tombstoneIndex++;
                                }
                            }
                            else{
                                this.player.dropItem(itemStack, true, false);
                            }
                            list.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            });
        }
    }
}
