package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.entity.minion.EntityZombieTitanMinion;
import net.byAqua3.thetitansneo.render.state.MinionHumanoidRenderState;
import net.minecraft.client.model.monster.zombie.ZombieVillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderZombieVillagerTitanMinion extends HumanoidMobRenderer<EntityZombieTitanMinion, MinionHumanoidRenderState, ZombieVillagerModel<MinionHumanoidRenderState>> {

	public static final Identifier ZOMBIE_VILLAGER = Identifier.withDefaultNamespace("textures/entity/zombie_villager/zombie_villager.png");
	public static final Identifier ZOMBIE_VILLAGER_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_villager_priest.png");
	public static final Identifier ZOMBIE_VILLAGER_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_villager_zealot.png");
	public static final Identifier ZOMBIE_VILLAGER_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_villager_bishop.png");
	public static final Identifier ZOMBIE_VILLAGER_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_villager_templar.png");

	public RenderZombieVillagerTitanMinion(EntityRendererProvider.Context context) {
		super(context, new ZombieVillagerModel<MinionHumanoidRenderState>(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER)), 0.5F);
		this.addLayer(
			new HumanoidArmorLayer<>(
				this,
				ArmorModelSet.bake(ModelLayers.ZOMBIE_VILLAGER_ARMOR, context.getModelSet(), ZombieVillagerModel::new),
				ArmorModelSet.bake(ModelLayers.ZOMBIE_VILLAGER_BABY_ARMOR, context.getModelSet(), ZombieVillagerModel::new),
				context.getEquipmentRenderer()
			)
		);
	}

	@Override
	public MinionHumanoidRenderState createRenderState() {
		return new MinionHumanoidRenderState();
	}

	@Override
	public void extractRenderState(EntityZombieTitanMinion entity, MinionHumanoidRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.texture = villagerTexture(entity);
	}

	@Override
	public Identifier getTextureLocation(MinionHumanoidRenderState state) {
		return state.texture != null ? state.texture : ZOMBIE_VILLAGER;
	}

	/** 根据 minion 等级选择村民僵尸的纹理。 */
	public static Identifier villagerTexture(EntityZombieTitanMinion entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return ZOMBIE_VILLAGER_PRIEST;
			case ZEALOT:
				return ZOMBIE_VILLAGER_ZEALOT;
			case BISHOP:
				return ZOMBIE_VILLAGER_BISHOP;
			case TEMPLAR:
				return ZOMBIE_VILLAGER_TEMPLAR;
			default:
				return ZOMBIE_VILLAGER;
			}
		}
		return ZOMBIE_VILLAGER;
	}
}
