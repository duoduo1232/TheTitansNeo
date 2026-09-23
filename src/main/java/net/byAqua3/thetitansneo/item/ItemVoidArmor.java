package net.byAqua3.thetitansneo.item;

import java.util.List;

import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoAttributes;
import net.byAqua3.thetitansneo.loader.TheTitansNeoConfigs;
import net.byAqua3.thetitansneo.loader.TheTitansNeoItems;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.minecraft.server.level.ServerLevel;
public class ItemVoidArmor extends Item {

	public ItemVoidArmor(Properties properties) {
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
				player.playSound(TheTitansNeoSounds.HARCACADIUM_HUM.get(), 5.0F, 0.5F);
				player.removeEffect(MobEffects.BLINDNESS);
				player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300, 0));
				player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0));
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.CHEST && slot == EquipmentSlot.CHEST) {
				player.playSound(TheTitansNeoSounds.HARCACADIUM_HUM.get(), 5.0F, 0.5F);
				player.removeEffect(MobEffects.WEAKNESS);
				player.removeEffect(MobEffects.MINING_FATIGUE);
				player.addEffect(new MobEffectInstance(MobEffects.HASTE, 300, 99));
				player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 300, 3));
				player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 300, 49));
				player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0));
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.LEGS && slot == EquipmentSlot.LEGS) {
				player.playSound(TheTitansNeoSounds.HARCACADIUM_HUM.get(), 5.0F, 0.0F);
				player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 199));
				player.removeEffect(MobEffects.NAUSEA);
				player.removeEffect(MobEffects.HUNGER);
				player.removeEffect(MobEffects.POISON);
			} else if (stack.get(net.minecraft.core.component.DataComponents.EQUIPPABLE).slot() == EquipmentSlot.FEET && slot == EquipmentSlot.FEET) {
				player.playSound(TheTitansNeoSounds.HARCACADIUM_HUM.get(), 5.0F, 0.5F);
				player.removeEffect(MobEffects.SLOWNESS);
				player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 300, 5));
				player.addEffect(new MobEffectInstance(MobEffects.SPEED, 300, 19));
				player.stuckSpeedMultiplier = Vec3.ZERO;
			}
			if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == TheTitansNeoItems.VOID_HELMET.get() && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == TheTitansNeoItems.VOID_CHESTPLATE.get() && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == TheTitansNeoItems.VOID_LEGGINGS.get() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == TheTitansNeoItems.VOID_BOOTS.get()) {
				for (int i = 0; i < 4; i++) {
					level.addParticle(ParticleTypes.MYCELIUM, player.getX() + (player.getRandom().nextDouble() - 0.5D) * player.getBbWidth() * 2.0D, player.getY() - 1.75D + player.getRandom().nextDouble() * player.getBbHeight(), player.getZ() + (player.getRandom().nextDouble() - 0.5D) * player.getBbWidth() * 2.0D, 0.0D, 0.05D, 0.0D);
				}
				player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 199));
				player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 300, 99));
				player.fallDistance = 0;

				if (!level.isClientSide()) {
					if (TheTitansNeoConfigs.getBoolean(TheTitansNeoConfigs.voidArmorRadiation, true)) {
						List<Entity> entities = player.level().getEntities(player, player.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
						for (Entity radiatedEntity : entities) {
							if (radiatedEntity != null && radiatedEntity instanceof LivingEntity && !(radiatedEntity instanceof EntityTitan) && !(radiatedEntity instanceof AbstractGolem) && !(radiatedEntity instanceof OwnableEntity) && !(radiatedEntity instanceof Villager)) {
								LivingEntity livingEntity = (LivingEntity) radiatedEntity;
								if (!level.isClientSide()) {
									if (TheTitansNeoConfigs.getBoolean(TheTitansNeoConfigs.voidArmorRadiationPlayer, true) && livingEntity instanceof Player) {
										continue;
									}
									livingEntity.hurtServer(level, livingEntity.damageSources().fellOutOfWorld(), 4.0F);
									livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 5000, 1));
									livingEntity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 5000, 1));
									livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 5000, 9));
									livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 5000, 3));
								}
							}
						}
					}
				}
			}
		}
	}
}
