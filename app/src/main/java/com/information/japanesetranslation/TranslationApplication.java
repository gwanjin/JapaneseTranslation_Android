package com.information.japanesetranslation;

import android.app.Application;

import com.information.japanesetranslation.volley.VolleyHelper;

public class TranslationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.init(getApplicationContext());
    }
}
