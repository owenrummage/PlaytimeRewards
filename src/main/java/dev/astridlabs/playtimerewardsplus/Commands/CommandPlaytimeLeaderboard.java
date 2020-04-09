package dev.astridlabs.playtimerewardsplus.Commands;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;

public class CommandPlaytimeLeaderboard implements CommandExecutor {

    private Plugin plugin;

    public CommandPlaytimeLeaderboard(Plugin plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("playtimerewards.ptleader") || player.hasPermission("playtimerewards.user")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&8-------- &7Leaderboard &8--------"));
                int i = 0;
                for (PlayerData pl : plugin.db.getSortedPlayerlist()){
                    if(i<9){
                        if((pl.getPlaytime()/3600) > 0){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&a"+pl.getDisplayName()+"&8 :   "+" &7"+Integer.toString(pl.getPlaytime()/3600))+" Hours");
                            i++;
                        }
                    }
                }
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&c You do not have permission to use this command"));
            }
        }

        return true;
    }
}