package com.arman.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    String[] name;
    int[] image;
    Context context;
    public CustomAdapter(Context context,String[] name, int[] image){
        this.name = name;
        this.image = image;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_name.setText(name[position]);
        holder.iv_image.setImageResource(image[position]);
        holder.iv_image1.setImageResource(image[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("name",name[position]);
                intent.putExtra("pic",image[position]);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image, iv_image1;
        TextView tv_name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.id_imageView);
            iv_image1 = itemView.findViewById(R.id.id_imageView1);
            tv_name = itemView.findViewById(R.id.id_textView);
            cardView = itemView.findViewById(R.id.id_cardView);
        }
    }
}
