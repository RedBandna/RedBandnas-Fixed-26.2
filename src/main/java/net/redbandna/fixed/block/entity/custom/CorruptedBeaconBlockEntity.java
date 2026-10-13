package net.redbandna.fixed.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.entity.ModBlockEntities;

import java.util.List;
import java.util.stream.Stream;

public class CorruptedBeaconBlockEntity extends BlockEntity {

    public static List<Holder<MobEffect>> MONSTER_EFFECTS = List.of(
            MobEffects.SPEED, MobEffects.ABSORPTION, MobEffects.RESISTANCE,
            MobEffects.JUMP_BOOST, MobEffects.STRENGTH, MobEffects.REGENERATION);
    public static List<Holder<MobEffect>> PLAYER_EFFECTS = List.of(
            MobEffects.SLOWNESS, MobEffects.BLINDNESS, MobEffects.HUNGER,
            MobEffects.UNLUCK, MobEffects.WEAKNESS, MobEffects.INSTANT_DAMAGE);

    public CorruptedBeaconBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CORRUPTED_BEACON_BE, worldPosition, blockState);
    }

    public void tick(ServerLevel level, BlockPos pos) {
        if (level.getRandom().nextInt(1000) == 0) {
            applyEffects(level, pos);
            playSound(level, pos, SoundEvents.BEACON_AMBIENT);
        }
    }

    @Override
    public void setRemoved() {
        playSound(level, worldPosition, SoundEvents.BEACON_DEACTIVATE);
        super.setRemoved();
    }

    private static void applyEffects(Level level, BlockPos pos) {
        if (!level.isClientSide()) {

            List<Monster> monsters = level.getEntitiesOfClass(Monster.class, new AABB(pos).inflate(50));
            List<Player> players= level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(30));

            for (Monster monster : monsters) {
                if (monster.position().closerThan(Vec3.atCenterOf(pos), 50)) {
                    getRandomMobEffects(level.getRandom()).forEach(effect -> {
                        if(monster.addEffect(new MobEffectInstance(effect, 2000, 1, true, true))) {
                            monster.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 10, true, true));
                            monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2000, 1, true, true));
                        }
                    });
                }
            }

            for (Player player : players) {
                if (!player.isCreative() && player.position().closerThan(Vec3.atCenterOf(pos), 30)) {
                    getRandomPlayerEffects(level.getRandom()).forEach(effect -> player.addEffect(new MobEffectInstance(effect, effect.equals(MobEffects.INSTANT_DAMAGE) ? 1 : 2000, 1, true, true)));
                }
            }
        }
    }

    private static List<Holder<MobEffect>> getRandomMobEffects(RandomSource randomSource) {
        return MONSTER_EFFECTS.stream().filter(_ -> randomSource.nextInt(5) == 0).toList();
    }

    private static List<Holder<MobEffect>> getRandomPlayerEffects(RandomSource randomSource) {
        return PLAYER_EFFECTS.stream().filter(_ -> randomSource.nextInt(5) == 0).toList();
    }

    public static void playSound(final Level level, final BlockPos worldPosition, final SoundEvent event) {
        level.playSound(null, worldPosition, event, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
