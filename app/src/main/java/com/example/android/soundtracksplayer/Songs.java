package com.example.android.soundtracksplayer;

import android.widget.ImageButton;

/**
 * Created by Capri on 28.02.2018.
 */

public class Songs {
    private int mSongNumber;
    private String mSongTitle;
    private String mSinger;
    private String mSongDuration;
    private int mSongResourceId;

    public Songs(int songNumber, String songTitle, String singer, String songDuration, int songResourceId) {
        mSongNumber = songNumber;
        mSongTitle = songTitle;
        mSinger = singer;
        mSongDuration = songDuration;
        mSongResourceId = songResourceId;
    }

    public int getSongNumber() {
        return mSongNumber;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public String getSinger() {
        return mSinger;
    }

    public String getSongDuration() {
        return mSongDuration;
    }

    public int getSongResourceId() {
        return mSongResourceId;
    }

    @Override
    public String toString() {
        return "Song number: " + mSongNumber + "\n Song " + mSongTitle + " performed by " +
                mSinger + "." + "\n Song duration: " + mSongDuration + "\n Song Resource Id: " + mSongResourceId;
    }
}