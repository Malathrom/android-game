package com.example.adrian.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Stats extends AppCompatActivity {
    LinearLayout layoutback;
    TextView xwins;
    TextView owins;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

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
        xwins =findViewById(R.id.xwins);
        owins =findViewById(R.id.owins);
        xwins.setText(db.getPref(0).get(2)+"");
        owins.setText(db.getPref(0).get(3)+"");

        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updatePref(0, db.getPref(0).get(1), 0,0);
                xwins.setText(db.getPref(0).get(2)+"");
                owins.setText(db.getPref(0).get(3)+"");
            }
        });

    }
}
