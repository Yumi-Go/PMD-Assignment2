package com.example.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment2.Model.MenuRecyclerItem;
import com.google.android.gms.maps.GoogleMap;


public class MenuDetailFragment extends Fragment {

    String category, name, description;
    int price, image;
    MenuRecyclerItem item;

    ImageView introImageView;
    TextView nameTextView, categoryTextView, descriptionTextView, addressTextView;

    GoogleMap gMap;

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
        price = item.getPrice();
        description = item.getDescription();
        image = item.getImage();

        nameTextView = rootView.findViewById(R.id.nameTv);
        descriptionTextView = rootView.findViewById(R.id.descriptionTv);
        addressTextView = rootView.findViewById(R.id.addressTv);
        introImageView = rootView.findViewById(R.id.introIv);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
        introImageView.setImageResource(image);


        return rootView;
    }
}