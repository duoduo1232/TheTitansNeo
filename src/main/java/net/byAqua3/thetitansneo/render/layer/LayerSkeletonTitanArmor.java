package net.byAqua3.thetitansneo.render.layer;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntitySkeletonTitan;
import net.byAqua3.thetitansneo.model.ModelSkeletonTitan;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerSkeletonTitanArmor extends RenderLayer<TitanRenderState, ModelSkeletonTitan> {
	
	private static final Identifier DISINTIGRATION = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/disintigration.png");
	public ModelSkeletonTitan model = new ModelSkeletonTitan(0.1F);

	public LayerSkeletonTitanArmor(RenderLayerParent<TitanRenderState, ModelSkeletonTitan> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, TitanRenderState state, float yRot, float xRot) {
		if (state.isArmored) {
			float f = state.ageInTicks;
			RenderType renderType = RenderTypes.energySwirl(DISINTIGRATION, this.xOffset(f) % 1.0F, f * 0.01F % 1.0F);
			this.model.heldItem.visible = false;
			this.model.heldItem2.visible = false;
			submitNodeCollector.submitModel(this.model, state, poseStack, renderType, packedLight, OverlayTexture.NO_OVERLAY, color(state, f), null, state.outlineColor, null);
		}
	}

	protected float xOffset(float tickCount) {
		return Mth.cos(tickCount * 0.02F) * 3.0F;
	}
}
