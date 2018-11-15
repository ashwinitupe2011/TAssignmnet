package com.example.ashwini.tassignment.interfaces;

import java.util.List;

import com.example.ashwini.tassignment.data.Article;

public interface GetDataListener {

    void onSuccess(String message, List<Article> list, String title);

    void onFailure(String message);

}
