package com.example.android.soundtracksplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener /*Runnable,
      //  SeekBar.OnSeekBarChangeListener */ {

    // UI Components
    @BindView(R.id.img_album_cover)
    ImageView imgAlbumCover;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_song_duration)
    TextView tvSongDuration;
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

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
  /*  private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //  imgBtnPlay.setVisibility(View.VISIBLE);
            //  imgBtnPause.setVisibility(View.INVISIBLE);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition + 1]);
            mediaPlayer.start();
            currentPosition++;
            // Now that the sound file has finished playing, release the media player resources.
            // releaseMediaPlayer();
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        fillPlayerViews();
        //Start playing the song
        mediaPlayer = mediaPlayer.create(this, currentSongId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
        //   seekBar.setOnSeekBarChangeListener(this);
        //Для чего????????????????
        //   seekBar.setEnabled(false);*/
    }

    public void fillPlayerViews() {
        Intent openPlayerIntent = getIntent();
        currentPosition = openPlayerIntent.getIntExtra("currentPosition", -1);
        tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
        tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
        tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
        currentSongId = ShowmanActivity.showmanSongsIds[currentPosition];
        if (openPlayerIntent.getStringExtra("activity").equals(ShowmanActivity.SHOWMAN_ACTIVITY_TAG)) {
            imgAlbumCover.setImageResource(R.drawable.showman);
        } else if (openPlayerIntent.getStringExtra("activity").equals(LaLaLandActivity.LALALAND_ACTIVITY_TAG)) {
            imgAlbumCover.setImageResource(R.drawable.lalaland);
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            // audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @OnClick({R.id.img_btn_repeat_song, R.id.img_btn_previous_song, R.id.img_btn_play, R.id.img_btn_pause, R.id.img_btn_next_song, R.id.img_btn_shuffle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_repeat_song:
                if (isRepeat) {
                    isRepeat = false;
                    Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
                    //   btnRepeat.setImageResource(R.drawable.btn_repeat);
                } else {
                    // make repeat to true
                    isRepeat = true;
                    Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
                    // make shuffle to false
                    isShuffle = false;
                    //  btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
                    //  btnShuffle.setImageResource(R.drawable.btn_shuffle);
                }
                break;
            case R.id.img_btn_previous_song:
                // check if previous song is there or not
                if (currentPosition > 0) {
                    releaseMediaPlayer();
                    tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition - 1]);
                    tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition - 1]);
                    tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition - 1]);
                    mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition - 1]);
                    mediaPlayer.start();
                    currentPosition--;
                } else {
                    // play first song
                    releaseMediaPlayer();
                    currentPosition = ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1;
                    tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
                    tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
                    tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
                    mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
                    mediaPlayer.start();
                }
                break;
            case R.id.img_btn_play:
                imgBtnPlay.setVisibility(View.INVISIBLE);
                imgBtnPause.setVisibility(View.VISIBLE);
                if (mediaPlayer == null) {
                    mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.start();
                }
                //     mediaPlayer.setOnCompletionListener(completionListener);
                break;
            case R.id.img_btn_pause:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    imgBtnPause.setVisibility(View.INVISIBLE);
                    imgBtnPlay.setVisibility(View.VISIBLE);
                    mediaPlayer.pause();
                }
                break;
            case R.id.img_btn_next_song:
                // check if next song is there or not
                if (currentPosition < (ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1)) {
                    releaseMediaPlayer();
                    tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition + 1]);
                    tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition + 1]);
                    tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition + 1]);
                    mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition + 1]);
                    mediaPlayer.start();
                    currentPosition++;
                } else {
                    // play first song
                    releaseMediaPlayer();
                    currentPosition = 0;
                    tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
                    tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
                    tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
                    mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
                    mediaPlayer.start();
                }
                break;
            case R.id.img_btn_shuffle:
                if (isShuffle) {
                    isShuffle = false;
                    Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
                    //  btnShuffle.setImageResource(R.drawable.btn_shuffle);
                } else {
                    // make repeat to true
                    isShuffle = true;
                    Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
                    // make shuffle to false
                    isRepeat = false;
                    //  btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
                    //  btnRepeat.setImageResource(R.drawable.btn_repeat);
                }
                break;
        }
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        imgBtnPlay.setVisibility(View.VISIBLE);
        imgBtnPause.setVisibility(View.INVISIBLE);
        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on, play same song again
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
            imgBtnPlay.setVisibility(View.INVISIBLE);
            imgBtnPause.setVisibility(View.VISIBLE);
        } else if (isShuffle) {
            // shuffle is on, play a random song
            Random rand = new Random();
            currentPosition = rand.nextInt((ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1) - 0 + 1) + 0;
            tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
            tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
            tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
            mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
            mediaPlayer.start();
            imgBtnPlay.setVisibility(View.INVISIBLE);
            imgBtnPause.setVisibility(View.VISIBLE);
        } else {
            // no repeat or shuffle ON, play next song
            if (currentPosition < (ShowmanActivity.SHOWMAN_SONGS_AMOUNT - 1)) {
                tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition + 1]);
                tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition + 1]);
                tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition + 1]);
                mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition + 1]);
                mediaPlayer.start();
                currentPosition++;
            } else {
                // play first song
                currentPosition = 0;
                tvSongName.setText(ShowmanActivity.showmanSongsList[currentPosition]);
                tvSinger.setText(ShowmanActivity.showmanSingers[currentPosition]);
                tvSongDuration.setText(ShowmanActivity.showmanSongsDuration[currentPosition]);
                mediaPlayer = mediaPlayer.create(this, ShowmanActivity.showmanSongsIds[currentPosition]);
                mediaPlayer.start();
            }
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    }
}

