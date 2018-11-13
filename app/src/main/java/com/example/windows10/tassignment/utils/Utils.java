package com.example.windows10.tassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ashwini on 14-Nov-18.
 */

public class Utils {

    public static Boolean checkInternet(Context context) {
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cn.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected() == true) {
            return true;
        } else {
            return false;
        }
    }
}
