package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class the_game extends AppCompatActivity {
    String[] names;
    TextView playerturntext;
    Button playagain;
    Button home;
    private Board board;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_game);
        Intent intent= getIntent();
        names= intent.getStringArrayExtra("names");
        playerturntext= findViewById(R.id.turn);
        playagain= findViewById(R.id.playagainbtn);
        home= findViewById(R.id.homebtn);
        board= findViewById(R.id.board);
        board.setupgame(playagain,home,playerturntext,names);
        if(names!=null) {
            playerturntext.setText(names[0] + "'s Turn");
        }
        //hiding the btns
        home.setVisibility(View.GONE);
        playagain.setVisibility(View.GONE);
        //if play again btn is clicked
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.reset();
                board.invalidate();
                playerturntext.setText(names[0]+"'s Turn");
            }
        });
        //if home btn is clicked
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gohome= new Intent(the_game.this,MainActivity.class);
                startActivity(gohome);
            }
        });
    }
}