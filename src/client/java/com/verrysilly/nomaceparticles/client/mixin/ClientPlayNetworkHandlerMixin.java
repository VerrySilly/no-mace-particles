package com.verrysilly.nomaceparticles.client.mixin;

import com.verrysilly.nomaceparticles.client.MaceHelper;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(at = @At("HEAD"), method = "onParticle", cancellable = true)
	private void nomaceparticles$hideMaceParticles(ParticleS2CPacket packet, CallbackInfo ci) {
		ParticleType<?> type = packet.getParameters().getType();

		if (type == ParticleTypes.DUST_PILLAR) {
			ci.cancel();
			return;
		}

		if ((type == ParticleTypes.BLOCK || type == ParticleTypes.GUST || type == ParticleTypes.GUST_EMITTER_LARGE
				|| type == ParticleTypes.GUST_EMITTER_SMALL)
				&& MaceHelper.maceHolderNear(packet.getX(), packet.getY(), packet.getZ())) {
			ci.cancel();
		}
	}
}