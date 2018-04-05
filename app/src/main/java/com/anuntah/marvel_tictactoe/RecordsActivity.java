package com.anuntah.marvel_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class RecordsActivity extends AppCompatActivity {

    ArrayList<String> arr=new ArrayList<>(10);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat);
        setContentView(R.layout.records);
        Intent intent=getIntent();
        arr=intent.getStringArrayListExtra("ArrayName");
        Collections.reverse(arr);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arr);
        adapter.notifyDataSetChanged();
        ListView listView=findViewById(R.id.list1);
        listView.setAdapter(adapter);
    }
}
