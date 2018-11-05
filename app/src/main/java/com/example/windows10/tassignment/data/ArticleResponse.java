package com.example.windows10.tassignment.data;

import java.util.List;

/**
 *         Created by ashwini on 11/03/18.
 */

public class ArticleResponse {

    private List<Article> earthquakes;

    public ArticleResponse(List<Article> earthquakes) {

        this.earthquakes = earthquakes;
    }

    public List<Article> getArticles() {
        return earthquakes;
    }

    public void setArticles(List<Article> earthquakes) {
        this.earthquakes = earthquakes;
    }


}
