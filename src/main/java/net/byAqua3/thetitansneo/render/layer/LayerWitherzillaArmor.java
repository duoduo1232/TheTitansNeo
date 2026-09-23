package net.byAqua3.thetitansneo.render.layer;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.model.ModelWitherzilla;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerWitherzillaArmor extends RenderLayer<TitanRenderState, ModelWitherzilla> {

	private static final Identifier DISINTIGRATION = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/disintigration.png");
	private static final Identifier WITHERZILLA_AURA = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/witherzilla_aura.png");

	public ModelWitherzilla model = new ModelWitherzilla(0.0F);

	public LayerWitherzillaArmor(RenderLayerParent<TitanRenderState, ModelWitherzilla> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		float f = state.ageInTicks;
		int i = state.invulTime;
		Identifier texture = (i > 0 && (i > 300 || i / 10 % 2 != 1)) ? DISINTIGRATION : WITHERZILLA_AURA;
		RenderType renderType = RenderTypes.energySwirl(texture, f * 0.015F % 1.0F, f * 0.01F % 1.0F);
		submitNodeCollector.submitModel(this.model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, new Color(0.5F, 0.5F, 0.5F, 1.0F).getRGB(), null, state.outlineColor, null);
	}

	protected float xOffset(float tickCount) {
		return Mth.cos(tickCount * 0.02F) * 5.0F;
	}
}
