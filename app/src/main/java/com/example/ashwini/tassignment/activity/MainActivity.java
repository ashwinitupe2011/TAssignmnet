package com.example.ashwini.tassignment.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ashwini.tassignment.R;
import com.example.ashwini.tassignment.fragment.ArticleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Load the fragment
    //Load the fragment
    ArticleFragment articleListFragment = new ArticleFragment();
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, articleListFragment, getResources().getString(R.string.fragment_name)).commit();
    }

}

