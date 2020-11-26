package com.example.news.View.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.news.R;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etFullname, etAddress, etBirthday;
    RadioGroup radioGroupSex;
    RadioButton radioButton_male, radioButton_female;
    Button btClear, btApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);
        etAddress = findViewById(R.id.etAddress);
        etBirthday = findViewById(R.id.etBirthday);
       // radioGroupSex = findViewById(R.id.radioButton_female);
        radioButton_female = findViewById(R.id.radioButton_female);
        radioButton_male = findViewById(R.id.radioButton_male);
        btClear = findViewById(R.id.btClear);

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmail.setText("");
                etPassword.setText("");
                etFullname.setText("");
                etAddress.setText("");
                etBirthday.setText("");
                etEmail.requestFocus();
            }
        });

    }
}