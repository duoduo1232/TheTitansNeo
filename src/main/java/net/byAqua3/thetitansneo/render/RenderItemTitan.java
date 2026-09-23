package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.byAqua3.thetitansneo.entity.titan.EntityItemTitan;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderItemTitan extends EntityRenderer<EntityItemTitan, ItemEntityRenderState> {

	private final RandomSource random = RandomSource.create();
	private final ItemModelResolver itemModelResolver;

	public RenderItemTitan(Context context) {
		super(context);
		this.itemModelResolver = context.getItemModelResolver();
		this.shadowRadius = 0.15F * 16.0F;
		this.shadowStrength = 0.75F;
	}

	@Override
	public ItemEntityRenderState createRenderState() {
		return new ItemEntityRenderState();
	}

	@Override
	public void extractRenderState(EntityItemTitan entity, ItemEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.bobOffset = entity.bobOffs;
		state.shouldBob = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(entity.getItem()).shouldBobAsEntity(entity.getItem());
		state.shouldSpread = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(entity.getItem()).shouldSpreadAsEntity(entity.getItem());
		state.extractItemGroupRenderState(entity, entity.getItem(), this.itemModelResolver);
	}

	@Override
	public void submit(ItemEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		// 26.1.2: 物品渲染改由 ItemStackRenderState + submitMultipleFromCount 完成。
		if (state.item.isEmpty()) {
			return;
		}
		poseStack.pushPose();
		AABB boundingBox = state.item.getModelBoundingBox();
		float minOffsetY = -((float) boundingBox.minY) + 0.0625F;
		float bob = state.shouldBob ? Mth.sin(state.ageInTicks / 10.0F + state.bobOffset) * 0.1F + 0.1F : 0.0F;
		poseStack.translate(0.0F, 16.0F * (bob + minOffsetY) - 0.1F, 0.0F);
		poseStack.scale(16.0F, 16.0F, 16.0F);
		float spin = ItemEntity.getSpin(state.ageInTicks, state.bobOffset);
		poseStack.mulPose(Axis.YP.rotation(spin));
		ItemEntityRenderer.submitMultipleFromCount(poseStack, submitNodeCollector, state.lightCoords, state, this.random, boundingBox);
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
