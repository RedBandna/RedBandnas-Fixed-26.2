package net.redbandna.fixed.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.redbandna.fixed.item.crafting.SmithingTransformRecipeExtension;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(SmithingTransformRecipe.class)
public abstract class SmithingTransformRecipeMixin extends SimpleSmithingRecipe implements SmithingTransformRecipeExtension {
    public SmithingTransformRecipeMixin(CommonInfo commonInfo) { super(commonInfo); }

    @Unique
    private int additionCount = 1;

    @Shadow @Final
    private ItemStackTemplate result;

    @Unique @Override
    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    @Unique @Override
    public void setAdditionCount(int count) {
        additionCount = count;
    }

    @Unique @Override
    public int getAdditionCount() {
        return additionCount;
    }

    @Unique @Override
    public ItemStackTemplate getResult() {
        return result;
    }


    @Shadow @Final @Mutable
    public static MapCodec<SmithingTransformRecipe> MAP_CODEC;

    @Shadow @Final @Mutable
    public static StreamCodec<RegistryFriendlyByteBuf, SmithingTransformRecipe> STREAM_CODEC;

    @Shadow @Final @Mutable
    public static RecipeSerializer<SmithingTransformRecipe> SERIALIZER;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void overrideStaticField(CallbackInfo ci) {
        MAP_CODEC = RecordCodecBuilder.mapCodec(
                i -> i.group(
                                Recipe.CommonInfo.MAP_CODEC.forGetter(o -> ((SmithingTransformRecipeExtension) o).getCommonInfo()),
                                Ingredient.CODEC.optionalFieldOf("template").forGetter(SmithingTransformRecipe::templateIngredient),
                                Ingredient.CODEC.fieldOf("base").forGetter(SmithingTransformRecipe::baseIngredient),
                                Ingredient.CODEC.optionalFieldOf("addition").forGetter(SmithingTransformRecipe::additionIngredient),
                                Codec.INT.fieldOf("addition_count").forGetter(o -> ((SmithingTransformRecipeExtension) o).getAdditionCount()),
                                ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> ((SmithingTransformRecipeExtension) o).getResult())
                        ).apply(i, SmithingTransformRecipeExtension::withCount));
        STREAM_CODEC = StreamCodec.composite(
                Recipe.CommonInfo.STREAM_CODEC,
                o -> ((SmithingTransformRecipeExtension) o).getCommonInfo(),
                Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
                SmithingTransformRecipe::templateIngredient,
                Ingredient.CONTENTS_STREAM_CODEC,
                SmithingTransformRecipe::baseIngredient,
                Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
                SmithingTransformRecipe::additionIngredient,
                ByteBufCodecs.INT,
                o -> ((SmithingTransformRecipeExtension) o).getAdditionCount(),
                ItemStackTemplate.STREAM_CODEC,
                o -> ((SmithingTransformRecipeExtension) o).getResult(),
                SmithingTransformRecipeExtension::withCount
        );
        SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    }

    @Override
    public boolean matches(final SmithingRecipeInput input, final Level level) {
        return super.matches(input, level) && input.addition().getCount() >= additionCount;
    }
}
