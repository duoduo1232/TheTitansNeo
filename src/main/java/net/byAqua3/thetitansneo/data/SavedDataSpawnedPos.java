package net.byAqua3.thetitansneo.data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

/**
 * 26.1.2 迁移说明：
 * <p>
 * 旧版（1.21.1）的 {@code SavedData} 走 {@code load(CompoundTag, HolderLookup.Provider)} +
 * {@code save(CompoundTag, HolderLookup.Provider)} 这对手工 NBT 方法，并用
 * {@code new SavedData.Factory<>(ctor, loader, dataFixType)} 注册。
 * <p>
 * 26.1.2 把这套整个换了：{@code SavedData} 变成不含任何序列化契约的抽象基类，序列化改由
 * {@link SavedDataType} 携带的 {@link Codec} 完成，{@code SavedDataStorage#computeIfAbsent}
 * 只接受 {@code SavedDataType}（不再接受名字字符串）。查阅
 * {@code src_ref/net/minecraft/world/level/saveddata/WanderingTraderData.java} 与
 * {@code SavedDataType.java} 得到本实现骨架。
 */
public class SavedDataSpawnedPos extends SavedData {

	/** 单个 BlockPos 的编解码：与旧版 NBT 的 x/y/z 三字段一一对应。 */
	private static final Codec<BlockPos> POS_CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.INT.fieldOf("x").forGetter(BlockPos::getX),
					Codec.INT.fieldOf("y").forGetter(BlockPos::getY),
					Codec.INT.fieldOf("z").forGetter(BlockPos::getZ))
					.apply(instance, BlockPos::new));

	/** 顶层编解码：与旧版 NBT 的 "TitanSpawnedPos" 列表键同名同结构。 */
	public static final Codec<SavedDataSpawnedPos> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					POS_CODEC.listOf().optionalFieldOf("TitanSpawnedPos", List.of())
							.forGetter(SavedDataSpawnedPos::getSpawnedPosCopy))
					.apply(instance, SavedDataSpawnedPos::new));

	/** 注册用的类型描述符（替代旧版的 SavedData.Factory + 数据名 "titan_spawned_pos"）。 */
	public static final SavedDataType<SavedDataSpawnedPos> TYPE = new SavedDataType<>(
			Identifier.fromNamespaceAndPath(TheTitansNeo.MODID, "titan_spawned_pos"),
			SavedDataSpawnedPos::new,
			CODEC);

	private final List<BlockPos> spawnedPos = new CopyOnWriteArrayList<>();

	public SavedDataSpawnedPos() {
	}

	public SavedDataSpawnedPos(List<BlockPos> positions) {
		this.spawnedPos.addAll(positions);
	}

	public List<BlockPos> getSpawnedPos() {
		return this.spawnedPos;
	}

	/** Codec 的 getter 需要不可变视图，避免序列化期间观察到并发修改。 */
	public List<BlockPos> getSpawnedPosCopy() {
		return List.copyOf(this.spawnedPos);
	}
}
