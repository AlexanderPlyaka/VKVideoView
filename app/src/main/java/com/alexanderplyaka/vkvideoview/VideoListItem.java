package com.alexanderplyaka.vkvideoview;


import java.io.Serializable;

public class VideoListItem implements Serializable {

    public String title;
    public String urlPhoto;
    private int mTime;

    public VideoListItem() {
    }

    VideoListItem(String title, String urlPhoto, int mTime) {
        this.title = title;
        this.urlPhoto = urlPhoto;
        this.mTime = mTime;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public String getTime() {
        Long minutes = (long) ((mTime % 3600) / 60);
        Long seconds = (long) (mTime % 60);
        String mString;
        if (seconds < 10) {
            mString = String.valueOf("" + minutes + ":0" + seconds);
        } else {
            mString = String.valueOf("" + minutes + ":" + seconds);
        }
        return mString;
    }
}
