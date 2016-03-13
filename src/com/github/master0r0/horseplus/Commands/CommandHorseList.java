package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorsePlus;
import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Map;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 13/03/2016.
 */
public class CommandHorseList {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public static boolean CommandHorseList(Player player){
        Map<String,Object> horses = PlayerRegistration.getHorses(player.getDisplayName());
        if (horses!=null) {
            player.sendMessage(String.format("%sHorses you own:",plugin.pluginPrefix));
            Iterator it = horses.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(!(String.valueOf(horses.get(pair.getKey())).equalsIgnoreCase("")))
                    player.sendMessage(String.format("%s",pair.getKey()));
                it.remove();
            }
            return true;
        }
        player.sendMessage(String.format("%sYou do not own any horses!",plugin.pluginPrefix));
        return true;
    }

}
