package com.example.ashwini.tassignment.interfaces;

import java.util.List;

import com.example.ashwini.tassignment.data.Article;

/**
 /**
 *         Created by ashwini on 11/03/18.
 */

public interface GetDataListener {

    void onSuccess(String message, List<Article> list, String title);

    void onFailure(String message);

}
