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

            int i = 0;
            for (PlayerData pl : plugin.db.getSortedPlayerlist()){
                if(i<5){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&a"+pl.getDisplayName()+"&8| "+Integer.toString(pl.getPlaytime()/3600)));
                    i++;
                }
                i++;
            }
        }

        return true;
    }
}