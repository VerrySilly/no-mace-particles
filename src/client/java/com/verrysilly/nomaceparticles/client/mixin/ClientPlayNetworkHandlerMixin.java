package com.verrysilly.nomaceparticles.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
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

		if (type == ParticleTypes.BLOCK && nomaceparticles$maceHolderNear(packet.getX(), packet.getY(), packet.getZ())) {
			ci.cancel();
		}
	}

	private static boolean nomaceparticles$maceHolderNear(double x, double y, double z) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null) {
			return false;
		}
		for (PlayerEntity player : world.getPlayers()) {
			if (player.squaredDistanceTo(x, y, z) <= 16.0 && player.getMainHandStack().isOf(Items.MACE)) {
				return true;
			}
		}
		return false;
	}
}