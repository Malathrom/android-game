package com.example.adrian.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Game extends AppCompatActivity {
    int count=0;
    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid=findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Game.this, "" + position, Toast.LENGTH_SHORT).show();
                ImageView imageView = (ImageView) v;
                if(count%2==0){
                    imageView.setImageResource(R.drawable.cross);
                }else{
                    imageView.setImageResource(R.drawable.circle);
                }
                count++;

            }
        });
    }

}
