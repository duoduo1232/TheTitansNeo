package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityWitherzilla;
import net.byAqua3.thetitansneo.model.ModelWitherzilla;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWitherzilla extends LivingEntityRenderer<EntityWitherzilla, TitanRenderState, ModelWitherzilla> {

	public static final Identifier WITHERZILLA = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/witherzilla.png");
	public static final Identifier WITHERZILLA_OMEGA = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/witherzilla_omega.png");

	public RenderWitherzilla(Context context) {
		super(context, new ModelWitherzilla(0.0F), 1.0F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerWitherzillaArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityWitherzilla entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.affectTicks = entity.affectTicks;
		state.isInOmegaForm = entity.isInOmegaForm();
		state.worldTicks = (int) entity.level().getOverworldClockTime();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = state.isInOmegaForm ? 256.0F : 128.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.01F, 0.0F);
	}

	@Override
	protected boolean shouldShowName(EntityWitherzilla entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public boolean shouldRender(EntityWitherzilla livingEntity, net.minecraft.client.renderer.culling.Frustum camera, double cameraX, double cameraY, double cameraZ) {
		return true;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return WITHERZILLA;
	}
}
