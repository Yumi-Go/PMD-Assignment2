package com.example.assignment2.View.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.assignment2.Model.MenuRecyclerItem;
import com.example.assignment2.Presenter.itemDBhandler;
import com.example.assignment2.Presenter.userDBhandler;
import com.example.assignment2.R;


public class MenuDetailFragment extends Fragment {

    String category, name, stringPrice, description;
    int id, image, quantity, intPrice;
    Button btnPlus, btnMinus, btnAdd;
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
        intPrice = item.getPrice();
        stringPrice = item.priceToString(intPrice);
        image = item.getImage();
        description = item.getDescription();

        nameTextView = rootView.findViewById(R.id.nameTv);
        priceTextView = rootView.findViewById(R.id.priceTv);
        introImageView = rootView.findViewById(R.id.introIv);
        descriptionTextView = rootView.findViewById(R.id.descriptionTv);

        nameTextView.setText(name);
        priceTextView.setText(stringPrice);
        introImageView.setImageResource(image);
        descriptionTextView.setText(description);

        btnPlus = rootView.findViewById(R.id.btn_plus);
        quantityTextView = rootView.findViewById(R.id.quantityTv);
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

        btnAdd = rootView.findViewById(R.id.btn_add);

        itemDBhandler dbHandler = new itemDBhandler(getContext());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    dbHandler.insertItem(category, name, quantity, intPrice);
                    Toast.makeText(requireActivity().getApplicationContext(), "Added to cart successfully",Toast.LENGTH_LONG).show();


                }
            }
        });



        return rootView;
    }
}