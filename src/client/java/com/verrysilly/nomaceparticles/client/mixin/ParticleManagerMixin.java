package com.verrysilly.nomaceparticles.client.mixin;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
	@Inject(
		at = @At("HEAD"),
		method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;",
		cancellable = true
	)
	private void nomaceparticles$hideDustPillar(ParticleEffect effect, double x, double y, double z,
			double vx, double vy, double vz, CallbackInfoReturnable<Particle> cir) {
		if (effect.getType() == ParticleTypes.DUST_PILLAR) {
			cir.setReturnValue(null);
		}
	}
}