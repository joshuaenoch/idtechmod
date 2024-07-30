/*package com.idtech;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickHandler {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.ServerTickEvent.Phase.END) {
            // Your code to run on every server tick
            MinecraftServer server = event.;
            for (ServerLevel level : server.getAllLevels()) {
                // Example: Print the number of players in each dimension
                System.out.println("Dimension: " + level.dimension().location() + " Players: " + level.players().size());
            }
        }
    }
}*/
