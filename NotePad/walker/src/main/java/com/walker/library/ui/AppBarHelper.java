package com.walker.library.ui;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.walker.library.R;

public class AppBarHelper
{
	private AppBarActivity mActivity;
	private Toolbar mToolbar;
	private ShimmerTextView mTitle;
	private View mLayout;
	private Shimmer mShimmer;
	private LayoutInflater mInflater;

	private int mLayoutResId;

	private boolean mShimmerVisible = false;

	public AppBarHelper(AppBarActivity mContext, int mLayoutResId)
	{
		this.mActivity = mContext;
		this.mInflater = mActivity.getLayoutInflater();
		this.mLayoutResId = mLayoutResId;
		this.mShimmer = new Shimmer().setDuration(2000);

		initToolbar();
		initLayout();
	}

	public AppBarHelper(AppBarActivity mContext, View mLayout)
	{
		this.mActivity = mContext;
		this.mLayout = mLayout;

		initToolbar();
	}

	public void setTitle(int titleId)
	{
		setTitle(mActivity.getString(titleId));
	}

	public void setTitle(CharSequence title)
	{
		if (mShimmerVisible)
		{
			mShimmer.cancel();
			mTitle.setText(title);
			mShimmer.start(mTitle);
		}
		else
		{
			mTitle.setText(title);
		}
	}

	public void setNavigationIcon(int navigationIcon)
	{
		if(navigationIcon == 0){
			mToolbar.setNavigationIcon(null);
		}else {
			mToolbar.setNavigationIcon(navigationIcon);
		}
	}

	public void setTitleColor(int colorRes)
	{
		mTitle.setTextColor(colorRes);
	}

	public void setAppBarBackground(int barBackground)
	{
		mToolbar.setBackgroundResource(barBackground);
	}

	public void setShimmerTitleVisible(boolean visible)
	{
		if (mShimmerVisible == visible) return;

		mShimmerVisible = visible;
	}

	private void initLayout()
	{
		mLayout = mActivity.getLayoutInflater().inflate(mLayoutResId, null);
	}

	@SuppressLint("NewApi")
	private void initToolbar()
	{
		mToolbar = (Toolbar) mInflater.inflate(R.layout.app_bar, null);
		 ViewCompat.setElevation(mToolbar,
				 mActivity.getResources().getDimension(R.dimen.app_bar_elevation));
		mTitle = (ShimmerTextView) mToolbar.findViewById(R.id.app_bar_title);

		mActivity.setSupportActionBar(mToolbar);
		mToolbar.setNavigationOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mActivity.onClickNavMenu();
			}
		});
	}

	public void build()
	{
		LinearLayout contentView;
//		if (! SDKVersionUtils.hasLollipop())
//		{
			contentView = (LinearLayout) mInflater.inflate(
					R.layout.appbar_container, null);
			FrameLayout mAppBarContainer =
					(FrameLayout) contentView.findViewById(R.id.toolbar_container);
			FrameLayout mContentContainer =
					(FrameLayout) contentView.findViewById(R.id.content_container);
			mAppBarContainer.addView(mToolbar);
			mContentContainer.addView(mLayout);
//		}
//		else
//		{
//			contentView = (LinearLayout) mInflater.inflate(
//					R.layout.appbar_container_lollipop, null);
//			contentView.addView(mToolbar);
//			contentView.addView(mLayout,
//					new LayoutParams(
//							LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		}
		mActivity.setSuperContentView(contentView);
		mActivity.setShortcutsVisible(mToolbar.getMenu());
	}
}
