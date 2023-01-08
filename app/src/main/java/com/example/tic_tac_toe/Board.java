package com.example.tic_tac_toe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {
    private final int boardcolor;
    private final int Ocolor;
    private final int Xcolor;
    private final int winnerlinecolor;
    private final Paint paint= new Paint();
    int cellsize= Math.min(getWidth(),getHeight())/3;
    private final Logic_of_the_game game;
    private boolean winningline;

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray att= context.getTheme().obtainStyledAttributes(attrs,R.styleable.Board,0,0);
        game= new Logic_of_the_game();
        try{
            boardcolor= att.getInteger(R.styleable.Board_boardcolor,0);
            Ocolor= att.getInteger(R.styleable.Board_Ocolor,0);
            Xcolor= att.getInteger(R.styleable.Board_Xcolor,0);
            winnerlinecolor= att.getInteger(R.styleable.Board_winnerlinecolor,0);
        }finally{
            att.recycle();
        }
    }

    //onMeasure to determine the measures of our view
    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);
        //calculate the dimension of the board and cell size
        int dimension= Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellsize= dimension/3;
        //defining the dimension of our view
        setMeasuredDimension(dimension,dimension);
    }
    //taking the user input(touch) and updating the game board
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x= event.getX();
        float y=event.getY();
        int action= event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
            int row= (int)Math.ceil(y/cellsize);
            int col= (int)Math.ceil(x/cellsize);
            if(!winningline) {
                if (game.updategameboard(row, col)) {
                    invalidate();
                    if(game.winnercheck()){
                        winningline=true;
                        invalidate();
                    }
                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }
            invalidate();
            return true;
        }
        return false;
    }
    //setting everything to draw
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);//to draw lines
        paint.setAntiAlias(true);
        //draw the board
        drawBoard(canvas);
        drawMark(canvas);
        if(winningline){
            paint.setColor(winnerlinecolor);
            drawwinningline(canvas);
        }
    }
    //drawing either X or O based on turn and checking for cell availability
    public void drawMark(Canvas canvas){
        for(int i=0; i<3; i++){
            for(int j=0; j<3;j++){
                if(game.getGameboard()[i][j]!=0){
                    if(game.getGameboard()[i][j]==1){
                        drawX(canvas,i,j);
                    }
                    else{
                        drawO(canvas,i,j);
                    }
                }
            }
        }
    }
    //drawing the board
    private void drawBoard(Canvas canvas){
        paint.setColor(boardcolor);
        paint.setStrokeWidth(16);
        //draw the lines
        for(int i=1;i<3;i++){
            canvas.drawLine(cellsize*i,0,cellsize*i,canvas.getWidth(),paint);
        }
        for(int j=1;j<3;j++){
            canvas.drawLine(0,cellsize*j,canvas.getWidth(),cellsize*j,paint);
        }
    }
    public void drawwinningline(Canvas canvas){
        int row= game.getTypeofwin()[0];
        int col= game.getTypeofwin()[1];
        switch (game.getTypeofwin()[2]){
            case 1:
                drawHorizontalLine(canvas,row,col);
                break;
            case 2:
                drawVerticalLine(canvas,row,col);
                break;
            case 3:
                drawDiagonallinelefttoright(canvas);
                break;
            case 4:
                drawDiagonallinerighttoleft(canvas);
                break;
        }
    }
    //draw the X
    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(Xcolor);
        //draw the lines
        canvas.drawLine((float)(col*cellsize+cellsize*0.2),
                        (float)(row*cellsize+cellsize*0.2),
                        (float)((col+1)*cellsize-cellsize*0.2),
                        (float)((row+1)*cellsize-cellsize*0.2),
                                paint);

        canvas.drawLine((float)((col+1)*cellsize-cellsize*0.2),
                        (float)(row*cellsize+cellsize*0.2),
                        (float)(col*cellsize+cellsize*0.2),
                        (float)((row+1)*cellsize-cellsize*0.2),
                                paint);
    }
    //draw 0
    private void drawO(Canvas canvas,int row, int col){
        paint.setColor(Ocolor);
        //draw the circle
        canvas.drawOval((float)(col*cellsize+cellsize*0.2),
                        (float)(row*cellsize+cellsize*0.2),
                        (float)(col*cellsize+cellsize-cellsize*0.2),
                        (float)(row*cellsize+cellsize-cellsize*0.2),
                                paint);
    }
    private void drawHorizontalLine(Canvas canvas,int row, int col){
        canvas.drawLine(col,row*cellsize+cellsize/2, cellsize*3, row*cellsize+cellsize/2,paint);
    }
    private void drawVerticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellsize+cellsize/2, row, col*cellsize+cellsize/2, cellsize*3,paint);
    }
    private void drawDiagonallinelefttoright(Canvas canvas){
        canvas.drawLine(0,0,cellsize*3,cellsize*3,paint);
    }
    private void drawDiagonallinerighttoleft(Canvas canvas){
        canvas.drawLine(cellsize*3,0,0,cellsize*3,paint);
    }
    public void setupgame(Button playagain, Button home, TextView playerturn, String[] names){
        game.setHome(home);
        game.setPlayagain(playagain);
        game.setPlayerturn(playerturn);
        game.setNames(names);
    }
    //reset the game
    public void reset(){
        game.resetgame();
        winningline=false;
    }
}
