package com.example.assignment2.View.Menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.R;
import com.example.assignment2.Presenter.MenuRecyclerAdapter;
import com.example.assignment2.Model.MenuRecyclerItem;

import java.util.ArrayList;
import java.util.List;


public class Menu_Tab2Fragment extends Fragment {

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu_tab2, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        initView();

        return rootView;
    }


    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MenuRecyclerAdapter adapter = new MenuRecyclerAdapter(itemsList);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        itemsList.add(new MenuRecyclerItem(getString(R.string.deal), getString(R.string.double_deal), 20, getString(R.string.double_deal_description), R.drawable.deal_doubledeal));
        itemsList.add(new MenuRecyclerItem(getString(R.string.deal), getString(R.string.party_deal), 28, getString(R.string.party_deal_description), R.drawable.deal_partydeal));
        itemsList.add(new MenuRecyclerItem(getString(R.string.deal), getString(R.string.student_deal), 16, getString(R.string.student_deal_description), R.drawable.deal_studentdeal));
    }

}