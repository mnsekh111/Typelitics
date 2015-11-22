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

    public static final int TOT_USERS = 10;

    public DummyUser(String name, String profilePic) {
        this.name = name;
        this.profilePic = profilePic;
        this.id = nextId++;
    }

    public int getAvgWPM(){
        int totWPM=0,totRaces=0;
        for(int i=0;i<DummyRaceStats.getList().size();i++){
            if(DummyRaceStats.getList().get(i).racers.containsKey(this)){
                totRaces++;
                totWPM+=DummyRaceStats.getList().get(i).racers.get(this).wpm;
            }
        }

        try {
            return totWPM / totRaces;
        }catch (RuntimeException re){
            return 0;
        }
    }

    public int getAvgAcc(){
        int totAcc=0,totRaces=0;
        for(int i=0;i<DummyRaceStats.getList().size();i++){
            if(DummyRaceStats.getList().get(i).racers.containsKey(this)){
                totRaces++;
                totAcc+=DummyRaceStats.getList().get(i).racers.get(this).acc;
            }
        }

        try {
            return totAcc / totRaces;
        }catch (RuntimeException re){
            return 0;
        }
    }

    public int getAvgPos(){
        int totPos=0,totRaces=0;
        for(int i=0;i<DummyRaceStats.getList().size();i++){
            if(DummyRaceStats.getList().get(i).racers.containsKey(this)){
                totRaces++;
                totPos+=DummyRaceStats.getList().get(i).racers.get(this).pos;
            }
        }

        try {
            return totPos / totRaces;
        }catch (RuntimeException re){
            return 0;
        }
    }
    public static ArrayList<DummyUser> getList(){
        if(list == null){
            list = new ArrayList<>(TOT_USERS);
            list.add(new DummyUser("Person 0","http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg"));
            list.add(new DummyUser("Person 1","https://upload.wikimedia.org/wikipedia/en/7/70/Shawn_Tok_Profile.jpg"));
            list.add(new DummyUser("Person 2","http://organicthemes.com/demo/profile/files/2012/12/profile_img.png"));
            list.add(new DummyUser("Person 3","http://www.likecool.com/Gear/Pic/One%20Trippy%20Profile%20Pic/One-Trippy-Profile-Pic.jpg"));
            list.add(new DummyUser("Person 4","http://www.geek.com/wp-content/uploads/2010/07/Scott-Forstall-Executive-profile-image.jpg"));
            list.add(new DummyUser("Person 5","https://media.cirrusmedia.com.au/LW_Media_Library/LW-603-p28-partner-profile.jpg"));
            list.add(new DummyUser("Person 6","https://lh5.googleusercontent.com/-ZadaXoUTBfs/AAAAAAAAAAI/AAAAAAAAAGA/19US52OmBqc/photo.jpg"));
            list.add(new DummyUser("Person 7","http://www.jobswolf.com/img/testimonials/1/profile.jpg"));
            list.add(new DummyUser("Person 8","https://www.morganstanley.com/assets/images/people/tiles/karlene-quigley-large.jpg"));
            list.add(new DummyUser("Person 9","https://upload.wikimedia.org/wikipedia/en/e/ef/Sean_Hood_Avatar,_Profile_Picture.jpg"));
        }
        return list;
    }

}
