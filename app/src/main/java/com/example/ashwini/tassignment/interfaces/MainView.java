package com.example.ashwini.tassignment.interfaces;

import java.util.List;

import com.example.ashwini.tassignment.data.Article;

/**
 *         Created by ashwini on 11/03/18.
 */

public interface MainView {
    void onGetDataSuccess(String message, List<Article> list, String title);

    void onGetDataFailure(String message);

    void showProgress();

    void hideProgress();

    void showMessage(String message);
}
