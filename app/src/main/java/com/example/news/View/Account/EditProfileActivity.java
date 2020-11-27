package com.example.news.View.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.news.Model.Account;
import com.example.news.R;
import com.example.news.View.Home.Home_Fragment;
import com.example.news.View.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etFullname, etAddress, etBirthday;
    RadioGroup radioButton;
    Button btSave, btChangePassword;
    RadioButton selectedRadioButton;
    RadioButton radioButton_female, radioButton_male;

    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";

    DatabaseReference databaseReference;
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
        btChangePassword = findViewById(R.id.btChangePassword);

        radioButton_female = findViewById(R.id.radioButton_female);
        radioButton_male = findViewById(R.id.radioButton_male);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("account");

        sharedPreferences = this.getSharedPreferences(filename, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)){
            String id = sharedPreferences.getString("id","id");
            databaseReference.orderByKey().equalTo(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Account account = data.getValue(Account.class);

                        etFullname.setText(account.getName());
                        etAddress.setText(account.getAddress());
                        etBirthday.setText(account.getBirthday());
                        etEmail.setText(account.getEmail());

                        String gender = account.getSex();
                        if(gender.equals(radioButton_female.getText().toString())){
                            radioButton_female.setChecked(true);
                        }
                        else if(gender.equals(radioButton_male.getText().toString())){
                            radioButton_male.setChecked(true);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String fullName = etFullname.getText().toString();
                String birthday = etBirthday.getText().toString();
                String address = etAddress.getText().toString();

                String id = sharedPreferences.getString("id","id");

                int selectedRadioButtonId = radioButton.getCheckedRadioButtonId();
                String sex = null;
                if (selectedRadioButtonId != -1){
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    sex = selectedRadioButton.getText().toString();

                    if(email.isEmpty()  || fullName.isEmpty() || birthday.isEmpty() || address.isEmpty()){
                        Toast.makeText(EditProfileActivity.this,"You must to enter your information",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseReference.child(id).child("name").setValue(fullName);
                        databaseReference.child(id).child("birthday").setValue(birthday);
                        databaseReference.child(id).child("address").setValue(address);
                        databaseReference.child(id).child("sex").setValue(sex);

                        Toast.makeText(EditProfileActivity.this, "Account is updated successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(EditProfileActivity.this, "You must to choose gender", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}