package com.github.master0r0.horseplus.PlayerHandling;

import com.github.master0r0.horseplus.HorseHandling.CheckHorse;
import com.github.master0r0.horseplus.HorsePlus;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 04/03/2016.
 */
public class PlayerRegistration {
    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public PlayerRegistration(Player player){

        //Creates a directory for files to be stored in
        File playerdataDir = new File(plugin.getDataFolder(),File.separator+"Player Data");
        //Creates a File variable with the path of HorseDataDir(Where the config is found) and the Horses UUID with the .yml extension
        File playerdata = new File(playerdataDir,File.separator+player.getName()+".yml");
        //Generates a file configuration in the form of a yml file of File f
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerdata);

        if(!playerdata.exists()){
            try{
                ConfigurationSection playerInfo = playerData.createSection("Player Info");
                ConfigurationSection ownedHorses = playerData.createSection("Owned Horses");

                //Player Info data
                playerInfo.set("Name",player.getDisplayName());
                playerInfo.set("UUID",String.valueOf(player.getUniqueId()));

                //Owned horses
                //ownedHorses.set();

                //Attempts to save the data made above in File f
                playerData.save(playerdata);
            }catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static Map<String, Object> getHorses(Player player){
        //Creates a directory for files to be stored in
        File playerdataDir = new File(plugin.getDataFolder(),File.separator+"Player Data");
        //Creates a File variable with the path of HorseDataDir(Where the config is found) and the Horses UUID with the .yml extension
        File playerdata = new File(playerdataDir,File.separator+player.getName()+".yml");
        //Generates a file configuration in the form of a yml file of File f
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerdata);

        if(playerdata.exists()){
            ConfigurationSection ownedHorses = playerData.getConfigurationSection("Owned Horses");
            return ownedHorses.getValues(true);
        }
        return null;
    }

    public static void addHorse(Horse horse, Player player){
        //Creates a directory for files to be stored in
        File playerdataDir = new File(plugin.getDataFolder(),File.separator+"Player Data");
        //Creates a File variable with the path of HorseDataDir(Where the config is found) and the Horses UUID with the .yml extension
        File playerdata = new File(playerdataDir,File.separator+player.getName()+".yml");
        //Generates a file configuration in the form of a yml file of File f
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerdata);

        if(!playerdata.exists()){
            try{
                ConfigurationSection playerInfo = playerData.createSection("Player Info");
                ConfigurationSection ownedHorses = playerData.createSection("Owned Horses");

                //Player Info data
                playerInfo.set("Name",player.getDisplayName());
                playerInfo.set("UUID",String.valueOf(player.getUniqueId()));

                //Owned horses
                ownedHorses.set(String.valueOf(CheckHorse.getHorseData(horse,"Horse Info","PluginID")),String.valueOf(horse.getUniqueId()));

                //Attempts to save the data made above in File f
                playerData.save(playerdata);
            }catch(IOException exception) {
                exception.printStackTrace();
            }
        }else{
            try{
                ConfigurationSection ownedHorses = playerData.getConfigurationSection("Owned Horses");
                ownedHorses.set(String.valueOf(CheckHorse.getHorseData(horse,"Horse Info","PluginID")),String.valueOf(horse.getUniqueId()));

                playerData.save(playerdata);
            }catch(IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
