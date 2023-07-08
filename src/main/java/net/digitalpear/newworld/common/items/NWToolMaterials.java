package net.digitalpear.newworld.common.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum NWToolMaterials implements ToolMaterial {
    ANCIENT(4, 3086, 7.0F, 7.0F, 10, Ingredient.EMPTY);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;


    NWToolMaterials(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantmentValue, Ingredient repairIngredient) {
        this.level = miningLevel;
        this.uses = durability;
        this.speed = miningSpeed;
        this.damage = attackDamage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

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
    public int getMiningLevel() {
        return this.level;
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
