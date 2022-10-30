package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private int num_page = 4;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private FoodFragment fragmentFood = new FoodFragment();
    private EntertainmentFragment fragmentEntertainment = new EntertainmentFragment();
    private TransportationFragment fragmentTransportation = new TransportationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_food:
                    transaction.replace(R.id.frame_layout, fragmentFood).commitAllowingStateLoss();
                    break;
                case R.id.menu_entertainment:
                    transaction.replace(R.id.frame_layout, fragmentEntertainment).commitAllowingStateLoss();
                    break;
                case R.id.menu_transportation:
                    transaction.replace(R.id.frame_layout, fragmentTransportation).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}