package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class playerSetup extends AppCompatActivity {
    EditText player1name;
    EditText player2name;
    Button startgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);
        Intent intent= getIntent();
        player1name= findViewById(R.id.player1name);
        player2name= findViewById(R.id.player2name);
        startgame= findViewById(R.id.startgamebtn);
        startgame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nameofplayer1= player1name.getText().toString();
                String nameofplayer2= player2name.getText().toString();
                Intent thegame= new Intent(playerSetup.this,the_game.class);
                if(nameofplayer1.trim().length()!=0 && nameofplayer2.trim().length()!=0) {
                    thegame.putExtra("names", new String[]{nameofplayer1, nameofplayer2});
                }
                else if(nameofplayer1.trim().length()!=0 && nameofplayer2.trim().length()==0){
                    thegame.putExtra("names", new String[]{nameofplayer1, "player2"});
                }
                else if(nameofplayer1.trim().length()==0 && nameofplayer2.trim().length()!=0){
                    thegame.putExtra("names", new String[]{"player1", nameofplayer2});
                }
                else if(nameofplayer1.trim().length()==0 && nameofplayer2.trim().length()==0){
                    thegame.putExtra("names", new String[]{"player1", "player2"});
                }
                startActivity(thegame);
            }
        });
    }
}