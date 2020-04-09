package dev.astridlabs.playtimerewardsplus.Controllers;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.DataTypes.Reward;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;

public class ConfigController {
    private Plugin plugin;
    private File customConfigFile;
    private FileConfiguration customConfig;


    public ConfigController(Plugin plugin){
        this.plugin = plugin;
        this.customConfig = plugin.getConfig();
    }

    public void savePlayers(){
        for (PlayerData player : plugin.db.playerList) {
            if(player.getCurrentLocation() == null){
                customConfig.set("data."+player.getUuid()+".playtime", player.getPlaytime());
                customConfig.set("data."+player.getUuid()+".displayName", player.getDisplayName());
            }else{
                customConfig.set("data."+player.getUuid()+".playtime", player.getPlaytime());
                customConfig.set("data."+player.getUuid()+".displayName", player.getDisplayName());
                customConfig.set("data."+player.getUuid()+".lastLocation.X", player.getCurrentLocation().getBlockX());
                customConfig.set("data."+player.getUuid()+".lastLocation.Y", player.getCurrentLocation().getBlockY());
                customConfig.set("data."+player.getUuid()+".lastLocation.Z", player.getCurrentLocation().getBlockZ());
            }
        }
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            this.plugin.getServer().getLogger().log(Level.SEVERE, "ERROR OCCURED WHILE SAVING! DATA LOSS COULD OCCUR!    ", e);
        }
    }

    /////////////////////////////
    //  Custom Config Handler  //
    /////////////////////////////
    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    public void createCustomConfig() throws IOException, InvalidConfigurationException {
        customConfigFile = new File(plugin.getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        customConfig= new YamlConfiguration();
        customConfig.load(customConfigFile);

    }

    public List<PlayerData> getPlayersFromConfig(){
        List<PlayerData> returnList = new ArrayList<PlayerData>();

        if (!customConfig.getConfigurationSection("data").getKeys(false).isEmpty()) {
            customConfig.getConfigurationSection("data").getKeys(false).forEach(str -> {
                plugin.db.CreatePlayerRaw(new PlayerData(UUID.fromString(str), customConfig.getString("data."+str+".displayName"), customConfig.getInt("data."+str+".playtime")));
            });
        }

        return returnList;
    }

    public String getWelcomeBackMessage(Player player){
        return customConfig.getString("Config.WelcomeMessage.WelcomeBackMessage").replace("%player%", player.getDisplayName());
    }

    public String getFirstTimeWelcomeMessage(Player player){
        return customConfig.getString("Config.WelcomeMessage.FirstTimeWelcomeMessage").replace("%player%", player.getDisplayName());

    }

    public boolean isWelcomeMesageEnabled(){
        return customConfig.getBoolean("Config.WelcomeMessage.Override");
    }

    public List<Reward> getRewards(){
        List<Reward> returnList = new ArrayList<Reward>();
        if (!customConfig.getConfigurationSection("rewards").getKeys(false).isEmpty()) {
            customConfig.getConfigurationSection("rewards").getKeys(false).forEach((str) -> {
                returnList.add(new Reward(Integer.parseInt(str), customConfig.getBoolean("rewards." + str + ".doesRepeat"), customConfig.getString("rewards." + str + ".message"), customConfig.getStringList("rewards." + str + ".commands")));
            });
        }

        return returnList;
    }

    public String getSavedX(UUID uuid){
        return this.customConfig.getString("data."+uuid.toString()+".lastLocation.X");
    }
    public String getSavedY(UUID uuid){
        return this.customConfig.getString("data."+uuid.toString()+".lastLocation.Y");
    }
    public String getSavedZ(UUID uuid){
        return this.customConfig.getString("data."+uuid.toString()+".lastLocation.Z");
    }
}
