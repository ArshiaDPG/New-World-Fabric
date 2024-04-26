package net.digitalpear.newworld.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public enum NWToolMaterials implements ToolMaterial {
    ANCIENT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3086, 9.0F, 7.0F, 10, Ingredient.ofItems(Items.FLINT));

    private TagKey<Block> inverseTag;

    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;


    NWToolMaterials(TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantmentValue, Ingredient repairIngredient) {
        this.inverseTag = inverseTag;
        this.uses = durability;
        this.speed = miningSpeed;
        this.damage = attackDamage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurability() {
        return this.uses;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.speed;
    }

    @Override
    public float getAttackDamage() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
