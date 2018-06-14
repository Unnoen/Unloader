package com.unnoen.unloader;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import java.io.File;

@Mod(modid = "unloader", name = "Unloader", version = "{@version}", acceptableRemoteVersions = "*", acceptedMinecraftVersions = "1.12.2")

public class Unloader {
    public static Logger logger;
    public static Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Unloader.logger.info("Unloader loaded!... Wait... What?");
        File modDirectory = event.getModConfigurationDirectory();
        config = new Configuration(new File(modDirectory.getPath(), "unloader.cfg"));
        Config.readConfig();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new UnloadHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}