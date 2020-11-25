package com.example.news.View.Account;

import android.content.Context;
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

import com.example.news.R;

public class Profile_Fragment extends Fragment {
    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";

    TextView tvName;
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
        btEditProfile = view.findViewById(R.id.btEditProfile);
        btSingOut = view.findViewById(R.id.btSignOut);

        sharedPreferences = getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)){
            tvName.setText("ok");
        }

        btSingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("bao","ok");

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
    }
}
