// PlayerData.java
//   - This file is the main definition of all the data tracked by the plugin that is not already in the Player object inhibited by org.bukkit.player
//
// (c) Owen Rummage 2020

package dev.astridlabs.playtimerewardsplus.DataTypes;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class PlayerData {

    private int Playtime = 0;
    private UUID uuid;
    private String DisplayName;

    private Location CurrentLocation;
    private Location LastLocation;

    private List<String> RepetitiveUnlocks = new ArrayList<String>();

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
    public String getRepetitiveUnlocks(String timeRequired){
        String returnableString = "none";
        for(String reward : RepetitiveUnlocks){
            if(reward.split(":")[0].equals(timeRequired)){
                returnableString = reward;
            }
        }

        return returnableString;

    }
    public void setRepetitiveUnlocks(String timeRequired, Integer times){
        for(int i = 0; i<RepetitiveUnlocks.size(); i++){
            String reward = RepetitiveUnlocks.get(i);

            if(reward.split(":")[0].equals(timeRequired)){
                StringBuilder sb = new StringBuilder();

                sb.append(timeRequired);
                sb.append(":");
                sb.append(times);

                RepetitiveUnlocks.set(i, sb.toString());

            }
        }
    }


}
