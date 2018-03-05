package com.example.android.soundtracksplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.songNumber = (TextView) convertView.findViewById(R.id.tv_song_number);
            viewHolder.songName = (TextView) convertView.findViewById(R.id.tv_song_name);
            viewHolder.songSinger = (TextView) convertView.findViewById(R.id.tv_singer);
            viewHolder.songDuration = (TextView) convertView.findViewById(R.id.tv_song_length);
            viewHolder.favorite = (ImageButton) convertView.findViewById(R.id.img_favorite);
            viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
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

   /*     if (isFavorite == false) {
        favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        isFavorite = true;
        //  addToFavorites();
        //  Log.v("showman", favorites.toString());
        } else {
        favorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        isFavorite = false;
        //   removeFromFavorites();
        //   Log.v("showman", favorites.toString());
        }*/
