package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class EntertainmentFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    TabPagerAdapter adapter;
    ArrayList<String> tabTitles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_entertainment, container, false);

        tabLayout = rootView.findViewById(R.id.entertainment_tab_layout);
        viewPager = rootView.findViewById(R.id.entertainmentTabViewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Entertainment_Tab1Fragment());
        fragments.add(new Entertainment_Tab2Fragment());
        fragments.add(new Entertainment_Tab3Fragment());

        adapter = new TabPagerAdapter(requireActivity());
        adapter.setData(fragments);
        viewPager.setAdapter(adapter);

        tabTitles = new ArrayList<>();
        tabTitles.add("Park");
        tabTitles.add("History");
        tabTitles.add("Shopping");


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabTitles.get(position))).attach();


        return rootView;
    }

}