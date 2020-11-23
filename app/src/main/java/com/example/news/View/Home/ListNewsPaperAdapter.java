package com.example.news.View.Home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.Model.Newspaper;
import com.example.news.R;
import com.example.news.View.ReadActivity;

import java.util.ArrayList;

public class ListNewsPaperAdapter extends RecyclerView.Adapter<ListNewsPaperAdapter.RecyclerViewHolder> {

    private ArrayList<Newspaper> arrayList;
    private Context context;

    public ListNewsPaperAdapter(ArrayList<Newspaper> arrayList, Context context) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.tvTitle.setText((CharSequence) arrayList.get(position).getName());
        holder.tvDecription.setText((CharSequence) arrayList.get(position).getMaincontent());
        holder.tvType.setText((CharSequence) arrayList.get(position).getType());
        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.image);

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("bao", ""+arrayList.get(position).getAuthor());

                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("image",arrayList.get(position).getImage());
                intent.putExtra("title", arrayList.get(position).getName());
                intent.putExtra("author", arrayList.get(position).getAuthor());
                intent.putExtra("content", arrayList.get(position).getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvDecription;
        TextView tvType;
        ImageView image;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDecription = itemView.findViewById(R.id.tvDecription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvType = itemView.findViewById(R.id.tvType);
            image = itemView.findViewById(R.id.image);
        }
    }
}
