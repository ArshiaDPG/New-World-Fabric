package net.digitalpear.newworld.mixin;


import net.digitalpear.newworld.common.items.MattockItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuckEnchantment.class)
public abstract class FortuneEnchantmentMixin extends Enchantment {

    protected FortuneEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if(stack.getItem() instanceof MattockItem) {
            return false;
        } else {
            return this.target.isAcceptableItem(stack.getItem());
        }
    }
}