package net.byAqua3.thetitansneo.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.byAqua3.thetitansneo.render.item.IItemRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;

/**
 * 26.1.2: this class used to {@code @Inject} at HEAD of
 * {@code net.minecraft.client.renderer.entity.ItemRenderer#render(ItemStack, ItemDisplayContext, boolean,
 * PoseStack, MultiBufferSource, int, int, BakedModel)}, cancel it, and dispatch to the mod's {@code IItemRenderer}
 * map after applying NeoForge's {@code ClientHooks.handleCameraTransforms}.
 * <p>
 * That whole route is gone: {@code net.minecraft.client.renderer.entity.ItemRenderer} no longer exists as a
 * class (only {@code ItemEntityRenderer}, {@code ThrownItemRenderer} and the GUI's
 * {@code OversizedItemRenderer} remain), {@code BakedModel} left the item path, and items are no longer drawn
 * imperatively. An item is described as an {@link ItemStackRenderState} and its layers are submitted as deferred
 * nodes, so there is no imperative method left to intercept and no camera-transform hook to apply —
 * {@code ItemStackRenderState.LayerRenderState.applyTransform} now performs the display transform itself.
 * <p>
 * The extension point moved to NeoForge's
 * {@link net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent}, fired from
 * {@code SpecialModelRenderers.bootstrap()} on the mod event bus. The mod's renderers are registered there; see
 * the note written alongside this port for the client setup method and the resource files that select them.
 * <p>
 * The mixin body is intentionally empty: it still applies cleanly against {@code ItemStackRenderState} (which is
 * on the live item path) so the mixin config entry stays valid, while implementing the mod's rendering through
 * {@link IItemRenderer} / {@code SpecialModelRenderer} instead of by patching vanilla.
 */
@Mixin({ ItemStackRenderState.class })
public class MixinItemRenderer {

	// 26.1.2: no injection points remain. The legacy hijack is replaced by the special model renderer
	// registration event; see the class javadoc above.
}
