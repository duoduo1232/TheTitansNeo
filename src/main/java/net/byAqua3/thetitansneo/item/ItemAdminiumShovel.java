package net.byAqua3.thetitansneo.item;

import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.byAqua3.thetitansneo.loader.TheTitansNeoTiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;

import net.minecraft.server.level.ServerLevel;
public class ItemAdminiumShovel extends ShovelItem {

	public ItemAdminiumShovel(Properties properties) {
		super(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F, properties.shovel(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F));
		// 26.1.2：ToolMaterial 为 record，属性已并入 Properties.shovel(...)。
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
				entity.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 10.0F, 1.0F);
				entity.hurtServer((ServerLevel) entity.level(), entity.damageSources().mobAttack(attacker), 2.0E9F);
			}
		}
		super.hurtEnemy(stack, entity, attacker);
	}
}
