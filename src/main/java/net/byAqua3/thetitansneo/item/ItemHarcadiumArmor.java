package net.byAqua3.thetitansneo.item;

import net.byAqua3.thetitansneo.loader.TheTitansNeoAttributes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;

public class ItemHarcadiumArmor extends Item {

	public ItemHarcadiumArmor(Properties properties) {
		// 26.1.2：装备信息（耐久/属性/EQUIPPABLE/修复材料）已由
		// TheTitansNeoItems 侧调用 Properties.humanoidArmor(...) 打包。
		super(properties);
	}


	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		super.inventoryTick(stack, level, entity, slot);
		if (entity instanceof Player) {
			Player player = (Player) entity;

			if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.HEAD && slot == EquipmentSlot.HEAD) {
				player.removeEffect(MobEffects.BLINDNESS);
				player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300, 0));
				player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0));
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.CHEST && slot == EquipmentSlot.CHEST) {
				player.removeEffect(MobEffects.WEAKNESS);
				player.removeEffect(MobEffects.MINING_FATIGUE);
				player.addEffect(new MobEffectInstance(MobEffects.HASTE, 300, 3));
				player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 300, 3));
				player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 300, 9));
				player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0));
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.LEGS && slot == EquipmentSlot.LEGS) {
				player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 10));
				player.removeEffect(MobEffects.NAUSEA);
				player.removeEffect(MobEffects.HUNGER);
				player.removeEffect(MobEffects.POISON);
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.FEET && slot == EquipmentSlot.FEET) {
				player.removeEffect(MobEffects.SLOWNESS);
				player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 300, 3));
				player.addEffect(new MobEffectInstance(MobEffects.SPEED, 300, 3));
				player.stuckSpeedMultiplier = Vec3.ZERO;
			}
		}
	}
}
