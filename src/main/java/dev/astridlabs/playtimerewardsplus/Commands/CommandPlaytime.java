package dev.astridlabs.playtimerewardsplus.Commands;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlaytime implements CommandExecutor {
    private Plugin plugin;

    public CommandPlaytime(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PluginChatPrefix + "&aYour current playtime is: &7"+Integer.toString(plugin.db.getPlayer(player.getUniqueId()).getPlaytime()/3600)+" Hours"));
        }

        return true;
    }
}
