package com.unnoen.unloader;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = Unloader.MODID, version = Unloader.VERSION)
public class Unloader
{
    public static final String MODID = "unloader";
    public static final String VERSION = "1.0.0";

    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Unloader.logger.log(Level.INFO, "Unloader loaded!... Wait... What?");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new UnloaderHandle());
    }

    public static class UnloaderHandle {
        private int tickIndex = 0;

        @SubscribeEvent
        public void tick(TickEvent.ServerTickEvent tick) {
            if (tick.phase == TickEvent.Phase.END && (++tickIndex == 600)) {
                tickIndex = 0;
                for (WorldServer worldServer : DimensionManager.getWorlds()) {
                    int id = worldServer.provider.getDimension();
                    if (id != 0
                            && worldServer.getChunkProvider().getLoadedChunkCount() == 0
                            && worldServer.playerEntities.isEmpty()
                            && worldServer.loadedEntityList.isEmpty()
                            && worldServer.loadedTileEntityList.isEmpty()) {
                        DimensionManager.unloadWorld(id);
                    }
                }
            }
        }
    }
}