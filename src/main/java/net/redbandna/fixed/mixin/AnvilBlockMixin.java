package net.redbandna.fixed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.redbandna.fixed.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    @ModifyReturnValue(method = "damage", at = @At("TAIL"))
    private static BlockState damageVoidAnvil(BlockState original, @Local(argsOnly = true, name = "blockState") BlockState blockState) {
        return blockState.is(ModBlocks.VOID_ANVIL) ? ModBlocks.PURE_VOID_AIR.defaultBlockState() : null;
    }
}
