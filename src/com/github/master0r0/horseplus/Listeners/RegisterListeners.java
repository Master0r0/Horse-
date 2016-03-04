package com.github.master0r0.horseplus.Listeners;

import com.github.master0r0.horseplus.HorsePlus;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 03/03/2016.
 */
public class RegisterListeners {
    private HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public RegisterListeners(){
        plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EntityListener(),plugin);
    }
}
