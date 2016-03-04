package com.github.master0r0.horseplus;

import com.github.master0r0.horseplus.Listeners.RegisterListeners;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * Licensed under the GNU Public License
 *
 * Created by Master0r0 on 03/03/2016.
 */
public class HorsePlus extends JavaPlugin {

    public PluginLogger logger = new PluginLogger(this);
    public FileConfiguration config = this.getConfig();

    @Override
    public void onEnable(){
        new Config(config);
        new RegisterListeners();
    }

    @Override
    public void onDisable(){

    }
}
