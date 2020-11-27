package com.example.news.View.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.news.R;

public class EditProfileActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etFullname, etAddress, etBirthday;
    RadioGroup radioButton;
    Button btSave;
    RadioButton radioButton_female, radioButton_male;

    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);
        etAddress = findViewById(R.id.etAddress);
        etBirthday = findViewById(R.id.etBirthday);
        radioButton = findViewById(R.id.radioButton);
        btSave = findViewById(R.id.btSave);

        radioButton_female = findViewById(R.id.radioButton_female);
        radioButton_male = findViewById(R.id.radioButton_male);


        sharedPreferences = this.getSharedPreferences(filename, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)){
            etEmail.setText(sharedPreferences.getString(username,"email"));
            etAddress.setText(sharedPreferences.getString("address","address"));
            etBirthday.setText(sharedPreferences.getString("birthday","birthday"));
            etFullname.setText(sharedPreferences.getString("name","name"));
            String gender =  sharedPreferences.getString("sex","sex");

            if (gender.equals(radioButton_female.getText().toString())){
                radioButton_female.isChecked();
            }
            else if(gender.equals(radioButton_male.getText().toString())){
                radioButton_male.isChecked();
            }
        }
    }
}