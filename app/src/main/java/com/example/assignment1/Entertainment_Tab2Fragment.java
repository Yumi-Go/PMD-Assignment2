package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Entertainment_Tab2Fragment extends Fragment {

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_entertainment_tab2, container, false);

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
        itemsList.add(new RecyclerItem("Coffee", "aaa", "district1", "", 37.5682471, 126.9978173, "", R.drawable.image1));
        itemsList.add(new RecyclerItem("Coffee", "bbb", "district2", "", 37.5682471, 126.9978173, "", R.drawable.image2));
        itemsList.add(new RecyclerItem("Coffee","ccc","district3", "", 37.5682471, 126.9978173, "", R.drawable.image3));
        itemsList.add(new RecyclerItem("Coffee","ddd","district4", "", 37.5682471, 126.9978173, "", R.drawable.image1));
        itemsList.add(new RecyclerItem("Coffee","eee","district5", "", 37.5682471, 126.9978173, "", R.drawable.image2));
        itemsList.add(new RecyclerItem("Coffee","fff","district6", "", 37.5682471, 126.9978173, "", R.drawable.image3));
        itemsList.add(new RecyclerItem("Restaurant","ggg","district7", "", 37.5682471, 126.9978173, "", R.drawable.image1));
        itemsList.add(new RecyclerItem("Restaurant","hhh","district8", "", 37.5682471, 126.9978173, "", R.drawable.image2));
        itemsList.add(new RecyclerItem("Restaurant","iii","district9", "", 37.5682471, 126.9978173, "", R.drawable.image3));
        itemsList.add(new RecyclerItem("Restaurant","jjj","district10", "", 37.5682471, 126.9978173, "", R.drawable.image1));
        itemsList.add(new RecyclerItem("Restaurant","kkk","district11", "", 37.5682471, 126.9978173, "", R.drawable.image2));
    }

}