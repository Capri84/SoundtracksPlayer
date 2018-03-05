package com.example.android.soundtracksplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity/* implements Runnable,
      //  View.OnClickListener, SeekBar.OnSeekBarChangeListener */{

    // UI Components
    private ImageView albumCover;
    private SeekBar seekBar;
    private TextView songTitle;
    private TextView songSinger;
    private TextView songDuration;
    private TextView nextSongToPlay;
    private TextView nextSongTitle;
    private MediaPlayer mediaPlayer;
    private ImageButton repeatSong;
    private ImageButton previousSong;
    private ImageButton playSong;
    private ImageButton pauseSong;
    private ImageButton nextSong;
    private ImageButton shuffleSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        // Initializing UI Components
        albumCover = findViewById(R.id.img_album_cover);
        seekBar = findViewById(R.id.seekbar);
        songTitle = findViewById(R.id.tv_song_name);
        songSinger = findViewById(R.id.tv_singer);
        songDuration = findViewById(R.id.tv_song_duration);
        nextSongToPlay = findViewById(R.id.tv_next_song);
        nextSongTitle = findViewById(R.id.tv_next_song_title);
        repeatSong = findViewById(R.id.img_btn_repeat_song);
        previousSong = findViewById(R.id.img_btn_previous_song);
        playSong = findViewById(R.id.img_btn_play);
        pauseSong = findViewById(R.id.img_btn_pause);
        nextSong = findViewById(R.id.img_btn_next_song);
        shuffleSongs = findViewById(R.id.img_btn_shuffle);
        fillPlayerViews();
     /*   // Setting OnClickListeners on ImageButtons
        repeatSong.setOnClickListener(this);
        previousSong.setOnClickListener(this);
        playSong.setOnClickListener(this);
        pauseSong.setOnClickListener(this);
        nextSong.setOnClickListener(this);
        shuffleSongs.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        //Для чего????????????????
        //   seekBar.setEnabled(false);*/
    }

    public void fillPlayerViews() {
        Intent openPlayerIntent = getIntent();
        songTitle.setText(openPlayerIntent.getStringExtra("currentSong"));
        songSinger.setText(openPlayerIntent.getStringExtra("currentSinger"));
        songDuration.setText(openPlayerIntent.getStringExtra("currentSongDuration"));
        Log.v("currentSong", songTitle.toString());
        Log.v("currentSinger", songSinger.toString());
        Log.v("currentSongDuration", songDuration.toString());
    }
}

    /**
     * This method identifies progress while media is playing and sets position on SeekBar
     */
/*    public void run() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();

        while (mediaPlayer != null && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }

    /**
     * This method
     */
 /*   public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_repeat_song:
                if(isRepeat){
                    isRepeat = false;
                    Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
                    repeatSong.setImageResource(R.drawable.btn_repeat);
                }else{
                    isRepeat = true;
                    Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
                    isShuffle = false;
                    repeatSong.setImageResource(R.drawable.btn_repeat_focused);
                    shuffleSongs.setImageResource(R.drawable.btn_shuffle);
                }
                break;
            case R.id.img_btn_previous_song:
                break;
            case R.id.img_btn_play:
                break;
            case R.id.img_btn_pause:
                break;
            case R.id.img_btn_next_song:
                break;
            case R.id.img_btn_shuffle:
                if(isShuffle){
                    isShuffle = false;
                    Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
                    shuffleSongs.setImageResource(R.drawable.btn_shuffle);
                }else{
                    // make repeat to true
                    isShuffle= true;
                    Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
                    // make shuffle to false
                    isRepeat = false;
                    shuffleSongs.setImageResource(R.drawable.btn_shuffle_focused);
                    repeatSong.setImageResource(R.drawable.btn_repeat);
                }
                break;
        }
    }

}*/

