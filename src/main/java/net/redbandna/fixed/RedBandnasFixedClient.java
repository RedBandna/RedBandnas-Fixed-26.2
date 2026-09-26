package net.redbandna.fixed;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.redbandna.fixed.menu.ModMenuTypes;
import net.redbandna.fixed.menu.custom.NetherForgeScreen;
import net.redbandna.fixed.menu.custom.VoidAnvilScreen;

public class RedBandnasFixedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.NETHER_FORGE_MENU, NetherForgeScreen::new);
        MenuScreens.register(ModMenuTypes.VOID_ANVIL_MENU, VoidAnvilScreen::new);
    }
}
