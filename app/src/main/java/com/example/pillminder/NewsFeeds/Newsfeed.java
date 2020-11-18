package com.example.pillminder.NewsFeeds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pillminder.R;

public class Newsfeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        this.setTitle("NewsFeed");
    }
}