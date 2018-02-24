package com.unnoen.unloader;

import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = "unloader", name = "Unloader", version = "{@version}", acceptableRemoteVersions = "*", acceptedMinecraftVersions = "[1.10.2,1.12.2]")
public class Unloader
{

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Unloader.logger.info("Unloader loaded!... Wait... What?");
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
                for (int dimension : DimensionManager.getIDs()) {
                    WorldServer worldServer = DimensionManager.getWorld(dimension);
                    ChunkProviderServer provider = worldServer.getChunkProvider();
                    if (dimension != 0
                            && provider.getLoadedChunkCount() == 0
                            && worldServer.playerEntities.isEmpty()
                            && worldServer.loadedEntityList.isEmpty()
                            && worldServer.loadedTileEntityList.isEmpty()) {
                        DimensionManager.unloadWorld(dimension);
                    }
                }
            }
        }
    }
}