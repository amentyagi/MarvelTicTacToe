package com.anuntah.marvel_tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Start_screen extends AppCompatActivity {

    Button battle;

        static String Player1Name="player1name",Player2Avatar="player2avatar";
        static String Player2Name="player2name",Player1Avatar="player1avatar";
        public String player2_name,player2_avatar;
        public String player1_name,player1_avatar;
        Button button;
        EditText edt1,edt2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setTheme(R.style.AppTheme);
            setContentView(R.layout.activity_main);
            button=findViewById(R.id.btn1);
            edt1=findViewById(R.id.edt1);
            edt2=findViewById(R.id.edt2);
            player1_name=edt1.getText().toString();
            player2_name=edt2.getText().toString();
            //Toast.makeText(this,player1_name,Toast.LENGTH_SHORT).show();

        }

    @Override
    protected void onResume() {
        super.onResume();
        edt1.setText("");
        edt2.setText("");
    }

    public void radioButtonClicked(View v){
        RadioButton rb=(RadioButton)v;
        if(rb.getId()==R.id.rd1){
            player1_avatar="captain";
        }
        if(rb.getId()==R.id.rd2)
            player1_avatar="flash";
    }
    public void radioButtonClickedDc(View v){
        RadioButton rb=(RadioButton)v;
        if(rb.getId()==R.id.rd3){
            player2_avatar="ironman";
        }
        if(rb.getId()==R.id.rd4)
            player2_avatar="wolverine";
    }

    public void onClick(View view) {
        player1_name=edt1.getText().toString();
        player2_name=edt2.getText().toString();
        if(player1_name.isEmpty()||player2_name.isEmpty()){
            Toast.makeText(this,"Enter Player Name",Toast.LENGTH_SHORT).show();
        }
        else if(player1_avatar==null||player2_avatar==null)
            Toast.makeText(this,"Select Your Avatar",Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Player1Name, player1_name);
            intent.putExtra(Player2Name, player2_name);
            intent.putExtra(Player1Avatar, player1_avatar);
            intent.putExtra(Player2Avatar, player2_avatar);
            startActivity(intent);
        }
    }

}
