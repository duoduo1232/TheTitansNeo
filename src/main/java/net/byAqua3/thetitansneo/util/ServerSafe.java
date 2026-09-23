package net.byAqua3.thetitansneo.util;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * 26.1.2 迁移辅助：把「只能在服务端调用」的原版 API 包成客户端安全的形式。
 *
 * <p>背景：1.21.1 的 {@code Entity.hurt(float, DamageSource)}、{@code spawnAtLocation(ItemStack)}、
 * {@code kill()} 等 API 在 26.1.2 都变成了必须传 {@code ServerLevel} 的 {@code xxxServer(...)} 形式。
 * 直接写 {@code foo.hurtServer((ServerLevel) this.level(), ...)} 在<b>客户端也会执行的代码路径</b>
 * （{@code aiStep()}、{@code tick()}、{@code hurtEnemy()}、{@code use()} 等）里会抛
 * {@code ClassCastException: ClientLevel cannot be cast to ServerLevel}。
 *
 * <p>这些操作在客户端本来就无事可做，因此统一在客户端变成 no-op：
 * 既修掉崩溃，又不改变服务端行为。
 */
public final class ServerSafe {

	private ServerSafe() {
	}

	// ---------- 伤害 ----------

	public static boolean hurtServer(Entity target, Level level, DamageSource source, float amount) {
		return level instanceof ServerLevel serverLevel && target.hurtServer(serverLevel, source, amount);
	}

	public static boolean doHurtTarget(LivingEntity attacker, Level level, Entity target) {
		return level instanceof ServerLevel serverLevel && attacker.doHurtTarget(serverLevel, target);
	}

	// ---------- 掉落 / 经验 ----------

	@Nullable
	public static Entity spawnAtLocation(Entity entity, Level level, ItemStack stack) {
		return level instanceof ServerLevel serverLevel ? entity.spawnAtLocation(serverLevel, stack) : null;
	}

	@Nullable
	public static Entity spawnAtLocation(Entity entity, Level level, ItemStack stack, float offset) {
		return level instanceof ServerLevel serverLevel ? entity.spawnAtLocation(serverLevel, stack, offset) : null;
	}

	@Nullable
	public static Entity spawnAtLocation(Entity entity, Level level, ItemStack stack, Vec3 offset) {
		return level instanceof ServerLevel serverLevel ? entity.spawnAtLocation(serverLevel, stack, offset) : null;
	}

	public static void awardExperience(Level level, Vec3 pos, int amount) {
		if (level instanceof ServerLevel serverLevel) {
			ExperienceOrb.award(serverLevel, pos, amount);
		}
	}

	// ---------- 方块掉落 ----------

	public static List<ItemStack> getBlockDrops(BlockState state, Level level, BlockPos pos, @Nullable BlockEntity blockEntity) {
		return level instanceof ServerLevel serverLevel
				? Block.getDrops(state, serverLevel, pos, blockEntity)
				: List.of();
	}

	// ---------- 死亡 ----------

	public static void kill(Entity entity, Level level) {
		if (level instanceof ServerLevel serverLevel) {
			entity.kill(serverLevel);
		}
	}

	// ---------- 游戏规则（客户端没有 GameRules，默认放行，保证服务端逻辑不受影响）----------

	public static boolean mobDrops(Level level) {
		return !(level instanceof ServerLevel serverLevel) || serverLevel.getGameRules().get(GameRules.MOB_DROPS);
	}

	public static boolean mobGriefing(Level level) {
		return !(level instanceof ServerLevel serverLevel) || serverLevel.getGameRules().get(GameRules.MOB_GRIEFING);
	}

	// ---------- 生成 / 寻敌 ----------

	/** 26.1.2: Mob.finalizeSpawn 需要 ServerLevelAccessor；客户端直接跳过。 */
	public static void finalizeSpawn(Mob mob, Level level, BlockPos pos, EntitySpawnReason reason, SpawnGroupData data) {
		if (level instanceof ServerLevel serverLevel) {
			mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(pos), reason, data);
		}
	}

	public static boolean canEntityGrief(Level level, Entity entity) {
		return level instanceof ServerLevel serverLevel
				&& net.neoforged.neoforge.event.EventHooks.canEntityGrief(serverLevel, entity);
	}

	/** 需要一个 ServerLevel 才能完成的查询；客户端返回 null。 */
	public static <T> T whenServer(Level level, java.util.function.Function<ServerLevel, T> fn) {
		return level instanceof ServerLevel serverLevel ? fn.apply(serverLevel) : null;
	}
}
