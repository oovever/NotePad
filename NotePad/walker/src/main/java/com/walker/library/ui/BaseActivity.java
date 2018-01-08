package com.walker.library.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bigkoo.svprogresshud.SVProgressHUD;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity
{
    private SVProgressHUD mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int resId)
    {
        super.setContentView(resId);
        init();
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(view);
        init();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void init()
    {
        ButterKnife.bind(this);

        mProgressDialog = new SVProgressHUD(this);
    }


    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed()
    {
        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void finish()
    {
        super.finish();
    }

    public void showLoading()
    {
        if (! mProgressDialog.isShowing())
        {
            mProgressDialog.show();
        }
    }

    public void showLoading(String status)
    {
        if (! mProgressDialog.isShowing())
        {
            mProgressDialog.showWithStatus(status);
        }
    }

    public void hideLoading()
    {
//        if (mProgressDialog.isShowing())
//        {
            mProgressDialog.dismiss();
//        }
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard()
    {
        InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        View view = getCurrentFocus();
        if (null == view)
        {
            view = getWindow().getDecorView();
        }
        if (null == view)
        {
            return;
        }
        IBinder token = view.getWindowToken();
        if (null == token)
        {
            return;
        }

        inputMethodManager.hideSoftInputFromWindow(token,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public void showSoftKeyboard()
    {
        InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        View view = getCurrentFocus();
        if (null == view)
        {
            view = getWindow().getDecorView();
        }
        if (null == view)
        {
            return;
        }
        IBinder token = view.getWindowToken();
        if (null == token)
        {
            return;
        }

        inputMethodManager.hideSoftInputFromWindow(token,
                InputMethodManager.HIDE_NOT_ALWAYS);
        inputMethodManager.showSoftInputFromInputMethod(token, InputMethodManager.SHOW_IMPLICIT);
    }

}
