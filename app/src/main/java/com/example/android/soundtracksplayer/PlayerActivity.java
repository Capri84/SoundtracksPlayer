package com.example.android.soundtracksplayer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    // UI Components
    @BindView(R.id.img_album_cover)
    ImageView imgAlbumCover;
    @BindView(R.id.tv_song_name)
    TextView tvSongName;
    @BindView(R.id.tv_singer)
    TextView tvSinger;
    @BindView(R.id.img_btn_repeat_song)
    ImageButton imgBtnRepeatSong;
    @BindView(R.id.img_btn_previous_song)
    ImageButton imgBtnPreviousSong;
    @BindView(R.id.img_btn_play)
    ImageButton imgBtnPlay;
    @BindView(R.id.img_btn_pause)
    ImageButton imgBtnPause;
    @BindView(R.id.img_btn_next_song)
    ImageButton imgBtnNextSong;
    @BindView(R.id.img_btn_shuffle)
    ImageButton imgBtnShuffle;
    private MediaPlayer mediaPlayer;
    private int currentPosition;
    private int currentSongId;
    private boolean isShuffle = false;
    private boolean isRepeat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        fillPlayerViews();
        // Getting access to ActionBar
        ActionBar actionBar = getSupportActionBar();
        //Enabling Home button
        actionBar.setHomeButtonEnabled(true);
        //Displaying Home button
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Create and setup the {@link AudioManager} to request audio focus
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        /**
         * This listener gets triggered whenever the audio focus changes
         * (i.e., we gain or lose audio focus because of another app or device).
         */
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        if (mediaPlayer != null) {
                            imgBtnPlay.setVisibility(View.VISIBLE);
                            imgBtnPause.setVisibility(View.INVISIBLE);
                            mediaPlayer.start();
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        mediaPlayer.pause();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        if (mediaPlayer != null) {
                            mediaPlayer.pause();
                        }
                        break;
                }
            }
        };

        // Release the media player if it currently exists because we are about to
        // play a different sound file
        releaseMediaPlayer();

        // Request audio focus in order to play the audio file.
        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Start playing the song
            imgBtnPlay.setVisibility(View.INVISIBLE);
            imgBtnPause.setVisibility(View.VISIBLE);
            mediaPlayer = mediaPlayer.create(this, currentSongId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        }
    }

    public void fillPlayerViews() {
        Intent openPlayerIntent = getIntent();
        currentPosition = openPlayerIntent.getIntExtra("currentPosition", -1);
        tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
        tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
        currentSongId = ShowmanActivity.showmanSongsIds[currentPosition];
        imgAlbumCover.setImageResource(R.drawable.showman);
    }

    @OnClick({R.id.img_btn_repeat_song, R.id.img_btn_previous_song, R.id.img_btn_play, R.id.img_btn_pause, R.id.img_btn_next_song, R.id.img_btn_shuffle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_repeat_song:
                repeatSong();
                break;
            case R.id.img_btn_previous_song:
                playPreviousSong();
                break;
            case R.id.img_btn_play:
                playSong();
                break;
            case R.id.img_btn_pause:
                pauseSong();
                break;
            case R.id.img_btn_next_song:
                playNextSong();
                break;
            case R.id.img_btn_shuffle:
                shuffleSongs();
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        releaseMediaPlayer();
        // no repeat or shuffle ON, play next song
        if (currentPosition < (ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1)) {
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition + 1]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition + 1]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition + 1]);
            mediaPlayer.start();
            currentPosition++;
            mediaPlayer.setOnCompletionListener(this);
        } else {
            // play first song
            currentPosition = 0;
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        }
    }

    private void playSong() {
        imgBtnPlay.setVisibility(View.INVISIBLE);
        imgBtnPause.setVisibility(View.VISIBLE);
        if (mediaPlayer == null) {
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        } else {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        }
    }

    private void pauseSong() {
        imgBtnPause.setVisibility(View.INVISIBLE);
        imgBtnPlay.setVisibility(View.VISIBLE);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void repeatSong() {
        if (isRepeat) {
            isRepeat = false;
            Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
            imgBtnRepeatSong.setImageResource(R.drawable.ic_repeat_white_36dp);
            mediaPlayer.setLooping(false);
        } else {
            // make repeat to true
            isRepeat = true;
            Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
            // make shuffle to false
            isShuffle = false;
            imgBtnRepeatSong.setImageResource(R.drawable.ic_repeat_cyan_700_36dp);
            imgBtnShuffle.setImageResource(R.drawable.ic_shuffle_white_36dp);
            try {
                mediaPlayer.setLooping(true);
            } catch (NullPointerException e) {
            }
        }
    }

    private void playPreviousSong() {
        if (imgBtnPlay.getVisibility() == View.VISIBLE) {
            imgBtnPlay.setVisibility(View.INVISIBLE);
            imgBtnPause.setVisibility(View.VISIBLE);
        }
        if (isRepeat) {
            isRepeat = false;
            Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
            imgBtnRepeatSong.setImageResource(R.drawable.ic_repeat_white_36dp);
        }
        // check if previous song is there or not
        if (currentPosition > 0) {
            releaseMediaPlayer();
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition - 1]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition - 1]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition - 1]);
            mediaPlayer.start();
            currentPosition--;
        } else {
            // play first song
            releaseMediaPlayer();
            currentPosition = ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1;
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
        }
    }

    private void playNextSong() {
        if (imgBtnPlay.getVisibility() == View.VISIBLE) {
            imgBtnPlay.setVisibility(View.INVISIBLE);
            imgBtnPause.setVisibility(View.VISIBLE);
        }
        if (isRepeat) {
            isRepeat = false;
            Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
            imgBtnRepeatSong.setImageResource(R.drawable.ic_repeat_white_36dp);
            // check if next song is there or not
            if (currentPosition < (ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1)) {
                releaseMediaPlayer();
                tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition + 1]);
                tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition + 1]);
                mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition + 1]);
                mediaPlayer.start();
                currentPosition++;
            } else {
                // play first song
                releaseMediaPlayer();
                currentPosition = 0;
                tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
                tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
                mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
                mediaPlayer.start();
            }
        }
        if (isShuffle) {
            releaseMediaPlayer();
            currentPosition = rnd(ShowmanActivity.SHOWMAN_SONGS_AMOUNT);
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
        } else {
            currentPosition++;
            if (currentPosition > ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1) {
                currentPosition = 0;
            }
            releaseMediaPlayer();
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
        }
    }

    private void shuffleSongs() {
        if (isShuffle) {
            isShuffle = false;
            Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
            imgBtnShuffle.setImageResource(R.drawable.ic_shuffle_white_36dp);
        } else {
            // make shuffle to true
            isShuffle = true;
            Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
            // make repeat to false
            isRepeat = false;
            imgBtnShuffle.setImageResource(R.drawable.ic_shuffle_cyan_700_36dp);
            imgBtnRepeatSong.setImageResource(R.drawable.ic_repeat_white_36dp);
        }
    }

    public static int rnd(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
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
        startActivity(new Intent(this, ShowmanActivity.class));
        releaseMediaPlayer();
        finish();
    }
}

