package com.walker.library.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.svprogresshud.SVProgressHUD;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/11/4.
 */

public abstract class BaseFragment extends Fragment {
    private SVProgressHUD mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProgressDialog = new SVProgressHUD(getContext());

        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);

        return view;
    }

    protected abstract int getLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    protected void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void showStatusLoading(String status) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.showWithStatus(status);
        }
    }

    protected void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
