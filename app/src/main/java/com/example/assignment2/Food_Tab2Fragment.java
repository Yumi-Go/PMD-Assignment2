package com.example.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Food_Tab2Fragment extends Fragment {

    RecyclerView recyclerView;
    List<RecyclerItem> itemsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_food_tab2, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        initView();

        return rootView;
    }


    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(itemsList);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
//        itemsList.add(new RecyclerItem("Cafe", "Coffee Libre", "Mapo-gu", getString(R.string.food_coffeelibre_address), 37.56233816308158, 126.92670162827089, getString(R.string.food_coffeelibre_description), R.drawable.food_coffee_libre));
//        itemsList.add(new RecyclerItem("Cafe", "Fritz Coffee Company", "Mapo-gu", getString(R.string.food_fritzcoffeecompany_address), 37.540978709843856, 126.94903731661489, getString(R.string.food_fritzcoffeecompany_description), R.drawable.food_fritz_coffee_company));
//        itemsList.add(new RecyclerItem("Bakery","Tartine Bakery Dosan","Gangnam-gu", getString(R.string.food_tartinebakerydosan_address), 37.5258791526038, 127.03552989768754, getString(R.string.food_tartinebakerydosan_description), R.drawable.food_tartine_bakery_dosan));
//        itemsList.add(new RecyclerItem("Bakery","Maison M'O","Seocho-hu", getString(R.string.food_maisonmo_address), 37.48928760805711, 126.9941617915521, getString(R.string.food_maisonmo_description), R.drawable.food_maison_mo));
//        itemsList.add(new RecyclerItem("Dessert","Old Ferry Donut","Yongsan-gu", getString(R.string.food_oldferrydonut_address), 37.53954541639704, 127.00290900934485, getString(R.string.food_oldferrydonut_description), R.drawable.food_old_ferry_donut));
//        itemsList.add(new RecyclerItem("Cafe","Pont Cafe","Yongsan-gu", getString(R.string.food_pontcafe_address), 37.525749092070384, 126.96283573562783, getString(R.string.food_pontcafe_description), R.drawable.food_pont_cafe));

    }

}