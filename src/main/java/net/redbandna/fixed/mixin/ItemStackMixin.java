package net.redbandna.fixed.mixin;

import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.data.ModDataComponents;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {

    @Inject(method = "hurtEnemy", at = @At("RETURN"))
    public void applyEffects(LivingEntity mob, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {

        if (this.has(DataComponents.WEAPON) && this.has(ModDataComponents.FORGED_EFFECTS))
            this.get(ModDataComponents.FORGED_EFFECTS).effects().forEach(entry -> mob.addEffect(entry.createEffectInstance()));
    }
}
