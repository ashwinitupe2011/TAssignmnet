package com.example.ashwini.tassignment.interfaces;

import java.util.List;

import com.example.ashwini.tassignment.data.Article;

public interface MainView {
    void onGetDataSuccess(String message, List<Article> list, String title);

    void onGetDataFailure(String message);

    void showProgress();

    void hideProgress();

    void showMessage(String message);
}
