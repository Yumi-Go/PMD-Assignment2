package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {

    private Home_ViewPagerSliderAdapter adapter;
    private ViewPager2 viewPager;
    private CircleIndicator3 mIndicator;
    private Handler sliderHandler = new Handler();
    private int slideNumber = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = rootView.findViewById(R.id.homeViewpagerSlider);

        ArrayList<Home_ViewPagerSliderItems>sliderItems = new ArrayList<>();
        sliderItems.add(new Home_ViewPagerSliderItems(R.drawable.image1));
        sliderItems.add(new Home_ViewPagerSliderItems(R.drawable.image2));
        sliderItems.add(new Home_ViewPagerSliderItems(R.drawable.image3));
        sliderItems.add(new Home_ViewPagerSliderItems(R.drawable.image4));
        sliderItems.add(new Home_ViewPagerSliderItems(R.drawable.image5));

        adapter = new Home_ViewPagerSliderAdapter(sliderItems, viewPager);
        viewPager.setAdapter(adapter);
        slideNumber = adapter.getItemCount();

        mIndicator = rootView.findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);
        mIndicator.createIndicators(slideNumber, 0);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(6);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(0));

        viewPager.setPageTransformer(compositePageTransformer);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % slideNumber);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // each slide duration = 2 seconds
            }
        });
        return rootView;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }



}