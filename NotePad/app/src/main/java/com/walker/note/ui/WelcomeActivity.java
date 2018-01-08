package com.walker.note.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.walker.library.ui.BaseActivity;
import com.walker.note.R;

/**
 * Created by walke on 2017/6/4.
 */
public class WelcomeActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
