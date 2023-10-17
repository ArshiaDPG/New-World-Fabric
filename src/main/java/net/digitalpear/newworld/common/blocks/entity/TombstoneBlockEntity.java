package net.digitalpear.newworld.common.blocks.entity;

import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class TombstoneBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;


    public TombstoneBlockEntity(BlockPos pos, BlockState state) {
        super(NWBlockEntityTypes.TOMBSTONE, pos, state);
        this.inventory = DefaultedList.ofSize(45, ItemStack.EMPTY);
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }

    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }

    }

    public int size() {
        return 45;
    }

    public DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    public void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    protected Text getContainerName() {
        return Text.translatable("container.tombstone");
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }


//    public void addToLootTable(ItemStack itemStack){
//        getInvStackList().stream().filter(itemStack1 -> !itemStack1.isEmpty());
//
//        for (int i = 0; i < size(); i++){
//            ItemStack stack = getInvStackList().get(i);
//            if (stack == itemStack && stack.getCount() < stack.getMaxCount()){
//                if (stack.getCount() + itemStack.getCount() > stack.getMaxCount()){
//                    stack.setCount(stack.getMaxCount());
//                    itemStack.setCount(stack.getCount() + itemStack.getCount() - stack.getMaxCount());
//                    getInvStackList().stream().findAny().ifPresent(itemStack1 -> itemStack1.isEmpty());
//                }
//                else {
//                    stack.increment(itemStack.getCount());
//                }
//            }
//        }
//    }
//    private static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, int slot, @Nullable Direction side) {
//        ItemStack itemStack = to.getStack(slot);
//        if (canInsert(to, stack, slot, side)) {
//            boolean bl = false;
//            boolean bl2 = to.isEmpty();
//            if (itemStack.isEmpty()) {
//                to.setStack(slot, stack);
//                stack = ItemStack.EMPTY;
//                bl = true;
//            } else if (canMergeItems(itemStack, stack)) {
//                int i = stack.getMaxCount() - itemStack.getCount();
//                int j = Math.min(stack.getCount(), i);
//                stack.decrement(j);
//                itemStack.increment(j);
//                bl = j > 0;
//            }
//        }
//
//        return stack;
//    }
//    private static boolean canInsert(Inventory inventory, ItemStack stack, int slot, @Nullable Direction side) {
//        if (!inventory.isValid(slot, stack)) {
//            return false;
//        } else {
//            boolean var10000;
//            if (inventory instanceof SidedInventory) {
//                SidedInventory sidedInventory = (SidedInventory)inventory;
//                if (!sidedInventory.canInsert(slot, stack, side)) {
//                    var10000 = false;
//                    return var10000;
//                }
//            }
//
//            var10000 = true;
//            return var10000;
//        }
//    }
//    private static boolean canMergeItems(ItemStack first, ItemStack second) {
//        return first.getCount() <= first.getMaxCount() && ItemStack.canCombine(first, second);
//    }


    void playSound(BlockState state, SoundEvent soundEvent) {
        double d = (double)this.pos.getX() + 0.5;
        double e = (double)this.pos.getY() + 0.5;
        double f = (double)this.pos.getZ() + 0.5;
        this.world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
    }
}
