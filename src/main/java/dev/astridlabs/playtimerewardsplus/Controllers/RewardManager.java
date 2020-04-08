package dev.astridlabs.playtimerewardsplus.Controllers;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import dev.astridlabs.playtimerewardsplus.DataTypes.Reward;
import dev.astridlabs.playtimerewardsplus.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RewardManager {
    private List<Reward> rewards = new ArrayList<Reward>();
    private Plugin plugin;

    public RewardManager(Plugin plugin){
        this.plugin = plugin;
    }

    public void unlockRewards(Player player, String chatPrefix){
        for (int i = 0; i < rewards.size(); i++) {
            Reward reward = rewards.get(i);
            PlayerData pd = plugin.db.getPlayer(player.getUniqueId());
            if(reward.DoesRepeat == true){
                if((pd.getPlaytime()/reward.TimeRequred)>1){
                    //Put Unlock Code Here
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', chatPrefix+reward.UnlockMessage));

                    for (int b = 0; b < reward.Commands.size(); b++) {
                        String rewardCommand = reward.Commands.get(b);

                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, rewardCommand.replace("%PLAYER%", player.getDisplayName()));
                    }
                }
            }else if(reward.DoesRepeat == false){
                if(pd.getPlaytime() == reward.TimeRequred){
                    //Put Unlock Code Here
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', chatPrefix+reward.UnlockMessage));

                    for (int b = 0; b < reward.Commands.size(); b++) {
                        String rewardCommand = reward.Commands.get(b);

                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, rewardCommand.replace("%PLAYER%", player.getDisplayName()));
                    }
                }
            }
        }
    }

    public void loadRewards(){
        this.rewards = plugin.config.getRewards();
    }
}
