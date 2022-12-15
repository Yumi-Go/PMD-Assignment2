package com.example.assignment2.View.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment2.Model.MenuRecyclerItem;
import com.example.assignment2.R;
import com.google.android.gms.maps.GoogleMap;


public class MenuDetailFragment extends Fragment {

    String category, name, price, description;
    int image, quantity;
    Button btnPlus, btnMinus;
    MenuRecyclerItem item;
    ImageView introImageView;
    TextView nameTextView, priceTextView, descriptionTextView, quantityTextView;

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
        image = item.getImage();
        description = item.getDescription();

        nameTextView = rootView.findViewById(R.id.nameTv);
        priceTextView = rootView.findViewById(R.id.priceTv);
        introImageView = rootView.findViewById(R.id.introIv);
        descriptionTextView = rootView.findViewById(R.id.descriptionTv);
        quantityTextView = rootView.findViewById(R.id.quantityTv);
        btnPlus = rootView.findViewById(R.id.btn_plus);
        btnMinus = rootView.findViewById(R.id.btn_minus);

        quantity = 0;
        quantityTextView.setText(Integer.toString(quantity));
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = quantity + 1;
                quantityTextView.setText(Integer.toString(quantity));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity = quantity - 1;
                    quantityTextView.setText(Integer.toString(quantity));
                }
            }
        });

        nameTextView.setText(name);
        priceTextView.setText(price);
        introImageView.setImageResource(image);

        descriptionTextView.setText(description);



        return rootView;
    }
}