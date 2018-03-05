package com.example.android.soundtracksplayer;

/**
 * Created by Capri on 01.03.2018.
 */

public class Favorites {
    private int mSongNumber;
    private String mSongTitle;
    private String mSinger;
    private String mSongDuration;
    private int mSongResourceId;

    public Favorites(int songNumber, String songTitle, String singer, String songDuration, int songResourceId) {
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
}
