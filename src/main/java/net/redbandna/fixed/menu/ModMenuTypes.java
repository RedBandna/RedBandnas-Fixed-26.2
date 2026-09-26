package net.redbandna.fixed.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.menu.custom.NetherForgeMenu;
import net.redbandna.fixed.menu.custom.VoidAnvilMenu;


public class ModMenuTypes {
    public static final MenuType<NetherForgeMenu> NETHER_FORGE_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "nether_forge_menu"),
                    new ExtendedMenuType<>(NetherForgeMenu::new, BlockPos.STREAM_CODEC));
    public static final MenuType<VoidAnvilMenu> VOID_ANVIL_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "void_anvil_menu"),
                    new MenuType<>(VoidAnvilMenu::new, FeatureFlags.VANILLA_SET));

    public static void registerModMenuTypes() {
        RedBandnaSFixed.LOGGER.info("Registering Mod Menu Types for " + RedBandnaSFixed.MOD_ID);
    }
}
