package com.joaocardosodev.telecraft.blockentity;


import com.joaocardosodev.telecraft.Telecraft;
import com.joaocardosodev.telecraft.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LeydenJarBlockEntity extends BlockEntity {

    int counter;


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        CompoundTag telecraftModData = nbt.getCompound(Telecraft.MODID);
        this.counter = telecraftModData.getInt("Counter");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);

        var telecraftModData = new CompoundTag();
        telecraftModData.putInt("Counter",counter);
        nbt.put(Telecraft.MODID,telecraftModData);
    }

    public LeydenJarBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.LEYDEN_JAR_ENTITY.get(),p_155229_, p_155230_);
    }

    public int incrementCounter() {
        return ++this.counter;
    }
}
