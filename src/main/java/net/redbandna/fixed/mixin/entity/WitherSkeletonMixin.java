package net.redbandna.fixed.mixin.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.redbandna.fixed.item.forging.ModForgePatterns;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WitherSkeleton.class)
public abstract class WitherSkeletonMixin extends AbstractSkeleton {
    protected WitherSkeletonMixin(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean killedByPlayer) {
        if (getItemInHand(InteractionHand.MAIN_HAND).is(Items.STONE_SWORD)) {
            if (level.getRandom().nextFloat() < 0.8f)
                setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            else
                ModForgePatterns.WITHERING_WEAPON.apply(getItemInHand(InteractionHand.MAIN_HAND));
        }
        super.dropCustomDeathLoot(level, source, killedByPlayer);
    }
}
