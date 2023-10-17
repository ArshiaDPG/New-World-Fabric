package net.digitalpear.newworld.common.blocks.entity;

import net.digitalpear.newworld.common.blocks.TombstoneBlock;
import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TombstoneBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;
    private final ViewerCountManager stateManager;

    public TombstoneBlockEntity(BlockPos pos, BlockState state) {
        super(NWBlockEntityTypes.TOMBSTONE, pos, state);
        this.inventory = DefaultedList.ofSize(36, ItemStack.EMPTY);
        this.stateManager = new ViewerCountManager() {
            protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
                TombstoneBlockEntity.this.playSound(state, SoundEvents.BLOCK_GRINDSTONE_USE);
            }

            protected void onContainerClose(World world, BlockPos pos, BlockState state) {
                TombstoneBlockEntity.this.playSound(state, SoundEvents.BLOCK_GRINDSTONE_USE);
            }

            protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            }

            protected boolean isPlayerViewing(PlayerEntity player) {
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    Inventory inventory = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                    return inventory == TombstoneBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
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
        return 36;
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



    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }

    }

    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }

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

    public void tick() {
        if (!this.removed) {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }



    void playSound(BlockState state, SoundEvent soundEvent) {
        double d = (double)this.pos.getX() + 0.5;
        double e = (double)this.pos.getY() + 0.5;
        double f = (double)this.pos.getZ() + 0.5;
        this.world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
    }
}
