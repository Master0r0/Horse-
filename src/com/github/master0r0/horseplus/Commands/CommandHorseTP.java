package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorsePlus;
import org.bukkit.Location;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 13/03/2016.
 */
public class CommandHorseTP {
    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public static boolean CommandHorseTP(Player player, String[] args){
        if(args.length>1) {
            plugin.logger.info(args[1]);
            if (args[1] != null) {
                Horse horseEntity = CheckHorse.findHorseEntity(player, args[1]);
                if (horseEntity != null) {
                    if (String.valueOf(CheckHorse.getHorseData(String.valueOf(horseEntity.getUniqueId()), "Owner Info", "Name")).equalsIgnoreCase(player.getDisplayName())) {
                        Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY() + 2), player.getLocation().getZ());
                        if (!horseEntity.teleport(playerLoc)) {
                            player.sendMessage(String.format("%sCould not teleport horse", plugin.pluginPrefix));
                            return true;
                        } else {
                            return true;
                        }
                    } else {
                        //%s is a string format code in which each is replaced by the variables in order placed after the ,
                        //The %s is replaced with the data in the variable plugin.pluginPrefix
                        player.sendMessage(String.format("%sYou do not own this horse!", plugin.pluginPrefix));
                        return true;
                    }
                } else {
                    player.sendMessage(String.format("%sA horse with that ID does not exist or is not loaded", plugin.pluginPrefix));
                    return true;
                }
            } else {
                player.sendMessage(String.format("%sYou must enter the ID of a horse you own!", plugin.pluginPrefix));
                return false;
            }
        }
        return false;
    }
}
