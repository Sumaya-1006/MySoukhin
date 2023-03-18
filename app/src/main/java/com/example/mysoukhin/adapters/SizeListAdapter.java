package com.example.mysoukhin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysoukhin.R;

import java.util.ArrayList;

public class SizeListAdapter extends  RecyclerView.Adapter<SizeListAdapter.MyViewHolder> implements View.OnClickListener{

    ArrayList<String> listData;
    Context context;
    public static int pos;

    public SizeListAdapter(ArrayList<String> listData, Context context) {
        this.listData = listData;
        this.context = context;
        pos = -1;
    }

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public SizeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.size_list_items, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SizeListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
