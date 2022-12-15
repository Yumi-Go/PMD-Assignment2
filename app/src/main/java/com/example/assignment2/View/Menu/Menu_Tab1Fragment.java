package com.example.assignment2.View.Menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.R;
import com.example.assignment2.Presenter.RecyclerAdapter;
import com.example.assignment2.Model.MenuRecyclerItem;

import java.util.ArrayList;
import java.util.List;


public class Menu_Tab1Fragment extends Fragment {

    RecyclerView recyclerView;
    List<MenuRecyclerItem> itemsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu_tab1, container, false);

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
        itemsList.add(new MenuRecyclerItem("Pizza", "Cheese Pizza", 12, R.drawable.cheesepizza));
        itemsList.add(new MenuRecyclerItem("Pizza", "Pepperoni Pizza",13, R.drawable.pepperonipizza));
        itemsList.add(new MenuRecyclerItem("Pizza", "Margherita Pizza",14, R.drawable.margheritapizza));
    }
}