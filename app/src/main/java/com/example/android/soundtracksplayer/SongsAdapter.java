package com.example.android.soundtracksplayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by Capri on 01.03.2018.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {
    SongsAdapter(Context context, ArrayList<Songs> songs) {
        super(context, 0, songs);
    }

    ArrayList<Favorites> favorites = new ArrayList<Favorites>();

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.songNumber = convertView.findViewById(R.id.tv_song_number);
            viewHolder.songName = convertView.findViewById(R.id.tv_song_name);
            viewHolder.songSinger = convertView.findViewById(R.id.tv_singer);
            viewHolder.songDuration = convertView.findViewById(R.id.tv_song_length);
            viewHolder.favorite = convertView.findViewById(R.id.img_favorite);
            viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Songs currentItem = getItem(position);
                    if (!favorites.contains(currentItem)) {
                        Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                        viewHolder.favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                        favorites.add(new Favorites(1, ShowmanActivity.showmanSongsList[position],
                                ShowmanActivity.showmanSingers[position], ShowmanActivity.showmanSongsDuration[position],
                                ShowmanActivity.showmanSongsIds[position]));
                        Log.v("favorites", favorites.toString());
                    } else if (favorites.contains(currentItem)) {
                        Toast.makeText(getContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                        viewHolder.favorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                        if (favorites.contains(currentItem)) {
                            favorites.remove(currentItem);
                            Log.v("favorites", favorites.toString());
                        }
                    }
                }
            });
            convertView.setTag(viewHolder);
        }
        mainViewHolder = (ViewHolder) convertView.getTag();
        Songs currentSong = getItem(position);
        mainViewHolder.songName.setText(currentSong.getSongTitle());
        mainViewHolder.songSinger.setText(currentSong.getSinger());
        mainViewHolder.songDuration.setText(currentSong.getSongDuration());
        return convertView;
    }
}


