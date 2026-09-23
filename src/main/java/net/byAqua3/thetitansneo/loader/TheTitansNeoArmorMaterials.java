package net.byAqua3.thetitansneo.loader;

import java.util.Map;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

/**
 * 26.1.2 适配说明：
 * <p>
 * 1.21.1 的 {@code ArmorMaterial} 是一个<b>可注册对象</b>（DeferredRegister&lt;ArmorMaterial&gt;），
 * 且带 {@code List<ArmorMaterial.Layer>} 用于定位贴图。
 * <p>
 * 26.1.2 中它被改成不可变 record，并<b>不再走注册表</b>——由
 * {@code Item.Properties.humanoidArmor(material, type)} 直接把属性、装备信息
 * 写进物品的 DataComponents。贴图改由 assets 侧的 equipment JSON 描述（见
 * {@code assets/thetitansneo/equipment/<name>.json}）。
 * <p>
 * 因此本类由 DeferredRegister 持有者改为纯静态常量持有者。
 * 原先的 {@code Ingredient} 修复材料也改为 {@code TagKey<Item>}；
 * 本 mod 原本用的是「具体物品」而非标签，这里为每种材质新建了
 * {@code thetitansneo:repairs_<name>_armor} 标签作为承接点——
 * 请把对应的修理材料物品加进该标签的数据包文件。
 */
public class TheTitansNeoArmorMaterials {

	/** 本 mod 的 equipment 贴图命名空间根。 */
	private static final ResourceKey<EquipmentAsset> asset(String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(TheTitansNeo.MODID, name));
	}

	/** 修复材料标签工厂：对应原 Ingredient.of(具体物品)。 */
	private static TagKey<Item> repairs(String name) {
		return ItemTags.create(Identifier.fromNamespaceAndPath(TheTitansNeo.MODID, "repairs_" + name + "_armor"));
	}

	private static Map<ArmorType, Integer> defense(int boots, int legs, int chest, int helm) {
		// 原版 ArmorMaterials.makeDefense 的入参顺序即 boots, legs, chest, helm, body
		return Map.of(
				ArmorType.BOOTS, boots,
				ArmorType.LEGGINGS, legs,
				ArmorType.CHESTPLATE, chest,
				ArmorType.HELMET, helm,
				ArmorType.BODY, 0);
	}

	/** 原 1.21.1 注册名 "thetitansneo:copper"，贴图前缀 copper。 */
	public static final ArmorMaterial COPPER = new ArmorMaterial(
			6, defense(1, 2, 3, 1), 12, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("copper"), asset("copper"));

	/** 原 1.21.1 注册名 "thetitansneo:tin"，贴图前缀 tin。 */
	public static final ArmorMaterial TIN = new ArmorMaterial(
			6, defense(1, 2, 3, 1), 18, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("tin"), asset("tin"));

	/** 原 1.21.1 注册名 "thetitansneo:bronze"，贴图前缀 bronze。 */
	public static final ArmorMaterial BRONZE = new ArmorMaterial(
			12, defense(2, 4, 6, 2), 14, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("bronze"), asset("bronze"));

	/** 原 1.21.1 注册名 "thetitansneo:steel"，贴图前缀 steel。 */
	public static final ArmorMaterial STEEL = new ArmorMaterial(
			30, defense(3, 6, 8, 3), 20, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("steel"), asset("steel"));

	/** 原 1.21.1 注册名 "thetitansneo:harcadium"，贴图前缀 harcadium。 */
	public static final ArmorMaterial HARCADIUM = new ArmorMaterial(
			5000, defense(8, 12, 15, 9), 30, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("harcadium"), asset("harcadium"));

	/** 原 1.21.1 注册名 "thetitansneo:void"，贴图前缀 absence。 */
	public static final ArmorMaterial VOID = new ArmorMaterial(
			100000, defense(9, 13, 17, 11), 50, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("void"), asset("absence"));

	/** 原 1.21.1 注册名 "thetitansneo:adminium"，贴图前缀 adminium。 */
	public static final ArmorMaterial ADMINIUM = new ArmorMaterial(
			100000000, defense(100000, 100000, 100000, 100000), 60, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F,
			repairs("adminium"), asset("adminium"));

	private TheTitansNeoArmorMaterials() {
	}
}
