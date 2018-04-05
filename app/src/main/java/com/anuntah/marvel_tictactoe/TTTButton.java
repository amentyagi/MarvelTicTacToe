package com.anuntah.marvel_tictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;

/**
 * Created by Bhavit Yadav on 29-01-2018.
 */

public class TTTButton extends AppCompatImageButton {

    private int Player;


    public TTTButton(Context context) {
        super(context);
        Player= MainActivity.NO_PlAYER;

    }


    public int getPlayer() {
        return Player;
    }
    public void setPlayer(int Player){
        this.Player=Player;
    }
}
