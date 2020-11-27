package com.example.news.View.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.Model.Account;
import com.example.news.R;
import com.example.news.View.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    public static final String filename = "login";
    public static final String username = "username";

    EditText etOldPass, etNewPass;
    Button btConfrim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etNewPass = findViewById(R.id.etNewPass);
        etOldPass = findViewById(R.id.etOldPass);
        btConfrim = findViewById(R.id.btConfrim);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("account");

        sharedPreferences = this.getSharedPreferences(filename, Context.MODE_PRIVATE);

        btConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String newPass = etNewPass.getText().toString();

                if(etOldPass.getText().toString().isEmpty()||etNewPass.getText().toString().isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this,"You must to enter information",Toast.LENGTH_SHORT).show();
                    etOldPass.requestFocus();
                }

                else{
                    String pass = sharedPreferences.getString("password","password");
                    Log.e("bao",pass);
                    String id = sharedPreferences.getString("id","id");
                    if (etOldPass.getText().toString().equals(pass)){
                        databaseReference.child(id).child("password").setValue(etNewPass.getText().toString());

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", etNewPass.getText().toString());
                        editor.commit();

                        Toast.makeText(ChangePasswordActivity.this, "Password is updated successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ChangePasswordActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}