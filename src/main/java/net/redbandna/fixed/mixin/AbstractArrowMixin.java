package net.redbandna.fixed.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.redbandna.fixed.attribute.ModAttributes;
import net.redbandna.fixed.data.ModDataComponents;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Stream;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    @Shadow
    private ItemStack pickupItemStack;

    @Shadow
    private @Nullable ItemStack firedFromWeapon;

    public AbstractArrowMixin(EntityType<? extends Projectile> type, Level level) { super(type, level); }

    @ModifyArg(method = "shoot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;shoot(DDDFF)V"), index = 4)
    private float applyArrowSpread(float uncertainty) {
        if (getOwner() instanceof LivingEntity shooter)
            return uncertainty * (float) shooter.getAttributeValue(ModAttributes.ARROW_SPREAD);
        return uncertainty;
    }

    @Inject(method = "shoot", at = @At(value = "HEAD"))
    public void addEffects(double xd, double yd, double zd, float pow, float uncertainty, CallbackInfo ci) {
        if (firedFromWeapon != null && firedFromWeapon.has(ModDataComponents.FORGED_EFFECTS)) {
            pickupItemStack.update(ModDataComponents.FORGED_EFFECTS, new SuspiciousStewEffects(List.of()), current ->
                    new SuspiciousStewEffects(Stream.concat(current.effects().stream(), firedFromWeapon.get(ModDataComponents.FORGED_EFFECTS).effects().stream()).toList()));
        }
    }

    @Inject(method = "doPostHurtEffects", at = @At(value = "HEAD"))
    public void applyEffects(LivingEntity mob, CallbackInfo ci) {
        if (pickupItemStack.has(ModDataComponents.FORGED_EFFECTS))
            pickupItemStack.get(ModDataComponents.FORGED_EFFECTS).effects().forEach(entry -> mob.addEffect(entry.createEffectInstance()));
    }
}
