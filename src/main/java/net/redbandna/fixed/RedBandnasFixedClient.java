package net.redbandna.fixed;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.redbandna.fixed.menu.ModeMenuTypes;
import net.redbandna.fixed.menu.custom.NetherForgeScreen;

public class RedBandnasFixedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModeMenuTypes.NETHER_FORGE_MENU, NetherForgeScreen::new);
    }
}
