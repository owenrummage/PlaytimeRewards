package dev.astridlabs.playtimerewardsplus;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class MasterClock extends BukkitRunnable {
    private final Plugin plugin;
    private Integer clockTicks = 0;

    public MasterClock(Plugin plugin){
        this.plugin = plugin;
    }


    @Override
    public void run() {
        clockTicks++;

        if((clockTicks/72000) > 1){
            this.plugin.config.savePlayers();
            this.plugin.getServer().getLogger().log(Level.INFO, "Hourly save completed! Data loss avoided");
        }

        // What you want to schedule goes here
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            updatePlaytime(p);
            updatePlayerLocations(p);
        }
    }

    public void updatePlaytime(Player pl){
        PlayerData player = plugin.db.getPlayer(pl.getUniqueId());
        Integer playtime = player.getPlaytime();
        Integer newplaytime = playtime+1;
        player.setPlaytime(newplaytime);
    }

    public void updatePlayerLocations(Player pl){
        PlayerData player = plugin.db.getPlayer(pl.getUniqueId());

        if(player.getCurrentLocation() == null){
            player.setCurrentLocation(pl.getLocation());
        }else{
            player.setLastLocation(player.getCurrentLocation());
            player.setCurrentLocation(pl.getLocation());
        }

    }
}
