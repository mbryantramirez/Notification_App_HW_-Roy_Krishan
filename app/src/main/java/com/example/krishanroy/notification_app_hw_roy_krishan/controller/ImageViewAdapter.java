package com.example.krishanroy.notification_app_hw_roy_krishan.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krishanroy.notification_app_hw_roy_krishan.R;
import com.example.krishanroy.notification_app_hw_roy_krishan.model.Hit;
import com.example.krishanroy.notification_app_hw_roy_krishan.view.ImageViewHolder;

import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    List<Hit> hits;


    public ImageViewAdapter(List<Hit> hits) {
        this.hits = hits;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_itemview, viewGroup, false);
        return new ImageViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Hit hit = hits.get(i);
        imageViewHolder.onBind(hit);

    }

    @Override
    public int getItemCount() {
        return hits.size();
    }
}
