package com.walker.library.ui;

import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AppBarActivity extends BaseActivity
{
    protected AppBarHelper mAppBarHelper;

    @Override
    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        super.setTitle("");
        mAppBarHelper = new AppBarHelper(this, layoutResID);
        mAppBarHelper.build();
    }

    @Override
    public void setContentView(View view)
    {
        mAppBarHelper = new AppBarHelper(this, view);
        mAppBarHelper.build();
    }

    public void setSuperContentView(View view)
    {
        super.setContentView(view);
        setOverflowShowingAlways();
    }

    public void setShimmerTitleVisible(boolean visible)
    {
        mAppBarHelper.setShimmerTitleVisible(visible);
    }

    @Override
    public void setTitle(CharSequence title)
    {
        mAppBarHelper.setTitle(title);
        super.setTitle("");
    }

    @Override
    public void setTitle(int titleId)
    {
        mAppBarHelper.setTitle(titleId);
        super.setTitle("");
    }

    public void setTitleColor(int color)
    {
        mAppBarHelper.setTitleColor(getResources().getColor(color));
    }

    public void setAppBarBackground(int barBackground)
    {
        mAppBarHelper.setAppBarBackground(barBackground);
    }

    public void setNavigationIcon(int icon)
    {
        mAppBarHelper.setNavigationIcon(icon);
    }

    public void onClickNavMenu()
    {
        this.finish();
    }

/*	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if (menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method method = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					method.setAccessible(true);
					method.invoke(menu, true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}*/

    private void setOverflowShowingAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null)
            {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setShortcutsVisible(Menu menu)
    {
        if (MenuBuilder.class.isInstance(menu))
        {
            MenuBuilder builder = (MenuBuilder) menu;
            builder.setShortcutsVisible(true);
            try
            {
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(builder, true);
            }
            catch (Exception ie)
            {
            }
        }
    }
}
