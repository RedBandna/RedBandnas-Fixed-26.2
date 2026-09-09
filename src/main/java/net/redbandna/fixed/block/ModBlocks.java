package net.redbandna.fixed.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.custom.EndRelayBlock;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {

    public static final Block VOID_ESSENCE_ORE = registerBlock("void_essence_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties.strength(5f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final Block VOID_ESSENCE_BLOCK = registerBlock("void_essence_block",
            properties -> new Block(properties.strength(6f)
                    .requiresCorrectToolForDrops()));
    public static final Block PURE_VOID_ESSENCE_BLOCK = registerBlock("pure_void_essence_block",
            properties -> new Block(properties.strength(7f)
                    .requiresCorrectToolForDrops()));

    public static final Block END_RELAY = registerBlock("end_relay",
            properties -> new EndRelayBlock(properties.strength(6f)
                    .requiresCorrectToolForDrops()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name)))));
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static void registerModBlocks() {
        RedBandnaSFixed.LOGGER.info("Registering Mod Blocks for " + RedBandnaSFixed.MOD_ID);
    }
}
