package com.thesis.fixitadmin.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.fixitadmin.model.ModelStatistics;

import java.util.ArrayList;

public class AdapterRecycleStatistics extends RecyclerView.Adapter<AdapterRecycleStatistics.MyViewHolder> {
    private ArrayList<ModelStatistics> statList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleStatistics.MyViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return 0;
    }
}
