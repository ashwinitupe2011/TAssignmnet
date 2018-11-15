package com.example.ashwini.tassignment.fragment;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.ashwini.tassignment.Iterator.MainPresenterImpl;
import com.example.ashwini.tassignment.R;
import com.example.ashwini.tassignment.adapter.ArticleAdapter;
import com.example.ashwini.tassignment.data.Article;
import com.example.ashwini.tassignment.interfaces.MainView;

import java.util.List;

public class ArticleFragment extends Fragment implements  MainView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    LinearLayoutManager linearLayoutManager;
    ArticleAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeContainer;
    private MainPresenterImpl mMainPresenter;
    private LinearLayout llNoInternetConn ;
    private Button btnRetry;

    private  Toolbar toolbar_layout;
    private  TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);

        InitView(view);
        return view;
    }

    /**
     * Initialise the UI
     * @param view
     */
    private void InitView(View view) {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = view.findViewById(R.id.articleRecyclerList);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        llNoInternetConn = view.findViewById(R.id.llNoInternetConnection);
        btnRetry = view.findViewById(R.id.retry_Info);
        btnRetry.setOnClickListener(this);
        toolbar_layout = view.findViewById(R.id.toolbar_title_layout);
        toolbar_title = toolbar_layout.findViewById(R.id.toolbar_title);
        mSwipeContainer =  view.findViewById(R.id.swipeContainer);
        mSwipeContainer.setOnRefreshListener(this);

        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mSwipeContainer.setRefreshing(false);
        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.getDataForList(getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * On refresh of swipe view
     */
    @Override
    public void onRefresh() {
        mSwipeContainer.setRefreshing(true);
        mMainPresenter.getDataForList(getActivity());
    }

    /**
     * On success of network response
     * @param message
     * @param list
     * @param title
     */

    @Override
    public void onGetDataSuccess(String message, List<Article> list, String title) {
        llNoInternetConn.setVisibility(View.GONE);
        toolbar_title.setText(title);
        mArticleAdapter = new ArticleAdapter(getActivity(), list);
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    /**
     * on failure of network response
     * @param message
     */
    @Override
    public void onGetDataFailure(String message) {
        llNoInternetConn.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View view) {
        mSwipeContainer.setRefreshing(true);
        mMainPresenter.getDataForList(getActivity());
    }
}
