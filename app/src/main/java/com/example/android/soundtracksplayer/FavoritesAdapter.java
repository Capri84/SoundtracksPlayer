package com.example.android.soundtracksplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Capri on 01.03.2018.
 */

public class FavoritesAdapter extends ArrayAdapter<Favorites> {
    public FavoritesAdapter(Context context, ArrayList<Favorites> favorites) {
        super(context, 0, favorites);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Favorites currentFavorite = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.songs_list, parent, false);
        }
        TextView songNumber = (TextView) convertView.findViewById(R.id.tv_song_number);
        TextView songName = (TextView) convertView.findViewById(R.id.tv_song_name);
        TextView songSinger = (TextView) convertView.findViewById(R.id.tv_singer);
        TextView songDuration = (TextView) convertView.findViewById(R.id.tv_song_length);
        songName.setText(currentFavorite.getSongTitle());
        songSinger.setText(currentFavorite.getSinger());
        songDuration.setText(currentFavorite.getSongDuration());
        return convertView;
    }
}
