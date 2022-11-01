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


public class Entertainment_Tab1Fragment extends Fragment {

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_entertainment_tab1, container, false);

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
        itemsList.add(new RecyclerItem("Theme Park", "Lotte World", "Songpa-gu", getString(R.string.entertainment_LotteWorld_address), 37.51111314047022, 127.09816901164926, getString(R.string.entertainment_LotteWorld_description), R.drawable.entertainment_lotte_world));
        itemsList.add(new RecyclerItem("Amusement park",getString(R.string.entertainment_Everland_name),"Yongin-si", getString(R.string.entertainment_Everland_address), 37.29390280587992, 127.20255851629, getString(R.string.entertainment_Everland_description), R.drawable.entertainment_everland));
        itemsList.add(new RecyclerItem("Theme Park", getString(R.string.entertainment_SeoulChildrensGrandPark_name), "Gwangjin-gu", getString(R.string.entertainment_SeoulChildrensGrandPark_address), 37.54936404306452, 127.08181796192407, getString(R.string.entertainment_SeoulChildrensGrandPark_description), R.drawable.entertainment_seoul_childrens_grand_park));
        itemsList.add(new RecyclerItem("Natural Park",getString(R.string.entertainment_SeoulForestPark_name),"Seongdong-gu", getString(R.string.entertainment_SeoulForestPark_address), 37.54440489824703, 127.03742113701324, getString(R.string.entertainment_SeoulForestPark_description), R.drawable.entertainment_seoul_forest_park));
        itemsList.add(new RecyclerItem("Natural Park",getString(R.string.entertainment_WorldCupPark_name),"Mapo-gu", getString(R.string.entertainment_WorldCupPark_address), 37.563805443996465, 126.89350069750529, getString(R.string.entertainment_WorldCupPark_description), R.drawable.entertainment_worldcup_park));
        itemsList.add(new RecyclerItem("Natural Park",getString(R.string.entertainment_NaksanPark_name),"Jongno-gu", getString(R.string.entertainment_NaksanPark_address), 37.580614567008055, 127.00752641284993, getString(R.string.entertainment_NaksanPark_description), R.drawable.entertainment_naksan_park));
    }
}