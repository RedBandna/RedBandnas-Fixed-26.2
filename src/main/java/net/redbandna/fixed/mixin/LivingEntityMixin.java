package net.redbandna.fixed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.attribute.ModAttributes;
import net.redbandna.fixed.data.ModDataComponents;
import net.redbandna.fixed.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow
    public abstract boolean addEffect(MobEffectInstance newEffect);

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot slot);

    @Inject(method = "hurtServer", at = @At(value = "TAIL"))
    protected void applyModHurting(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (source.getEntity() instanceof Creaking) {
            addEffect(new MobEffectInstance(ModEffects.SYPHONED, 200, 0));
        }
    }

    @ModifyReturnValue(method = "createLivingAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder injectCustomAttributes(AttributeSupplier.Builder original) {
        return original.add(ModAttributes.ARROW_SPREAD);
    }

    @ModifyVariable(method = "hurtServer", at = @At(value = "HEAD"), argsOnly = true, name = "damage")
    public float multiplyFirstHit(float damage, @Local(argsOnly = true, name = "source") DamageSource source) {

        if (source.getEntity() == null) return damage;
        ItemStack weaponItem = source.getEntity().getWeaponItem();
        if (weaponItem == null) return damage;
        List<String> excludes = weaponItem.get(ModDataComponents.FIRST_HIT_BONUS_EXCLUDES);
        if (excludes == null || excludes.contains(stringUUID)) return damage;

        List<String> added = new ArrayList<>(excludes);
        added.add(stringUUID);
        weaponItem.set(ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, added);
        return damage * 1.3F;
    }

    @Inject(method = "tickEffects", at = @At(value = "HEAD"))
    public void tickCatalytic(CallbackInfo ci) {
        if (level() instanceof ServerLevel serverLevel) {
            if (getItemBySlot(EquipmentSlot.FEET).has(ModDataComponents.CATALYTIC)) {
                BlockPos pos = getOnPos();
                for (int i = 0; i < 5; ++i) {
                    pos = pos.relative(Direction.getRandom(random), random.nextInt(3));
                }
                BlockState state = serverLevel.getBlockState(pos);
                RedBandnaSFixed.LOGGER.info(state.getBlock().toString());
                RedBandnaSFixed.LOGGER.info(state.isRandomlyTicking() + "");
                if (state.isRandomlyTicking()) {
                    state.randomTick(serverLevel, pos, this.random);
                }
            }
        }
    }
}
