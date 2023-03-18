package com.example.tik_tak_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import java.util.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

//    player representations
//    0 - X
//    1 - O

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //state meanings:
    //0 - X
    //1 - 0
    //2 - null
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    @SuppressLint("SetTextI18n")
    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive)
        {
            gameReset(view);
        }

        if(gameState[tappedImage]==2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any player has won
        for(int[] winPosition : winPositions)
        {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2)
            {
                //Somebody won
                gameActive = false;
                String winnerStr;
                if(gameState[winPosition[0]]==0)
                {
                    winnerStr = "X has won!";
                }
                winnerStr = "Y has won!";

                //update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }

        }

    }
    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView73)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView83)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView93)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView71)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView81)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView91)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView72)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView82)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView92)).setImageResource(0);


        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}