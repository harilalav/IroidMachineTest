package com.example.iroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    Context mContext;
    List<Category> categoryLists = new ArrayList<>();

    public CategoryAdapter(Context mContext, List<Category> categoryLists) {
        this.mContext = mContext;
        this.categoryLists = categoryLists;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_cards, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position)
    {
        Category c=categoryLists.get(position);
        holder.title.setText(c.getCategory());
        Log.i("Name",c.getCategory());
        String thumbs=c.getImageUrl();
        Glide.with(mContext)
                .load(thumbs)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView image;
        public viewHolder(View view){

            super(view);
            title = (TextView) view.findViewById(R.id.categoryName);
            image = (ImageView) view.findViewById(R.id.thumbnail);

    }

}}
