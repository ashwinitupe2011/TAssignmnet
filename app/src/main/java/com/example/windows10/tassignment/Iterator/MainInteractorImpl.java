package com.example.windows10.tassignment.Iterator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.data.ArticleDataManager;
import com.example.windows10.tassignment.interfaces.GetDataListener;
import com.example.windows10.tassignment.interfaces.MainInteractor;
import com.example.windows10.tassignment.utils.Constants;

/**
 *         Created by ashwini on 11/03/18.
 */

public class MainInteractorImpl implements MainInteractor {

    private GetDataListener mGetDatalistener;
    private RequestQueue mRequestQueue;

    private final String REQUEST_TAG = "EQ-Network-Call";

    public MainInteractorImpl(GetDataListener mGetDatalistener) {

        this.mGetDatalistener = mGetDatalistener;
    }

    @Override
    public void provideData(Context context, boolean isRestoring) {

        Boolean shouldLoadFromNetwork = false;
        if (isRestoring) {

            List<Article> existingData = ArticleDataManager.getInstance().getLatestData();

            if (existingData != null && !existingData.isEmpty()) {
                // we have cached copy of data for restoring purpose
                shouldLoadFromNetwork = false;
                mGetDatalistener.onSuccess("Restored Data", existingData);
            } else {
                shouldLoadFromNetwork = true;
            }
        } else {
            shouldLoadFromNetwork = true;
        }

        if (shouldLoadFromNetwork) {

            if (checkInternet(context)) {
                this.initNetworkCall(context, Constants.Article_URL);
            } else {
                mGetDatalistener.onFailure("No internet connection.");
            }
        }
    }


    public void initNetworkCall(Context context, String url) {

        cancelAllRequests();

        mRequestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, url, onEQLoaded, onEQError);
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

    private final Response.Listener<String> onEQLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("EQ-Network", response);

            try {

                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("rows");

                List<Article> articlesList = new ArrayList<Article>();

                for (int i=0 ;i<array.length();i++)
                {
                    JSONObject object1 = array.getJSONObject(i);
                    articlesList.add(new Article(object1.getString(Constants.TITLE),object1.getString(Constants.DESC),object1.getString(Constants.IMAGE_URL)));
                }

                mGetDatalistener.onSuccess("data Success",articlesList);
//                mGetDatalistener.onSuccess("data success", eqResponse.getArticles());

            } catch (JsonSyntaxException ex) {
                Log.e("EQ-Network11", ex.toString());
                mGetDatalistener.onFailure(ex.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private final Response.ErrorListener onEQError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("EQ-Network", error.toString());
            mGetDatalistener.onFailure(error.toString());
        }
    };

    public Boolean checkInternet(Context context) {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cn.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() == true) {
            return true;
        } else {
            return false;
        }
    }
}