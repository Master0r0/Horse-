package com.github.master0r0.horseplus.Intergration;

import com.github.master0r0.horseplus.HorsePlus;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Licensed under the GNU Public License
 * <p/>
 * Created by Master0r0 on 11/03/2016.
 */
public class VaultApiIntergration {

    private static HorsePlus plugin = HorsePlus.getPlugin(HorsePlus.class);

    public Plugin vaultAPI = null;

    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;

    public VaultApiIntergration(){
        plugin.hasVaultApi = true;
        vaultAPI = plugin.getServer().getPluginManager().getPlugin("Vault");
        plugin.logger.info("VaultAPI Found!");
        setupEconomy();
        setupChat();
        setupPermissions();
    }

    private boolean setupEconomy(){
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            plugin.logger.severe("Could not find an Economy Provider");
        }else {
            econ = rsp.getProvider();
        }
        return econ != null;
    }

    private boolean setupChat(){
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

}
