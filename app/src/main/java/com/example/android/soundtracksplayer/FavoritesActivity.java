package com.example.android.soundtracksplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

  /*      final ArrayList<Favorites> favorites = new ArrayList<Favorites>();
        for (int i = 0; i < favorites.size(); i++) {
            if (SongsAdapter.isFavorite) {
                favorites.add(new Favorites(i + 1, ShowmanActivity.showmanSongsList[i], ShowmanActivity.showmanSingers[i], ShowmanActivity.showmanSongsDuration[i], ShowmanActivity.showmanSongsIds[i]));
                Log.v("favorites", ShowmanActivity.showmanSongsList[i]);
            }
        }
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favorites);
        ListView favoritesList = findViewById(R.id.lv_favorites_list);
        favoritesList.setAdapter(favoritesAdapter);
    }*/
}
}
