package com.github.master0r0.horseplus.Listeners;

import com.github.master0r0.horseplus.HorsePlus;
import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import static com.github.master0r0.horseplus.HorseHandling.CheckHorse.getHorseExists;
import static com.github.master0r0.horseplus.HorseHandling.CheckHorse.updateHorse;
import static com.github.master0r0.horseplus.HorseHandling.HorseRegistration.RegisterHorse;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 04/03/2016.
 */
public class EntityListener implements Listener {

    private HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    @EventHandler
    public void onEntityMount(EntityMountEvent event){
        if(event.getMount() instanceof Horse)
            if(((Horse) event.getMount()).getOwner()!=null)
                if(event.getEntity() instanceof Player) {
                    if (!RegisterHorse((Horse) event.getMount())) {
                        updateHorse((Horse) event.getMount());
                    } else {
                        PlayerRegistration.addHorse((Horse) event.getMount(), (Player) event.getEntity());
                    }
                }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Horse)
            if(!plugin.config.getConfigurationSection("Horse Configs").getBoolean("Should Horses take damage"))
                if(getHorseExists((Horse) event.getEntity()))
                    event.setCancelled(true);

    }

}
