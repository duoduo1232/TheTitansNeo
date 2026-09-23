package net.byAqua3.thetitansneo.biome;

import java.util.List;
import java.util.function.Function;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

/**
 * 26.1.2 迁移说明：
 * <ul>
 * <li>{@code MobSpawnSettings.Builder.addSpawn} 签名由 {@code (MobCategory, SpawnerData)} 变为
 * {@code (MobCategory, int weight, SpawnerData)} —— 权重从 SpawnerData 内部提升为独立参数。</li>
 * <li>{@code SpawnerData.CODEC} 由 {@code Codec} 改为 {@code MapCodec}，字段名变为
 * {@code type/minCount/maxCount}，因此 { S spawner, int weight } 无法再用 record 自动派生 codec，
 * 这里显式写成 { weight, type, minCount, maxCount } 的扁平结构。</li>
 * </ul>
 */
public record BiomeModifierAddSpawns(HolderSet<Biome> biomes, MobCategory category, List<WeightedSpawner> spawners)
		implements BiomeModifier {

	public static final MapCodec<BiomeModifierAddSpawns> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
			Biome.LIST_CODEC.fieldOf("biomes").forGetter(BiomeModifierAddSpawns::biomes),
			MobCategory.CODEC.fieldOf("category").forGetter(BiomeModifierAddSpawns::category),
			Codec.either(SpawnerData.CODEC.listOf(), SpawnerData.CODEC)
					.xmap(
							either -> either.map(Function.identity(), List::of),
							list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list))
					.fieldOf("spawners").forGetter(BiomeModifierAddSpawns::spawners))
			.apply(builder, BiomeModifierAddSpawns::new));

	public static BiomeModifierAddSpawns singleSpawn(HolderSet<Biome> biomes, MobCategory category, WeightedSpawner spawner) {
		return new BiomeModifierAddSpawns(biomes, category, List.of(spawner));
	}

	/**
	 * 26.1.2 中 addSpawn 的权重是独立参数；这里把 weight 与 SpawnerData 的字段拍平到同一层 JSON，
	 * 既保持了旧数据文件 {@code {type, weight, minCount, maxCount}} 的写法，又能直接喂给 addSpawn。
	 */
	public record WeightedSpawner(int weight, SpawnerData data) {
		public static final MapCodec<WeightedSpawner> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
				Codec.INT.optionalFieldOf("weight", 10).forGetter(WeightedSpawner::weight),
				SpawnerData.CODEC.forGetter(WeightedSpawner::data))
				.apply(i, WeightedSpawner::new));
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return CODEC;
	}

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && this.biomes.contains(biome)) {
			MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
			for (WeightedSpawner spawner : this.spawners) {
				spawns.addSpawn(this.category, spawner.weight(), spawner.data());
			}
		}
	}
}
