package com.github.master0r0.horseplus;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 04/03/2016.
 */
public class Config {
    private HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);


    public Config(FileConfiguration config){
        File file = new File(plugin.getDataFolder(),"config.yml");
        if(!file.exists()) {
            ConfigurationSection dataSection = config.createSection("Data Save Configs");
            ConfigurationSection horseSection = config.createSection("Horse Configs");
            //Data Section
            dataSection.addDefault("Data Type", "flat");

            //Horse Section
            horseSection.addDefault("Should Horses take damage", false);

            config.options().copyDefaults(true);
            plugin.saveConfig();
        }
        plugin.reloadConfig();
    }

}
