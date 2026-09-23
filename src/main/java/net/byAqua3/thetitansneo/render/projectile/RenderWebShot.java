package net.byAqua3.thetitansneo.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.entity.projectile.EntityWebShot;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;

public class RenderWebShot extends EntityRenderer<EntityWebShot, EntityRenderState> {

	// 26.1.2: 方块模型改为在 extract 阶段解析进 BlockModelRenderState，submit 阶段只提交。
	private static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
	private final BlockModelResolver blockModelResolver;

	public RenderWebShot(Context context) {
		super(context);
		this.blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}

	@Override
	public void submit(EntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		net.minecraft.client.renderer.block.BlockModelRenderState cobweb = new net.minecraft.client.renderer.block.BlockModelRenderState();
		this.blockModelResolver.update(cobweb, Blocks.COBWEB.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);

		poseStack.pushPose();
		poseStack.scale(4.0F, 4.0F, 4.0F);
		poseStack.translate(0.0F, 0.3F, 0.0F);
		poseStack.translate(-0.5F, -0.5F, -0.5F);
		cobweb.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
		poseStack.popPose();

		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
