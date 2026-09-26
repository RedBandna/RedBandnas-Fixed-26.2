package net.redbandna.fixed.menu.custom;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.item.custom.ForgeTemplateItem;

@Environment(EnvType.CLIENT)
public class VoidAnvilScreen extends ItemCombinerScreen<VoidAnvilMenu> {
    private static final Identifier TEXT_FIELD_SPRITE = Identifier.withDefaultNamespace("container/anvil/text_field");
    private static final Identifier TEXT_FIELD_DISABLED_SPRITE = Identifier.withDefaultNamespace("container/anvil/text_field_disabled");
    private static final Identifier ERROR_SPRITE = Identifier.withDefaultNamespace("container/anvil/error");
    private static final Identifier ANVIL_LOCATION = Identifier.withDefaultNamespace("textures/gui/container/anvil.png");
    private EditBox name;
    private final Player player;

    public VoidAnvilScreen(final VoidAnvilMenu menu, final Inventory inventory, final Component title) {
        super(menu, inventory, title, ANVIL_LOCATION);
        player = inventory.player;
        titleLabelX = 60;
    }

    @Override
    protected void subInit() {
        int xo = (width - imageWidth) / 2;
        int yo = (height - imageHeight) / 2;
        name = new EditBox(font, xo + 62, yo + 24, 103, 12, Component.translatable("container.repair"));
        name.setCanLoseFocus(false);
        name.setTextColor(-1);
        name.setTextColorUneditable(-1);
        name.setInvertHighlightedTextColor(false);
        name.setBordered(false);
        name.setMaxLength(50);
        name.setResponder(this::onNameChanged);
        name.setValue("");
        addRenderableWidget(name);
        slotChanged(null, 0, ItemStack.EMPTY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        minecraft.player.experienceDisplayStartTick = minecraft.player.tickCount;
    }

    @Override
    protected void setInitialFocus() {
        setInitialFocus(name);
    }

    @Override
    public void resize(final int width, final int height) {
        String oldEdit = name.getValue();
        init(width, height);
        name.setValue(oldEdit);
    }

    @Override
    public boolean keyPressed(final KeyEvent event) {
        if (event.isEscape()) {
            minecraft.player.closeContainer();
            return true;
        } else {
            return name.keyPressed(event) || name.canConsumeInput() || super.keyPressed(event);
        }
    }

    private void onNameChanged(final String name) {
        if (menu.getSlot(2).hasItem() && menu.setItemName(name)) {
            minecraft.player.connection.send(new ServerboundRenameItemPacket(name));
        }
    }

    @Override
    protected void extractLabels(final GuiGraphicsExtractor graphics, final int xm, final int ym) {
        super.extractLabels(graphics, xm, ym);
        int cost = !menu.getSlot(1).getItem().isEmpty() && menu.isApplicable() ? menu.getPattern().cost : 0;
        if (cost > 0) {
            int color = -8323296;
            Component line;
            if (!menu.getSlot(2).hasItem()) {
                line = null;
            } else {
                line = Component.translatable("container.repair.cost", cost);
                if (!menu.getSlot(2).mayPickup(player)) {
                    color = -40864;
                }
            }

            if (line != null) {
                int tx = imageWidth - 8 - font.width(line) - 2;
                int ty = 69;
                graphics.fill(tx - 2, 67, imageWidth - 8, 79, 1325400064);
                graphics.text(font, line, tx, 69, color);
            }
        }
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED, menu.getSlot(0).hasItem() ? TEXT_FIELD_SPRITE : TEXT_FIELD_DISABLED_SPRITE, leftPos + 59, topPos + 20, 110, 16
        );
    }

    @Override
    protected void extractErrorIcon(final GuiGraphicsExtractor graphics, final int xo, final int yo) {
        if ((menu.getSlot(0).hasItem() || menu.getSlot(1).hasItem()) && !menu.getSlot(menu.getResultSlot()).hasItem()) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, ERROR_SPRITE, xo + 99, yo + 45, 28, 21);
        }
    }

    @Override
    public void slotChanged(final AbstractContainerMenu container, final int slotIndex, final ItemStack itemStack) {
        ItemStack result = menu.getSlot(2).getItem();
        if (!result.isEmpty()) {
            name.setValue(result.getHoverName().getString());
            name.setEditable(true);
            setFocused(name);
        } else {
            name.setValue("");
            name.setEditable(false);
        }
    }
}