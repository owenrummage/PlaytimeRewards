// Reward.java
//   - Datatype saved by the RewardManager.java
//
// (c) Owen Rummage 2020

package dev.astridlabs.playtimerewardsplus.DataTypes;

import java.util.ArrayList;
import java.util.List;

public class Reward {

    public Integer TimeRequred;
    public boolean DoesRepeat;
    public String UnlockMessage;

    public List<String> Commands = new ArrayList<String>();

    public Reward(Integer timeRequred, boolean doesRepeat, String unlockMessage, List commands){
        this.TimeRequred = timeRequred;
        this.DoesRepeat = doesRepeat;
        this.UnlockMessage = unlockMessage;
        this.Commands = commands;
    }

}