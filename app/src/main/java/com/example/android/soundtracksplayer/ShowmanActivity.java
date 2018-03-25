package com.example.android.soundtracksplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class ShowmanActivity extends AppCompatActivity {

    public static String[] showmanSongsList = {"The Greatest Show", "A Million Dreams", "A Million Dreams (Reprise)",
            "Come Alive", "The Other Side", "Never Enough", "This Is Me", "Rewrite the Stars", "Tightrope",
            "Never Enough (Reprise)", "From Now On"};

    public static String[] showmanSingers = {"Hugh Jackman, Keala Settle, Zac Efron, Zendaya",
            "Ziv Zaifman, Hugh Jackman, Michelle Williams", "Austyn Johnson, Cameron Seely, Hugh Jackman",
            "Hugh Jackman, Keala Settle, Daniel Everidge, Zendaya", "Hugh Jackman, Zac Efron", "Loren Allred",
            "Keala Settle", "Zac Efron, Zendaya", "Michelle Williams", "Loren Allred", "Hugh Jackman"};

    public static String[] showmanSongsDuration = {"5:02", "4:29", "1:00", "3:45", "3:34", "3:27", "3:54", "3:37",
            "3:54", "1:20", "5:49"};

    public static int[] showmanSongsIds = {R.raw.the_greatest_show, R.raw.a_million_dreams, R.raw.a_million_dreams_reprise,
            R.raw.come_alive, R.raw.the_other_side, R.raw.never_enough, R.raw.this_is_me, R.raw.rewrite_the_stars,
            R.raw.tightrope, R.raw.never_enough_reprise, R.raw.from_now_on};

    public final static int SHOWMAN_SONGS_AMOUNT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        // Getting access to ActionBar
        ActionBar actionBar = getSupportActionBar();
        //Enabling Home button
        actionBar.setHomeButtonEnabled(true);
        //Displaying Home button
        actionBar.setDisplayHomeAsUpEnabled(true);

        final ArrayList<Songs> songs = new ArrayList<>();
        for (int i = 0; i < SHOWMAN_SONGS_AMOUNT; i++) {
            songs.add(new Songs(i + 1, showmanSongsList[i], showmanSingers[i],
                    showmanSongsDuration[i], showmanSongsIds[i]));
        }

        SongsAdapter songsAdapter = new SongsAdapter(this, songs);
        ListView songsList = findViewById(R.id.lv_songs_list);
        songsList.setAdapter(songsAdapter);
        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openPlayerIntent = new Intent(ShowmanActivity.this, PlayerActivity.class);
                openPlayerIntent.putExtra("currentPosition", position);
                startActivity(openPlayerIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
