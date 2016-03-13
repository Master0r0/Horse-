package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorseHandling.HorseRegistration;
import com.github.master0r0.horseplus.HorsePlus;
import org.bukkit.entity.Player;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 13/03/2016.
 */
public class CommandHorseDisown {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public static boolean CommandHorseDisown(Player player, String[] args){
        if(args.length>=2){
            if(HorseRegistration.getHorseUUIDFromID(args[1])!=null){
                String ownerName = String.valueOf(CheckHorse.getHorseData(HorseRegistration.getHorseUUIDFromID(args[1]),"Owner Info","Name"));
                if(ownerName.equalsIgnoreCase(player.getDisplayName())){
                    HorseRegistration.unregisterHorse(HorseRegistration.getHorseUUIDFromID(args[1]),ownerName);
                    player.sendMessage(String.format("%sYou have disowned this horse and it is now free for anyone to take",plugin.pluginPrefix));
                }else{
                    player.sendMessage(String.format("%sYou must enter the ID of a horse you own!",plugin.pluginPrefix));
                    return false;
                }
                return true;
            }else{
                player.sendMessage(String.format("%sThat horse does not exist",plugin.pluginPrefix));
                return false;
            }
        }else{
            player.sendMessage(String.format("%sYou must enter the ID of a horse you own!",plugin.pluginPrefix));
            return false;
        }
    }

}
