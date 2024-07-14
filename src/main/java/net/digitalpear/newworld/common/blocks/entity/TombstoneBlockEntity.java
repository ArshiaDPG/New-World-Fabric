package net.digitalpear.newworld.common.blocks.entity;

import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TombstoneBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;


    public TombstoneBlockEntity(BlockPos pos, BlockState state) {
        super(NWBlockEntityTypes.TOMBSTONE, pos, state);
        this.inventory = DefaultedList.ofSize(45, ItemStack.EMPTY);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (!this.writeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, registryLookup);
        }
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.readLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory, registryLookup);
        }
        super.readNbt(nbt, registryLookup);
    }

    public int size() {
        return 45;
    }

    public DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }


    protected Text getContainerName() {
        return Text.translatable("container.tombstone");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    public int getEmptySlot() {
        for(int i = 0; i < size(); ++i) {
            if ((inventory.get(i)).isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    public int getCompatibleSlot(ItemStack stack) {
        for(int i = 0; i < size(); ++i) {
            ItemStack currentStack = inventory.get(i);
            if (currentStack.isEmpty() || (ItemStack.areItemsAndComponentsEqual(currentStack, stack) && currentStack.getCount() < currentStack.getMaxCount())) {
                return i;
            }
        }
        return -1;
    }


    public static boolean contains(List<DefaultedList<ItemStack>> inv, Item item) {
        for (DefaultedList<ItemStack> itemStacks : inv) {
            if (itemStacks.stream().anyMatch(itemStack -> itemStack.isOf(item))){
                return true;
            }
        }
        return false;
    }

    void playSound(SoundEvent soundEvent) {
        double d = (double)this.pos.getX() + 0.5;
        double e = (double)this.pos.getY() + 0.5;
        double f = (double)this.pos.getZ() + 0.5;
        this.world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
    }
}
