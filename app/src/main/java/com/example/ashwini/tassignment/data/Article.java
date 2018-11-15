package com.example.ashwini.tassignment.data;


public class Article {

   private  String articleTitle;
   private String articleDescription;
   private String userURL;

    public Article(String articleTitle, String articleDescription, String userURL) {
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.userURL = userURL;
    }

    public Article() {
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }
}
