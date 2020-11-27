package com.example.news.View.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.news.Model.Account;
import com.example.news.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etFullname, etAddress, etBirthday;
    RadioGroup radioButton;
    Button btClear, btApply;
    RadioButton selectedRadioButton;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);
        etAddress = findViewById(R.id.etAddress);
        etBirthday = findViewById(R.id.etBirthday);
        radioButton = findViewById(R.id.radioButton);
        btClear = findViewById(R.id.btClear);
        btApply = findViewById(R.id.btApply);

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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("account");
        final Account account = new Account();
        btApply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String fullName = etFullname.getText().toString();
                String birthday = etBirthday.getText().toString();
                String address = etAddress.getText().toString();

                int selectedRadioButtonId = radioButton.getCheckedRadioButtonId();
                String sex = null;
                if (selectedRadioButtonId != -1){
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    sex = selectedRadioButton.getText().toString();
                    if(email.isEmpty() || password.isEmpty() || fullName.isEmpty() || birthday.isEmpty() || address.isEmpty()){
                        Toast.makeText(RegisterActivity.this,"You must to enter your information",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        account.setName(fullName);
                        account.setPassword(password);
                        account.setAddress(address);
                        account.setEmail(email);
                        account.setBirthday(birthday);
                        account.setSex(sex);

                        databaseReference.push().setValue(account);

                        Toast.makeText(RegisterActivity.this, "Account is registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, Account_Fragment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "You must to enter your information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}