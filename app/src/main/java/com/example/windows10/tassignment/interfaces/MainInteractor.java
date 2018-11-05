package com.example.windows10.tassignment.interfaces;

import android.content.Context;

/**
 *         Created by ashwini on 11/03/18.
 */

public interface MainInteractor {

    void provideData(Context context, boolean isRestoring);

    void onDestroy();

}
