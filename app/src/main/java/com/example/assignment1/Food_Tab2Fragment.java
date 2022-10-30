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
    List<Food_RecyclerItem> itemsList = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_food_tab1, container, false);

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
        itemsList.add(new Food_RecyclerItem("Cafe", "Coffee Libre", "Mapo-gu", R.drawable.food_coffee_libre));
        itemsList.add(new Food_RecyclerItem("Cafe", "Fritz Coffee Company", "Mapo-gu", R.drawable.food_fritz_coffee_company));
        itemsList.add(new Food_RecyclerItem("Bakery","Tartine Bakery Dosan","Gangnam-gu", R.drawable.food_tartine_bakery_dosan));
        itemsList.add(new Food_RecyclerItem("Bakery","Maison M'O","Seocho-hu", R.drawable.food_maison_mo));
        itemsList.add(new Food_RecyclerItem("Dessert","Old Ferry Donut","Yongsan-gu", R.drawable.food_old_ferry_donut));
        itemsList.add(new Food_RecyclerItem("Cafe","Pont Cafe","Yongsan-gu", R.drawable.food_pont_cafe));

    }

}