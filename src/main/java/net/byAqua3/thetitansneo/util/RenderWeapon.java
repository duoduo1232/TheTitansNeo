package net.byAqua3.thetitansneo.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.InventoryMenu;

/**
 * 26.1.2: 原版移除了 {@code BlockModel.bakeFace} / {@code UnbakedGeometryHelper}，
 * 无法再把一张贴图烘焙成 BakedQuad。泰坦手持武器本来就是一张 2D 平面贴图，
 * 这里直接按 sprite 的 UV 生成一个 0..16 的正方形双面 quad。
 */
public class RenderWeapon {

	private static final float SIZE = 16.0F;

	public static void submitItemSprite(Identifier texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords) {
		TextureAtlas textureAtlas = Minecraft.getInstance().getModelManager().getAtlas(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS);
		TextureAtlasSprite sprite = textureAtlas.getSprite(texture);
		float u0 = sprite.getU0();
		float u1 = sprite.getU1();
		float v0 = sprite.getV0();
		float v1 = sprite.getV1();

		submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.translucentItemSheet(), (pose, vertexConsumer) -> {
			vertex(vertexConsumer, pose, 0.0F, 0.0F, u0, v1, lightCoords);
			vertex(vertexConsumer, pose, SIZE, 0.0F, u1, v1, lightCoords);
			vertex(vertexConsumer, pose, SIZE, SIZE, u1, v0, lightCoords);
			vertex(vertexConsumer, pose, 0.0F, SIZE, u0, v0, lightCoords);
			// 背面（镜像 UV），保证从两侧都可见。
			vertex(vertexConsumer, pose, SIZE, 0.0F, u1, v1, lightCoords);
			vertex(vertexConsumer, pose, 0.0F, 0.0F, u0, v1, lightCoords);
			vertex(vertexConsumer, pose, 0.0F, SIZE, u0, v0, lightCoords);
			vertex(vertexConsumer, pose, SIZE, SIZE, u1, v0, lightCoords);
		});
	}

	private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, float x, float y, float u, float v, int lightCoords) {
		vertexConsumer
			.addVertex(pose, x, y, 0.0F)
			.setColor(-1)
			.setUv(u, v)
			.setOverlay(OverlayTexture.NO_OVERLAY)
			.setLight(lightCoords)
			.setNormal(pose, 0.0F, 0.0F, 1.0F);
	}
}
