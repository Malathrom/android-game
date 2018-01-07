package com.example.adrian.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button jouer;
    Button options;
    Button stats;
    RelativeLayout layoutback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFirstRun();
        jouer=findViewById(R.id.jouer);
        jouer.setOnClickListener(this);

        options=findViewById(R.id.options);
        options.setOnClickListener(this);

        stats=findViewById(R.id.stats);
        stats.setOnClickListener(this);

        final DatabaseHandler db = new DatabaseHandler(this);

        if (db.getPref(0).get(0)==null){
            db.addPref(0,0,0,0);
        }
        layoutback= findViewById(R.id.layoutback);
        switch (db.getPref(0).get(1)){
            case 0: layoutback.setBackgroundResource(R.drawable.back_blue);
                break;
            case 1: layoutback.setBackgroundResource(R.drawable.back_red);
                break;
            case 2: layoutback.setBackgroundResource(R.drawable.back_green);
                break;

        }
    }
    @Override
    public void onResume(){
        super.onResume();
        final DatabaseHandler db = new DatabaseHandler(this);
        switch (db.getPref(0).get(1)){
            case 0: layoutback.setBackgroundResource(R.drawable.back_blue);
                break;
            case 1: layoutback.setBackgroundResource(R.drawable.back_red);
                break;
            case 2: layoutback.setBackgroundResource(R.drawable.back_green);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.jouer:
                lancerPartie();
                break;
            case R.id.options:
                lancerOptions();
                break;
            case R.id.stats:
                lancerStats();
                break;
        }
    }

    private void lancerPartie() {
        Intent intent= new Intent(this, Game.class);
        startActivity(intent);
    }
    private void lancerOptions() {
        Intent intent= new Intent(this, Options.class);
        startActivity(intent);
    }
    private void lancerStats() {
        Intent intent= new Intent(this, Stats.class);
        startActivity(intent);
    }
    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        int currentVersionCode = BuildConfig.VERSION_CODE;

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        if (currentVersionCode == savedVersionCode) {

            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            DatabaseHandler db = new DatabaseHandler(this);
            db.addPref(0,0,0,0);

        }

        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
}