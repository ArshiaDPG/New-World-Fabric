package net.digitalpear.newworld.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.Objects;

public class JebBookItem extends BookItem {
    public JebBookItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!Objects.equals(Registries.ENTITY_TYPE.getId(target.getType()).getNamespace(), "minecraft")){
            target.damage(target.getDamageSources().inWall(), (float) target.getAttributes().getValue(EntityAttributes.GENERIC_MAX_HEALTH));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
