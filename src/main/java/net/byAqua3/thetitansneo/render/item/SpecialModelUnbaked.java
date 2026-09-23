package net.byAqua3.thetitansneo.render.item;

import com.mojang.serialization.MapCodec;

import net.minecraft.client.renderer.special.SpecialModelRenderer;

/**
 * 26.1.2: shared "unbaked" counterpart for the mod's item renderers. It binds the wildcard-free
 * {@link SpecialModelRenderer.BakingContext} to an {@link IItemRenderer} so each renderer can expose a
 * {@code MapCodec} that NeoForge's {@code RegisterSpecialModelRendererEvent} can register.
 */
public interface SpecialModelUnbaked extends SpecialModelRenderer.Unbaked<IItemRenderer> {

	@Override
	MapCodec<? extends SpecialModelUnbaked> type();

	@Override
	IItemRenderer bake(SpecialModelRenderer.BakingContext context);
}
