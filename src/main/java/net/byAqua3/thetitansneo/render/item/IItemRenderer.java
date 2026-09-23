package net.byAqua3.thetitansneo.render.item;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;

/**
 * 26.1.2: the item render pipeline was rebuilt around deferred submission. The old
 * {@code render(ItemStack, ItemDisplayContext, boolean, PoseStack, MultiBufferSource, int, int, BakedModel)}
 * contract is impossible to reproduce because {@code BakedModel}, {@code ItemRenderer} and direct
 * {@code MultiBufferSource} drawing no longer exist on the item path.
 * <p>
 * The signature now mirrors {@link net.minecraft.client.renderer.special.SpecialModelRenderer}: geometry is
 * recorded into a {@link SubmitNodeCollector} and drawn later, per {@link ItemDisplayContext}. Each
 * implementation still applies its own per-context {@code PoseStack} transform exactly as before, and the
 * caller no longer needs the legacy camera-transform hook because 26.1.2 applies the display transform to the
 * {@code ItemStackRenderState} layer before {@code submit} is ever called.
 */
public interface IItemRenderer {

	public void submit(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor);

	/**
	 * Reports the model extents used by the GUI to size the item. Mirrors
	 * {@link net.minecraft.client.renderer.special.SpecialModelRenderer#getExtents(Consumer)}.
	 */
	public void getExtents(Consumer<Vector3fc> output);
}
