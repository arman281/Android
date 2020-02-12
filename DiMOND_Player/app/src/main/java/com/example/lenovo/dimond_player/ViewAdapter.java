package com.example.lenovo.dimond_player;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ViewAdapter extends BaseAdapter {
    private Context context;
    private String[] songName,artName;
    public ViewAdapter(Context context, String[] songName, String[] artName) {
        this.context = context;
        this.songName = songName;
        this.artName = artName;
    }

    @Override
    public int getCount() {
        return songName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.song_item,viewGroup,false);
        final TextView songname = view1.findViewById(R.id.songName_id);
        TextView artname = view1.findViewById(R.id.songArt_id);
        songname.setText(songName[i]);
        artname.setText(artName[i]);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("position",""+i);
                intent.putExtra("name",songName[i]);
                intent.putExtra("artist",artName[i]);
                context.startActivity(intent);
            }
        });
        return view1;
    }
}
