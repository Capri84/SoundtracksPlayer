package com.example.android.soundtracksplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing
        ImageView showman = findViewById(R.id.img_showman);
        //Setting listener to the View
        showman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showmanActivityIntent = new Intent(MainActivity.this, ShowmanActivity.class);
                startActivity(showmanActivityIntent);
            }
        });
    }
}
