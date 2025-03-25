package net.digitalpear.newworld.mixin;


import net.digitalpear.newworld.init.NWBlockEntityTypes;
import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.NWStats;
import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.minecraft.entity.EntityEquipment;
import net.minecraft.entity.EquipmentSlot;
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

@Mixin(PlayerInventory.class)
public abstract class PlayerDeathMixin {
    @Shadow @Final public PlayerEntity player;


    @Shadow @Final private DefaultedList<ItemStack> main;
    @Shadow @Final private EntityEquipment equipment;

    @Inject(method = "dropAll", at = @At("HEAD"))
    private void method(CallbackInfo ci){
        World world = player.getWorld();
        BlockPos pos = getValidPos(world, player.getBlockPos());

        if (pos != null && hasTombstoneInInventory()) {
            if (!world.getBlockState(pos).isOf(NWBlocks.TOMBSTONE)) {
                world.playSound(player, pos, world.getBlockState(pos).getSoundGroup().getBreakSound(), SoundCategory.BLOCKS);
                world.setBlockState(pos, NWBlocks.TOMBSTONE.getDefaultState().with(Properties.CRACKED, true));
            }
            world.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE).ifPresent(tombstoneBlockEntity -> {
                player.increaseStat(NWStats.TOMBSTONE_ACTIVATION, 1);

                boolean decrementedTombstone = false;


                    for (ItemStack itemStack : main) {
                /*
                    Remove a single tombstone from the player's inventory.
                 */
                        if (itemStack.isOf(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone) {
                        itemStack.decrement(1);
                        decrementedTombstone = true;
                    }
                    tombstoneBlockEntity.placeOrDropStack(itemStack.copyAndEmpty());
                }
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack stack = equipment.get(slot).copyAndEmpty();
                    if (!stack.isEmpty()) {
                        tombstoneBlockEntity.placeOrDropStack(stack.copyAndEmpty());
                    }
                }

            });

        }
    }

    @Unique
    private boolean hasTombstoneInInventory(){
        return main.stream().anyMatch(itemStack -> itemStack.isOf(NWBlocks.TOMBSTONE.asItem()));
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
