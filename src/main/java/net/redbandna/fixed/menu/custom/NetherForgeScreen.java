package net.redbandna.fixed.menu.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.redbandna.fixed.RedBandnaSFixed;

public class NetherForgeScreen extends AbstractContainerScreen<NetherForgeMenu> {
    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, "textures/gui/nether_forge/nether_forge.png");
    private static final Identifier LIT_TEXTURE =
            Identifier.withDefaultNamespace("container/furnace/lit_progress");

    public NetherForgeScreen(NetherForgeMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, leftPos, topPos, 0, 0,
                imageWidth, imageHeight, 256, 256);

        renderLitProgress(graphics, x, y);
    }

    private void renderLitProgress(GuiGraphicsExtractor graphics, int x, int y) {
        if(menu.isCrafting()) {
            int litProgressHeight = Mth.ceil(menu.getLitProgress() * 13.0F) + 1;
            graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED, LIT_TEXTURE, 14, 14, 0, 14 - litProgressHeight, x + 80, y + 36 + 14 - litProgressHeight, 14, litProgressHeight);
        }
    }
}
