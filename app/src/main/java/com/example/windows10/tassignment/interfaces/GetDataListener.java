package com.example.windows10.tassignment.interfaces;

import java.util.List;

import com.example.windows10.tassignment.data.Article;

/**
 /**
 *         Created by ashwini on 11/03/18.
 */

public interface GetDataListener {

    void onSuccess(String message, List<Article> list);

    void onFailure(String message);

}
