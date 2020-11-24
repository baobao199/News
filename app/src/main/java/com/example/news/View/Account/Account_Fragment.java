package com.example.news.View.Account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news.Model.Account;
import com.example.news.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Account_Fragment extends Fragment {
    EditText etEmail;
    EditText etPassword;
    Button btSignIn;
    Button btSignUp;

    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btSignIn = view.findViewById(R.id.btSignIn);
        btSignUp = view.findViewById(R.id.btSignUp);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")){
                    Toast.makeText(getContext(),"You must enter your email or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    String email = etEmail.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference("account");
                    Query query = databaseReference
                            .orderByChild("email")
                            .equalTo(email);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String password = etPassword.getText().toString();
                            for (DataSnapshot data : snapshot.getChildren()){
                                Account account = data.getValue(Account.class);
                                if (password.equals(account.getPassword())){
                                    Intent intent = new Intent(getContext(),Profile_Activity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getContext(),"Emal or Password is not correct",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

}
