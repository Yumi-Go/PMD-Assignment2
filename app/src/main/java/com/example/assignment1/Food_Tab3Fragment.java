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


public class Food_Tab3Fragment extends Fragment {

    RecyclerView recyclerView;

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
        RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.itemsList);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        MainActivity.itemsList.add(new RecyclerItem("Wine", "Big Lights", "Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_big_lights));
        MainActivity.itemsList.add(new RecyclerItem("Bar", "The Library", "Jung-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_the_library));
        MainActivity.itemsList.add(new RecyclerItem("Wine", "Big Lights", "Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_big_lights));
        MainActivity.itemsList.add(new RecyclerItem("Bar", "The Library", "Jung-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_the_library));
        MainActivity.itemsList.add(new RecyclerItem("Wine", "Big Lights", "Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_big_lights));
        MainActivity.itemsList.add(new RecyclerItem("Bar", "The Library", "Jung-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_the_library));
        MainActivity.itemsList.add(new RecyclerItem("Wine", "Big Lights", "Yongsan-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_big_lights));
        MainActivity.itemsList.add(new RecyclerItem("Bar", "The Library", "Jung-gu", "", 37.5682471, 126.9978173, "", R.drawable.food_the_library));
    }



}