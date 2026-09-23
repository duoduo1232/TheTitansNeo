package net.byAqua3.thetitansneo.item;

import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.byAqua3.thetitansneo.loader.TheTitansNeoTiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemUseAnimation;

import net.minecraft.server.level.ServerLevel;
public class ItemHarcadiumShovel extends ShovelItem {

	public ItemHarcadiumShovel(Properties properties) {
		super(TheTitansNeoTiers.HARCADIUM, 47, -2.0F, properties.shovel(TheTitansNeoTiers.HARCADIUM, 47, -2.0F));
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack pStack) {
		return ItemUseAnimation.BOW;
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity attacker) {
		if (entity != null) {
			if (entity.getBbHeight() >= 6.0F || entity instanceof EntityTitan || !entity.onGround()) {
				entity.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 10.0F, 1.0F);
				entity.hurtServer((ServerLevel) entity.level(), entity.damageSources().mobAttack(attacker), 350.0F);
			}
		}
		super.hurtEnemy(stack, entity, attacker);
	}
}
