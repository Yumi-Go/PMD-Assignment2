package com.example.assignment2.View.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment2.Presenter.userDBhandler;
import com.example.assignment2.R;


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

        userDBhandler dbHandler = new userDBhandler(getContext());

        if (getArguments() != null)
        {
            String email = getArguments().getString("email"); // 프래그먼트1에서 받아온 값 넣기
            name = dbHandler.GetNameByEmail(email);
            nameTextView.setText(name);
        }

        return rootView;
    }


}