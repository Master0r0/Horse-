package com.github.master0r0.horseplus;

import com.github.master0r0.horseplus.Commands.CommandHorsePlus;
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
    public String pluginPrefix = "[Horse+] ";

    @Override
    public void onEnable(){
        new Config(config);
        new RegisterListeners();
        this.getCommand("horsep").setExecutor(new CommandHorsePlus());
    }

    @Override
    public void onDisable(){

    }
}
