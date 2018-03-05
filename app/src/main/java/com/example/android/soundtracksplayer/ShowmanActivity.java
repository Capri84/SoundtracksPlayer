package com.example.android.soundtracksplayer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowmanActivity extends AppCompatActivity {

    private String[] showmanSongsList = {"The Greatest Show", "A Million Dreams", "A Million Dreams (Reprise)",
            "Come Alive", "The Other Side", "Never Enough", "This Is Me", "Rewrite the Stars", "Tightrope",
            "Never Enough (Reprise)", "From Now On"};

    private String[] showmanSingers = {"Hugh Jackman, Keala Settle, Zac Efron, Zendaya",
            "Ziv Zaifman, Hugh Jackman, Michelle Williams", "Austyn Johnson, Cameron Seely, Hugh Jackman",
            "Hugh Jackman, Keala Settle, Daniel Everidge, Zendaya", "Hugh Jackman, Zac Efron", "Loren Allred",
            "Keala Settle", "Zac Efron, Zendaya", "Michelle Williams", "Loren Allred", "Hugh Jackman"};

    private String[] showmanSongsDuration = {"5:02", "4:29", "1:00", "3:45", "3:34", "3:27", "3:54", "3:37",
            "3:54", "1:20", "5:49"};

    public final int SHOWMAN_SONGS_AMOUNT = 11;

    private MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    //ArrayList<Favorites> favorites;
    boolean isFavorite = false;
    ImageView play;
    ImageView pause;
    // ArrayList<Favorites> favorites;
    // Button toFavorites;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
   /* private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            mediaPlayer.release();
            pause.setVisibility(View.INVISIBLE);
            play.setVisibility(View.VISIBLE);
            isPlaying = false;
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);
        //  toFavorites = findViewById(R.id.btn_to_favorites);
        //  toFavorites.setOnClickListener(this);

        final ArrayList<Songs> songs = new ArrayList<Songs>();
        for (int i = 0; i < SHOWMAN_SONGS_AMOUNT; i++) {
            songs.add(new Songs(1, showmanSongsList[i], showmanSingers[i], showmanSongsDuration[i], R.raw.number_two));
        }
        SongsAdapter songsAdapter = new SongsAdapter(this, songs);
        ListView songsList = findViewById(R.id.lv_songs_list);
        songsList.setAdapter(songsAdapter);
        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent openPlayerIntent = new Intent(ShowmanActivity.this, PlayerActivity.class);
             openPlayerIntent.putExtra("currentSong", showmanSongsList[position]);
             openPlayerIntent.putExtra("currentSinger", showmanSingers[position]);
             openPlayerIntent.putExtra("currentSongDuration", showmanSongsDuration[position]);
             startActivity(openPlayerIntent);
                /*   Toast.makeText(getApplicationContext(), "Item is clicked", Toast.LENGTH_SHORT).show();
                play = view.findViewById(R.id.img_play);
                pause = view.findViewById(R.id.img_pause);
                if (!isPlaying) {
                    play.setVisibility(View.INVISIBLE);
                    pause.setVisibility(View.VISIBLE);
                    mediaPlayer = mediaPlayer.create(ShowmanActivity.this, R.raw.number_two);
                    mediaPlayer.start();
                    isPlaying = true;
                } else {
                    pause.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.VISIBLE);
                    mediaPlayer.pause();
                    isPlaying = false;
                }
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });*/
            }

   /*     favorites = new ArrayList<Favorites>();
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favorites);
        ListView favoritesList = (ListView) findViewById(R.id.lv_songs_list);
        favoritesList.setAdapter(favoritesAdapter);
    }*/

   /*     case R.id.btn_to_favorites:
                Intent toFavoritesIntent = new Intent(this, FavoritesActivity.class);
                startActivity(toFavoritesIntent);
        }
    }

    public static void addToFavorites() {
        for (int i = 0; i < SHOWMAN_SONGS_AMOUNT; i++) {
            SongsAdapter.favorites.add(new Favorites(1, showmanSongsList[i], showmanSingers[i], showmanSongsDuration[i], R.raw.number_two));
        }
    }

    public static void removeFromFavorites() {
        for (int i = 0; i < SHOWMAN_SONGS_AMOUNT; i++) {
            SongsAdapter.favorites.remove(new Favorites(1, showmanSongsList[i], showmanSingers[i], showmanSongsDuration[i], R.raw.number_two));
        }
    }*/
        });
    }

}