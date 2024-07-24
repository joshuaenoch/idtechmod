package com.idtech;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BaseMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CrouchEventHandler {

    @SubscribeEvent
    public static void onPlayerCrouch(TickEvent.PlayerTickEvent event) {
        int duration = 1000;
        duration++;
        if (duration >= 1000) {
            duration = 0;
            Player player = event.player;
            Level level = player.level;
            BlockPos location = player.getOnPos();
            location = location.above();
            if (player.isCrouching()) {
                Creeper creeper = EntityType.CREEPER.create(level);
                Utils.spawnEntity(level, creeper, location);
            }
        }
    }
}
