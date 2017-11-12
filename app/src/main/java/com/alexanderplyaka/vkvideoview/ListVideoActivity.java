package com.alexanderplyaka.vkvideoview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiVideo;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

public class ListVideoActivity extends AppCompatActivity {

    RecyclerView listVideo;
    private VideoAdapter mAdapter;
    private ArrayList<VideoListItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);

        listVideo = (RecyclerView) findViewById(R.id.list_video);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listVideo.setLayoutManager(layoutManager);

        mItems = new ArrayList<>();

        VKRequest vkRequest = VKApi.video().get(VKParameters.from(VKApiConst.FILTERS, "video"));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList<VKApiVideo> list = (VKList) response.parsedModel;

                for (VKApiVideo video : list) {
                    mItems.add(new VideoListItem(video.title, video.photo_130, video.duration));
                }

                mAdapter = new VideoAdapter(mItems, new VideoAdapter.OnVideoClickItem() {
                    @Override
                    public void onPopularClick(VideoListItem item) {
                        Toast.makeText(getApplicationContext(), "Нажатие", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListVideoActivity.this, VideoPlayActivity.class);
                        startActivity(intent);
                    }
                });

                listVideo.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_exit:
                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
                VKSdk.logout();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
