package net.dawkmeow.quinta.mixin;

import net.dawkmeow.quinta.util.IEntityDataSaver;
import net.dawkmeow.quinta.util.ManaData;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ModPlayerDeathMixin {

    @Inject(method = "onDeath", at = @At("TAIL"))
    protected void injectOnDeathMethod(DamageSource damageSource, CallbackInfo info){
        ManaData.removeMana((IEntityDataSaver) this, 100);
    }
}
