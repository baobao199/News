package com.example.news.View.Catogory;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.Model.Catogory;
import com.example.news.Model.Newspaper;
import com.example.news.R;

import java.util.ArrayList;

public class CatogoryAdapter extends RecyclerView.Adapter<CatogoryAdapter.RecyclerViewHolder> {

    private ArrayList<Catogory> arrayList;
    private Context context;

    public CatogoryAdapter(ArrayList<Catogory> arrayList, Context context) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_catogory, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvCatogory.setText((CharSequence) arrayList.get(position).getName());
        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvCatogory;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvCatogory = itemView.findViewById(R.id.tvCatogory);
        }
    }
}
