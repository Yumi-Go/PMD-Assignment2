package com.example.assignment1;

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
        RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        MainActivity.itemsList.add(new RecyclerItem("Cafe", "Coffee Libre", "Mapo-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_coffee_libre));
        MainActivity.itemsList.add(new RecyclerItem("Cafe", "Fritz Coffee Company", "Mapo-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_fritz_coffee_company));
        MainActivity.itemsList.add(new RecyclerItem("Bakery","Tartine Bakery Dosan","Gangnam-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_tartine_bakery_dosan));
        MainActivity.itemsList.add(new RecyclerItem("Bakery","Maison M'O","Seocho-hu", "", 37.5682471, 126.9978173, "", R.drawable.food_maison_mo));
        MainActivity.itemsList.add(new RecyclerItem("Dessert","Old Ferry Donut","Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_old_ferry_donut));
        MainActivity.itemsList.add(new RecyclerItem("Cafe","Pont Cafe","Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_pont_cafe));

    }

}