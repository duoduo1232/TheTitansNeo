package net.byAqua3.thetitansneo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.model.ModelSlimeTitan;
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
public class LayerSlimeTitanOuter extends RenderLayer<TitanRenderState, ModelSlimeTitan> {

	public static final Identifier SLIME_LOCATION = Identifier.withDefaultNamespace("textures/entity/slime/slime.png");

	public ModelSlimeTitan model = new ModelSlimeTitan(0);

	public LayerSlimeTitanOuter(RenderLayerParent<TitanRenderState, ModelSlimeTitan> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		boolean flag = state.appearsGlowing() && state.isInvisible;
		if (!state.isInvisible || flag) {
			this.getParentModel().copyPropertiesTo(this.model);
			// 26.1.2: 用 appearsGlowing() 取代 Minecraft.shouldEntityAppearGlowing(entity)。
			net.minecraft.client.renderer.rendertype.RenderType renderType = flag
				? RenderTypes.outline(this.getTextureLocation())
				: RenderTypes.entityTranslucent(this.getTextureLocation());
			submitNodeCollector.submitModel(this.model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
		}
	}

	protected Identifier getTextureLocation() {
		return SLIME_LOCATION;
	}
}
