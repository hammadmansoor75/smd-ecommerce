package com.hammadmansoor.ecommerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FashionAdapter extends RecyclerView.Adapter<FashionAdapter.ViewHolder> {
    private List<FashionItem> fashionItemList;

    public FashionAdapter(List<FashionItem> fashionItemList) {
        this.fashionItemList = fashionItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fashion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FashionItem fashionItem = fashionItemList.get(position);

        // Set data to views in the ViewHolder
        holder.imageView.setImageResource(fashionItem.getImageResource());
        holder.titleTextView.setText(fashionItem.getTitle());
        holder.descriptionTextView.setText(fashionItem.getDescription());
        holder.priceTextView.setText(fashionItem.getPrice());

        // You can add click listeners or other customizations here
    }

    @Override
    public int getItemCount() {
        return fashionItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            titleTextView = itemView.findViewById(R.id.item_title);
            descriptionTextView = itemView.findViewById(R.id.item_description);
            priceTextView = itemView.findViewById(R.id.item_price);
        }
    }
}


