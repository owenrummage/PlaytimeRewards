package dev.astridlabs.playtimerewardsplus.Commands;

import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPluginInfo implements CommandExecutor {

    private Plugin plugin;

    public CommandPluginInfo(Plugin plugin){
        this.plugin = plugin;
    }
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"&8[&6Playtime Rewards&7+&8]"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"This plugin was developed by: &aAstrid (O_dude)"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix+"Current Plugin Version: &70.01a"));
        }

        return true;
    }
}