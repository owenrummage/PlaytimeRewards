// PlayerData.java
//   - This file is the main definition of all the data tracked by the plugin that is not already in the Player object inhibited by org.bukkit.player
//
// (c) Owen Rummage 2020

package dev.astridlabs.playtimerewardsplus.DataTypes;

import org.bukkit.Location;

import java.util.Enumeration;
import java.util.UUID;

public class PlayerData {

    private Integer Playtime;
    private UUID uuid;
    private String DisplayName;

    private Location CurrentLocation;
    private Location LastLocation;

    public PlayerData(UUID uuid, String DisplayName){
        this.uuid = uuid;
        this.DisplayName = DisplayName;

    }

    //Getters and Setters
    public Integer getPlaytime() {
        return Playtime;
    }
    public void setPlaytime(Integer playtime) {
        Playtime = playtime;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUuid() {
        return uuid;
    }
    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
    public String getDisplayName() {
        return DisplayName;
    }
    public Location getCurrentLocation() {
        return CurrentLocation;
    }
    public void setCurrentLocation(Location currentLocation) {
        this.CurrentLocation = currentLocation;
    }
    public Location getLastLocation() {
        return LastLocation;
    }
    public void setLastLocation(Location lastLocation) {
        this.LastLocation = lastLocation;
    }
}
