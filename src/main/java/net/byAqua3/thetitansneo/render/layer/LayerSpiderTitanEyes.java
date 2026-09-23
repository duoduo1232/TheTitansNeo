package net.byAqua3.thetitansneo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.model.ModelSpiderTitan;
import net.byAqua3.thetitansneo.render.RenderSpiderTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerSpiderTitanEyes extends RenderLayer<TitanRenderState, ModelSpiderTitan> {

	public LayerSpiderTitanEyes(RenderLayerParent<TitanRenderState, ModelSpiderTitan> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		// 26.1.2: renderToBuffer + VertexConsumer 改为 submitModel + RenderTypes.eyes。
		submitNodeCollector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.eyes(RenderSpiderTitan.SPIDER_TITAN_EYE), 15728640, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
	}
}
