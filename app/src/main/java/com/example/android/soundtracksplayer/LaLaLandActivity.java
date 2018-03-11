package com.example.android.soundtracksplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaLaLandActivity extends AppCompatActivity {

    public final static String LALALAND_ACTIVITY_TAG = "LaLaLand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);
    }
}
