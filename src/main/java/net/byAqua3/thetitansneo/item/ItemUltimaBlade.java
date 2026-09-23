package net.byAqua3.thetitansneo.item;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;

import net.byAqua3.thetitansneo.damage.DamageSourceTitanAttack;
import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoConfigs;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.byAqua3.thetitansneo.loader.TheTitansNeoTiers;
import net.byAqua3.thetitansneo.util.EntityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.EquipmentSlot;

public class ItemUltimaBlade extends Item {

	public ItemUltimaBlade(Properties properties) {
		super(bladeProperties(properties));
	}

	/**
	 * 26.1.2 适配：{@code ToolMaterial} 是 record（{@code getAttackDamageBonus()} 已不存在），
	 * 且 {@code Item.Properties.sword(...)} 不再接受自定义 range。
	 * <p>
	 * 原 {@code Float.MAX_VALUE} 攻击速度收敛到 1024.0F —— 原因同 {@code ItemOptimaAxe}：
	 * 溢出的攻速修饰符会让攻击冷却计算归零。
	 * <p>
	 * 原 {@code BLOCK_INTERACTION_RANGE = 24} 改用 26.1.2 的 {@code DataComponents.ATTACK_RANGE}。
	 */
	private static final float ATTACK_SPEED = 1024.0F;
	private static final float INTERACTION_RANGE = 24.0F;

	private static Properties bladeProperties(Properties properties) {
		return properties
				.sword(TheTitansNeoTiers.ULTIMA, 0.0F, ATTACK_SPEED)
				.component(net.minecraft.core.component.DataComponents.ATTACK_RANGE,
						new net.minecraft.world.item.component.AttackRange(0.0F, INTERACTION_RANGE, 0.0F, INTERACTION_RANGE, 0.3F, 1.0F));
	}

	@Override
	public int getDamage(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, display, tooltip, flag);
		tooltip.accept(Component.translatable("item.thetitansneo.ultima_blade.info1").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.accept(Component.translatable("item.thetitansneo.ultima_blade.info2").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA));
		tooltip.accept(Component.translatable("item.thetitansneo.ultima_blade.info3").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA));
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		if (entity instanceof Player) {
			Player player = (Player) entity;

			if (!TheTitansNeoConfigs.getBoolean(TheTitansNeoConfigs.ultimaBladeHiddenParticles, false)) {
				for (int i = 0; i < 3; i++) {
					level.addParticle(ParticleTypes.FIREWORK, player.getX() + (level.getRandom().nextDouble() - 0.5D) * player.getBbWidth(), player.getY() + level.getRandom().nextDouble() * player.getBbHeight(), player.getZ() + (level.getRandom().nextDouble() - 0.5D) * player.getBbWidth(), (level.getRandom().nextDouble() - 0.5D) * 2.0D, 1.0D, (level.getRandom().nextDouble() - 0.5D) * 2.0D);
					level.addParticle(ParticleTypes.POOF, player.getX() + (level.getRandom().nextDouble() - 0.5D) * player.getBbWidth(), player.getY() + level.getRandom().nextDouble() * player.getBbHeight(), player.getZ() + (level.getRandom().nextDouble() - 0.5D) * player.getBbWidth(), (level.getRandom().nextDouble() - 0.5D) * 2.0D, 1.0D, (level.getRandom().nextDouble() - 0.5D) * 2.0D);
				}
			}

			if (player.getY() < -100.0D) {
				player.push(0.0D, 10.0D, 0.0D);
			}

			player.heal(player.getMaxHealth());
			if (!level.isClientSide()) {
				player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 5, 249, false, false));
			}
		}
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity livingEntity) {
		if (livingEntity instanceof Player) {
			Player player = (Player) livingEntity;
			Block block = state.getBlock();

			player.playSound(TheTitansNeoSounds.TITAN_SWING.get(), 10.0F, 1.0F);

			if (!state.isAir()) {
				if (!level.isClientSide() && !player.isCreative()) {
					List<ItemStack> drops = new ArrayList<>();
					List<ItemStack> blockDrops = Block.getDrops(state, (ServerLevel) level, pos, null);

					if (!blockDrops.isEmpty()) {
						drops.addAll(blockDrops);
					} else {
						drops.add(new ItemStack(block));
					}

					if (!drops.isEmpty()) {
						for (ItemStack drop : drops) {
							ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), drop);
							itemEntity.setDefaultPickUpDelay();
							itemEntity.push(-Math.sin(player.getYRot() * Math.PI / 180.0F) * 1.0D, 0.75D, Math.cos(player.getYRot() * Math.PI / 180.0F) * 1.0D);
							level.addFreshEntity(itemEntity);
						}
					}
				}

				if (!(block instanceof BaseFireBlock)) {
					level.levelEvent(2001, pos, Block.getId(state));
				}
				level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
			}
		}
		return super.mineBlock(stack, level, state, pos, livingEntity);
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity livingEntity) {
		Level level = livingEntity.level();

		if (livingEntity instanceof Player) {
			Player player = (Player) livingEntity;

			player.playSound(TheTitansNeoSounds.TITAN_SWING.get(), 10.0F, 1.0F);

			if (!player.isCrouching()) {
				for (int i = 0; i < 24; i++) {
					double d8 = 3.0D;
					Vec3 vec3 = player.getViewVector(1.0F);
					double dx = vec3.x * i;
					double dy = player.getEyeHeight() + vec3.y * i;
					double dz = vec3.z * i;

					List<Entity> entities = level.getEntities(player, player.getBoundingBox().inflate(d8, d8, d8).move(dx, dy, dz));
					List<Entity> bigEntities = EntityUtils.getEntities(entities, player, d8, d8, d8, dx, dy, dz);
					if (!bigEntities.isEmpty()) {
						entities.addAll(bigEntities);
					}
					for (Entity entity : entities) {
						if (entity != null) {
							DamageSourceTitanAttack damageSource = new DamageSourceTitanAttack(player);

							player.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 2.0F, 0.5F + level.getRandom().nextFloat() * 0.25F);
							player.playSound(TheTitansNeoSounds.SLASH_FLESH.get(), 10.0F, 1.0F);

							if (entity instanceof EntityTitan) {
								player.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 10.0F, 1.0F);

								EntityTitan titan = (EntityTitan) entity;
								titan.hurtServer((ServerLevel) livingEntity.level(), damageSource, 2000.0F);
							} else if (entity instanceof PrimedTnt) {
								if (!level.isClientSide()) {
									level.explode(player, entity.getX(), entity.getY(), entity.getZ(), 4.0F, false, Level.ExplosionInteraction.BLOCK);
									entity.remove(RemovalReason.KILLED);
								}
							} else if (entity instanceof Fireball) {
								if (!level.isClientSide()) {
									level.explode(player, entity.getX(), entity.getY(), entity.getZ(), 0.0F, false, Level.ExplosionInteraction.BLOCK);
									entity.remove(RemovalReason.KILLED);
								}
							} else if (entity instanceof LivingEntity) {
								entity.setRemainingFireTicks(Integer.MAX_VALUE);
								if (!entity.hurtServer((ServerLevel) level, damageSource, 20000.0F)) {
									((LivingEntity) entity).setHealth(0.0F);
								}
								entity.push(-Math.sin(player.getYRot() * Math.PI / 180.0F) * 6.0D, 6.0D, Math.cos(player.getYRot() * Math.PI / 180.0F) * 6.0D);
							}
						}
					}
				}
			} else {
				double d8 = 64.0D;

				List<Entity> entities = level.getEntities(player, player.getBoundingBox().inflate(d8, d8, d8));
				List<Entity> bigEntities = EntityUtils.getEntities(entities, player, d8, d8, d8);
				if (!bigEntities.isEmpty()) {
					entities.addAll(bigEntities);
				}
				for (Entity entity : entities) {
					if (entity != null) {
						DamageSourceTitanAttack damageSource = new DamageSourceTitanAttack(player);

						player.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 2.0F, 0.5F + level.getRandom().nextFloat() * 0.25F);
						player.playSound(TheTitansNeoSounds.SLASH_FLESH.get(), 10.0F, 1.0F);

						if (entity instanceof EntityTitan) {
							player.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 10.0F, 1.0F);

							EntityTitan titan = (EntityTitan) entity;
							titan.hurtServer((ServerLevel) level, damageSource, 2000.0F);
						} else if (entity instanceof LivingEntity) {
							entity.setRemainingFireTicks(Integer.MAX_VALUE);
							if (!entity.hurtServer((ServerLevel) level, damageSource, Float.MAX_VALUE)) {
								((LivingEntity) entity).setHealth(0.0F);
							}
							entity.push(-Math.sin(player.getYRot() * Math.PI / 180.0F) * 6.0D, 6.0D, Math.cos(player.getYRot() * Math.PI / 180.0F) * 6.0D);
						} else {
							entity.hurtServer((ServerLevel) level, entity.damageSources().playerAttack(player), 20000.0F);
						}
					}
				}
			}
		}
		return false;
	}
}
