package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorseHandling.HorseRegistration;
import com.github.master0r0.horseplus.HorsePlus;
import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 05/03/2016.
 */
public class CommandHorsePlus implements CommandExecutor {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length>=1) {
            //plugin.logger.info(strings[0]);
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (strings[0].equalsIgnoreCase("tp")) {
                    if (player.isOp()||player.hasPermission(new Permission("horseplus.tp"))) {
                        if(strings[1]!=null) {
                            List<Entity> entities = plugin.getServer().getWorld(player.getWorld().getName()).getEntities();
                            for (Entity horseEntity : entities) {
                                if (horseEntity instanceof Horse) {
                                    if (String.valueOf(CheckHorse.getHorseData(String.valueOf(horseEntity.getUniqueId()), "Horse Info", "PluginID")).equalsIgnoreCase(strings[1])) {
                                        if (String.valueOf(CheckHorse.getHorseData(String.valueOf(horseEntity.getUniqueId()), "Owner Info", "Name")).equalsIgnoreCase(player.getDisplayName())) {
                                            Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY() + 2), player.getLocation().getZ());
                                            horseEntity.teleport(playerLoc);
                                            return true;
                                        }
                                        player.sendMessage(plugin.pluginPrefix + "You do not own this horse!");
                                        return true;
                                    }
                                }
                            }
                        }else{
                            player.sendMessage(plugin.pluginPrefix + "You must enter the ID of a horse you own!");
                            return false;
                        }
                    }else{
                        player.sendMessage(plugin.pluginPrefix+"You do not have permission to use this command!");
                        return true;
                    }
                }else if(strings[0].equalsIgnoreCase("list")){
                    if(player.isOp()||player.hasPermission(new Permission("horseplus.list"))) {
                        Map<String,Object> horses = PlayerRegistration.getHorses(player.getDisplayName());
                        if (horses!=null) {
                            player.sendMessage(plugin.pluginPrefix + "Horses you own:");
                            Iterator it = horses.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                if(!(String.valueOf(horses.get(pair.getKey())).equalsIgnoreCase("")))
                                    player.sendMessage(plugin.pluginPrefix + pair.getKey());
                                it.remove();
                            }
                            return true;
                        }
                    }
                }else if(strings[0].equalsIgnoreCase("disown")) {
                    if(strings.length>=2){
                        if(HorseRegistration.getHorseUUIDFromID(strings[1])!=null){
                            String ownerName = String.valueOf(CheckHorse.getHorseData(HorseRegistration.getHorseUUIDFromID(strings[1]),"Owner Info","Name"));
                            if(ownerName.equalsIgnoreCase(player.getDisplayName())){
                                HorseRegistration.unregisterHorse(HorseRegistration.getHorseUUIDFromID(strings[1]),ownerName);
                                player.sendMessage(plugin.pluginPrefix + "You have disowned this horse and it is now free for anyone to take");
                            }else{
                                player.sendMessage(plugin.pluginPrefix + "You must enter the ID of a horse you own!");
                                return false;
                            }
                            return true;
                        }else{
                            player.sendMessage(plugin.pluginPrefix + "That horse does not exist");
                            return false;
                        }
                    }else{
                        player.sendMessage(plugin.pluginPrefix + "You must enter the ID of a horse you own!");
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return false;
    }
}
