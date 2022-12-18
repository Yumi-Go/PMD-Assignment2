package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.assignment2.View.Account.AccountFragment;
import com.example.assignment2.View.Cart.CartFragment;
import com.example.assignment2.View.Home.HomeFragment;
import com.example.assignment2.View.Menu.MenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private int num_page = 4;

    public static Stack<Fragment> fragmentStack;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private AccountFragment fragmentAccount = new AccountFragment();
    private MenuFragment fragmentMenu = new MenuFragment();
    private CartFragment fragmentCart = new CartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setIcon(R.drawable.pizza_logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());

        fragmentStack = new Stack<>();
        fragmentStack.push(fragmentHome);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate (R.menu.menu_toolbar, menu);

        return true;
    }

    //앱바(App Bar)에 표시된 액션 또는 오버플로우 메뉴가 선택되면
    //액티비티의 onOptionsItemSelected() 메서드가 호출
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.search_item:
                // do your code
                return true;
            case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();
//                    MenuPresenter mVideoPresenter = new MenuPresenter((MenuContract.View) fragmentHome);
                    break;
                case R.id.menu_account:
                    transaction.replace(R.id.frame_layout, fragmentAccount).commitAllowingStateLoss();
                    break;
                case R.id.menu_menu:
                    transaction.replace(R.id.frame_layout, fragmentMenu).commitAllowingStateLoss();
                    break;
                case R.id.menu_cart:
                    transaction.replace(R.id.frame_layout, fragmentCart).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }


}