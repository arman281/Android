package com.example.lenovo.dimond_player;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PlayList extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        if(ContextCompat.checkSelfPermission(PlayList.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(PlayList.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(PlayList.this,new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }else {
                ActivityCompat.requestPermissions(PlayList.this,new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }else {
            listView = findViewById(R.id.listView_id);
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String Selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
            Cursor cursor = getContentResolver().query(uri,null,Selection,null,null);
            if(cursor.getCount()>0){
                String[] sName = new String[cursor.getCount()];
                String[] sArt = new String[cursor.getCount()];
                for(int i= 0;cursor.moveToNext();i++){
                    sName[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    sArt[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                }
                ViewAdapter viewAdapter = new ViewAdapter(PlayList.this,sName,sArt);
                listView.setAdapter(viewAdapter);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(PlayList.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(PlayList.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            ActivityCompat.requestPermissions(PlayList.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            ActivityCompat.requestPermissions(PlayList.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    } else {
                        listView = findViewById(R.id.listView_id);
                        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        String Selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
                        Cursor cursor = getContentResolver().query(uri, null, Selection, null, null);
                        if (cursor.getCount() > 0) {
                            String[] sName = new String[cursor.getCount()];
                            String[] sArt = new String[cursor.getCount()];
                            for (int i = 0; cursor.moveToNext(); i++) {
                                sName[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                                sArt[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                            }
                            ViewAdapter viewAdapter = new ViewAdapter(PlayList.this, sName, sArt);
                            listView.setAdapter(viewAdapter);
                        }
                    }
                } else {
                    Toast.makeText(PlayList.this, "Permission Not Granted!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            return;
        }
    }
}
