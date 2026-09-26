package net.redbandna.fixed.mixin;

import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.redbandna.fixed.menu.custom.VoidAnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.redbandna.fixed.RedBandnaSFixed.LOGGER;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Shadow
    public ServerPlayer player;

    @Inject(method = "handleRenameItem", at = @At(value = "TAIL"))
    public void handleVoidAnvil(ServerboundRenameItemPacket packet, CallbackInfo ci) {
        if (player.containerMenu instanceof VoidAnvilMenu menu) {
            if (!menu.stillValid(player)) {
                LOGGER.debug("Player {} interacted with invalid menu {}", player, menu);
                return;
            }

            menu.setItemName(packet.getName());
        }
    }
}
