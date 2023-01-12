package com.example.tic_tac_toe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Logic_of_the_game {
    private int[][] gameboard;
    private int player=1;//always palyer 1 start
    private Button playagain;
    private Button home;
    private TextView playerturn;
    private String[] names={"player1, player2"};
    //row, col,how the line is drawn: 1:horizontal, 2:vertical, 3:diagonal left to right, 4:diagonal right to left, 5: Tie
    private int[] typeofwin={-1,-1,-1};
    public Logic_of_the_game(){
        gameboard= new int[3][3];
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                if(gameboard[i][j]!=0) {
                    gameboard[i][j] = 0;
                }
            }
        }
    }
    //check if cell is available and choose player
    public boolean updategameboard(int row,int col){
        if(gameboard[row-1][col-1]==0){
            gameboard[row-1][col-1]= player;
            if(player==1){
                playerturn.setText(names[1]+"'s Turn");
            }
            else{
                playerturn.setText(names[0]+"'s Turn");
            }
            return true;
        }
        else {
            return false;
        }
    }
    public boolean winnercheck(){
        boolean iswinner=false;
        for(int i=0; i<3; i++){
            if(gameboard[i][0]==gameboard[i][1] && gameboard[i][0]==gameboard[i][2] && gameboard[i][0]!=0){
                typeofwin= new int[] {i,0,1};
                iswinner=true;
            }
        }
        for(int i=0; i<3; i++){
            if(gameboard[0][i]==gameboard[1][i] && gameboard[0][i]==gameboard[2][i] && gameboard[0][i]!=0){
                typeofwin=new int[] {0,i,2};
                iswinner=true;
            }
        }
        if(gameboard[0][0]==gameboard[1][1] && gameboard[0][0]==gameboard[2][2] && gameboard[0][0]!=0){
            typeofwin=new int[] {0,0,3};
            iswinner=true;
        }
        if(gameboard[0][2]==gameboard[1][1] && gameboard[0][2]==gameboard[2][0] && gameboard[0][2]!=0){
            typeofwin=new int[] {0,2,4};
            iswinner=true;
        }
        int boardisfilled=0;
        for(int i=0;i<3; i++){
            for(int j=0; j<3;j++){
                if(gameboard[i][j]!=0) {
                    boardisfilled+=1;
                }
            }
        }
        if(iswinner){
            home.setVisibility(View.VISIBLE);
            playagain.setVisibility(View.VISIBLE);
            playerturn.setText(names[player-1]+" Won!!!!");
            return true;
        }
        else if(boardisfilled==9){
            home.setVisibility(View.VISIBLE);
            playagain.setVisibility(View.VISIBLE);
            playerturn.setText("Tie!!!!!");
            typeofwin=new int[] {0,2,5};
            return true;
        }
        else {
            return false;
        }
    }
    public void resetgame(){
        for(int i=0; i<3;i++){
            for(int j=0; j<3;j++){
                gameboard[i][j]=0;
            }
        }
        player=1;
        home.setVisibility(View.GONE);
        playagain.setVisibility(View.GONE);
    }
    public void setPlayagain(Button playagain) {
        this.playagain = playagain;
    }

    public void setHome(Button home) {
        this.home = home;
    }

    public void setPlayerturn(TextView playerturn) {
        this.playerturn = playerturn;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
    public int getPlayer(){
        return  player;
    }

    public int[][] getGameboard(){
        return gameboard;
    }

    public int[] getTypeofwin() {
        return typeofwin;
    }
}
