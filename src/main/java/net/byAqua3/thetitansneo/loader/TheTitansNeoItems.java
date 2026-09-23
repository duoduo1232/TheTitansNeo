package net.byAqua3.thetitansneo.loader;

import java.util.HashMap;
import java.util.Map;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.item.ItemAdminiumArmor;
import net.byAqua3.thetitansneo.item.ItemAdminiumAxe;
import net.byAqua3.thetitansneo.item.ItemAdminiumHoe;
import net.byAqua3.thetitansneo.item.ItemAdminiumPickaxe;
import net.byAqua3.thetitansneo.item.ItemAdminiumShovel;
import net.byAqua3.thetitansneo.item.ItemAdminiumSword;
import net.byAqua3.thetitansneo.item.ItemCreepyWitherDoll;
import net.byAqua3.thetitansneo.item.ItemFoodMalgrum;
import net.byAqua3.thetitansneo.item.ItemGrowthSerum;
import net.byAqua3.thetitansneo.item.ItemHarcadium;
import net.byAqua3.thetitansneo.item.ItemHarcadiumArmor;
import net.byAqua3.thetitansneo.item.ItemHarcadiumAxe;
import net.byAqua3.thetitansneo.item.ItemHarcadiumBow;
import net.byAqua3.thetitansneo.item.ItemHarcadiumHoe;
import net.byAqua3.thetitansneo.item.ItemHarcadiumPickaxe;
import net.byAqua3.thetitansneo.item.ItemHarcadiumShovel;
import net.byAqua3.thetitansneo.item.ItemHarcadiumSword;
import net.byAqua3.thetitansneo.item.ItemOptimaAxe;
import net.byAqua3.thetitansneo.item.ItemPleasantBladeBrew;
import net.byAqua3.thetitansneo.item.ItemPleasantBladeFlower;
import net.byAqua3.thetitansneo.item.ItemRngRelinquisher;
import net.byAqua3.thetitansneo.item.ItemTitanSpawnEgg;
import net.byAqua3.thetitansneo.item.ItemUltimaBlade;
import net.byAqua3.thetitansneo.item.ItemVoidArmor;
import net.byAqua3.thetitansneo.item.ItemVoidAxe;
import net.byAqua3.thetitansneo.item.ItemVoidHoe;
import net.byAqua3.thetitansneo.item.ItemVoidPickaxe;
import net.byAqua3.thetitansneo.item.ItemVoidShovel;
import net.byAqua3.thetitansneo.item.ItemVoidSword;
import net.byAqua3.thetitansneo.item.ItemWitherTurret;
import net.byAqua3.thetitansneo.item.ItemWitherTurretGround;
import net.byAqua3.thetitansneo.item.ItemWitherTurretMortar;
import net.byAqua3.thetitansneo.render.item.RenderOptimaAxe;
import net.byAqua3.thetitansneo.render.item.RenderTitanSpawnEgg;
import net.byAqua3.thetitansneo.render.item.RenderUltimaBlade;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TheTitansNeoItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, TheTitansNeo.MODID);

	public static final Rarity GODLY_RARITY = TheTitansNeoEnumParams.GODLY_RARITY_ENUM_PROXY.getValue();

	public static final DeferredHolder<Item, ItemUltimaBlade> ULTIMA_BLADE = ITEMS.register("ultima_blade", () -> new ItemUltimaBlade(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, ItemOptimaAxe> OPTIMA_AXE = ITEMS.register("optima_axe", () -> new ItemOptimaAxe(new Item.Properties().rarity(GODLY_RARITY)));

	public static final DeferredHolder<Item, Item> DIAMOND_STRING = ITEMS.register("diamond_string", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_WAFLET = ITEMS.register("harcadium_waflet", () -> new ItemHarcadium(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_WAFER = ITEMS.register("harcadium_wafer", () -> new ItemHarcadium(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_NUGGET = ITEMS.register("harcadium_nugget", () -> new ItemHarcadium(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM = ITEMS.register("harcadium", () -> new ItemHarcadium(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID = ITEMS.register("void", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> ADAMANTIUM = ITEMS.register("adamantium", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> GROWTH_SERUM = ITEMS.register("growth_serum", () -> new ItemGrowthSerum(new Item.Properties()));

	public static final DeferredHolder<Item, Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new Item(new Item.Properties().sword(TheTitansNeoTiers.COPPER, 3.0F, -2.4F)));
	public static final DeferredHolder<Item, Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(TheTitansNeoTiers.COPPER, 6.0F, -3.2F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new Item(new Item.Properties().pickaxe(TheTitansNeoTiers.COPPER, 1.0F, -2.8F)));
	public static final DeferredHolder<Item, Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(TheTitansNeoTiers.COPPER, 1.5F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new HoeItem(TheTitansNeoTiers.COPPER, 0.0F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new Item(new Item.Properties().durability(66).humanoidArmor(TheTitansNeoArmorMaterials.COPPER, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new Item(new Item.Properties().durability(96).humanoidArmor(TheTitansNeoArmorMaterials.COPPER, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new Item(new Item.Properties().durability(90).humanoidArmor(TheTitansNeoArmorMaterials.COPPER, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new Item(new Item.Properties().durability(78).humanoidArmor(TheTitansNeoArmorMaterials.COPPER, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> TIN_SWORD = ITEMS.register("tin_sword", () -> new Item(new Item.Properties().sword(TheTitansNeoTiers.TIN, 3.0F, -2.4F)));
	public static final DeferredHolder<Item, Item> TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(TheTitansNeoTiers.TIN, 6.0F, -3.2F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new Item(new Item.Properties().pickaxe(TheTitansNeoTiers.TIN, 1.0F, -2.8F)));
	public static final DeferredHolder<Item, Item> TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(TheTitansNeoTiers.TIN, 1.5F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> TIN_HOE = ITEMS.register("tin_hoe", () -> new HoeItem(TheTitansNeoTiers.TIN, 0.0F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> TIN_HELMET = ITEMS.register("tin_helmet", () -> new Item(new Item.Properties().durability(66).humanoidArmor(TheTitansNeoArmorMaterials.TIN, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> TIN_CHESTPLATE = ITEMS.register("tin_chestplate", () -> new Item(new Item.Properties().durability(96).humanoidArmor(TheTitansNeoArmorMaterials.TIN, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> TIN_LEGGINGS = ITEMS.register("tin_leggings", () -> new Item(new Item.Properties().durability(90).humanoidArmor(TheTitansNeoArmorMaterials.TIN, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> TIN_BOOTS = ITEMS.register("tin_boots", () -> new Item(new Item.Properties().durability(78).humanoidArmor(TheTitansNeoArmorMaterials.TIN, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> BRONZE_SWORD = ITEMS.register("bronze_sword", () -> new Item(new Item.Properties().sword(TheTitansNeoTiers.BRONZE, 3.0F, -2.4F)));
	public static final DeferredHolder<Item, Item> BRONZE_AXE = ITEMS.register("bronze_axe", () -> new AxeItem(TheTitansNeoTiers.BRONZE, 6.0F, -3.2F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe", () -> new Item(new Item.Properties().pickaxe(TheTitansNeoTiers.BRONZE, 1.0F, -2.8F)));
	public static final DeferredHolder<Item, Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel", () -> new ShovelItem(TheTitansNeoTiers.BRONZE, 1.5F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> BRONZE_HOE = ITEMS.register("bronze_hoe", () -> new HoeItem(TheTitansNeoTiers.BRONZE, 0.0F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> BRONZE_HELMET = ITEMS.register("bronze_helmet", () -> new Item(new Item.Properties().durability(132).humanoidArmor(TheTitansNeoArmorMaterials.BRONZE, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate", () -> new Item(new Item.Properties().durability(192).humanoidArmor(TheTitansNeoArmorMaterials.BRONZE, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings", () -> new Item(new Item.Properties().durability(180).humanoidArmor(TheTitansNeoArmorMaterials.BRONZE, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> BRONZE_BOOTS = ITEMS.register("bronze_boots", () -> new Item(new Item.Properties().durability(156).humanoidArmor(TheTitansNeoArmorMaterials.BRONZE, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new Item(new Item.Properties().sword(TheTitansNeoTiers.STEEL, 3.0F, -2.4F)));
	public static final DeferredHolder<Item, Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(TheTitansNeoTiers.STEEL, 6.0F, -3.2F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new Item(new Item.Properties().pickaxe(TheTitansNeoTiers.STEEL, 1.0F, -2.8F)));
	public static final DeferredHolder<Item, Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(TheTitansNeoTiers.STEEL, 1.5F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(TheTitansNeoTiers.STEEL, 0.0F, -3.0F, new Item.Properties()));
	public static final DeferredHolder<Item, Item> STEEL_HELMET = ITEMS.register("steel_helmet", () -> new Item(new Item.Properties().durability(330).humanoidArmor(TheTitansNeoArmorMaterials.STEEL, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> new Item(new Item.Properties().durability(480).humanoidArmor(TheTitansNeoArmorMaterials.STEEL, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings", () -> new Item(new Item.Properties().durability(450).humanoidArmor(TheTitansNeoArmorMaterials.STEEL, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> STEEL_BOOTS = ITEMS.register("steel_boots", () -> new Item(new Item.Properties().durability(390).humanoidArmor(TheTitansNeoArmorMaterials.STEEL, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> MAGNESIUM_INGOT = ITEMS.register("magnesium_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties()));

	public static final DeferredHolder<Item, Item> HARCADIUM_ARROW = ITEMS.register("harcadium_arrow", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_BOW = ITEMS.register("harcadium_bow", () -> new ItemHarcadiumBow(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_SWORD = ITEMS.register("harcadium_sword", () -> new ItemHarcadiumSword(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_AXE = ITEMS.register("harcadium_axe", () -> new ItemHarcadiumAxe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_PICKAXE = ITEMS.register("harcadium_pickaxe", () -> new ItemHarcadiumPickaxe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_SHOVEL = ITEMS.register("harcadium_shovel", () -> new ItemHarcadiumShovel(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_HOE = ITEMS.register("harcadium_hoe", () -> new ItemHarcadiumHoe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> HARCADIUM_HELMET = ITEMS.register("harcadium_helmet", () -> new ItemHarcadiumArmor(new Item.Properties().durability(55001).humanoidArmor(TheTitansNeoArmorMaterials.HARCADIUM, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> HARCADIUM_CHESTPLATE = ITEMS.register("harcadium_chestplate", () -> new ItemHarcadiumArmor(new Item.Properties().durability(80001).humanoidArmor(TheTitansNeoArmorMaterials.HARCADIUM, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> HARCADIUM_LEGGINGS = ITEMS.register("harcadium_leggings", () -> new ItemHarcadiumArmor(new Item.Properties().durability(75001).humanoidArmor(TheTitansNeoArmorMaterials.HARCADIUM, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> HARCADIUM_BOOTS = ITEMS.register("harcadium_boots", () -> new ItemHarcadiumArmor(new Item.Properties().durability(65001).humanoidArmor(TheTitansNeoArmorMaterials.HARCADIUM, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> VOID_SWORD = ITEMS.register("void_sword", () -> new ItemVoidSword(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID_AXE = ITEMS.register("void_axe", () -> new ItemVoidAxe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID_PICKAXE = ITEMS.register("void_pickaxe", () -> new ItemVoidPickaxe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID_SHOVEL = ITEMS.register("void_shovel", () -> new ItemVoidShovel(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID_HOE = ITEMS.register("void_hoe", () -> new ItemVoidHoe(new Item.Properties()));
	public static final DeferredHolder<Item, Item> VOID_HELMET = ITEMS.register("void_helmet", () -> new ItemVoidArmor(new Item.Properties().durability(1100001).humanoidArmor(TheTitansNeoArmorMaterials.VOID, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> VOID_CHESTPLATE = ITEMS.register("void_chestplate", () -> new ItemVoidArmor(new Item.Properties().durability(1600001).humanoidArmor(TheTitansNeoArmorMaterials.VOID, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> VOID_LEGGINGS = ITEMS.register("void_leggings", () -> new ItemVoidArmor(new Item.Properties().durability(1500001).humanoidArmor(TheTitansNeoArmorMaterials.VOID, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> VOID_BOOTS = ITEMS.register("void_boots", () -> new ItemVoidArmor(new Item.Properties().durability(1300001).humanoidArmor(TheTitansNeoArmorMaterials.VOID, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> ADMINIUM_SWORD = ITEMS.register("adminium_sword", () -> new ItemAdminiumSword(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, Item> ADMINIUM_AXE = ITEMS.register("adminium_axe", () -> new ItemAdminiumAxe(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, Item> ADMINIUM_PICKAXE = ITEMS.register("adminium_pickaxe", () -> new ItemAdminiumPickaxe(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, Item> ADMINIUM_SHOVEL = ITEMS.register("adminium_shovel", () -> new ItemAdminiumShovel(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, Item> ADMINIUM_HOE = ITEMS.register("adminium_hoe", () -> new ItemAdminiumHoe(new Item.Properties().rarity(GODLY_RARITY)));
	public static final DeferredHolder<Item, Item> ADMINIUM_HELMET = ITEMS.register("adminium_helmet", () -> new ItemAdminiumArmor(new Item.Properties().durability(1100000001).rarity(GODLY_RARITY).humanoidArmor(TheTitansNeoArmorMaterials.ADMINIUM, ArmorType.HELMET)));
	public static final DeferredHolder<Item, Item> ADMINIUM_CHESTPLATE = ITEMS.register("adminium_chestplate", () -> new ItemAdminiumArmor(new Item.Properties().durability(1600000001).rarity(GODLY_RARITY).humanoidArmor(TheTitansNeoArmorMaterials.ADMINIUM, ArmorType.CHESTPLATE)));
	public static final DeferredHolder<Item, Item> ADMINIUM_LEGGINGS = ITEMS.register("adminium_leggings", () -> new ItemAdminiumArmor(new Item.Properties().durability(1500000001).rarity(GODLY_RARITY).humanoidArmor(TheTitansNeoArmorMaterials.ADMINIUM, ArmorType.LEGGINGS)));
	public static final DeferredHolder<Item, Item> ADMINIUM_BOOTS = ITEMS.register("adminium_boots", () -> new ItemAdminiumArmor(new Item.Properties().durability(1300000001).rarity(GODLY_RARITY).humanoidArmor(TheTitansNeoArmorMaterials.ADMINIUM, ArmorType.BOOTS)));

	public static final DeferredHolder<Item, Item> CREEPY_WITHER_DOLL = ITEMS.register("creepy_wither_doll", () -> new ItemCreepyWitherDoll(new Item.Properties()));
	public static final DeferredHolder<Item, Item> RNG_RELINQUISHER = ITEMS.register("rng_relinquisher", () -> new ItemRngRelinquisher(new Item.Properties()));

	public static final DeferredHolder<Item, Item> WITHER_TURRET = ITEMS.register("wither_turret", () -> new ItemWitherTurret(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> WITHER_TURRET_GROUND = ITEMS.register("wither_turret_ground", () -> new ItemWitherTurretGround(new Item.Properties().rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> WITHER_TURRET_MORTAR = ITEMS.register("wither_turret_mortar", () -> new ItemWitherTurretMortar(new Item.Properties().rarity(Rarity.RARE)));

	public static final DeferredHolder<Item, Item> SPAWN_EGG_OMEGAFISH_MINION = ITEMS.register("spawn_egg_omegafish_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.OMEGAFISH_MINION.get())) /* 原颜色 7237230/3158064：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ZOMBIE_TITAN_MINION = ITEMS.register("spawn_egg_zombie_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.ZOMBIE_TITAN_MINION.get())) /* 原颜色 44975/7969893：26.1.2 颜色已移至 item model 贴图 */);

	public static final DeferredHolder<Item, Item> SPAWN_EGG_SKELETON_TITAN_MINION = ITEMS.register("spawn_egg_skeleton_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.SKELETON_TITAN_MINION.get())) /* 原颜色 12698049/4802889：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_WITHER_SKELETON_TITAN_MINION = ITEMS.register("spawn_egg_wither_skeleton_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.WITHER_SKELETON_TITAN_MINION.get())) /* 原颜色 1315860/4672845：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_CREEPER_TITAN_MINION = ITEMS.register("spawn_egg_creeper_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.CREEPER_TITAN_MINION.get())) /* 原颜色 894731/0：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_SPIDER_TITAN_MINION = ITEMS.register("spawn_egg_spider_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.SPIDER_TITAN_MINION.get())) /* 原颜色 3419431/11013646：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_CAVE_SPIDER_TITAN_MINION = ITEMS.register("spawn_egg_cave_spider_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.CAVE_SPIDER_TITAN_MINION.get())) /* 原颜色 803406/11013646：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ZOMBIFIED_PIGLIN_TITAN_MINION = ITEMS.register("spawn_egg_zombified_piglin_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.ZOMBIFIED_PIGLIN_TITAN_MINION.get())) /* 原颜色 15373203/5009705：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_BLAZE_TITAN_MINION = ITEMS.register("spawn_egg_blaze_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.BLAZE_TITAN_MINION.get())) /* 原颜色 16167425/16775294：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ENDER_COLOSSUS_MINION = ITEMS.register("spawn_egg_ender_colossus_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.ENDER_COLOSSUS_MINION.get())) /* 原颜色 1447446/0：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_GHAST_TITAN_MINION = ITEMS.register("spawn_egg_ghast_titan_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.GHAST_TITAN_MINION.get())) /* 原颜色 16382457/12369084：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_WITHERZILLA_MINION = ITEMS.register("spawn_egg_witherzilla_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.WITHERZILLA_MINION.get())) /* 原颜色 1315860/1842204：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ZOMBIE_TITAN_GIANT_MINION = ITEMS.register("spawn_egg_zombie_titan_giant_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.ZOMBIE_TITAN_GIANT_MINION.get())) /* 原颜色 44975/5870909：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_GHAST_GUARD_MINION = ITEMS.register("spawn_egg_ghast_guard_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.GHAST_GUARD_MINION.get())) /* 原颜色 16382457/12369084：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_WITHER_MINION = ITEMS.register("spawn_egg_wither_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.WITHER_MINION.get())) /* 原颜色 1315860/1842204：26.1.2 颜色已移至 item model 贴图 */);
	public static final DeferredHolder<Item, Item> SPAWN_EGG_DRAGON_MINION = ITEMS.register("spawn_egg_dragon_minion", () -> new SpawnEggItem(new Item.Properties().spawnEgg(TheTitansNeoEntities.DRAGON_MINION.get())) /* 原颜色 1447446/13369594：26.1.2 颜色已移至 item model 贴图 */);

	public static final DeferredHolder<Item, Item> SPAWN_EGG_SNOW_GOLEM_TITAN = ITEMS.register("spawn_egg_snow_golem_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "snow_golem_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_SLIME_TITAN = ITEMS.register("spawn_egg_slime_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "slime_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_MAGMACUBE_TITAN = ITEMS.register("spawn_egg_magmacube_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "magmacube_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_OMEGAFISH = ITEMS.register("spawn_egg_omegafish", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "omegafish")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ZOMBIE_TITAN = ITEMS.register("spawn_egg_zombie_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "zombie_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_SKELETON_TITAN = ITEMS.register("spawn_egg_skeleton_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "skeleton_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_WITHER_SKELETON_TITAN = ITEMS.register("spawn_egg_wither_skeleton_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "skeleton_titan"), 1, TheTitansNeoSpawns.SPAWNED_WITHER_SKELETON_TITAN));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_CREEPER_TITAN = ITEMS.register("spawn_egg_creeper_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "creeper_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_CHARGED_CREEPER_TITAN = ITEMS.register("spawn_egg_charged_creeper_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "creeper_titan"), 1, TheTitansNeoSpawns.SPAWNED_CHARGED_CREEPER_TITAN));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_SPIDER_TITAN = ITEMS.register("spawn_egg_spider_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "spider_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_CAVE_SPIDER_TITAN = ITEMS.register("spawn_egg_cave_spider_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "cave_spider_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_SPIDER_JOCKEY_TITAN = ITEMS.register("spawn_egg_spider_jockey_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "spider_titan"), 1, TheTitansNeoSpawns.SPAWNED_SPIDER_JOCKEY_TITAN));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ZOMBIFIED_PIGLIN_TITAN = ITEMS.register("spawn_egg_zombified_piglin_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "zombified_piglin_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_BLAZE_TITAN = ITEMS.register("spawn_egg_blaze_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "blaze_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_ENDER_COLOSSUS = ITEMS.register("spawn_egg_ender_colossus", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "ender_colossus")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_GHAST_TITAN = ITEMS.register("spawn_egg_ghast_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "ghast_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_IRON_GOLEM_TITAN = ITEMS.register("spawn_egg_iron_golem_titan", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "iron_golem_titan")));
	public static final DeferredHolder<Item, Item> SPAWN_EGG_WITHERZILLA = ITEMS.register("spawn_egg_witherzilla", () -> new ItemTitanSpawnEgg(new Item.Properties().rarity(Rarity.UNCOMMON), Identifier.tryBuild(TheTitansNeo.MODID, "witherzilla")));

	public static final DeferredHolder<Item, Item> MALGRUM_FRUIT = ITEMS.register("malgrum_fruit", () -> new ItemFoodMalgrum(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(20.0F).nutrition(20).alwaysEdible().build()).rarity(Rarity.EPIC)));
	public static final DeferredHolder<Item, Item> PLEASANT_BLADE_FLOWER = ITEMS.register("pleasant_blade_flower", () -> new ItemPleasantBladeFlower(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(2).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> PLEASANT_BLADE_BREW = ITEMS.register("pleasant_blade_brew", () -> new ItemPleasantBladeBrew(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(0.0F).nutrition(0).alwaysEdible().build())));
	public static final DeferredHolder<Item, Item> PLEASANT_BLADE_LEAF = ITEMS.register("pleasant_blade_leaf", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> GOLDEN_POTATO = ITEMS.register("golden_potato", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(6).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_GOLDEN_POTATO = ITEMS.register("enchanted_golden_potato", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(6).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> GOLDEN_BREAD = ITEMS.register("golden_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(5).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_GOLDEN_BREAD = ITEMS.register("enchanted_golden_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(5).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> GOLDEN_COOKIE = ITEMS.register("golden_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_GOLDEN_COOKIE = ITEMS.register("enchanted_golden_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> GOLDEN_MELON = ITEMS.register("golden_melon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_GOLDEN_MELON = ITEMS.register("enchanted_golden_melon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> GOLDEN_PUMPKIN_PIE = ITEMS.register("golden_pumpkin_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(8).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_GOLDEN_PUMPKIN_PIE = ITEMS.register("enchanted_golden_pumpkin_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(1.2F).nutrition(8).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_APPLE = ITEMS.register("diamond_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(4).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_APPLE = ITEMS.register("enchanted_diamond_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(4).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_POTATO = ITEMS.register("diamond_potato", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(6).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_POTATO = ITEMS.register("enchanted_diamond_potato", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(6).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_BREAD = ITEMS.register("diamond_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(5).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_BREAD = ITEMS.register("enchanted_diamond_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(5).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_COOKIE = ITEMS.register("diamond_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_COOKIE = ITEMS.register("enchanted_diamond_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_MELON = ITEMS.register("diamond_melon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_MELON = ITEMS.register("enchanted_diamond_melon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(2).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final DeferredHolder<Item, Item> DIAMOND_PUMPKIN_PIE = ITEMS.register("diamond_pumpkin_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(8).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), new MobEffectInstance(MobEffects.RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), new MobEffectInstance(MobEffects.SPEED, 2000, 0), new MobEffectInstance(MobEffects.HASTE, 2000, 0)))).build()).rarity(Rarity.RARE)));
	public static final DeferredHolder<Item, Item> ENCHANTED_DIAMOND_PUMPKIN_PIE = ITEMS.register("enchanted_diamond_pumpkin_pie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationModifier(2.4F).nutrition(8).alwaysEdible().build(), net.minecraft.world.item.component.Consumables.defaultFood().onConsume(new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(java.util.List.of(new MobEffectInstance(MobEffects.REGENERATION, 1200, 9), new MobEffectInstance(MobEffects.STRENGTH, 3000, 2), new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), new MobEffectInstance(MobEffects.SPEED, 6000, 1), new MobEffectInstance(MobEffects.HASTE, 6000, 1)))).build()).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

	public static void registerItems(IEventBus modEventBus) {
		ITEMS.register(modEventBus);
	}

	public static void registerRepairIngredients() {
		// 26.1.2: ToolMaterial 为不可变 record，setRepairIngredient 已不存在；
		// 修复材料改由数据包中的 repair_ingredient / 标签决定。
	}
}
