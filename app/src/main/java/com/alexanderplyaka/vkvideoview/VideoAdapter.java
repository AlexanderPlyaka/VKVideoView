package com.alexanderplyaka.vkvideoview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private ArrayList<VideoListItem> mData;
    private OnVideoClickItem listener;

    VideoAdapter(ArrayList<VideoListItem> item, OnVideoClickItem listener) {
        this.mData = item;
        this.listener = listener;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,
                parent, false));
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
        final VideoListItem videoListItem = mData.get(position);
        holder.title.setText(mData.get(position).getTitle());
        holder.time.setText(mData.get(position).getTime());
        holder.photo.setImageURI(Uri.parse(mData.get(position).getUrlPhoto()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPopularClick(videoListItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;
        ImageView photo;

        VideoViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

    public interface OnVideoClickItem {
        void onPopularClick(VideoListItem item);
    }
}
