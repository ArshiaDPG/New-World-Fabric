package net.digitalpear.newworld.common.entities.automaton;

import net.digitalpear.newworld.init.NWEntityTypes;
import net.digitalpear.newworld.init.data.tags.NWEntityTypeTags;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AutomatonEntity extends HostileEntity {
    private static final TrackedData<Boolean> RUSTED = DataTracker.registerData(AutomatonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public AutomatonEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AutomatonAttackGoal(this, 1.0, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, GolemEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, false));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(RUSTED, false);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        int disabledChance = world.getDifficulty() == Difficulty.PEACEFUL ? 10 : 3 - world.getDifficulty().getId();
        if (random.nextInt() <= disabledChance && spawnReason == SpawnReason.NATURAL){
            deactivate(this);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public void tickMovement() {
        if (!this.isRusted() && this.getWorld().getDifficulty() == Difficulty.PEACEFUL){
            deactivate(this);
        }
        super.tickMovement();
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return false;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if (isRusted()){
            super.onDeath(damageSource);
        }
        else{
            AutomatonEntity automatonEntity = new AutomatonEntity(NWEntityTypes.AUTOMATON, this.getWorld());
            deactivate(automatonEntity);

            this.remove(RemovalReason.DISCARDED);
            this.getWorld().spawnEntity(automatonEntity);
        }
    }

    public void deactivate(AutomatonEntity automatonEntity){
        automatonEntity.setRusted(true);
        automatonEntity.copyPositionAndRotation(this);
        automatonEntity.setHealth(7);
    }

    public static DefaultAttributeContainer.Builder createAutomatonAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 40)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 12)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.5);
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("rusted", isRusted());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setRusted(nbt.getBoolean("rusted"));
    }

    public boolean isRusted() {
        return this.dataTracker.get(RUSTED);
    }
    public void setRusted(boolean value) {
        this.dataTracker.set(RUSTED, value);
    }

    @Override
    public boolean isAiDisabled() {
        return isRusted();
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return isRusted() ? SoundEvents.ENTITY_IRON_GOLEM_DAMAGE : SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 120;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Override
    public boolean canTarget(EntityType<?> type) {
        return !type.isIn(NWEntityTypeTags.AUTOMATONS_WILL_IGNORE);
    }

    public class AutomatonAttackGoal extends MeleeAttackGoal {
        private final AutomatonEntity robot;
        private int ticks;

        public AutomatonAttackGoal(AutomatonEntity robot, double speed, boolean pauseWhenMobIdle) {
            super(robot, speed, pauseWhenMobIdle);
            this.robot = robot;
        }

        @Override
        public void start() {
            super.start();
            this.ticks = 0;
        }

        @Override
        public void stop() {
            super.stop();
            this.robot.setAttacking(false);
        }

        @Override
        public void tick() {
            super.tick();
            ++this.ticks;
            this.robot.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
        }
    }

}
