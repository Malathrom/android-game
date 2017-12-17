package com.example.adrian.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jouer=findViewById(R.id.jouer);
        jouer.setOnClickListener(this);
        this.setTheme(R.style.AppTheme_NoActionBar);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.jouer:
                lancerPartie();
                break;
        }
    }

    private void lancerPartie() {
        Intent intent= new Intent(this, Game.class);
        startActivity(intent);
    }
}
