package net.byAqua3.thetitansneo.loader;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

/**
 * 26.1.2 迁移说明
 * ---------------
 * 1.21.1 的写法是「拼 CompositeState」：
 *     RenderType.create(name, fmt, mode, size, ..., CompositeState.builder()
 *         .setShaderState(POSITION_COLOR_SHADER).setWriteMaskState(COLOR_WRITE)
 *         .setTransparencyState(LIGHTNING_TRANSPARENCY).setOutputState(WEATHER_TARGET)...)
 *
 * 26.1.2 把渲染状态重构成了「RenderPipeline + RenderSetup」，拼装器整条链被删掉了。
 * 这里逐字段对上 vanilla 现成的管线：
 *
 *   WITHERZILLA_RAYS       : POSITION_COLOR + TRIANGLES + LIGHTNING 混合 + WEATHER_TARGET
 *                            -> 与 vanilla 的 DRAGON_RAYS 完全同构（RenderPipelines.DRAGON_RAYS）
 *   WITHERZILLA_RAYS_DEPTH : POSITION     + TRIANGLES + 只写深度          + WEATHER_TARGET
 *                            -> 与 vanilla 的 DRAGON_RAYS_DEPTH 完全同构
 *
 * 因为语义一致，这里**直接复用 vanilla 的 RenderType**，不再自造：
 * 少一份自维护的管线、也不会因为 GL 状态拼错而在运行时静默画不出来。
 * （唯一差别是 vanilla 那两处没设 sortOnUpload/WEATHER_TARGET 的完整组合，
 *   但 WEATHER_TARGET 输出在 26.1.2 由 RenderSetup 控制，见下方显式指定。）
 */
public class TheTitansNeoRenderTypes {

	/** 末影龙光柱同款：POSITION_COLOR + 闪电混合，画在天候目标上。 */
	public static final RenderType WITHERZILLA_RAYS = createRays("witherzilla_rays", false);

	/** 只写深度的版本，用于遮挡。 */
	public static final RenderType WITHERZILLA_RAYS_DEPTH = createRays("witherzilla_rays_depth", true);

	/**
	 * 26.1.2 里自定义 RenderType 的入口仍然存在：RenderType.create(String, RenderSetup)。
	 * 这里用 vanilla 同款管线 + WEATHER_TARGET 输出目标复刻原语义。
	 */
	private static RenderType createRays(String name, boolean depthOnly) {
		var pipeline = depthOnly
				? net.minecraft.client.renderer.RenderPipelines.DRAGON_RAYS_DEPTH
				: net.minecraft.client.renderer.RenderPipelines.DRAGON_RAYS;
		return RenderType.create(
				name,
				net.minecraft.client.renderer.rendertype.RenderSetup.builder(pipeline)
						.setOutputTarget(net.minecraft.client.renderer.rendertype.OutputTarget.WEATHER_TARGET)
						.createRenderSetup());
	}

	/** 供渲染层按需取用（与原 API 保持一致的访问点）。 */
	public static RenderType witherzillaRays() {
		return WITHERZILLA_RAYS;
	}

	public static RenderType witherzillaRaysDepth() {
		return WITHERZILLA_RAYS_DEPTH;
	}
}
