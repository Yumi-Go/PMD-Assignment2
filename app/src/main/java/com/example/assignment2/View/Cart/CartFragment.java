package com.example.assignment2.View.Cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

    ListView cartLv;
    TextView totalPriceTv;
    Button backBtn, clearBtn;
    MenuFragment fragmentMenu = new MenuFragment();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cart, container, false);

        itemDBhandler dbHandler = new itemDBhandler(getContext());
        ArrayList<HashMap<String, String>> itemList = dbHandler.GetItems();

        cartLv = (ListView) rootView.findViewById(R.id.cart_list);
        ListAdapter adapter = new SimpleAdapter(getContext(), itemList,
                R.layout.list_row,new String[]{"id","category","name", "quantity", "price"},
                new int[]{R.id.id, R.id.category, R.id.name, R.id.quantity, R.id.price});
        cartLv.setAdapter(adapter);

        totalPriceTv = rootView.findViewById(R.id.total_price);
        int totalPrice = dbHandler.GetTotalPrice();

        totalPriceTv.setText(totalPrice + " €");

        backBtn = (Button) rootView.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentMenu).commitAllowingStateLoss();
//                Toast.makeText(requireActivity().getApplicationContext(), "Continue to add",Toast.LENGTH_SHORT).show();

            }
        });

        clearBtn = (Button) rootView.findViewById(R.id.btnClear);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.ClearCartItems();
                Toast.makeText(requireActivity().getApplicationContext(), "All deleted",Toast.LENGTH_SHORT).show();
                cartLv.setAdapter(null);
                int totalPrice = dbHandler.GetTotalPrice();
                totalPriceTv.setText(totalPrice + " €");            }
        });

        return rootView;
    }
}
