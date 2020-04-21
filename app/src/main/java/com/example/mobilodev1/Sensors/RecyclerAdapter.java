package com.example.mobilodev1.Sensors;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilodev1.R;

import java.util.List;
//by Arda Dizdaroglu 19574016
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<String> list;
    TextView textView;
    boolean changed;

    public RecyclerAdapter(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        textView = (TextView)LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(textView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sensorName.setText(list.get(position));
        if (changed){
            holder.sensorName.setTextColor(Color.BLACK);
            textView.setTextColor(Color.BLACK);
        }
        else {
            holder.sensorName.setTextColor(Color.WHITE);
            textView.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sensorName;
        public MyViewHolder(@NonNull TextView itemView) {
            super(itemView);
            sensorName = itemView;
        }
    }
    void setChanged(boolean a){
        this.changed = a;
    }
}

