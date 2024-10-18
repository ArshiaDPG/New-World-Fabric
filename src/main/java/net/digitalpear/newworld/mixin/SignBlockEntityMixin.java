package net.digitalpear.newworld.mixin;

import net.digitalpear.newworld.init.data.Woodset;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public abstract class SignBlockEntityMixin {

    @Shadow public abstract BlockEntityType<?> getType();

    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supportSigns(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (Woodset.WOODSETS.isEmpty()){
            cir.cancel();
        }
        if (getType() == BlockEntityType.SIGN){
            cir.setReturnValue(Woodset.getAllSigns().stream().anyMatch(block -> block == state.getBlock()));
        } else if (getType() == BlockEntityType.HANGING_SIGN) {
            cir.setReturnValue(Woodset.getAllHangingSigns().stream().anyMatch(block -> block == state.getBlock()));
        }
    }
}
