package com.walker.library.utils;

import android.content.Context;
import android.view.WindowManager;
import com.walker.library.app.*;

/**
 * Created by walker.sun on 2016/11/4
 */

public class DimenUtil 
{
	public static int dip2px(float dipValue)
	{
		float scale = App.getAppContext().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int sp2px(float spValue)
	{
		float fontScale = App.getAppContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int px2dip(int pxValue)
	{
		float scale = App.getAppContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕的宽度
	 *
	 * @return
	 */
	public static int getScreenWidth()
	{
		WindowManager manager = (WindowManager) App.getAppContext().getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getWidth();
	}

	/**
	 * 获取屏幕的高度
	 *
	 * @return
	 */
	public static int getScreenHeight()
	{
		WindowManager manager = (WindowManager) App.getAppContext().getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getHeight();
	}
	/**
	 */
	public static float getHeight(float initWidth, float initHeight,
								  float targetWidth) {
		return initHeight * targetWidth / initWidth;
	}
	public static int getHeight(int initWidth, int initHeight,
								int targetWidth) {
		return initHeight * targetWidth / initWidth;
	}
}
