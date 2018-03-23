package com.example.android.soundtracksplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/**
 * Created by Capri on 01.03.2018.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {

    SongsAdapter(Context context, ArrayList<Songs> songs) {
        super(context, 0, songs);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.songNumber = convertView.findViewById(R.id.tv_song_number);
        viewHolder.songName = convertView.findViewById(R.id.tv_song_name);
        viewHolder.songSinger = convertView.findViewById(R.id.tv_singer);
        viewHolder.songDuration = convertView.findViewById(R.id.tv_song_length);
        final Songs currentSong = getItem(position);
        if (currentSong != null) {
            viewHolder.songNumber.setText(String.valueOf(currentSong.getSongNumber()));
            viewHolder.songName.setText(currentSong.getSongTitle());
            viewHolder.songSinger.setText(currentSong.getSinger());
            viewHolder.songDuration.setText(currentSong.getSongDuration());
        }
        return convertView;
    }
}