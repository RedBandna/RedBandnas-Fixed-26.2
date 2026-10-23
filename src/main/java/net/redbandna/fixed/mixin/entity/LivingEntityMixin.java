package net.redbandna.fixed.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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

    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract void dealDefaultKnockback(DamageSource source, float damage, boolean blocked);

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
    public float scaleHit(float damage, @Local(argsOnly = true, name = "source") DamageSource source) {

        if (source.getEntity() == null) return damage;
        ItemStack weaponItem = source.getEntity().getWeaponItem();
        if (weaponItem == null) return damage;

        if (weaponItem.has(ModDataComponents.NO_DAMAGE)) return 0;

        List<String> excludes = weaponItem.get(ModDataComponents.FIRST_HIT_BONUS_EXCLUDES);
        if (excludes == null || excludes.contains(stringUUID)) return damage;

        List<String> added = new ArrayList<>(excludes);
        added.add(stringUUID);
        weaponItem.set(ModDataComponents.FIRST_HIT_BONUS_EXCLUDES, added);
        return damage * 1.3F;
    }

    @Inject(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;resolveMobResponsibleForDamage(Lnet/minecraft/world/damagesource/DamageSource;)V"), cancellable = true)
    public void noHurtIfNoHurt(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir, @Local(name = "blocked") boolean blocked) {
        if (damage <= 0f) {
            if (!source.is(DamageTypeTags.NO_KNOCKBACK)) {
                dealDefaultKnockback(source, damage, blocked);
            }
            cir.cancel();
        }
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

    @Inject(method = "checkTotemDeathProtection", at = @At(value = "HEAD"), cancellable = true)
    public void applyNeckTotem(DamageSource killingDamage, CallbackInfoReturnable<Boolean> cir) {
        if (getItemInHand(InteractionHand.MAIN_HAND).get(DataComponents.DEATH_PROTECTION) != null) return;
        if (getItemInHand(InteractionHand.OFF_HAND).get(DataComponents.DEATH_PROTECTION) != null) return;
        ItemStack itemStack = getItemBySlot(EquipmentSlot.RBFIXED_NECK);
        DeathProtection protection = itemStack.get(DataComponents.DEATH_PROTECTION);
        if (protection != null) {

            if ((LivingEntity) (Object) this instanceof ServerPlayer player) {
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                CriteriaTriggers.USED_TOTEM.trigger(player, itemStack);
                itemStack.causeUseVibration(this, GameEvent.ITEM_INTERACT_FINISH);
            }

            setHealth(1.0F);
            protection.applyEffects(itemStack, (LivingEntity) (Object) this);
            this.level().broadcastEntityEvent(this, (byte) 35);
            itemStack.shrink(1);
            cir.setReturnValue(true);
        }
    }
}
