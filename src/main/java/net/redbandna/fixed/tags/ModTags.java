package net.redbandna.fixed.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> VOID_BUTTONS = createTag("void_buttons");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> VOID_MINERALS = createTag("void_minerals");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name));
        }
    }
}
