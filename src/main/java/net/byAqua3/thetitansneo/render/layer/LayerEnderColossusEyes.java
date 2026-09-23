package net.byAqua3.thetitansneo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.model.ModelEnderColossus;
import net.byAqua3.thetitansneo.render.RenderEnderColossus;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerEnderColossusEyes extends RenderLayer<TitanRenderState, ModelEnderColossus> {

	public LayerEnderColossusEyes(RenderLayerParent<TitanRenderState, ModelEnderColossus> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		Identifier eyesTextures;
		if (state.animationID == 10 && state.deathTicks > 160) {
			eyesTextures = RenderEnderColossus.ENDER_COLOSSUS_EYES_DEAD;
		} else {
			eyesTextures = (state.eyeLaserTime >= 0) ? RenderEnderColossus.ENDER_COLOSSUS_EYES_DEAD : RenderEnderColossus.ENDER_COLOSSUS_EYES;
		}

		submitNodeCollector.submitModel(this.getParentModel(), state, poseStack, RenderTypes.eyes(eyesTextures), 15728640, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
	}
}
