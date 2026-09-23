package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.entity.minion.EntityZombieTitanMinion;
import net.byAqua3.thetitansneo.render.state.MinionZombieRenderState;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderZombieTitanMinion extends AbstractZombieRenderer<EntityZombieTitanMinion, MinionZombieRenderState, ZombieModel<MinionZombieRenderState>> {

	public static final Identifier ZOMBIE = Identifier.withDefaultNamespace("textures/entity/zombie/zombie.png");
	public static final Identifier ZOMBIE_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_priest.png");
	public static final Identifier ZOMBIE_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_zealot.png");
	public static final Identifier ZOMBIE_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_bishop.png");
	public static final Identifier ZOMBIE_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_templar.png");

	private final RenderZombieVillagerTitanMinion villagerRenderer;

	public RenderZombieTitanMinion(EntityRendererProvider.Context context) {
		this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_BABY, ModelLayers.ZOMBIE_ARMOR, ModelLayers.ZOMBIE_BABY_ARMOR);
	}

	public RenderZombieTitanMinion(
		EntityRendererProvider.Context context,
		ModelLayerLocation zombieLayer,
		ModelLayerLocation babyZombieLayer,
		ArmorModelSet<ModelLayerLocation> armorSet,
		ArmorModelSet<ModelLayerLocation> babyArmorSet
	) {
		super(
			context,
			new ZombieModel<MinionZombieRenderState>(context.bakeLayer(zombieLayer)),
			new ZombieModel<MinionZombieRenderState>(context.bakeLayer(babyZombieLayer)),
			ArmorModelSet.bake(armorSet, context.getModelSet(), ZombieModel::new),
			ArmorModelSet.bake(babyArmorSet, context.getModelSet(), ZombieModel::new)
		);
		this.villagerRenderer = new RenderZombieVillagerTitanMinion(context);
	}

	@Override
	public MinionZombieRenderState createRenderState() {
		return new MinionZombieRenderState();
	}

	// 26.1.2: 原实现按实体是否为村民在 render 里切换另一套渲染器；
	// 新架构下渲染器不再持有实体，改为在 extract 阶段选好对应的纹理。
	@Override
	public void extractRenderState(EntityZombieTitanMinion entity, MinionZombieRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		if (entity.isVillager()) {
			state.texture = RenderZombieVillagerTitanMinion.villagerTexture(entity);
		} else {
			state.texture = getMinionTexture(entity);
		}
	}

	private static Identifier getMinionTexture(EntityZombieTitanMinion entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return ZOMBIE_PRIEST;
			case ZEALOT:
				return ZOMBIE_ZEALOT;
			case BISHOP:
				return ZOMBIE_BISHOP;
			case TEMPLAR:
				return ZOMBIE_TEMPLAR;
			default:
				return ZOMBIE;
			}
		}
		return ZOMBIE;
	}

	@Override
	public Identifier getTextureLocation(MinionZombieRenderState state) {
		return state.texture != null ? state.texture : ZOMBIE;
	}

	@Override
	protected boolean isShaking(MinionZombieRenderState state) {
		return super.isShaking(state) || state.isConverting;
	}
}
