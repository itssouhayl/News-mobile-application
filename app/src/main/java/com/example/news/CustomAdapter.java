package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    NewsHeadlines currentHeadline = headlines.get(position);
    holder.text_title.setText(currentHeadline.getTitle());
    holder.text_source.setText(currentHeadline.getSource().getName());

    // Load image if URL is not null using Picasso
    if (currentHeadline.getUrlToImage() != null) {
        Picasso.get().load(currentHeadline.getUrlToImage()).into(holder.img_headline);
    } else {
        // If URL is null, you might want to set a placeholder image
        holder.img_headline.setImageResource(R.drawable.not_available);
    }

    // Set a click listener for the card view
    holder.cardView.setOnClickListener(v -> {
        // Pass the clicked headline to the listener
        listener.OnNewsClicked(currentHeadline);
    });
}



    @Override
    public int getItemCount() {
        return headlines.size();
    }
}


