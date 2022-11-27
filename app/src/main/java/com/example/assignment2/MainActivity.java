package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private int num_page = 4;
//    static List<RecyclerItem> itemsList = new ArrayList<>();

    public static Stack<Fragment> fragmentStack;

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

        fragmentStack = new Stack<>();
        fragmentStack.push(fragmentHome);


    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_account:
                    transaction.replace(R.id.frame_layout, fragmentFood).commitAllowingStateLoss();
                    break;
                case R.id.menu_menu:
                    transaction.replace(R.id.frame_layout, fragmentEntertainment).commitAllowingStateLoss();
                    break;
                case R.id.menu_cart:
                    transaction.replace(R.id.frame_layout, fragmentTransportation).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }


//    @Override
//    public void onBackPressed(){
////        Toast.makeText(this, "no way", Toast.LENGTH_SHORT).show();
//        if (!fragmentStack.isEmpty()) {
//            Fragment nextFragment = fragmentStack.pop();
//            fragmentManager.beginTransaction().replace(R.id.frame_layout, nextFragment).commit();
//            System.out.println("[TESTING >>] " + fragmentStack.size());
//        } else {
//            super.onBackPressed();
//        }
//    }



}