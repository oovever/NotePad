package com.walker.library.utils;

import android.widget.Toast;
import com.walker.library.app.*;

/**
 * Created by admin on 2016/8/26.
 */

public class ToastUtils
{
    public static void showShort(String text)
    {
        Toast.makeText(App.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String text)
    {
        Toast.makeText(App.getAppContext(), text, Toast.LENGTH_LONG).show();
    }
}
