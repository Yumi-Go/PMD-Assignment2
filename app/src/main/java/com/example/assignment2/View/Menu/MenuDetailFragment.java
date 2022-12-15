package com.example.assignment2.View.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment2.Model.MenuRecyclerItem;
import com.example.assignment2.R;
import com.google.android.gms.maps.GoogleMap;


public class MenuDetailFragment extends Fragment {

    String category, name, price, description;
    int image;
    MenuRecyclerItem item;

    ImageView introImageView;
    TextView nameTextView, priceTextView, descriptionTextView;

    public MenuDetailFragment(MenuRecyclerItem item) {
        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu_detail, container, false);

        name = item.getName();
        category = item.getCategory();
        price = item.priceToString(item.getPrice());
        description = item.getDescription();
        image = item.getImage();

        nameTextView = rootView.findViewById(R.id.nameTv);
        introImageView = rootView.findViewById(R.id.introIv);
        descriptionTextView = rootView.findViewById(R.id.descriptionTv);
        priceTextView = rootView.findViewById(R.id.priceTv);

        nameTextView.setText(name);
        priceTextView.setText(price);
        descriptionTextView.setText(description);
        introImageView.setImageResource(image);


        return rootView;
    }
}