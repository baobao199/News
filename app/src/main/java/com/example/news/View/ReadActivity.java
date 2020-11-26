package com.example.news.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;

public class ReadActivity extends AppCompatActivity {

    TextView tvTitle, tvContent, tvAuthor;
    ImageView ivimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        tvAuthor = findViewById(R.id.tvAuthor);
        ivimage = findViewById(R.id.image);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String author = getIntent().getStringExtra("author");
        String link = getIntent().getStringExtra("image");

        Glide.with(this).load(link).into(ivimage);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvAuthor.setText(author);
    }
}