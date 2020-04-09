package dev.astridlabs.playtimerewardsplus.Events;

import dev.astridlabs.playtimerewardsplus.Plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    public Plugin plugin;

    public PlayerJoin(Plugin pl){
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(plugin.config.isWelcomeMesageEnabled()){
            if(plugin.db.playerExists(event.getPlayer().getUniqueId())){
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+plugin.config.getWelcomeBackMessage(event.getPlayer())));
            }else{
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+plugin.config.getFirstTimeWelcomeMessage(event.getPlayer())));
            }
        }
        plugin.db.createPlayer(event.getPlayer());
    }
}
