package com.mns.myapplication.pojo;

/**
 * Created by mns on 11/15/15.
 */
public class FeedItem {
    private String title;
    private String thumbnail;
    public static int rand = 0;
    public void FeedItem(){
        title = "This is title " +rand++;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}