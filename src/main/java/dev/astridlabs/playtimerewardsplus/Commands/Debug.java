package dev.astridlabs.playtimerewardsplus.Commands;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Debug implements CommandExecutor {
    private Plugin plugin;
    private String DebugPrefix = "&7[&cDEBUG MODE&7] ";

    public Debug(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!player.hasPermission("playtimerewards.debug")) player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&c You do not have permission to use this command"));
            if(args[0].equals("time")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aUser playtime in seconds is: "+this.plugin.db.getPlayer(player.getUniqueId()).getPlaytime()));
            }

            if(args[0].equals("location")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aLast player locaiton coordinates (X,Y,Z): "+this.plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getX()+" "+this.plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getY()+" "+this.plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getZ()));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aCurrent player locaiton coordinates (X,Y,Z): "+this.plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getX()+" "+this.plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getY()+" "+this.plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getZ()));

            }

            if(args[0].equals("save")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aForce dumping memory to disk, this could cause file corruption"));
                plugin.config.savePlayers();

            }

            if(args[0].equals("rawdata")){
                List<PlayerData> playerDataList = plugin.db.getSortedPlayerlist();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aPlayer UUID: &7"+ player.getUniqueId()));

                for(PlayerData p : playerDataList){
                    if(player.getUniqueId().toString().equals(p.getUuid().toString())){//FIXME: This is never called, meaning somehow the UUID's are not compatable with eachother
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aYou are in the Database!"));
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+DebugPrefix + "&aPlayer UUID: &7"+ p.getUuid()));
                }
            }
        }

        return true;
    }
}