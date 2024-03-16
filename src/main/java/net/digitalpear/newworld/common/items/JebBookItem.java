package net.digitalpear.newworld.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JebBookItem extends BookItem {
    public JebBookItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (Registries.ENTITY_TYPE.getId(target.getType()).getNamespace() != "minecraft"){
            target.damage(target.getDamageSources().inWall(), (float) target.getAttributes().getValue(EntityAttributes.GENERIC_MAX_HEALTH));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getTranslationKey() + ".attack_desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(this.getTranslationKey() + ".mining_desc").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (Registries.BLOCK.getId(state.getBlock()).getNamespace() != "minecraft"){
            return 150.0F;
        }
        return 1.0F;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }
}
