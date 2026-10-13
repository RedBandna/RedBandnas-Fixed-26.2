package net.redbandna.fixed;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import net.redbandna.fixed.block.entity.renderer.CorruptedBeaconRenderer;
import net.redbandna.fixed.menu.ModMenuTypes;
import net.redbandna.fixed.menu.custom.NetherForgeScreen;
import net.redbandna.fixed.menu.custom.VoidAnvilScreen;

public class RedBandnasFixedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.CORRUPTED_BEACON_BE, _ -> new CorruptedBeaconRenderer());

        MenuScreens.register(ModMenuTypes.NETHER_FORGE_MENU, NetherForgeScreen::new);
        MenuScreens.register(ModMenuTypes.VOID_ANVIL_MENU, VoidAnvilScreen::new);
    }
}
