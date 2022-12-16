package com.example.assignment2.View.Cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment2.Presenter.itemDBhandler;
import com.example.assignment2.R;
import com.example.assignment2.View.Menu.MenuFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class CartFragment extends Fragment {

    MenuFragment fragmentMenu = new MenuFragment();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cart, container, false);

        itemDBhandler dbHandler = new itemDBhandler(getContext());
        ArrayList<HashMap<String, String>> itemList = dbHandler.GetItems();

        ListView lv = (ListView) rootView.findViewById(R.id.cart_list);
        ListAdapter adapter = new SimpleAdapter(getContext(), itemList,
                R.layout.list_row,new String[]{"id","category","name", "quantity", "price"},
                new int[]{R.id.id, R.id.category, R.id.name, R.id.quantity, R.id.price});
        lv.setAdapter(adapter);
        Button back = (Button) rootView.findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentMenu).commitAllowingStateLoss();
//                Toast.makeText(requireActivity().getApplicationContext(), "Continue to add",Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
}
