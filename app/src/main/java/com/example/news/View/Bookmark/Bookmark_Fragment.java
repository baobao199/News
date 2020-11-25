package com.example.news.View.Bookmark;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.news.R;
import com.example.news.View.Account.Account_Fragment;
import com.example.news.View.Account.Profile_Fragment;

public class Bookmark_Fragment extends Fragment {

    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";

    LinearLayout line;
    TextView tvInform;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bookmark_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvInform = view.findViewById(R.id.tvInform);
        line = view.findViewById(R.id.line);

        sharedPreferences = getContext().getSharedPreferences(filename, Context.MODE_PRIVATE);

        if (!sharedPreferences.contains(username)){
            line.setVisibility(View.VISIBLE);
        }
        else{
        }
    }
}
