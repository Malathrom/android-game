package com.example.adrian.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Options extends AppCompatActivity {


    RadioGroup radioThemeGroup;
    RadioButton blue, red, green;
    Button appliquer;
    LinearLayout layoutback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        blue=findViewById(R.id.bleu);
        red=findViewById(R.id.rouge);
        green=findViewById(R.id.vert);

        radioThemeGroup = findViewById(R.id.radiotheme);

        final DatabaseHandler db = new DatabaseHandler(this);
        layoutback= findViewById(R.id.layoutback);
        switch (db.getPref(0).get(1)){
            case 0: layoutback.setBackgroundResource(R.drawable.back_blue);
                break;
            case 1: layoutback.setBackgroundResource(R.drawable.back_red);
                break;
            case 2: layoutback.setBackgroundResource(R.drawable.back_green);
                break;

        }


        appliquer = findViewById(R.id.appliquer);
        appliquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layoutback= findViewById(R.id.layoutback);
                if (blue.isChecked()){
                    db.updatePref(0,0, db.getPref(0).get(2),db.getPref(0).get(3));
                    layoutback.setBackgroundResource(R.drawable.back_blue);
                }else if (red.isChecked()){
                    db.updatePref(0,1, db.getPref(0).get(2),db.getPref(0).get(3));
                    layoutback.setBackgroundResource(R.drawable.back_red);
                }else if (green.isChecked()){
                    db.updatePref(0,2, db.getPref(0).get(2),db.getPref(0).get(3));
                    layoutback.setBackgroundResource(R.drawable.back_green);
                }else{
                    Toast.makeText(Options.this, getResources().getString(R.string.errorSelectTheme), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
