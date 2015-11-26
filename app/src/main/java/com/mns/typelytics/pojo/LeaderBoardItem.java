package com.mns.typelytics.pojo;

import com.mns.typelytics.dummy.DummyUser;

import java.util.ArrayList;

/**
 * Created by mns on 11/15/15.
 */
public class LeaderBoardItem {
    private DummyUser dm;


    public LeaderBoardItem(DummyUser dm) {
        this.dm = dm;
    }

    public String getName() {
        return dm.name;
    }

    public String getProfilePicUrl() {
        return dm.profilePic;
    }

    public int getAvgWPM(){
        return dm.getAvgWPM();
    }

    public int getId(){
        return dm.id;
    }


    //For testing

    public static ArrayList<LeaderBoardItem> getSamLeaderBoardItems(int n) {
        ArrayList<DummyUser> sampleUsers = DummyUser.getNRandomUsers(n);
        ArrayList<LeaderBoardItem> sample = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sample.add(new LeaderBoardItem(sampleUsers.get(i)));
        }
        return sample;
    }
}