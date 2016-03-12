package com.github.master0r0.horseplus.Commands;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorseHandling.HorseRegistration;
import com.github.master0r0.horseplus.HorsePlus;
import com.github.master0r0.horseplus.PlayerHandling.PlayerRegistration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Iterator;
import java.util.Map;

import static com.github.master0r0.horseplus.HorseHandling.HorseRegistration.RegisterHorse;
import static com.github.master0r0.horseplus.HorseHandling.HorseRegistration.unregisterHorse;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 05/03/2016.
 */
public class CommandHorsePlus implements CommandExecutor {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        command.setPermissionMessage(String.format("%sYou do not have permission to use this command",plugin.pluginPrefix));
        if(strings.length>=1) {
            //plugin.logger.info(strings[0]);
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (strings[0].equalsIgnoreCase("tp")) {
                    if (player.isOp()||player.hasPermission(new Permission("horseplus.tp"))) {
                        if(strings[1]!=null) {
                            Horse horseEntity = CheckHorse.findHorseEntity(player,strings[1]);
                            if(horseEntity!=null) {
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
                            }else{
                                player.sendMessage(String.format("%sA horse with that ID does not exist or is not loaded",plugin.pluginPrefix));
                                return true;
                            }
                        }else{
                            player.sendMessage(String.format("%sYou must enter the ID of a horse you own!",plugin.pluginPrefix));
                            return false;
                        }

                    }else{
                        player.sendMessage(command.getPermissionMessage());
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
                                    player.sendMessage(String.format("%s",pair.getKey()));
                                it.remove();
                            }
                            return true;
                        }
                    }else{
                        player.sendMessage(command.getPermissionMessage());
                    }
                }else if(strings[0].equalsIgnoreCase("disown")) {
                    if(strings.length>=2){
                        if(HorseRegistration.getHorseUUIDFromID(strings[1])!=null){
                            String ownerName = String.valueOf(CheckHorse.getHorseData(HorseRegistration.getHorseUUIDFromID(strings[1]),"Owner Info","Name"));
                            if(ownerName.equalsIgnoreCase(player.getDisplayName())){
                                HorseRegistration.unregisterHorse(HorseRegistration.getHorseUUIDFromID(strings[1]),ownerName);
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
                }else if(strings[0].equalsIgnoreCase("gift")) {
                    if(player.isOp()||player.hasPermission(new Permission("horseplus.gift"))) {
                        if (strings.length > 2) {
                            String HorseUUID = HorseRegistration.getHorseUUIDFromID(strings[1]);
                            if (HorseUUID != null) {
                                Player giftPlayer = plugin.getServer().getPlayerExact(strings[2]);
                                if (giftPlayer != null) {
                                    Horse horse = CheckHorse.findHorseEntity(player,strings[1]);
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
                    }else{
                        player.sendMessage(command.getPermissionMessage());
                        return true;
                    }
                }else{
                    return false;
                }
            }
        }
        return false;
    }
}
