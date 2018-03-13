package com.example.android.soundtracksplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.soundtracksplayer.ShowmanActivity.favorites;

/**
 * Created by Capri on 01.03.2018.
 */

public class SongsAdapter extends ArrayAdapter<Songs> {
    SongsAdapter(Context context, ArrayList<Songs> songs) {
        super(context, 0, songs);
    }

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
                    if (favorites.size() == 0) {
                        favorites.add(currentItem);
                        Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                        viewHolder.favorite.setImageResource(R.drawable.ic_favorite_black_36dp);
                        Log.i("favorites", favorites.toString());
                    } else {
                        for (int i = 0; i < favorites.size(); i++) {
                            if (!favorites.contains(currentItem)) {
                                favorites.add(currentItem);
                                Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                                viewHolder.favorite.setImageResource(R.drawable.ic_favorite_black_36dp);
                                Log.i("favorites", favorites.toString());
                                return;
                            } else {
                                favorites.remove(currentItem);
                                Toast.makeText(getContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
                                viewHolder.favorite.setImageResource(R.drawable.ic_favorite_border_black_36dp);
                                Log.i("favorites", favorites.toString());
                                return;
                            }
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