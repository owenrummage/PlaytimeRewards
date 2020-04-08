// PlaceholderAPIIntegration.java
//   - Send data to PlaceholderAPI for other plugins to use
//
// (c) Owen Rummage 2020
package dev.astridlabs.playtimerewardsplus.Integrations;

import dev.astridlabs.playtimerewardsplus.Plugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class PlaceholderAPIIntegration extends PlaceholderExpansion {

    /*
     * The identifier, shouldn't contain any _ or %
     */
    private Plugin plugin;

    public PlaceholderAPIIntegration(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }
    @Override
    public boolean canRegister(){
        return true;
    }
    @Override
    public String getAuthor(){
        return "O_dude";
    }
    @Override
    public String getIdentifier(){
        return "playtimerewards";
    }
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }



    public String onPlaceholderRequest(Player player, String identifier) {

        // We check if the player is null (not online) before any player-related placeholder
        if(player == null){
            return "";
        }

        // Placeholder: %playtimerewards_prefix%
        if(identifier.equals("prefix")) {
            return plugin.PlaceholderChatPrefix.replace("%time%", "&a"+ Integer.toString(plugin.db.getPlayer(player.getUniqueId()).getPlaytime()/3600)+"&7h");
        }

        if(identifier.equals("playtime")) {
            return Integer.toString(plugin.db.getPlayer(player.getUniqueId()).getPlaytime());
        }

        if(identifier.equals("lastloc_x")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getX());
        }
        if(identifier.equals("lastloc_y")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getY());
        }
        if(identifier.equals("lastloc_z")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getLastLocation().getZ());
        }
        if(identifier.equals("curloc_x")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getX());
        }
        if(identifier.equals("curloc_y")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getY());
        }
        if(identifier.equals("curloc_z")) {
            return Double.toString(plugin.db.getPlayer(player.getUniqueId()).getCurrentLocation().getZ());
        }


        // We return null, if an invalid placeholder was called.
        return null;
    }
}