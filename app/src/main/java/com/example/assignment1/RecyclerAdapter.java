package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<RecyclerItem> itemArrayList;

    public RecyclerAdapter(List<RecyclerItem> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView categoryTextview, nameTextview, districtTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            categoryTextview = itemView.findViewById(R.id.item1_textview);
            nameTextview = itemView.findViewById(R.id.item2_textview);
            districtTextview = itemView.findViewById(R.id.item3_textview);
            imageView = itemView.findViewById(R.id.iv_photo);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String TAG = "Food_Tab1Adapter";
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerItem item = itemArrayList.get(position);
        holder.categoryTextview.setText(item.getCategory());
        holder.nameTextview.setText(item.getName());
        holder.districtTextview.setText(item.getDistrict());
        holder.imageView.setImageResource(item.getImage());

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    CardViewItemFragment fragmentCardViewItem = new CardViewItemFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame_layout, fragmentCardViewItem).commitAllowingStateLoss();
                    RecyclerItem item = itemArrayList.get(pos);
                    String category = item.getCategory();
                    String name = item.getName();
                    String district = item.getDistrict();
                    String address = item.getAddress();
                    String latitude = Double.toString(item.getLatitude());
                    String longitude = Double.toString(item.getLongitude());
                    String image = Integer.toString(item.getImage());

                    Bundle bundle = new Bundle();
                    bundle.putString("category", category);
                    bundle.putString("name", name);
                    bundle.putString("district", district);
                    bundle.putString("address", address);
                    bundle.putString("latitude", latitude);
                    bundle.putString("longitude", longitude);
                    bundle.putString("image", image);
                    fragmentCardViewItem.setArguments(bundle);



                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }




}
