package net.redbandna.fixed.effect;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.redbandna.fixed.RedBandnaSFixed;

public class SyphonedEffect extends MobEffect {
    protected SyphonedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {

        if (mob instanceof Player player && player.isCreative())
            return super.applyEffectTick(serverLevel, mob, amplification);

        double amount = -1;
        Identifier syphoned = Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "effect.syphoned");
        AttributeModifier instance = mob.getAttribute(Attributes.MAX_HEALTH).getModifier(syphoned);
        if (instance != null) {
            amount += instance.amount();
            mob.getAttribute(Attributes.MAX_HEALTH).removeModifier(syphoned);
        }
        mob.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(new AttributeModifier(syphoned, amount, AttributeModifier.Operation.ADD_VALUE));

        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return tickCount % (Math.pow(3.0, 3.0 - amplification / 90.0)) < 1;
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {
        entity.getAttribute(Attributes.MAX_HEALTH).removeModifier(Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "effect.syphoned"));
        super.onEffectRemoved(effectInstance, entity);
    }
}
