package com.mns.typelytics.dummy;

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
            list.add(new DummyUser("Cercei Blonde","http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg"));
            list.add(new DummyUser("Jamie Lower","https://upload.wikimedia.org/wikipedia/en/7/70/Shawn_Tok_Profile.jpg"));
            list.add(new DummyUser("Varys Spider","http://organicthemes.com/demo/profile/files/2012/12/profile_img.png"));
            list.add(new DummyUser("Baelish Finger","http://www.likecool.com/Gear/Pic/One%20Trippy%20Profile%20Pic/One-Trippy-Profile-Pic.jpg"));
            list.add(new DummyUser("Cat Star","http://www.geek.com/wp-content/uploads/2010/07/Scott-Forstall-Executive-profile-image.jpg"));
            list.add(new DummyUser("Denny Targy","https://media.cirrusmedia.com.au/LW_Media_Library/LW-603-p28-partner-profile.jpg"));
            list.add(new DummyUser("Trion Impman","https://lh5.googleusercontent.com/-ZadaXoUTBfs/AAAAAAAAAAI/AAAAAAAAAGA/19US52OmBqc/photo.jpg"));
            list.add(new DummyUser("Maggie Tyrell","http://www.jobswolf.com/img/testimonials/1/profile.jpg"));
            list.add(new DummyUser("Jeffry Batminton","https://www.morganstanley.com/assets/images/people/tiles/karlene-quigley-large.jpg"));
            list.add(new DummyUser("Cal Dragon","https://upload.wikimedia.org/wikipedia/en/e/ef/Sean_Hood_Avatar,_Profile_Picture.jpg"));
        }
        return list;
    }

    public static DummyUser getUser(int id){
        for(int i=0;i<DummyUser.getList().size();i++){
            if(DummyUser.getList().get(i).id == id){
                return DummyUser.getList().get(i);
            }
        }

        return null;
    }

    public static ArrayList<DummyUser> getNRandomUsers(int n){
        Random ran = new Random();
        ArrayList<DummyUser> sample = new ArrayList<>();

        int start_index = ran.nextInt(DummyUser.getList().size());
        for (int i = 0; i < DummyUser.getList().size(); i++) {
            DummyUser d = DummyUser.getList().get((start_index+i) % DummyUser.getList().size());
            sample.add(d);
        }
        return sample;
    }

}
