package net.byAqua3.thetitansneo.render.layer;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.model.ModelCreeperTitan;
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
public class LayerCreeperTitanArmor extends RenderLayer<TitanRenderState, ModelCreeperTitan> {

	private static final Identifier DISINTIGRATION = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/creeper_titan_charge.png");
	public ModelCreeperTitan model = new ModelCreeperTitan(1.6F);

	public LayerCreeperTitanArmor(RenderLayerParent<TitanRenderState, ModelCreeperTitan> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		if (state.isCharged) {
			float f = state.ageInTicks;
			// 26.1.2: 改用 SubmitNodeCollector 提交模型，实体数据从 state 读取。
			RenderType renderType = RenderTypes.energySwirl(DISINTIGRATION, this.xOffset(f) % 1.0F, f * 0.01F % 1.0F);
			submitNodeCollector.submitModel(this.model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, color(state, f), null, state.outlineColor, null);
		}
	}

	protected int color(TitanRenderState state, float f) {
		return new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB();
	}

	protected float xOffset(float tickCount) {
		return Mth.cos(tickCount * 0.02F) * 5.0F;
	}
}
