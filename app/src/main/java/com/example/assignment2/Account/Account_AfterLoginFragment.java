package com.example.assignment2.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Adapter.DBhandler;
import com.example.assignment2.MainActivity;
import com.example.assignment2.R;
import com.example.assignment2.RecyclerItem;

import java.util.ArrayList;
import java.util.List;


public class Account_AfterLoginFragment extends Fragment {

    TextView nameTextView;
    String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_tab3_afterlogin, container, false);

        nameTextView = rootView.findViewById(R.id.nameTv);

        DBhandler dbHandler = new DBhandler(getContext());

        if (getArguments() != null)
        {
            String email = getArguments().getString("email"); // 프래그먼트1에서 받아온 값 넣기
            name = dbHandler.GetNameByEmail(email);
            nameTextView.setText(name);
        }

        return rootView;
    }


}