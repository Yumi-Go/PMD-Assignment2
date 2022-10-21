package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragments;
//        fragments.add(new Order_Tab1Fragment());
//        fragments.add(new Order_Tab2Fragment());
//        fragments.add(new Order_Tab3Fragment());


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
//        this.fragments = fragments;
//        this.viewPager2 = viewPager2;
//        fragments.add(new Order_Tab1Fragment());
//        fragments.add(new Order_Tab2Fragment());
//        fragments.add(new Order_Tab3Fragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

//    public void addFragment(Fragment fragment){
//        fragments.add(fragment);
//    }


    @Override
    public int getItemCount() {
        return fragments.size();
    }


    public void setData(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }


}
