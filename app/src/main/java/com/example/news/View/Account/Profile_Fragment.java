package com.example.news.View.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.news.Model.Account;
import com.example.news.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Fragment extends Fragment {
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";

    TextView tvName,tvEmail, tvAddress, tvSex, tvBirthday;
    Button btSingOut, btEditProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvSex = view.findViewById(R.id.tvSex);
        tvBirthday = view.findViewById(R.id.tvBirthday);

        btEditProfile = view.findViewById(R.id.btEditProfile);
        btSingOut = view.findViewById(R.id.btSignOut);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("account");
        sharedPreferences = getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(username)){
            String id = sharedPreferences.getString("id","id");

            databaseReference.orderByKey().equalTo(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Account account = data.getValue(Account.class);

                        tvName.setText("Full name: "+account.getName());
                        tvAddress.setText("Address: "+account.getAddress());
                        tvSex.setText("Gender: "+account.getSex());
                        tvBirthday.setText("Birthday: "+account.getBirthday());
                        tvEmail.setText("Email: "+account.getEmail());
                        account.getPassword();
                        Log.e("test",account.getPassword());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        btSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getContext().getSharedPreferences(filename,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Account_Fragment account_fragment = new Account_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,account_fragment);
                transaction.commit();
            }
        });
        btEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
    }
}
