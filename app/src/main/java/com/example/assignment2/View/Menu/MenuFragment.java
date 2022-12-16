package com.example.assignment2.View.Menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2.R;
import com.example.assignment2.Presenter.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_menu, container, false);

        tabLayout = rootView.findViewById(R.id.menu_tab_layout);
        viewPager = rootView.findViewById(R.id.menuTabViewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Menu_Tab1Fragment());
        fragments.add(new Menu_Tab2Fragment());
        fragments.add(new Menu_Tab3Fragment());
        fragments.add(new Menu_Tab4Fragment());

        adapter = new TabPagerAdapter(requireActivity());
        adapter.setData(fragments);
        viewPager.setAdapter(adapter);

        tabTitles = new ArrayList<>();
        tabTitles.add(getString(R.string.pizza));
        tabTitles.add(getString(R.string.deal));
        tabTitles.add(getString(R.string.side));
        tabTitles.add(getString(R.string.drink));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabTitles.get(position))).attach();


        return rootView;
    }

}