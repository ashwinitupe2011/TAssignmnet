package com.example.windows10.tassignment.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import com.example.windows10.tassignment.adapter.ArticleAdapter;
import com.example.windows10.tassignment.Iterator.MainPresenterImpl;
import com.example.windows10.tassignment.R;
import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.fragment.ArticleFragment;
import com.example.windows10.tassignment.interfaces.MainView;

/**
 * @author ashwini tupe
 *         Created by ashwini on 11/03/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ArticleFragment articleListFragment = new ArticleFragment();

    getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, articleListFragment, getResources().getString(R.string.fragment_name)).commit();
    }

}

