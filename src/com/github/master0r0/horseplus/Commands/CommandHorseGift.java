package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorseHandling.HorseRegistration;
import com.github.master0r0.horseplus.HorsePlus;
import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import static com.github.master0r0.horseplus.HorseHandling.HorseRegistration.RegisterHorse;
import static com.github.master0r0.horseplus.HorseHandling.HorseRegistration.unregisterHorse;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 13/03/2016.
 */
public class CommandHorseGift {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public static boolean CommandHorseGift(Player player, String[] args){
        if (args.length > 2) {
            String HorseUUID = HorseRegistration.getHorseUUIDFromID(args[1]);
            if (HorseUUID != null) {
                Player giftPlayer = plugin.getServer().getPlayerExact(args[2]);
                if (giftPlayer != null) {
                    Horse horse = CheckHorse.findHorseEntity(player,args[1]);
                    if(horse!=null){
                        unregisterHorse(String.valueOf(horse.getUniqueId()),String.valueOf(CheckHorse.getHorseData(HorseUUID,"Owner Info","Name")));
                        horse.setOwner(player);
                        if (!RegisterHorse(horse)) {
                            player.sendMessage(String.format("%sHorse already registered",plugin.pluginPrefix));
                            return false;
                        } else {
                            PlayerRegistration.addHorse(horse, player);
                            return true;
                        }
                    }else{
                        player.sendMessage(String.format("%sA horse with that ID does not exist or is not loaded",plugin.pluginPrefix));
                        return true;
                    }
                }else{
                    player.sendMessage(String.format("%scould not find a player with that name",plugin.pluginPrefix));
                    return true;
                }
            }else{
                player.sendMessage(String.format("%scould not find a horse with that ID",plugin.pluginPrefix));
                return false;
            }
        }
        return false;
    }

}
