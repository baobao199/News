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

        sharedPreferences = getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)){
            tvEmail.setText("Email: "+sharedPreferences.getString(username,"email"));
            tvAddress.setText("Address: "+sharedPreferences.getString("address","address"));
            tvSex.setText("Gender: "+sharedPreferences.getString("sex","sex"));
            tvBirthday.setText("Birthday: "+sharedPreferences.getString("birthday","birthday"));
            tvName.setText("Full name: "+sharedPreferences.getString("name","name"));
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
    }
}
