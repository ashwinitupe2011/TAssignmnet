package com.example.ashwini.tassignment.Iterator;

import android.content.Context;

import java.util.List;

import com.example.ashwini.tassignment.data.Article;
import com.example.ashwini.tassignment.data.ArticleDataManager;
import com.example.ashwini.tassignment.interfaces.GetDataListener;
import com.example.ashwini.tassignment.interfaces.MainInteractor;
import com.example.ashwini.tassignment.interfaces.MainPresenter;
import com.example.ashwini.tassignment.interfaces.MainView;

public class MainPresenterImpl implements MainPresenter, GetDataListener {

    private MainView mMainView;
    private MainInteractor mInteractor;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
        this.mInteractor = new MainInteractorImpl(this);
    }

    public MainView getMainView() {
        return mMainView;
    }

    @Override
    public void getDataForList(Context context ) {
        // get this done by the interactor
        mMainView.showProgress();
        mInteractor.provideData(context);
    }

    @Override
    public void onDestroy() {
        mInteractor.onDestroy();
        if (mMainView != null) {
            mMainView.hideProgress();
            mMainView = null;
        }
    }

    @Override
    public void onSuccess(String message, List<Article> list, String title) {
        ArticleDataManager.getInstance().setLatestData(list);

        if (mMainView != null) {
            mMainView.hideProgress();

            mMainView.onGetDataSuccess(message, list,title);
        }
    }

    @Override
    public void onFailure(String message) {
        if (mMainView != null) {
            mMainView.hideProgress();
            mMainView.onGetDataFailure(message);
        }
    }
}
