package net.redbandna.fixed.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.redbandna.fixed.block.entity.ModBlockEntities;
import org.jspecify.annotations.Nullable;


public class EndRelayBlockEntity extends BlockEntity {

    public GlobalPos destination;

    public EndRelayBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.END_RELAY_BE, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {

        if (destination != null) {
            output.store("Destination", GlobalPos.CODEC, destination);
        }
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        destination = input.read("Destination", GlobalPos.CODEC).orElse(null);
    }


    @Override
    public void setChanged() {
        super.setChanged();
        if (!level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
