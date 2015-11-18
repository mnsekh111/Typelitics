package com.mns.myapplication.pojo;

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
        sample.add(new LeaderBoardItem("Person 1","http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg"));
        sample.add(new LeaderBoardItem("Person 2","https://upload.wikimedia.org/wikipedia/en/7/70/Shawn_Tok_Profile.jpg"));
        sample.add(new LeaderBoardItem("Person 3","http://organicthemes.com/demo/profile/files/2012/12/profile_img.png"));
        sample.add(new LeaderBoardItem("Person 4","http://www.likecool.com/Gear/Pic/One%20Trippy%20Profile%20Pic/One-Trippy-Profile-Pic.jpg"));
        sample.add(new LeaderBoardItem("Person 5","http://www.geek.com/wp-content/uploads/2010/07/Scott-Forstall-Executive-profile-image.jpg"));
        sample.add(new LeaderBoardItem("Person 6","https://media.cirrusmedia.com.au/LW_Media_Library/LW-603-p28-partner-profile.jpg"));
        sample.add(new LeaderBoardItem("Person 7","https://lh5.googleusercontent.com/-ZadaXoUTBfs/AAAAAAAAAAI/AAAAAAAAAGA/19US52OmBqc/photo.jpg"));
        sample.add(new LeaderBoardItem("Person 8","http://www.jobswolf.com/img/testimonials/1/profile.jpg"));
        sample.add(new LeaderBoardItem("Person 9","https://www.morganstanley.com/assets/images/people/tiles/karlene-quigley-large.jpg"));
        sample.add(new LeaderBoardItem("Person 10","https://upload.wikimedia.org/wikipedia/en/e/ef/Sean_Hood_Avatar,_Profile_Picture.jpg"));
        return sample;
    }
}