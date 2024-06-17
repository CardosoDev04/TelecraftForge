package com.joaocardosodev.telecraft.init;

import com.joaocardosodev.telecraft.block.LeydenJar;
import com.joaocardosodev.telecraft.blockentity.LeydenJarBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.joaocardosodev.telecraft.Telecraft.MODID;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<LeydenJarBlockEntity>> LEYDEN_JAR_ENTITY =
            BLOCK_ENTITIES.register("leyden_jar_block_entity", () ->
                    BlockEntityType.Builder.of(LeydenJarBlockEntity::new, ModBlocks.LEYDEN_JAR.get())
                            .build(null)
            );
}
