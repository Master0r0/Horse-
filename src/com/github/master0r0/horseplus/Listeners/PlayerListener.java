package com.github.master0r0.horseplus.Listeners;

import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 03/03/2016.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        new PlayerRegistration(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){

    }


}
