package com.example.assignment1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Home_ViewPagerSliderAdapter extends RecyclerView.Adapter<Home_ViewPagerSliderAdapter.SliderViewHolder> {

    private ArrayList<Home_ViewPagerSliderItems> sliderItems;
    private ViewPager2 viewPager2;

    Home_ViewPagerSliderAdapter(ArrayList<Home_ViewPagerSliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_items_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(Home_ViewPagerSliderItems sliderItems){
            imageView.setImageResource(sliderItems.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };


}


//package com.example.mycafe;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//
//public class SliderAdapter extends FragmentStateAdapter {
//
//    public int mCount;
//
//    public SliderAdapter(FragmentActivity fa, int count) {
//        super(fa);
//        mCount = count;
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        int index = getRealPosition(position);
//
//        if(index==0) return new MainMenuHomeFragment();
//        else if(index==1) return new MainMenuOrderFragment();
//        else if(index==2) return new MainMenuAccountFragment();
//        else return new MainMenuStoresFragment();
//    }
//
//    @Override
//    public int getItemCount() {
//        return 2000;
//    }
//
//    public int getRealPosition(int position) { return position % mCount; }
//
//}
