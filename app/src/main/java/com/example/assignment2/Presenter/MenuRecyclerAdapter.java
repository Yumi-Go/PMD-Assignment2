package com.example.assignment2.Presenter;

import android.content.Context;
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

import com.example.assignment2.View.Menu.MenuDetailFragment;
import com.example.assignment2.R;
import com.example.assignment2.Model.MenuRecyclerItem;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    Context context;
    List<MenuRecyclerItem> itemsList;

    public MenuRecyclerAdapter(List<MenuRecyclerItem> itemsList) {
        this.itemsList = itemsList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView categoryTextview, nameTextview, priceTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            categoryTextview = itemView.findViewById(R.id.item1_categoryTv);
            nameTextview = itemView.findViewById(R.id.item2_nameTv);
            priceTextview = itemView.findViewById(R.id.item3_priceTv);
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
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuRecyclerItem item = itemsList.get(position);
        holder.categoryTextview.setText(item.getCategory());
        holder.nameTextview.setText(item.getName());
        holder.priceTextview.setText(item.priceToString(item.getPrice()));
        holder.imageView.setImageResource(item.getImage());

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    MenuDetailFragment fragmentCardViewItem = new MenuDetailFragment(item);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame_layout, fragmentCardViewItem).commitAllowingStateLoss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }




}
