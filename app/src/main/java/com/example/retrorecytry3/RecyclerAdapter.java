package com.example.retrorecytry3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List <Post> dataList;
//    Context context;

    public RecyclerAdapter(List<Post> dataList) {
        this.dataList = dataList;
    }

    public void setData(List<Post> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_items,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = dataList.get(position);
        holder.userId.setText(String.valueOf(post.getUserId()));
        holder.id.setText(String.valueOf(post.getId()));
        holder.title.setText(post.getTitle());
        holder.text.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userId, id, title, text;

        public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        userId = (TextView) itemView.findViewById(R.id.userId);
        id = (TextView) itemView.findViewById(R.id.id);
        title = (TextView) itemView.findViewById(R.id.title);
        text = (TextView) itemView.findViewById(R.id.body);
        }
    }
}
