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
//        itemsList.add(new RecyclerItem("Palace", getString(R.string.entertainment_deoksugung_name), "Jung-gu", getString(R.string.entertainment_deoksugung_address), 37.565753854275364, 126.97513536866924, getString(R.string.entertainment_deoksugung_description), R.drawable.entertainment_deoksugung));
//        itemsList.add(new RecyclerItem("Palace", getString(R.string.entertainment_changgyeonggung_name), "Jongno-gu", getString(R.string.entertainment_changgyeonggung_address), 37.57871976315565, 126.99483761099776, "", R.drawable.entertainment_changgyeonggung));
//        itemsList.add(new RecyclerItem("Palace",getString(R.string.entertainment_changdeokgung_name),"Jongno-gu", getString(R.string.entertainment_changdeokgung_address), 37.579883194712565, 126.9911793644108, "", R.drawable.entertainment_changdeokgung));
//        itemsList.add(new RecyclerItem("Land Mark",getString(R.string.entertainment_gwanghwamun_name),"Jongno-gu", getString(R.string.entertainment_gwanghwamun_address), 37.575872948449465, 126.976833557665, getString(R.string.entertainment_gwanghwamun_description), R.drawable.entertainment_gwanghwamun));
//        itemsList.add(new RecyclerItem("Palace",getString(R.string.entertainment_gyeongbokgung_name),"Jongno-gu", getString(R.string.entertainment_gyeongbokgung_address), 37.579613791292765, 126.97703429198323, getString(R.string.entertainment_gyeongbokgung_description), R.drawable.entertainment_gyeongbokgung));
//        itemsList.add(new RecyclerItem("Village",getString(R.string.entertainment_bukchonhanokvillage_name),"Jongno-gu", getString(R.string.entertainment_bukchonhanokvillage_address), 37.581520594550966, 126.9848875244898, getString(R.string.entertainment_bukchonhanokvillage_description), R.drawable.entertainment_bukchon_hanok_village));

    }

}