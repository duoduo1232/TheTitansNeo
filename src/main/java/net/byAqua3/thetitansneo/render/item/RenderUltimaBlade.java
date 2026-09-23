package net.byAqua3.thetitansneo.render.item;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.model.ModelUltimaBlade;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;

/**
 * 26.1.2: migrated from the deleted NeoForge {@code IItemRenderer} hook to
 * {@link net.minecraft.client.renderer.special.SpecialModelRenderer}. All per-context transforms are unchanged;
 * the model is submitted through the deferred node collector instead of drawing into a {@code MultiBufferSource}
 * directly, and the texture is resolved through the atlas {@link SpriteGetter}.
 */
public class RenderUltimaBlade implements IItemRenderer {

	public ModelUltimaBlade model = new ModelUltimaBlade();

	private final SpriteGetter sprites;

	public RenderUltimaBlade(SpriteGetter sprites) {
		this.sprites = sprites;
	}

	@Override
	public void submit(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		Identifier texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/ultima_blade");
		// 26.1.2: Material.buffer(MultiBufferSource, ...) is gone; this is replaced by a submitted model.
		final SpriteId spriteId = new SpriteId(InventoryMenu.BLOCK_ATLAS, texture);
		final ModelUltimaBlade bladeModel = this.model;

		switch (context) {
		case FIRST_PERSON_LEFT_HAND:
			break;
		case FIRST_PERSON_RIGHT_HAND:
			poseStack.pushPose();
			poseStack.scale(2.0F, 2.0F, 2.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-116.5F));
			poseStack.mulPose(Axis.XP.rotationDegrees(110.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(18.0F));
			poseStack.translate(0.0F, -1.3F, -1.2F);
			poseStack.mulPose(Axis.YP.rotationDegrees(-107.2F));
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case THIRD_PERSON_LEFT_HAND:
			poseStack.pushPose();
			poseStack.scale(2.0F, 2.0F, 2.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(40.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(-10.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-180.0F));
			poseStack.translate(-0.3F, -1.68F, 0.0F);
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case THIRD_PERSON_RIGHT_HAND:
			poseStack.pushPose();
			poseStack.scale(2.0F, 2.0F, 2.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(40.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(-10.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-180.0F));
			poseStack.translate(-0.3F, -1.68F, 0.0F);
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case GROUND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
			poseStack.translate(0.0F, -1.5F, 0.0F);
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case GUI:
			poseStack.pushPose();
			poseStack.scale(0.6F, 0.6F, 0.6F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
			poseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
			poseStack.translate(0.0F, -2.2F, 0.3F);
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case FIXED:
			poseStack.pushPose();
			poseStack.scale(2.0F, 2.0F, 2.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
			poseStack.translate(0.0F, 0.0F, 0.25F);
			submitNodeCollector.submitModel(bladeModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		default:
			break;
		}
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	/** 26.1.2: replaces the deleted per-item renderer map with the data-driven special model route. */
	public static final class Unbaked implements SpecialModelUnbaked {

		public static final MapCodec<RenderUltimaBlade.Unbaked> MAP_CODEC = MapCodec.unit(new RenderUltimaBlade.Unbaked());

		@Override
		public MapCodec<RenderUltimaBlade.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public RenderUltimaBlade bake(SpecialModelRenderer.BakingContext context) {
			return new RenderUltimaBlade(context.sprites());
		}
	}
}
