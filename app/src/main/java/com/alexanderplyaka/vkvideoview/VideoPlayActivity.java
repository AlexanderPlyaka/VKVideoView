package com.alexanderplyaka.vkvideoview;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vk.sdk.WebView;

public class VideoPlayActivity extends AppCompatActivity {

    WebView webVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
    }
}
