package com.example.windows10.tassignment.data;

import java.util.ArrayList;
import java.util.List;

/**
 *         Created by ashwini on 11/03/18.
 */

public class ArticleDataManager {

    private List<Article> latestData;

    private static ArticleDataManager instance=null;

    private ArticleDataManager(){
        latestData = new ArrayList<Article>();
    }
    public static ArticleDataManager getInstance(){

        synchronized (ArticleDataManager.class) {
            if(instance == null){
                instance = new ArticleDataManager();
            }
        }

        return instance;
    }

    public List<Article> getLatestData() {
        return latestData;
    }

    public void setLatestData(List<Article> latestData) {

        this.latestData = latestData;
    }
}
