package com.walker.library.app;

import android.app.Application;
import android.content.Context;

import com.walker.library.utils.PreferenceUtils;

public class App extends Application
{
    private static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();

        mContext = getApplicationContext();

        PreferenceUtils.getInstance().init(mContext);
//        CrashHandler.getInstance().init(getApplicationContext());
    }

    public static Context getAppContext()
    {
        return mContext;
    }
}
