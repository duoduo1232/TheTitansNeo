package net.byAqua3.thetitansneo.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.byAqua3.thetitansneo.render.state.MinionHumanoidRenderState;
import net.minecraft.util.Mth;

// 26.1.2: PlayerModel 不再带类型参数（固定为 AvatarRenderState），
// 这里模型只用于 HumanoidMobRenderer 的渲染状态，故直接固定为 LivingEntityRenderState。
public class ModelZombifiedPiglinTitanMinion extends HumanoidModel<MinionHumanoidRenderState> {

	public ModelPart leftEar = this.head.getChild("left_ear");
	public ModelPart rightEar = this.head.getChild("right_ear");

	public PartPose headDefault;
	public PartPose bodyDefault;
	public PartPose leftArmDefault;
	public PartPose rightArmDefault;

	public ModelZombifiedPiglinTitanMinion(ModelPart root) {
		super(root);
		this.leftEar = this.head.getChild("left_ear");
		this.rightEar = this.head.getChild("right_ear");
		this.headDefault = this.head.storePose();
		this.bodyDefault = this.body.storePose();
		this.leftArmDefault = this.leftArm.storePose();
		this.rightArmDefault = this.rightArm.storePose();
	}

	@Override
	public void setupAnim(MinionHumanoidRenderState state) {
		this.body.loadPose(this.bodyDefault);
		this.head.loadPose(this.headDefault);
		this.leftArm.loadPose(this.leftArmDefault);
		this.rightArm.loadPose(this.rightArmDefault);
		super.setupAnim(state);
		float f = Mth.PI / 6.0F;
		float f1 = state.ageInTicks * 0.1F + state.walkAnimationPos * 0.5F;
		float f2 = 0.08F + state.walkAnimationSpeed * 0.4F;
		this.leftEar.zRot = -f - Mth.cos(f1 * 1.2F) * f2;
		this.rightEar.zRot = f + Mth.cos(f1) * f2;
		float attackTime = state instanceof net.minecraft.client.renderer.entity.state.ArmedEntityRenderState armed ? armed.attackTime : 0.0F;
		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, state.isAggressive(), state instanceof net.minecraft.client.renderer.entity.state.ArmedEntityRenderState armed ? armed.attackTime : 0.0F, state.ageInTicks);
	}
}
