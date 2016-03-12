package com.github.master0r0.horseplus;

import com.github.master0r0.horseplus.Commands.CommandHorsePlus;
import com.github.master0r0.horseplus.Intergration.PermsExIntergration;
import com.github.master0r0.horseplus.Intergration.VaultApiIntergration;
import com.github.master0r0.horseplus.Listeners.RegisterListeners;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
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

    public PermsExIntergration permsExIntergration = null;
    public VaultApiIntergration vaultApiIntergration = null;

    public boolean hasPermEx = false;
    public boolean hasVaultApi = false;

    @Override
    public void onEnable(){

        intergrationCheck();
        new Config(config);
        new RegisterListeners();
        this.getCommand("horsep").setExecutor(new CommandHorsePlus());
    }

    @Override
    public void onDisable(){
        logger.info(String.format("[%s] - Disabled Version %s", getDescription().getName(),getDescription().getVersion()));
    }

    public void intergrationCheck(){
        if(this.getServer().getPluginManager().getPlugin("PermissionsEx")!=null)
            permsExIntergration = new PermsExIntergration();
        if(this.getServer().getPluginManager().getPlugin("Vault")!=null)
            vaultApiIntergration = new VaultApiIntergration();
    }


}
