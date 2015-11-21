package com.mns.myapplication.pojo;

import com.mns.myapplication.dummy.DummyUser;

import java.util.ArrayList;

/**
 * Created by mns on 11/15/15.
 */
public class LeaderBoardItem {
    private String name;
    private String profilePicUrl;


    public LeaderBoardItem(String name, String url) {
        setName(name);
        setProfilePicUrl(url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    //For testing
    public static ArrayList<LeaderBoardItem> getSamLeaderBoardItems() {
        ArrayList<LeaderBoardItem> sample = new ArrayList<>();
        for(int i=0;i< DummyUser.getList().size();i++){
            DummyUser d = DummyUser.getList().get(i);
            sample.add(new LeaderBoardItem(d.name,d.profilePic));
        }
        return sample;
    }
}