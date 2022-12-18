package com.example.assignment2.View.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment2.View.MainActivity;
import com.example.assignment2.Presenter.userDBhandler;
import com.example.assignment2.R;

import java.util.Locale;


public class Account_AfterLoginFragment extends Fragment {

    TextView nameTextView;
    String name;

//    HomeFragment fragmentHome = new HomeFragment();
    Intent intent;

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
            String email = getArguments().getString("email");
            name = dbHandler.GetNameByEmail(email);
            nameTextView.setText(name.toUpperCase(Locale.ROOT) + " :)");
        }

        Button homeBtn = (Button) rootView.findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.frame_layout, fragmentHome).commitAllowingStateLoss();
            }
        });


        return rootView;
    }


}