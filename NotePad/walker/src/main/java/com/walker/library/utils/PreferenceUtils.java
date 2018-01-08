package com.walker.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceUtils
{
	private static PreferenceUtils mInstance = new PreferenceUtils();
	
	private Context mContext;
	private SharedPreferences mPref;

	private PreferenceUtils()
	{
	}

	public synchronized static PreferenceUtils getInstance() 
	{
		return mInstance;
	}
	
	public void init(Context context)
	{
		if (mContext == null)
		{
			mContext = context;
		}
		if (mPref == null)
		{
			mPref = PreferenceManager.getDefaultSharedPreferences(mContext);
		}
	}

	public void put(String key, String value)
	{
		Editor editor = mPref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getString(String key)
	{
		return mPref.getString(key, "");
	}
	public String getString(String key, String def)
	{
		return mPref.getString(key, def);
	}
	public void put(String key, int value)
	{
		Editor editor = mPref.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public int getInt(String key)
	{
		return mPref.getInt(key, 0);
	}

	public void put(String key, long value)
	{
		Editor editor = mPref.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getLong(String key)
	{
		return mPref.getLong(key, 0);
	}

	public void put(String key, boolean value)
	{
		Editor editor = mPref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getBoolean(String key)
	{
		return getBoolean(key, false);
	}
	
	public boolean getBoolean(String key, boolean defaultVal)
	{
		return mPref.getBoolean(key, defaultVal);
	}
	
	public boolean contains(String key)
	{
		return mPref.contains(key);
	}
	
	public void remove(String key)
	{
		Editor editor = mPref.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public void clear()
	{
		Editor editor = mPref.edit();
		editor.clear();
		editor.commit();
	}

}
