package com.example.assignment2.Account;

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

import com.example.assignment2.Adapter.DBhandler;
import com.example.assignment2.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;


public class Account_LoginFragment extends Fragment {

    Button btnLogin;
    EditText emailEditText, passwordEditText;
    Account_AfterLoginFragment fragmentAfterLogin = new Account_AfterLoginFragment();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_tab1_login, container, false);

        emailEditText = rootView.findViewById(R.id.et_email);
        passwordEditText = rootView.findViewById(R.id.et_password);

        btnLogin = rootView.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhandler dbHandler = new DBhandler(getContext());
                boolean isExist = dbHandler.checkUserExist(emailEditText.getText().toString(), passwordEditText.getText().toString());
                if(isExist){
                    Bundle bundle = new Bundle();
                    bundle.putString("email", emailEditText.getText().toString());
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    fragmentAfterLogin.setArguments(bundle);
                    transaction.replace(R.id.frame_layout, fragmentAfterLogin).commitAllowingStateLoss();
                    Toast.makeText(requireActivity().getApplicationContext(), "Login Successfully",Toast.LENGTH_SHORT).show();

                } else {
                    passwordEditText.setText(null);
                    Toast.makeText(requireActivity().getApplicationContext(), "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }



}