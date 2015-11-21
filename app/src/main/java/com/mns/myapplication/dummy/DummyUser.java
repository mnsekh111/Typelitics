package com.mns.myapplication.dummy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mns on 11/20/15.
 */
public class DummyUser {
    public int id;
    public String name;
    public String profilePic;
    private Random random = new Random();


    private static int nextId = 0;
    private static ArrayList<DummyUser> list = null;
    private static final int TOT_USERS = 10;

    public DummyUser(String name, String profilePic) {
        this.name = name;
        this.profilePic = profilePic;
        this.id = nextId++;
    }

    public static ArrayList<DummyUser> getList(){
        if(list == null){
            list = new ArrayList<>(TOT_USERS);
        }
        return list;
    }

}
