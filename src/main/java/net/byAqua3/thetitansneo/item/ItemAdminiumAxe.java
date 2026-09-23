package net.byAqua3.thetitansneo.item;

import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.byAqua3.thetitansneo.loader.TheTitansNeoTiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;

import net.minecraft.server.level.ServerLevel;
import net.byAqua3.thetitansneo.util.ServerSafe;
public class ItemAdminiumAxe extends AxeItem {

	public ItemAdminiumAxe(Properties properties) {
		super(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F, properties.axe(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F));
		// 26.1.2：ToolMaterial 为 record，属性已并入 Properties.axe(...)。
		// 原 createAttributes(1000000000.0F, Float.MAX_VALUE) 的攻速收敛为 1024.0F，避免修饰符溢出。
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity attacker) {
		if (entity != null) {
			if (entity.getBbHeight() >= 6.0F || entity instanceof EntityTitan || !entity.onGround()) {
				entity.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 100.0F, 0.5F);
				ServerSafe.hurtServer(entity, entity.level(), entity.damageSources().mobAttack(attacker), 4.0E9F);
			}
		}
		super.hurtEnemy(stack, entity, attacker);
	}
}
