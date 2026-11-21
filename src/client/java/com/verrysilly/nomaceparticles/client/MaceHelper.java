package com.verrysilly.nomaceparticles.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public final class MaceHelper {
	private MaceHelper() {
	}

	public static boolean maceHolderNear(double x, double y, double z) {
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