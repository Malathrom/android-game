package com.example.adrian.game;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    LinearLayout layoutback;
    protected int count=0;
    protected boolean end=false;
    GridView grid;
    ArrayList<Integer> list= new ArrayList<Integer>();
    Button rejouer;
    TextView affiche;
    public boolean verifierVictoire(ArrayList<Integer> list){



        if(list.get(0)==list.get(1) && list.get(0)== list.get(2) && list.get(0)!=0){
            return true;
        }else if(list.get(3)==list.get(4)&&list.get(3)==list.get(5) && list.get(3)!=0){
            return true;
        }else if(list.get(6)==list.get(7)&&list.get(6)==list.get(8) && list.get(6)!=0){
            return true;
        }else if(list.get(0)==list.get(3)&&list.get(0)==list.get(6) && list.get(0)!=0){
            return true;
        }else if(list.get(1)==list.get(4)&&list.get(1)==list.get(7) && list.get(1)!=0){
            return true;
        }else if(list.get(2)==list.get(5)&&list.get(2)==list.get(8) && list.get(2)!=0){
            return true;
        }else if(list.get(0)==list.get(4)&&list.get(0)==list.get(8) && list.get(0)!=0){
            return true;
        }else if(list.get(2)==list.get(4)&&list.get(2)==list.get(6) && list.get(2)!=0){
            return true;
        }else{
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final DatabaseHandler db = new DatabaseHandler(this);
        layoutback = findViewById(R.id.layoutback);
        switch (db.getPref(0).get(1)){
            case 0: layoutback.setBackgroundResource(R.drawable.back_blue);
                break;
            case 1: layoutback.setBackgroundResource(R.drawable.back_red);
                break;
            case 2: layoutback.setBackgroundResource(R.drawable.back_green);
                break;

        }




        rejouer = findViewById(R.id.rejouer);
        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        for(int i=0; i<9; i++){
            list.add(0);
        }


        affiche=findViewById(R.id.tour);
        grid=findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Drawable cross = getResources().getDrawable( R.drawable.cross );
                cross.setTint(Color.RED);
                Drawable circle = getResources().getDrawable( R.drawable.circle );
                circle.setTint(Color.BLUE);

                ImageView imageView = (ImageView) v;
                Boolean win, draw;
                if (list.get(position)==0 && !end){

                    if(count%2==0){
                        imageView.setImageResource(R.drawable.cross);
                        list.set(position, 1);
                        affiche.setText(getResources().getString(R.string.otour));

                    }else{
                        imageView.setImageResource(R.drawable.circle);
                        list.set(position, 2);
                        affiche.setText(getResources().getString(R.string.xtour));
                    }
                    count++;
                }
                win =verifierVictoire(list);

                if (win){
                    end=true;
                    rejouer.setVisibility(View.VISIBLE);
                    if(count%2==0){
                        db.updatePref(0,db.getPref(0).get(1),db.getPref(0).get(2),db.getPref(0).get(3)+1);
                        affiche.setText(getResources().getString(R.string.ovictory));
                    }else{
                        db.updatePref(0,db.getPref(0).get(1),db.getPref(0).get(2)+1,db.getPref(0).get(3));
                        affiche.setText(getResources().getString(R.string.xvictory));
                    }

                }
                draw=true;
                for (int i=0; i<9; i++){
                    if (list.get(i)==0){
                        draw=false;
                    }
                }
                if(draw)
                    end=true;
                if (!win && draw){
                    affiche.setText(getResources().getString(R.string.draw));
                    rejouer.setVisibility(View.VISIBLE);
                }




            }
        });
    }

}
