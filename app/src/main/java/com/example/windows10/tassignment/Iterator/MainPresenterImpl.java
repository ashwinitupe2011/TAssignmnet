package com.example.windows10.tassignment.Iterator;

import android.content.Context;

import java.util.List;

import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.data.ArticleDataManager;
import com.example.windows10.tassignment.interfaces.GetDataListener;
import com.example.windows10.tassignment.interfaces.MainInteractor;
import com.example.windows10.tassignment.interfaces.MainPresenter;
import com.example.windows10.tassignment.interfaces.MainView;

/**
 *         Created by ashwini on 11/03/18.
 */

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
    public void getDataForList(Context context, boolean isRestoring) {

        // get this done by the interactor
        mMainView.showProgress();
        mInteractor.provideData(context, isRestoring);

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
    public void onSuccess(String message, List<Article> list) {

        // updating cache copy of data for restoring purpose
        ArticleDataManager.getInstance().setLatestData(list);

        if (mMainView != null) {
            mMainView.hideProgress();

            mMainView.onGetDataSuccess(message, list);
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
