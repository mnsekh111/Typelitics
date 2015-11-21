package com.mns.myapplication.dummy;

import com.mns.myapplication.SettingsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by mns on 11/20/15.
 */
public class DummyRaceStats {
    public static final int MAX_RACERS = 5;
    private static int next_raceId = 0;
    private static ArrayList<DummyRaceStats> list = null;
    private Random random = new Random();

    public int raceId;
    public SettingsActivity.Settings raceSettings; //wrong to store the entire settings (only id)
    public Map<DummyUser,DummyStat> racers = new HashMap<>(MAX_RACERS); //wrong to store the entire races (only id)

    public DummyRaceStats(){
        raceId = next_raceId++;
        raceSettings.keyboard = random.nextInt(3);
        raceSettings.auto_complete = random.nextBoolean();
        raceSettings.auto_correct = random.nextBoolean();
        raceSettings.suggest = random.nextBoolean();

        int start = random.nextInt(DummyUser.TOT_USERS);
        for(int i =0;i<MAX_RACERS;i++){
            racers.put(DummyUser.getList().get(start++%DummyUser.TOT_USERS),new DummyStat(100-i*10,i+1,95-i));
        }
    }


    public static ArrayList<DummyRaceStats> getList(){
        if(list == null){
            for(int i=0;i<100;i++){
                list.add(new DummyRaceStats());
            }
        }

        return null;
    }

    public void add(DummyRaceStats ds){
        list.add(ds);
    }
}
