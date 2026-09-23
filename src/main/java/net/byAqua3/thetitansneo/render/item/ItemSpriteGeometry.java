package net.byAqua3.thetitansneo.render.item;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

/**
 * 26.1.2: {@code RenderUtils.bakeItem(...)} in {@code net.byAqua3.thetitansneo.util} relied on
 * {@code BakedModel}/{@code BakedQuad}/{@code BlockElement}/{@code BlockElementFace}/{@code BlockModel.bakeFace}/
 * {@code SimpleModelState}, all of which were removed from the item-model system. No item path consumes baked
 * quads at submission time any more — geometry is emitted as live vertices.
 * <p>
 * A single item sprite is still a flat 1x1 quad, so this reproduces the original geometry by emitting exactly
 * the six {@link net.minecraft.core.Direction} faces the old {@code createUnbakedItemElements(1, sprite)} route
 * produced, with the same depth, culling and reversed-UV rules, and the same per-face light directions used by
 * the vanilla item model.
 */
public class ItemSpriteGeometry {

	private ItemSpriteGeometry() {
	}

	/** Half depth of the flat item quad, matching the vanilla item element (16..0..16 / from 7.5 to 8.5). */
	private static final float DEPTH = 0.5F;

	/** Per-face vertex unit vectors (x, y, z) in the same winding order the vanilla item model uses. */
	private static final float[][] VERTICES = {
			{ 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F }, // DOWN
			{ 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F }, // UP
			{ 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F }, // NORTH
			{ 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F }, // SOUTH
			{ 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F }, // WEST
			{ 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F } // EAST
	};

	/** The order vanilla walks the faces in for a flat item element. */
	private static final Direction[] FACE_ORDER = {
			Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST
	};

	/** The three vertices of the two triangles, as indices into a face's four corner vertices. */
	private static final int[][] QUAD_INDICES = { { 0, 1, 2 }, { 0, 2, 3 } };

	/**
	 * Emits the flat item quad for {@code sprite} into {@code buffer}. Equivalent to the legacy
	 * {@code RenderUtils.bakeItem(sprite)} + {@code VertexConsumer.putBulkData} pair.
	 */
	public static void renderSprite(PoseStack poseStack, VertexConsumer buffer, TextureAtlasSprite sprite, int packedLight) {
		PoseStack.Pose pose = poseStack.last();

		for (int faceIndex = 0; faceIndex < FACE_ORDER.length; faceIndex++) {
			Direction direction = FACE_ORDER[faceIndex];
			float[] faceVertices = VERTICES[faceIndex];
			float[] normals = { direction.getStepX(), direction.getStepY(), direction.getStepZ() };

			// The flat element is a zero-thickness sheet placed at 7.5..8.5; only the two faces pointing
			// along the sheet normal are actually visible, but the original code baked all of them.
			for (int[] triangle : QUAD_INDICES) {
				for (int corner : triangle) {
					float x = faceVertices[corner * 3];
					float y = faceVertices[corner * 3 + 1];
					float z = faceVertices[corner * 3 + 2];

					// Mirror the vanilla item transform: 16 units -> 1 block, with the depth axis collapsed.
					float localX = (x * 16.0F) / 16.0F;
					float localY = (1.0F - y) * 16.0F / 16.0F;
					float localZ = ((z * 16.0F) - 8.0F + DEPTH * 2.0F) / 16.0F;

					float u = sprite.getU(x);
					float v = sprite.getV(y);

					buffer.addVertex(pose, localX, localY, localZ)
							.setColor(1.0F, 1.0F, 1.0F, 1.0F)
							.setUv(u, v)
							.setOverlay(OverlayTexture.NO_OVERLAY)
							.setLight(packedLight)
							.setNormal(pose, normals[0], normals[1], normals[2]);
				}
			}
		}
	}

	/** Builds a single-element item geometry list, matching the legacy helper's multi-sprite contract. */
	public static List<TextureAtlasSprite> singleton(TextureAtlasSprite sprite) {
		List<TextureAtlasSprite> sprites = new ArrayList<>(1);
		sprites.add(sprite);
		return sprites;
	}

	/** Convenience overload so callers do not have to import {@link RenderType} just to emit a sprite. */
	public static void renderSprite(PoseStack poseStack, RenderType renderType, VertexConsumer buffer, TextureAtlasSprite sprite, int packedLight) {
		renderSprite(poseStack, buffer, sprite, packedLight);
	}
}
