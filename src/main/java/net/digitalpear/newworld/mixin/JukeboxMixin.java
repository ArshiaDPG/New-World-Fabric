package net.digitalpear.newworld.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.jukebox.JukeboxManager;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JukeboxManager.class)
public abstract class JukeboxMixin {

    @Shadow @Final private BlockPos pos;

    @Shadow @Nullable private RegistryEntry<JukeboxSong> song;

    @Inject(method = "tick", at = @At("RETURN"))
    private void placeParticles(WorldAccess world, BlockState state, CallbackInfo ci){
        Random random = world.getRandom();
        if (song != null && random.nextFloat() < 0.4 && world instanceof ServerWorld) {
            int x = pos.getX();
            int y = pos.getY();
            int k = pos.getZ();
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            int radius =  song != null ? song.value().comparatorOutput() : 4;
            int height = radius/3;

            for(int index = 0; index < random.nextBetweenExclusive(1, 6); ++index) {
                mutable.set(x + MathHelper.nextInt(random, -radius, radius), y + random.nextInt(height), k + MathHelper.nextInt(random, -radius, radius));
                BlockState blockState = world.getBlockState(mutable);
                float f = (float)world.getRandom().nextInt(4) / 24.0F;
                if (!blockState.isFullCube(world, mutable)) {
                    ((ServerWorld)world).spawnParticles(ParticleTypes.NOTE, mutable.getX() + random.nextFloat(), mutable.getY() + random.nextFloat(), mutable.getZ() + random.nextFloat(), 0, f, 0.0, 0.0, 1.0);
                }
            }
        }
    }
}
