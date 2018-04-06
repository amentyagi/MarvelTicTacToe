package com.anuntah.marvel_tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {


    int cid,fid,iid,wid;
    int currentPlayer;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 2;
    public static final int NO_PlAYER = -1;
    ArrayList<String> arr=new ArrayList<>();
    LinearLayout rootLayout;
    int size = 3;
    TTTButton[][] btn;
    boolean gameOver;
    TextView tv;
    int i1=0;
    Button play_again,records;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String playerO_Avatar,playerX_Avatar,playerX_Name,playerO_Name;
    int player=NO_PlAYER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("list",MODE_PRIVATE);
        int size1=sharedPreferences.getInt("size",0);
        i1=size1;
        for(int i=0;i<size1;i++){
            String s=sharedPreferences.getString("winner"+i,"");
            arr.add(s);
        }
        setContentView(R.layout.activity_game_);
        rootLayout = findViewById(R.id.linearLayout);
        play_again=findViewById(R.id.play_again);
        records=findViewById(R.id.records);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            intIt();
    }

    public  void home(View v){
        Intent intent=new Intent(this,Start_screen.class);
        startActivity(intent);
    }
    public void reset(View v){
        intIt();
    }

    private void intIt() {
        tv=findViewById(R.id.tvWinner);
        tv.setText("");
        Intent intent=getIntent();
        playerX_Name=intent.getStringExtra(Start_screen.Player1Name);
        playerO_Name=intent.getStringExtra(Start_screen.Player2Name);
        playerX_Avatar=intent.getStringExtra(Start_screen.Player1Avatar);
        playerO_Avatar=intent.getStringExtra(Start_screen.Player2Avatar);
        currentPlayer=PLAYER_X;
        gameOver=false;
        btn = new TTTButton[size][size];
        setIcon();
        setUpBoard();
    }

    private void setIcon() {
        if(size==3){
            cid=R.drawable.ca_o;
            fid=R.drawable.flash_o;
            iid=R.drawable.im_o;
            wid=R.drawable.wol_o;
        }
        if(size==4){
            cid=R.drawable.ca_41;
            fid=R.drawable.flash_41;
            iid=R.drawable.im_41;
            wid=R.drawable.wol_41;
        }
        if(size==5){

            cid=R.drawable.ca_4;
            fid=R.drawable.flash_4;
            iid=R.drawable.im_4;
            wid=R.drawable.wol_4;
        }
    }

    private void setUpBoard() {
        rootLayout.removeAllViews();
        for (int row = 0; row < size; row++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(layoutParams);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            for (int col = 0; col < size; col++) {
                TTTButton button = new TTTButton(this);
                LinearLayout.LayoutParams buttonParms = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                button.setOnClickListener(this);
                button.setLayoutParams(buttonParms);
                button.setBackgroundResource(R.drawable.button_bg);
                layout.addView(button);
                btn[row][col] = button;
            }
            rootLayout.addView(layout);
        }
    }

    @Override
    public void onClick(View view) {
        TTTButton button = (TTTButton) view;

        if (!gameOver) {
            if (currentPlayer == PLAYER_X) {
                button.setPlayer(PLAYER_X);
                currentPlayer = PLAYER_O;
                if(playerX_Avatar.equals("captain")) {
                    button.setImageResource(cid);
                }
                if(playerX_Avatar.equals("flash"))
                    button.setImageResource(fid);
                button.setEnabled(false);

            } else {
                button.setPlayer(PLAYER_O);
                currentPlayer = PLAYER_X;
                if(playerO_Avatar.equals("ironman"))
                    button.setImageResource(iid);
                if(playerO_Avatar.equals("wolverine"))
                    button.setImageResource(wid);

                button.setEnabled(false);
            }
            checkGameStatus();
        }
    }

    private void checkGameStatus() {

        for (int row = 0; row < size; row++) {
            boolean samerow = true;
            for (int col = 0; col < size; col++) {
                TTTButton currentButton = btn[row][col];
                TTTButton firstButton = btn[row][0];
                if (currentButton.getPlayer() != firstButton.getPlayer() || currentButton.getPlayer() == NO_PlAYER) {
                    samerow = false;
                    break;
                }
            }
            if (samerow) {
                gameOver = true;
                TTTButton firstButton = btn[row][0];
                player = firstButton.getPlayer();
                onGameOver(player);
                //Toast.makeText(this, String.valueOf(player) + "Won", Toast.LENGTH_SHORT).show();
                for (int row1 = 0; row1 < size; row1++) {
                    //boolean samerow = true;
                    for (int col = 0; col < size; col++)
                        btn[row1][col].setEnabled(false);
                }
                return;

            }
        }
        for (int col = 0; col < size; col++) {
            boolean sameCol = true;
            for (int row = 0; row < size; row++) {
                TTTButton currentButton = btn[row][col];
                TTTButton firstButton = btn[0][col];
                if (currentButton.getPlayer() != firstButton.getPlayer() || currentButton.getPlayer() == NO_PlAYER) {
                    sameCol = false;
                    break;
                }
            }
            if (sameCol) {
                gameOver = true;
                TTTButton firstButton = btn[0][col];
                player = firstButton.getPlayer();
                onGameOver(player);
                //Toast.makeText(this, String.valueOf(player) + "Won", Toast.LENGTH_SHORT).show();
                for (int row1 = 0; row1 < size; row1++) {
                    for (col = 0; col < size; col++)
                        btn[row1][col].setEnabled(false);
                }
                return;

            }
        }
        boolean sameDiag = true;
        for (int row = 0; row < size; row++) {
            sameDiag = true;

            TTTButton currentButton = btn[row][row];
            TTTButton firstButton = btn[0][0];
            if (currentButton.getPlayer() != firstButton.getPlayer() || currentButton.getPlayer() == NO_PlAYER) {
                sameDiag = false;
                break;
            }
        }
        if (sameDiag) {
            gameOver = true;
            TTTButton firstButton1 = btn[0][0];
            player = firstButton1.getPlayer();
            onGameOver(player);
            //Toast.makeText(this, String.valueOf(player) + "Won", Toast.LENGTH_SHORT).show();
            for (int row1 = 0; row1 < size; row1++) {
                //boolean samerow = true;
                for (int col = 0; col < size; col++)
                    btn[row1][col].setEnabled(false);
            }
            return;

        }


        boolean sameDiag2 = true;
        for (int row = 0; row < size; row++) {
            sameDiag2 = true;

            TTTButton currentButton = btn[row][size - row - 1];
            TTTButton firstButton = btn[0][size - 1];
            if (currentButton.getPlayer() != firstButton.getPlayer() || currentButton.getPlayer() == NO_PlAYER) {
                sameDiag2 = false;
                break;
            }
        }
        if (sameDiag2) {
            gameOver = true;
            TTTButton firstButton1 = btn[0][size - 1];
            player = firstButton1.getPlayer();
            onGameOver(player);
            // Toast.makeText(this, String.valueOf(player) + "Won", Toast.LENGTH_SHORT).show();
            for (int row1 = 0; row1 < size; row1++) {
                //boolean samerow = true;
                for (int col = 0; col < size; col++)
                    btn[row1][col].setEnabled(false);
            }
            return;

        }
        boolean draw=true;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(btn[row][col].getPlayer()==NO_PlAYER){
                    draw=false;
                    break;
                }
            }}
        if(draw){
            gameOver=true;
            onGameOver(NO_PlAYER);
        }
    }

    private void onGameOver(int player) {
        editor=sharedPreferences.edit();
        if (player == PLAYER_X) {
            tv.setText(playerX_Name.concat(" Won!!!"));
            arr.add(playerX_Name);
            editor.putString("winner"+i1,playerX_Name);
            editor.commit();
            i1++;
        }
        if (player == PLAYER_O) {
            tv.setText(playerO_Name.concat(" Won!!!"));
            arr.add(playerO_Name);
            editor.putString("winner"+i1,playerO_Name);
            editor.commit();
            i1++;
        }
        if(player==NO_PlAYER)
            tv.setText("DRAW");
        gameOver=true;
        editor.putInt("size",i1);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public void records(View v){
        Intent intent=new Intent(this,RecordsActivity.class);
        intent.putExtra("ArrayName",arr);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:this.finish();
            case R.id.three : size=3;intIt();item.setChecked(true);
                break;
            case R.id.four : size=4;intIt();item.setChecked(true);
                break;
            case R.id.five : size=5;intIt();item.setChecked(true);
        }
        return true;
    }
}
