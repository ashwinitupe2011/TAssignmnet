package com.example.ashwini.tassignment.Iterator;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashwini.tassignment.utils.Utils;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.ashwini.tassignment.data.Article;
import com.example.ashwini.tassignment.interfaces.GetDataListener;
import com.example.ashwini.tassignment.interfaces.MainInteractor;
import com.example.ashwini.tassignment.utils.Constants;

/**
 * Created by ashwini on 11/03/18.
 */

public class MainInteractorImpl implements MainInteractor {

    private GetDataListener mGetDatalistener;
    private RequestQueue mRequestQueue;
    private final String REQUEST_TAG = "Network-Call";

    public MainInteractorImpl(GetDataListener mGetDatalistener) {
        this.mGetDatalistener = mGetDatalistener;
    }

    @Override
    public void provideData(Context context) {

        if (Utils.isInternetConnection(context)) {
            this.initNetworkCall(context, Constants.Article_URL);
        } else {
            mGetDatalistener.onFailure("No internet connection.");
        }
    }

    public void initNetworkCall(Context context, String url) {

        cancelAllRequests();
        mRequestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, onListLoaded, onListError);
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000, /* 10 sec timeout policy */
                0, /*no retry*/
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(REQUEST_TAG);
        mRequestQueue.add(request);
    }

    private void cancelAllRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onDestroy() {
        cancelAllRequests();
    }

    private final Response.Listener<String> onListLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("Network", response);

            try {
                JSONObject object = new JSONObject(response);
                String articleTitle = object.getString("title");
                JSONArray array = object.getJSONArray("rows");

                List<Article> articlesList = new ArrayList<Article>();

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);

                    Article article = new Article();

                    if (object1.getString(Constants.TITLE) != "null" && object1.getString(Constants.DESC) != null && object1.getString(Constants.IMAGE_URL) != null) {
                        if (object1.getString(Constants.TITLE) == null || object1.getString(Constants.TITLE) == "null")
                            article.setArticleTitle(Constants.NO_TITLE);
                        else
                            article.setArticleTitle(object1.getString(Constants.TITLE));

                        if (object1.getString(Constants.DESC) == null || object1.getString(Constants.DESC) == "null")
                            article.setArticleDescription(Constants.NO_DESC);
                        else
                            article.setArticleDescription(object1.getString(Constants.DESC));

                        if (object1.getString(Constants.IMAGE_URL) == null || object1.getString(Constants.IMAGE_URL) == "null")
                            article.setUserURL(null);
                        else
                            article.setUserURL(object1.getString(Constants.IMAGE_URL));

                        articlesList.add(article);
                    }
                }
                mGetDatalistener.onSuccess("data Success", articlesList, articleTitle);
            } catch (JsonSyntaxException ex) {
                Log.e("Network11", ex.toString());
                mGetDatalistener.onFailure(ex.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private final Response.ErrorListener onListError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Network", error.toString());
            mGetDatalistener.onFailure(error.toString());
        }
    };
}
