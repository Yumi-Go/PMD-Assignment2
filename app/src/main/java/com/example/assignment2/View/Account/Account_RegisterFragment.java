package com.example.assignment2.View.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment2.Presenter.userDBhandler;
import com.example.assignment2.R;


public class Account_RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    EditText nameEditText, emailEditText, passwordEditText;
    AccountFragment fragmentAccount = new AccountFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_tab2_register, container, false);

        nameEditText = rootView.findViewById(R.id.et_name);
        emailEditText = rootView.findViewById(R.id.et_email);
        passwordEditText = rootView.findViewById(R.id.et_password);

        Button btnRegister = rootView.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                userDBhandler dbHandler = new userDBhandler(getContext());
                dbHandler.insertUser(name, email, password);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentAccount).commitAllowingStateLoss();

                Toast.makeText(requireActivity().getApplicationContext(), "Registered Successfully",Toast.LENGTH_SHORT).show();
                nameEditText.setText(null);
                emailEditText.setText(null);
                passwordEditText.setText(null);

            }
        });

        return rootView;
    }


}

