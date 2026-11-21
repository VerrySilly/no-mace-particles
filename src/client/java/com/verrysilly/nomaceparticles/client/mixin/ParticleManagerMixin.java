package com.verrysilly.nomaceparticles.client.mixin;

import com.verrysilly.nomaceparticles.client.MaceHelper;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
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
	private void nomaceparticles$hideMaceParticles(ParticleEffect effect, double x, double y, double z,
			double vx, double vy, double vz, CallbackInfoReturnable<Particle> cir) {
		ParticleType<?> type = effect.getType();

		if (type == ParticleTypes.DUST_PILLAR) {
			cir.setReturnValue(null);
			return;
		}

		if ((type == ParticleTypes.GUST || type == ParticleTypes.GUST_EMITTER_LARGE || type == ParticleTypes.GUST_EMITTER_SMALL)
				&& MaceHelper.maceHolderNear(x, y, z)) {
			cir.setReturnValue(null);
		}
	}
}