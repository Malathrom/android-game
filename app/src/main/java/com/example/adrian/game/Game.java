package com.example.adrian.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    protected int count=0;
    GridView grid;
    ArrayList<Integer> list= new ArrayList<Integer>();
    public boolean verifierVictoire(ArrayList<Integer> list){
        if(list.get(0)==list.get(1) && list.get(0)== list.get(2)){
            return true;
        }else if(list.get(3)==list.get(4)&&list.get(3)==list.get(5)){
            return true;
        }else if(list.get(6)==list.get(7)&&list.get(6)==list.get(8)){
            return true;
        }else if(list.get(0)==list.get(3)&&list.get(0)==list.get(6)){
            return true;
        }else if(list.get(1)==list.get(4)&&list.get(1)==list.get(7)){
            return true;
        }else if(list.get(2)==list.get(5)&&list.get(2)==list.get(8)){
            return true;
        }else if(list.get(0)==list.get(4)&&list.get(0)==list.get(8)){
            return true;
        }else if(list.get(2)==list.get(4)&&list.get(2)==list.get(6)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for(int i=0; i<9; i++){
            list.add(0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid=findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this));


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView imageView = (ImageView) v;
                if(count%2==0){
                    imageView.setImageResource(R.drawable.cross);
                    list.set(position, 1);
                }else{
                    imageView.setImageResource(R.drawable.circle);
                    list.set(position, 2);
                }
                if (verifierVictoire(list)){
                    Toast.makeText(Game.this, "Bravo", Toast.LENGTH_SHORT).show();
                }
                count++;

            }
        });
    }

}
