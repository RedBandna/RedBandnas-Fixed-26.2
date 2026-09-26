package net.redbandna.fixed.data;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.redbandna.fixed.RedBandnaSFixed;

public class ModAttributes {

    public static final Holder<Attribute> ARROW_SPREAD = register("arrow_spread", 1.0, 0.0, 50.0, true);

    private static Holder<Attribute> register(String name, double defVal, double minVal, double maxVal, boolean synced) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(RedBandnaSFixed.MOD_ID, name),
                new RangedAttribute("attribute.name." + RedBandnaSFixed.MOD_ID + "." + name, defVal, minVal, maxVal).setSyncable(synced));
    }

    public static void registerModAttributes() {
        RedBandnaSFixed.LOGGER.info("Registering Attributes for " + RedBandnaSFixed.MOD_ID);
    }
}
