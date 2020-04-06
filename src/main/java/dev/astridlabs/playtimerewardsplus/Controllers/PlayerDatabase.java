// PlayerDatabase.java
//   - A database to keep track of players within the plugin
//
// (c) Owen Rummage 2020

package dev.astridlabs.playtimerewardsplus.Controllers;

import dev.astridlabs.playtimerewardsplus.DataTypes.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDatabase {
    public List<PlayerData> playerList = new ArrayList<PlayerData>();

    public PlayerData getPlayer(UUID uuid){
        PlayerData returnedPlayer = null;

        for (PlayerData player : playerList) {
            if(player.getUuid() == uuid){
                returnedPlayer = player;
            }
        }
        return returnedPlayer;
    }


    public void updatePlayerDisplayName(UUID uuid, String displayName){
        PlayerData player = getPlayer(uuid);
        player.setDisplayName(displayName);
    }
    public void updatePlayerPlaytime(UUID uuid, Integer playtime){
        PlayerData player = getPlayer(uuid);
        player.setPlaytime(playtime);
    }
    public void updateCurrentLocation(UUID uuid, Location currentLocation){
        PlayerData player = getPlayer(uuid);
        player.setCurrentLocation(currentLocation);
    }
    public void updateLastLocation(UUID uuid, Location lastLocation){
        PlayerData player = getPlayer(uuid);
        player.setLastLocation(lastLocation);
    }


    public PlayerData createPlayer(Player player){
        playerList.add(new PlayerData(player.getUniqueId(), player.getDisplayName()));
        return getPlayer(player.getUniqueId());
    }

}
