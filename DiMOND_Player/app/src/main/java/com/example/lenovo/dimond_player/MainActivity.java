package com.example.lenovo.dimond_player;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ImageButton play,previous,next,forward,skip;
    private TextView text_id, id_song, id_artist;
    private SeekBar positionBar,volumeBar;
    private MediaPlayer media;
    private AudioManager audio;
    private int position = 0;
    private String[] list;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getData();
        if(!getIntent().getStringExtra("position").isEmpty()){
            position = Integer.parseInt(getIntent().getStringExtra("position"));
        }
        media = MediaPlayer.create(MainActivity.this,Uri.parse(list[position]));

        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        handler = new Handler();

        int maxV = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        text_id = findViewById(R.id.songArt_id);
        id_song = findViewById(R.id.id_song);
        id_artist = findViewById(R.id.id_artist);
        play = findViewById(R.id.play_btn);
        previous = findViewById(R.id.previous_btn);
        next = findViewById(R.id.next_btn);
        forward = findViewById(R.id.forward_btn);
        skip = findViewById(R.id.skip_btn);
        positionBar = findViewById(R.id.positionBar);
        volumeBar = findViewById(R.id.volumeBar);

        volumeBar.setMax(maxV);
        volumeBar.setProgress(curV);


        id_song.setText(getIntent().getStringExtra("name"));
        id_artist.setText(getIntent().getStringExtra("artist"));


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.length>0){
                    positionBar.setMax(media.getDuration());
                    if(!media.isPlaying()){
                        media.start();
                        play.setImageResource(R.drawable.play);
                        changeSeekBar();
                    }
                    else {
                        media.pause();
                        play.setImageResource(R.drawable.pause);
                    }
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.length>0){
                    if(list.length-1>position){
                        position++;
                        media.release();
                        media = MediaPlayer.create(MainActivity.this,Uri.parse(list[position]));
                        media.start();
                        changeSeekBar();
                    }else{
                        position = 0;
                        media.release();
                        media = MediaPlayer.create(MainActivity.this,Uri.parse(list[position]));
                        media.start();
                        changeSeekBar();
                    }
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.length>0){
                    if(position > 0){
                        position--;
                        media.release();
                        media = MediaPlayer.create(MainActivity.this,Uri.parse(list[position]));
                        media.start();
                        changeSeekBar();
                        if(position == 0){
                            position = list.length - 1;
                        }
                    }
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(media.getDuration() > media.getCurrentPosition()+10000){
                    media.seekTo(media.getCurrentPosition() + 10000);
                    changeSeekBar();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(media.getCurrentPosition() > 10000){
                   media.seekTo(media.getCurrentPosition() - 10000);
                   changeSeekBar();
               }else {
                   media.seekTo(0);
                   changeSeekBar();
               }
            }
        });
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    int x = Math.round(media.getDuration()/100);
                    media.seekTo(x*i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    audio.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        media.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(media.isPlaying()){
            media.release();
        }
    }

    private void changeSeekBar() {
        int x = Math.round(media.getDuration()/100);

        positionBar.setProgress(Math.round(media.getCurrentPosition()/x));
        if(media.isPlaying()){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void getData() {
        super.onStart();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String Selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        Cursor cursor = getContentResolver().query(uri,null,Selection,null,null);
        if(cursor.getCount()>0){
            list = new String[cursor.getCount()];
            for(int i= 0;cursor.moveToNext();i++){
                list[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
        }
    }
}
