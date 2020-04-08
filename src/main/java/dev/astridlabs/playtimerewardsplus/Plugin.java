package dev.astridlabs.playtimerewardsplus;

import dev.astridlabs.playtimerewardsplus.Commands.CommandPlaytime;
import dev.astridlabs.playtimerewardsplus.Commands.CommandSeen;
import dev.astridlabs.playtimerewardsplus.Commands.Debug;
import dev.astridlabs.playtimerewardsplus.Controllers.ConfigController;
import dev.astridlabs.playtimerewardsplus.Controllers.PlayerDatabase;
import dev.astridlabs.playtimerewardsplus.Events.PlayerJoin;
import dev.astridlabs.playtimerewardsplus.Integrations.PlaceholderAPIIntegration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;
import java.util.logging.Level;

public class Plugin extends JavaPlugin {

    //Initialize Everything
    public PlayerDatabase db = new PlayerDatabase(this);
    public BukkitScheduler scheduler = getServer().getScheduler();
    public ConfigController config = new ConfigController(this);

    //Config
    public String PluginChatPrefix;
    public String PlaceholderChatPrefix;

    @Override
    public void onEnable(){
        //Get the clock running
        new MasterClock(this).runTaskTimer(this, 0L, 20L);


        try {
            config.createCustomConfig();
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, "Failed to createCustomConfig");
        } catch (InvalidConfigurationException e) {
            Bukkit.getLogger().log(Level.WARNING, "Failed to createCustomConfig");
        }



        PlaceholderChatPrefix = getConfig().getString("Config.PlaceholderChatPrefix")+"  ";
        PluginChatPrefix = getConfig().getString("Config.PluginChatPrefix")+"  ";
        //Initialize Events
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this); // This fires the event and allows any listener to listen to the event

        //Only initialize if PlaceholderAPI is actually installed
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIIntegration(this).register();
        }

        //Initialize Commands
        this.getCommand("pt").setExecutor(new CommandPlaytime(this));
        this.getCommand("ptdebug").setExecutor(new Debug(this));
        this.getCommand("ptseen").setExecutor(new CommandSeen(this));

    }

    @Override
    public void onDisable(){
        config.savePlayers();
    }
}
