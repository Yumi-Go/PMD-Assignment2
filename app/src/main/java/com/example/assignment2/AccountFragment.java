package com.example.assignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class AccountFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    TabPagerAdapter adapter;
    ArrayList<String> tabTitles;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_account, container, false);

        tabLayout = rootView.findViewById(R.id.food_tab_layout);
        viewPager = rootView.findViewById(R.id.foodTabViewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Account_LoginFragment());
        fragments.add(new Account_RegisterFragment());
//        fragments.add(new Account_AfterLoginFragment());

        adapter = new TabPagerAdapter(requireActivity());
        adapter.setData(fragments);
        viewPager.setAdapter(adapter);

        tabTitles = new ArrayList<>();
        tabTitles.add("Lgo In");
        tabTitles.add("Register");
//        tabTitles.add("Pub & Bar");

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabTitles.get(position))).attach();

        return rootView;
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_toolbar, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item1:
//            case R.id.item2:
//            case R.id.item3: {
//                // navigate to settings screen
//                return true;
//            }// save profile changes
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

}