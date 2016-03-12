package com.github.master0r0.horseplus.Intergration;

import com.github.master0r0.horseplus.HorsePlus;
import org.bukkit.plugin.Plugin;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 11/03/2016.
 */
public class PermsExIntergration {
    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public Plugin permsEx = null;

    public PermsExIntergration(){
        plugin.hasPermEx = true;
        permsEx = plugin.getServer().getPluginManager().getPlugin("PermissionsEx");
        plugin.logger.info("PermissionsEx Found!");
    }

}
