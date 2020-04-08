package dev.astridlabs.playtimerewardsplus.Events;

import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.Bukkit;
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
        event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
        plugin.db.createPlayer(event.getPlayer());
    }
}
