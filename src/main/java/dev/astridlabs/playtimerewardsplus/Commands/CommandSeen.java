package dev.astridlabs.playtimerewardsplus.Commands;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSeen implements CommandExecutor {
    private Plugin plugin;

    public CommandSeen(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("playtimerewards.ptseen") || player.hasPermission("playtimerewards.admin")) {
                String pName = args[0];
                Player mentioned = Bukkit.getPlayer(pName);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&8--------&7 "+mentioned.getDisplayName()+" &8--------"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&Playtime: &7"+Integer.toString(plugin.db.getPlayer(mentioned.getUniqueId()).getPlaytime()/3600)+" Hours"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&aLogoff Location (X,Y,Z): &7"+plugin.config.getSavedX(mentioned.getUniqueId())+" "+plugin.config.getSavedY(mentioned.getUniqueId())+" "+plugin.config.getSavedZ(mentioned.getUniqueId())));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&c You do not have permission to use this command"));
            }

        }

        return true;
    }
}