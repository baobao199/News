package com.example.news.View.Catogory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.news.Model.Catogory;
import com.example.news.Model.Newspaper;
import com.example.news.R;
import com.example.news.View.Home.ListNewsPaperAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CatogoryActivity extends AppCompatActivity {

    RecyclerView recyclerviewCatogory;
    DatabaseReference databaseReference;
    ArrayList<Newspaper> arrayList;
    ListNewsPaperAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catogory);

        String nameCatorogy = getIntent().getStringExtra("nameCatogory");

        recyclerviewCatogory = findViewById(R.id.recyclerviewCatogory);
        recyclerviewCatogory.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.e("test1",""+nameCatorogy);

    Query query = databaseReference.child("newspaper")
            .orderByChild("type")
            .equalTo(nameCatorogy);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()){
                    Newspaper newspaper = data.getValue(Newspaper.class);
                    arrayList.add(newspaper);
                }
                listAdapter = new ListNewsPaperAdapter(arrayList, CatogoryActivity.this);
                recyclerviewCatogory.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}