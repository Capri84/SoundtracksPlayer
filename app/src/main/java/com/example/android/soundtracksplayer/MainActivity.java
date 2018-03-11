package com.example.android.soundtracksplayer;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing Views
        ImageView showman = findViewById(R.id.img_showman);
        ImageView laLaLand = findViewById(R.id.img_lalaland);
        //Setting listeners to the Views
        showman.setOnClickListener(this);
        laLaLand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_showman:
                Intent showmanActivityIntent = new Intent(this, ShowmanActivity.class);
                startActivity(showmanActivityIntent);
                break;
            case R.id.img_lalaland:
                Intent laLaLandActivityIntent = new Intent(this, LaLaLandActivity.class);
                startActivity(laLaLandActivityIntent);
                break;
        }
    }
}
