package com.example.assignment2.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    RecyclerView recyclerView;
    List<RecyclerItem> itemsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_tab3_afterlogin, container, false);


        DBhandler db = new DBhandler(getContext());
        return rootView;
    }


}