package com.example.windows10.tassignment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.windows10.tassignment.Iterator.MainPresenterImpl;
import com.example.windows10.tassignment.R;
import com.example.windows10.tassignment.adapter.ArticleAdapter;
import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.interfaces.MainView;

import java.util.List;

/**
 * Created by ashwini on 13-Nov-18.
 */

public class ArticleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , MainView {

    static final String TAG = "ArticleFragment";

    LinearLayoutManager linearLayoutManager;
    ArticleAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeContainer;
    private MainPresenterImpl mMainPresenter;

    private  Toolbar toolbar_layout;
    private  TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);

        InitView(view);
        return view;

    }

    private void InitView(View view) {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = view.findViewById(R.id.articleRecyclerList);
        mRecyclerView.setLayoutManager(linearLayoutManager);



        toolbar_layout = view.findViewById(R.id.toolbar_title_layout);
        toolbar_title = toolbar_layout.findViewById(R.id.toolbar_title);


        mSwipeContainer =  view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        mSwipeContainer.setOnRefreshListener(this);
        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.getDataForList(getActivity(), false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(),"hehdjfhg",Toast.LENGTH_LONG).show();
        mMainPresenter.getDataForList(getActivity(), false);
    }

    @Override
    public void onGetDataSuccess(String message, List<Article> list, String title) {

        toolbar_title.setText(title);

        mArticleAdapter = new ArticleAdapter(getActivity(), list);
        mRecyclerView.setAdapter(mArticleAdapter);

    }

    @Override
    public void onGetDataFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        hideProgress();
        mSwipeContainer.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        if (mSwipeContainer != null && mSwipeContainer.isRefreshing()) {
            mSwipeContainer.setRefreshing(false);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
