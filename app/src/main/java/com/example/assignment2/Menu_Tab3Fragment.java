package com.example.assignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Menu_Tab3Fragment extends Fragment {

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu_tab3, container, false);

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
//        itemsList.add(new RecyclerItem("Shopping Area", getString(R.string.entertainment_myeongdong_name), "Jung-gu", getString(R.string.entertainment_myeongdong_address), 37.56363089329996, 126.98261752078477, getString(R.string.entertainment_myeongdong_description), R.drawable.entertainment_myeongdong));
//        itemsList.add(new RecyclerItem("Shopping Area", getString(R.string.entertainment_dongdaemun_name), "Jung-gu", getString(R.string.entertainment_dongdaemun_address), 37.5662988480274, 127.00789632369924, getString(R.string.entertainment_dongdaemun_description), R.drawable.entertainment_dongdaemun));
//        itemsList.add(new RecyclerItem("Shopping Street",getString(R.string.entertainment_hongdae_name),"Mapo-gu", getString(R.string.entertainment_hongdae_address), 37.55581587843709, 126.9240854268621, getString(R.string.entertainment_hongdae_description), R.drawable.entertainment_hongdae));
    }


}