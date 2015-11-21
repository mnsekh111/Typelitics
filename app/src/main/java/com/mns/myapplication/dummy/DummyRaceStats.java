package com.mns.myapplication.dummy;

import com.mns.myapplication.SettingsActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mns on 11/20/15.
 */
public class DummyRaceStats {
    public static final int MAX_RACERS = 5;
    private static int next_raceId = 0;
    public int raceId;
    public SettingsActivity.Settings raceSettings; //wrong to store the entire settings (only id)
    public Map<DummyUser,DummyStat> racers = new HashMap<>(MAX_RACERS); //wrong to store the entire races (only id)

    public DummyRaceStats(){

    }
}
