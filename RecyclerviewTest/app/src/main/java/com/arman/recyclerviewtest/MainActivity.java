package com.arman.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    Context context;

    String[] name = {"Apple","Avocado","Banana","Blackgrapes",
                     "Cherry","Cucumber","Dates","Dragonfruit",
                     "Grapes","Guava","Jackfruit","Kiwi",
                     "Lemon","Mango","Olive","Orange"};
    int[] images = {R.drawable.apple2,R.drawable.avocado1,R.drawable.banana,R.drawable.blackgrapes,
                    R.drawable.cherry,R.drawable.cucumber,R.drawable.dates,R.drawable.dragonfruit,
                    R.drawable.grapes,R.drawable.guava,R.drawable.jackfruit,R.drawable.kiwi,
                    R.drawable.lemon,R.drawable.mango,R.drawable.olive,R.drawable.orange};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(new CustomAdapter(MainActivity.this,name,images));
    }

}
