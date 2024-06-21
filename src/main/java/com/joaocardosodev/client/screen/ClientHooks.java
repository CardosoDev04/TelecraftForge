package com.joaocardosodev.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {

    public static Object openLeydenJarBlockScreen(BlockPos pos) {
        Minecraft.getInstance().setScreen(new LeydenJarBlockScreen(pos));
        return null;
    }


}
