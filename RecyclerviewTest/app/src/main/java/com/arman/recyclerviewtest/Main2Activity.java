package com.arman.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.id_imageView2);
        textView = findViewById(R.id.id_textView1);

        imageView.setImageResource(getIntent().getIntExtra("pic",0));
        textView.setText(getIntent().getStringExtra("name"));
    }
}
