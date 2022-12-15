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

import com.example.assignment2.Presenter.DBhandler;
import com.example.assignment2.R;


public class Account_RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    EditText nameEditText, emailEditText, passwordEditText;
    Account_LoginFragment fragmentLogin = new Account_LoginFragment();

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
                DBhandler dbHandler = new DBhandler(getContext());
                dbHandler.insertUser(name, email, password);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragmentLogin).commitAllowingStateLoss();

                Toast.makeText(requireActivity().getApplicationContext(), "Registered Successfully",Toast.LENGTH_SHORT).show();



            }
        });

        return rootView;
    }


}









//package com.example.assignment2.Account;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.assignment2.MainActivity;
//import com.example.assignment2.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//
//public class Account_RegisterFragment extends Fragment {
//
//    private static final String TAG = "RegisterFragment";
//    private FirebaseAuth mAuth;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_tab2_register, container, false);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        Button btnRegister = rootView.findViewById(R.id.btn_register);
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                register();
//            }
//        });
//
//        return rootView;
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//    }
//
//    private void register() {
//        EditText emailEditText = requireActivity().findViewById(R.id.et_email);
//        String email = emailEditText.getText().toString();
//        EditText passwordEditText = requireActivity().findViewById(R.id.et_password);
//        String password = passwordEditText.getText().toString();
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(getActivity(), MainActivity.class);
//                            startActivity(intent);
//                            Toast.makeText(getActivity(), "Register Finished", Toast.LENGTH_SHORT).show();
//                            requireActivity().finish();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(getActivity(), "Register Error", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
////    Intent intent = getIntent();
//
//}

