package net.byAqua3.thetitansneo.render.layer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.loader.TheTitansNeoConfigs;
import net.byAqua3.thetitansneo.model.ModelSkeletonTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.byAqua3.thetitansneo.util.RenderWeapon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerSkeletonTitanItemInHand extends RenderLayer<TitanRenderState, ModelSkeletonTitan> {

	public LayerSkeletonTitanItemInHand(RenderLayerParent<TitanRenderState, ModelSkeletonTitan> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		if (TheTitansNeoConfigs.getBoolean(TheTitansNeoConfigs.titanWeaponOldModel, false)) {
			if (state.skeletonType == 1) {
				if (!this.getParentModel().heldItem2.visible) {
					this.getParentModel().heldItem2.visible = true;
				}
			} else {
				if (!this.getParentModel().heldItem.visible) {
					this.getParentModel().heldItem.visible = true;
				}
			}
			return;
		}

		if (state.skeletonType == 1) {
			this.getParentModel().heldItem2.visible = false;
		} else {
			this.getParentModel().heldItem.visible = false;
		}
		poseStack.pushPose();
		if (state.isBaby) {
			poseStack.translate(0.0F, 0.75F, 0.0F);
			poseStack.scale(0.5F, 0.5F, 0.5F);
		}
		this.getParentModel().hips.translateAndRotate(poseStack);

		poseStack.pushPose();

		this.getParentModel().spine1.translateAndRotate(poseStack);
		this.getParentModel().spine2.translateAndRotate(poseStack);
		this.getParentModel().spine3.translateAndRotate(poseStack);
		this.getParentModel().spine4.translateAndRotate(poseStack);
		this.getParentModel().spine5.translateAndRotate(poseStack);
		this.getParentModel().spine6.translateAndRotate(poseStack);
		this.getParentModel().ribs.translateAndRotate(poseStack);
		this.getParentModel().rightShoulder.translateAndRotate(poseStack);
		this.getParentModel().rightForearm.translateAndRotate(poseStack);

		if (state.skeletonType == 1) {
			this.getParentModel().heldItem2.translateAndRotate(poseStack);
		} else {
			this.getParentModel().heldItem.translateAndRotate(poseStack);
		}

		if (state.skeletonType == 1) {
			poseStack.mulPose(Axis.XP.rotationDegrees(-145.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			poseStack.translate(0.425F, -0.5F, 0.0F);
		} else {
			poseStack.mulPose(Axis.XP.rotationDegrees(35.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			poseStack.translate(0.425F, -0.8F, 0.2F);
		}

		poseStack.scale(0.85F, 0.85F, 0.85F);
		poseStack.translate(0.0F, 0.25F, 0.03125F);
		poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(55.0F));
		Identifier texture = (state.skeletonType == 1)
			? Identifier.tryBuild(TheTitansNeo.MODID, "item/stone_sword_256")
			: ((state.attackTimer < 20 && state.attackTimer >= 10) ? Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_pulling_0_256")
				: ((state.attackTimer < 30 && state.attackTimer >= 20) ? Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_pulling_1_256")
					: ((state.attackTimer >= 30) ? Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_pulling_2_256")
						: (state.isStunned ? Identifier.tryBuild(TheTitansNeo.MODID, "item/broken_bow_256")
							: Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_256")))));
		// 26.1.2: 旧的 BakedQuad 烘焙 API 已移除，直接按 sprite UV 提交平面 quad。
		RenderWeapon.submitItemSprite(texture, poseStack, submitNodeCollector, lightCoords);

		poseStack.popPose();

		poseStack.popPose();
	}
}
