package net.digitalpear.newworld.common.items;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Objects;

public class JebBookItem extends Item {
    public JebBookItem(Settings settings) {
        super(settings.maxCount(1));
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!Objects.equals(Registries.ENTITY_TYPE.getId(target.getType()).getNamespace(), "minecraft")){
            target.damage(target.getDamageSources().inWall(), (float) target.getAttributes().getValue(EntityAttributes.MAX_HEALTH));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return false;
    }
}
