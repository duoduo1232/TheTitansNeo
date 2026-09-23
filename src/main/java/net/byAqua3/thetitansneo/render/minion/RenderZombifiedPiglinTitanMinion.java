package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.model.ModelZombifiedPiglinTitanMinion;
import net.byAqua3.thetitansneo.render.state.MinionHumanoidRenderState;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderZombifiedPiglinTitanMinion extends HumanoidMobRenderer<ZombifiedPiglin, MinionHumanoidRenderState, ModelZombifiedPiglinTitanMinion> {

	public static final Identifier ZOMBIFIED_PIGLIN = Identifier.withDefaultNamespace("textures/entity/piglin/zombified_piglin.png");
	public static final Identifier ZOMBIFIED_PIGLIN_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_pigman_priest.png");
	public static final Identifier ZOMBIFIED_PIGLIN_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_pigman_zealot.png");
	public static final Identifier ZOMBIFIED_PIGLIN_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_pigman_bishop.png");
	public static final Identifier ZOMBIFIED_PIGLIN_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/zombie/zombie_pigman_templar.png");

	public RenderZombifiedPiglinTitanMinion(EntityRendererProvider.Context context, ModelLayerLocation layer, ArmorModelSet<ModelLayerLocation> armorSet, boolean noRightEar) {
		super(context, createModel(context.getModelSet(), layer, noRightEar), 0.5F, 1.0019531F, 1.0F, 1.0019531F);
		this.addLayer(
			new HumanoidArmorLayer<>(
				this,
				ArmorModelSet.bake(armorSet, context.getModelSet(), ModelZombifiedPiglinTitanMinion::new),
				ArmorModelSet.bake(armorSet, context.getModelSet(), ModelZombifiedPiglinTitanMinion::new),
				context.getEquipmentRenderer()
			)
		);
	}

	public RenderZombifiedPiglinTitanMinion(EntityRendererProvider.Context context) {
		this(context, ModelLayers.ZOMBIFIED_PIGLIN, ModelLayers.ZOMBIFIED_PIGLIN_ARMOR, true);
	}

	private static ModelZombifiedPiglinTitanMinion createModel(EntityModelSet modelSet, ModelLayerLocation layer, boolean noRightEar) {
		ModelZombifiedPiglinTitanMinion piglinModel = new ModelZombifiedPiglinTitanMinion(modelSet.bakeLayer(layer));
		if (noRightEar) {
			piglinModel.rightEar.visible = false;
		}
		return piglinModel;
	}

	@Override
	public MinionHumanoidRenderState createRenderState() {
		return new MinionHumanoidRenderState();
	}

	@Override
	public void extractRenderState(ZombifiedPiglin entity, MinionHumanoidRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: 纹理按 RenderState 取，所以在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(ZombifiedPiglin entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return ZOMBIFIED_PIGLIN_PRIEST;
			case ZEALOT:
				return ZOMBIFIED_PIGLIN_ZEALOT;
			case BISHOP:
				return ZOMBIFIED_PIGLIN_BISHOP;
			case TEMPLAR:
				return ZOMBIFIED_PIGLIN_TEMPLAR;
			default:
				return ZOMBIFIED_PIGLIN;
			}
		}
		return ZOMBIFIED_PIGLIN;
	}

	@Override
	public Identifier getTextureLocation(MinionHumanoidRenderState state) {
		return state.texture != null ? state.texture : ZOMBIFIED_PIGLIN;
	}
}
