package com.vanhackathon.mybesthelper;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by daividsilverio on 5/21/16.
 */
public class MBHApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.fontPathLatoRegular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
