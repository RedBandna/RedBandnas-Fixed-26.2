package net.redbandna.fixed.block;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.redbandna.fixed.RedBandnaSFixed;
import net.redbandna.fixed.block.custom.EndRelayBlock;
import net.redbandna.fixed.block.custom.PlayerPressurePlateBlock;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModBlocks {
    public class ModBlockSetType {
        private static final Map<String, BlockSetType> TYPES = new Object2ObjectArrayMap<>();
        public static final BlockSetType VOID = register("void",
                new BlockSetType("void",false,false,false,BlockSetType.PressurePlateSensitivity.EVERYTHING,
                        SoundType.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY,SoundEvents.EMPTY));
        private static BlockSetType register(String name, final BlockSetType type) {
            TYPES.put(name, type);
            return type;
        }
    }

    public static final Block VOID_ESSENCE_ORE = registerBlock("void_essence_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties.strength(25f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final Block VOID_ESSENCE_BLOCK = registerBlock("void_essence_block",
            properties -> new Block(properties.strength(40f)
                    .requiresCorrectToolForDrops().sound(SoundType.EMPTY)));
    public static final Block PURE_VOID_ESSENCE_BLOCK = registerBlock("pure_void_essence_block",
            properties -> new Block(properties.strength(80f)
                    .requiresCorrectToolForDrops().sound(SoundType.EMPTY)));

    public static final Block END_RELAY = registerBlock("end_relay",
            properties -> new EndRelayBlock(properties.strength(20f)
                    .requiresCorrectToolForDrops()));

    public static final Block VOID_ESSENCE_STAIRS = registerBlock("void_essence_stairs",
            properties -> new StairBlock(ModBlocks.VOID_ESSENCE_BLOCK.defaultBlockState(),
                    properties.strength(40f).requiresCorrectToolForDrops().sound(SoundType.EMPTY)));
    public static final Block VOID_ESSENCE_SLAB = registerBlock("void_essence_slab",
            properties -> new SlabBlock(properties.strength(40f).requiresCorrectToolForDrops().sound(SoundType.EMPTY)));
    public static final Block VOID_ESSENCE_PRESSURE_PLATE = registerBlock("void_essence_pressure_plate",
            properties -> new PlayerPressurePlateBlock(ModBlockSetType.VOID, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()
                    .mapColor(MapColor.COLOR_BLACK).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).pushReaction(PushReaction.DESTROY)));
    public static final Block VOID_ESSENCE_DOOR = registerBlock("void_essence_door",
            properties -> new DoorBlock(ModBlockSetType.VOID, properties.strength(30f).requiresCorrectToolForDrops()));
    public static final Block VOID_ESSENCE_TRAPDOOR = registerBlock("void_essence_trapdoor",
            properties -> new TrapDoorBlock(ModBlockSetType.VOID, properties.strength(30f).requiresCorrectToolForDrops()));

    public static final Block VOID_AMETHYST_BUTTON = registerBlock("void_amethyst_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops()
                    .sound(SoundType.EMPTY).noCollision().lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 0 : 10)));
    public static final Block VOID_COAL_BUTTON = registerBlock("void_coal_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_COPPER_BUTTON = registerBlock("void_copper_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_DIAMOND_BUTTON = registerBlock("void_diamond_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_EMERALD_BUTTON = registerBlock("void_emerald_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_GOLD_BUTTON = registerBlock("void_gold_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 4, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_IRON_BUTTON = registerBlock("void_iron_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 100, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_LAPIS_BUTTON = registerBlock("void_lapis_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_NETHERITE_BUTTON = registerBlock("void_netherite_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_QUARTZ_BUTTON = registerBlock("void_quartz_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final Block VOID_REDSTONE_BUTTON = registerBlock("void_redstone_button",
            properties -> new ButtonBlock(ModBlockSetType.VOID, 20, properties.strength(20f).requiresCorrectToolForDrops().sound(SoundType.EMPTY).noCollision()));
    public static final List<Block> VOID_BUTTONS = List.of(VOID_AMETHYST_BUTTON, VOID_COAL_BUTTON, VOID_COPPER_BUTTON, VOID_DIAMOND_BUTTON, VOID_EMERALD_BUTTON,
            VOID_GOLD_BUTTON, VOID_IRON_BUTTON, VOID_LAPIS_BUTTON, VOID_NETHERITE_BUTTON, VOID_QUARTZ_BUTTON, VOID_REDSTONE_BUTTON);

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
