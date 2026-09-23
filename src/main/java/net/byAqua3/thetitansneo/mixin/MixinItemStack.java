package net.byAqua3.thetitansneo.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.byAqua3.thetitansneo.loader.TheTitansNeoItemTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;

@Mixin({ ItemStack.class })
public class MixinItemStack {

	@Inject(method = { "canBeHurtBy" }, at = { @At("HEAD") }, cancellable = true)
	public void canBeHurtBy(DamageSource damageSource, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		ItemStack itemStack = (ItemStack) (Object) this;

		// 26.1.2: ItemStack.item 变成 Holder<Item>，@Shadow 无法定位；用 isEmpty() 达到同样效果。
		if (!itemStack.isEmpty() && itemStack.is(TheTitansNeoItemTags.IMMORTAL)) {
			if (damageSource.is(DamageTypeTags.IS_EXPLOSION)) {
				callbackInfoReturnable.setReturnValue(false);
				callbackInfoReturnable.cancel();
			}
		}
	}
}
