package com.joaocardosodev.telecraft.blockentity;


import com.joaocardosodev.telecraft.Telecraft;
import com.joaocardosodev.telecraft.init.ModBlockEntities;
import com.joaocardosodev.telecraft.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LeydenJarBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int secondsExisted;
    private int ticks;


    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            LeydenJarBlockEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        CompoundTag telecraftModData = nbt.getCompound(Telecraft.MODID);
        this.inventory.deserializeNBT(telecraftModData.getCompound("LeydenJarInventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);

        var telecraftModData = new CompoundTag();
        telecraftModData.put("LeydenJarInventory", this.inventory.serializeNBT());
        nbt.put(Telecraft.MODID,telecraftModData);
    }

    public LeydenJarBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.LEYDEN_JAR_ENTITY.get(),p_155229_, p_155230_);
    }


    @Override
    public void tick() {
        if(this.level == null || this.level.isClientSide()) return;
        this.ticks++;
        this.secondsExisted = ticks / 20;
        setChanged();
        this.level.sendBlockUpdated(this.worldPosition,getBlockState(),getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getItem() {
        return this.inventory.getStackInSlot(0);
    }

    public void setItem(ItemStack stack) {
        this.inventory.setStackInSlot(0,stack);
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return this.optional;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public int getSecondsExisted() {
        return this.secondsExisted;
    }
}
