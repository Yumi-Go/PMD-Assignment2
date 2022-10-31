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


public class Food_Tab1Fragment extends Fragment {

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
        itemsList.add(new RecyclerItem("Korean", "Hwangsaengga", "Jongno-gu", "", 37.5682471, 126.9978173, R.drawable.food_hwangsaengga));
        itemsList.add(new RecyclerItem("Korean", "Wooraeok", "Jung-gu", "62-29 Changgyeonggung-ro, Jung-gu, Seoul, South Korea", 37.5682471, 126.9978173, R.drawable.food_wooraeok));
        itemsList.add(new RecyclerItem("Asian","Tuk Tuk Noodle Thai","Mapo-gu", "", 37.5682471, 126.9978173, R.drawable.food_tuktuk_noodle_thai));
        itemsList.add(new RecyclerItem("Western","Charles H","Jongno-gu", "", 37.5682471, 126.9978173, R.drawable.food_charles_h));
        itemsList.add(new RecyclerItem("Fusion","Bar Cham","Jongno-gu", "", 37.5682471, 126.9978173, R.drawable.food_bar_cham));
        itemsList.add(new RecyclerItem("Asian","Kojima","Gangnam-gu", "", 37.5682471, 126.9978173, R.drawable.food_kojima));
        itemsList.add(new RecyclerItem("Fusion","Bawi Pasta Bar","Yongsan-gu", "", 37.5682471, 126.9978173, R.drawable.food_bawi_pasta_bar));
        itemsList.add(new RecyclerItem("Vegan","Maison Jo","Seocho-gu", "", 37.5682471, 126.9978173, R.drawable.food_maison_jo));
    }

}