package net.byAqua3.thetitansneo.render.minion;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.model.ModelGhastGuard;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Ghast;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderGhastTitanMinion extends MobRenderer<Ghast, TitanRenderState, ModelGhastGuard> {

	public static final Identifier GHAST = Identifier.withDefaultNamespace("textures/entity/ghast/ghast.png");
	public static final Identifier GHAST_SHOOTING = Identifier.withDefaultNamespace("textures/entity/ghast/ghast_shooting.png");
	public static final Identifier GHAST_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_priest.png");
	public static final Identifier GHAST_PRIEST_SHOOTING = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_priest_shooting.png");
	public static final Identifier GHAST_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_zealot.png");
	public static final Identifier GHAST_ZEALOT_SHOOTING = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_zealot_shooting.png");
	public static final Identifier GHAST_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_bishop.png");
	public static final Identifier GHAST_BISHOP_SHOOTING = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_bishop_shooting.png");
	public static final Identifier GHAST_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_templar.png");
	public static final Identifier GHAST_TEMPLAR_SHOOTING = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/ghast/ghast_templar_shooting.png");

	public RenderGhastTitanMinion(EntityRendererProvider.Context context) {
		super(context, new ModelGhastGuard(), 3.0F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(Ghast entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isCharged = entity.isCharging();
		// 26.1.2: 纹理按 RenderState 取，所以在 extract 阶段选好充能/普通纹理。
		state.texture = getGhastTexture(entity);
	}

	private static Identifier getGhastTexture(Ghast entity) {
		boolean charging = entity.isCharging();
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return charging ? GHAST_PRIEST_SHOOTING : GHAST_PRIEST;
			case ZEALOT:
				return charging ? GHAST_ZEALOT_SHOOTING : GHAST_ZEALOT;
			case BISHOP:
				return charging ? GHAST_BISHOP_SHOOTING : GHAST_BISHOP;
			case TEMPLAR:
				return charging ? GHAST_TEMPLAR_SHOOTING : GHAST_TEMPLAR;
			default:
				return charging ? GHAST_SHOOTING : GHAST;
			}
		}
		return charging ? GHAST_SHOOTING : GHAST;
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		poseStack.scale(4.5F, 4.5F, 4.5F);
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
