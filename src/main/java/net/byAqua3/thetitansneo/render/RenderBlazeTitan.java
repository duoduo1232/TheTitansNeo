package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityBlazeTitan;
import net.byAqua3.thetitansneo.model.ModelBlazeTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBlazeTitan extends LivingEntityRenderer<EntityBlazeTitan, TitanRenderState, ModelBlazeTitan> {

	public static final Identifier BLAZE_TITAN = Identifier.withDefaultNamespace("textures/entity/blaze.png");

	public RenderBlazeTitan(Context context) {
		super(context, new ModelBlazeTitan(0.0F), 0.5F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerBlazeTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityBlazeTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		if (i > 900) {
			poseStack.scale(1.0F, -1.0F, 1.0F);
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 1.26F, 0.0F);
	}

	@Override
	protected boolean shouldShowName(EntityBlazeTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return BLAZE_TITAN;
	}
}
