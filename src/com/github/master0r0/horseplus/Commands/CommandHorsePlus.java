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

import static com.github.master0r0.horseplus.Commands.CommandHorseDisown.*;
import static com.github.master0r0.horseplus.Commands.CommandHorseGift.CommandHorseGift;
import static com.github.master0r0.horseplus.Commands.CommandHorseList.*;
import static com.github.master0r0.horseplus.Commands.CommandHorseTP.CommandHorseTP;
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
                        if(!CommandHorseTP(player,strings))
                            return false;
                        else
                            return true;
                    }else{
                        player.sendMessage(command.getPermissionMessage());
                        return true;
                    }

                }else if(strings[0].equalsIgnoreCase("list")){
                    if(player.isOp()||player.hasPermission(new Permission("horseplus.list"))) {
                        if(!CommandHorseList(player))
                            return false;
                        else
                            return true;
                    }else{
                        player.sendMessage(command.getPermissionMessage());
                    }
                }else if(strings[0].equalsIgnoreCase("disown")) {
                    if(player.isOp()||player.hasPermission(new Permission("horseplus.disown"))) {
                        if (!CommandHorseDisown(player, strings))
                            return false;
                        else
                            return true;
                    }else{
                        player.sendMessage(command.getPermissionMessage());
                        return true;
                    }
                }else if(strings[0].equalsIgnoreCase("gift")) {
                    if(player.isOp()||player.hasPermission(new Permission("horseplus.gift"))) {
                        if(!CommandHorseGift(player,strings))
                            return false;
                        else
                            return true;
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
