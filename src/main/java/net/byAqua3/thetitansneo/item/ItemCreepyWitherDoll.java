package net.byAqua3.thetitansneo.item;

import java.util.List;

import net.byAqua3.thetitansneo.loader.TheTitansNeoDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public class ItemCreepyWitherDoll extends Item {

	public ItemCreepyWitherDoll(Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!player.level().isClientSide()) {
			if (player.level().dimension() == Level.END) {
				List<EnderDragon> entities = player.level().getEntitiesOfClass(EnderDragon.class, player.getBoundingBox().inflate(1024.0D, 1024.0D, 1024.0D));
				if (!entities.isEmpty()) {
					player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.end.killdragon"));
				} else if (player.getX() < 5.0D && player.getX() > -5.0D && player.getZ() < 5.0D && player.getZ() > -5.0D) {
					player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.end.to"));
					player.push(0.0D, 3.0D, 0.0D);
					ServerLevel serverLevel = (ServerLevel) player.level();
					MinecraftServer server = serverLevel.getServer();
					ServerLevel voidLevel = server.getLevel(TheTitansNeoDimensions.THE_VOID);
					BlockPos spawnPos = voidLevel.getRespawnData().pos();
					player.teleport(new TeleportTransition(voidLevel, new Vec3(spawnPos.getX(), 85.0D, spawnPos.getZ()), Vec3.ZERO, 0.0F, 0.0F, java.util.Set.of(), TeleportTransition.DO_NOTHING));
					return InteractionResult.SUCCESS;
				} else {
					player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.end.pos"));
				}
			} else if (player.level().dimension() == TheTitansNeoDimensions.THE_NOWHERE) {
				player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.nowhere"));
			} else if (player.level().dimension() == TheTitansNeoDimensions.THE_VOID) {
				player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.void"));
			} else {
				player.sendSystemMessage(Component.translatable("item.thetitansneo.creepy_wither_doll.other"));
			}
		}
		return InteractionResult.SUCCESS;
	}
}
