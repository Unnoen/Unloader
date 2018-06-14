package com.unnoen.unloader;

import net.minecraftforge.common.config.Configuration;

public class Config {
    private static final String CATEGORY_GENERAL = "General";

    public static String[] blacklistDims = {"0", "overworld"};
    public static Integer unloadCheck = 600;

    public static void readConfig() {
        Configuration cfg = Unloader.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e) {
            Unloader.logger.error("Problem loading config file!", e);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    public static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration settings for Unloader!");
        unloadCheck = cfg.getInt("unloadCheckDelay", CATEGORY_GENERAL, unloadCheck, 200, 6000, "Time (in ticks) you want Unloader to wait before checking dimensions.");
        blacklistDims = cfg.getStringList("blacklistDims", CATEGORY_GENERAL, blacklistDims, "List of Dimensions you don't want Unloader to unload. Can be dimension name or ID. Uses Regular Expressions!");
    }
}
