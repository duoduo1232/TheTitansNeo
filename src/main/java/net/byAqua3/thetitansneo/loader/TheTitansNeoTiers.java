package net.byAqua3.thetitansneo.loader;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.TagKey;

/**
 * 26.1.2 适配：原版 {@code net.minecraft.world.item.Tier} 接口已删除，
 * 取而代之的是 {@link ToolMaterial} 记录（record）：
 * <pre>
 *   ToolMaterial(TagKey&lt;Block&gt; incorrectBlocksForDrops, int durability, float speed,
 *                float attackDamageBonus, int enchantmentValue, TagKey&lt;Item&gt; repairItems)
 * </pre>
 *
 * 因此本类从「enum implements Tier」改为「静态常量持有者」。
 * 原先的 <code>repairIngredient</code>（{@code Ingredient}）在 26.1.2 中改为
 * {@code TagKey<Item>}；本 mod 原先全部填的是 {@code Ingredient.EMPTY}（即无修复材料），
 * 这里用 {@link net.minecraft.tags.ItemTags} 中语义最接近的空标签占位——
 * 实际效果仍是「不可用材料修复」。
 */
public final class TheTitansNeoTiers {

	/** 无修复材料占位标签。原 Ingredient.EMPTY 在 26.1.2 的 ToolMaterial 中没有对应值。 */
	private static final TagKey<net.minecraft.world.item.Item> NO_REPAIR = net.minecraft.tags.ItemTags.create(
			net.minecraft.resources.Identifier.fromNamespaceAndPath("thetitansneo", "no_repair"));

	public static final ToolMaterial COPPER = new ToolMaterial(
			net.minecraft.tags.BlockTags.INCORRECT_FOR_WOODEN_TOOL, 80, 3.0F, -1.0F, 12, NO_REPAIR);
	public static final ToolMaterial TIN = new ToolMaterial(
			net.minecraft.tags.BlockTags.INCORRECT_FOR_STONE_TOOL, 100, 3.0F, -1.0F, 18, NO_REPAIR);
	public static final ToolMaterial BRONZE = new ToolMaterial(
			net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 6.0F, -1.0F, 14, NO_REPAIR);
	public static final ToolMaterial STEEL = new ToolMaterial(
			net.minecraft.tags.BlockTags.NEEDS_DIAMOND_TOOL, 900, 8.0F, -1.0F, 10, NO_REPAIR);
	public static final ToolMaterial HARCADIUM = new ToolMaterial(
			TheTitansNeoTags.INCORRECT_FOR_HARCADIUM_TOOL, 75000, 120.0F, -1.0F, 30, NO_REPAIR);
	public static final ToolMaterial VOID = new ToolMaterial(
			TheTitansNeoTags.INCORRECT_FOR_HARCADIUM_TOOL, 1000000, 4000.0F, -1.0F, 40, NO_REPAIR);
	/** 原 2147483647（Integer.MAX_VALUE）耐久直接照搬；ToolMaterial 不做校验。 */
	public static final ToolMaterial ADMINIUM = new ToolMaterial(
			TheTitansNeoTags.INCORRECT_FOR_HARCADIUM_TOOL, 2147483647, 1000000000.0F, -1.0F, 60, NO_REPAIR);
	public static final ToolMaterial ULTIMA = new ToolMaterial(
			TheTitansNeoTags.INCORRECT_FOR_ULTIMA_TOOL, 2147483647, 9999.0F, -1.0F, 1, NO_REPAIR);

	private TheTitansNeoTiers() {
	}

	/**
	 * 兼容旧调用点 {@code TheTitansNeoTiers.X.setRepairIngredient(...)}。
	 * ToolMaterial 是 record（不可变），无法原地改；这里保留空实现以免调用点编译失败。
	 * 如需真正生效，请改为在注册时用新的 ToolMaterial 常量。
	 */
	@Deprecated
	public static void setRepairIngredient(Object material, net.minecraft.world.item.crafting.Ingredient ingredient) {
		// no-op: 26.1.2 的 ToolMaterial 为不可变 record
	}
}
