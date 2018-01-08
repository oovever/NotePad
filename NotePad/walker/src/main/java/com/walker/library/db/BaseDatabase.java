package com.walker.library.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

public abstract class BaseDatabase
{
    private Context mContext;

    public BaseDatabase(Context context)
    {
        this.mContext = context;
    }

    public Context getContext()
    {
        return this.mContext;
    }

    protected abstract Uri getContentUri();

    public void notifyChange()
    {
        this.mContext.getContentResolver().notifyChange(getContentUri(), null);
    }

    protected final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return this.mContext.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    protected final Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return this.mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Uri insert(ContentValues values)
    {
        return this.mContext.getContentResolver().insert(getContentUri(), values);
    }

    protected final int bulkInsert(ContentValues[] values)
    {
        return this.mContext.getContentResolver().bulkInsert(getContentUri(), values);
    }

    protected final int update(ContentValues values, String where, String[] whereArgs)
    {
        return this.mContext.getContentResolver().update(getContentUri(), values, where, whereArgs);
    }

    protected final int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return this.mContext.getContentResolver().delete(getContentUri(), selection, selectionArgs);
    }

    protected final Cursor getList(String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return this.mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getCursorLoader(Context context)
    {
        return getCursorLoader(context, null, null, null, null);
    }

    protected final CursorLoader getCursorLoader(Context context, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return new CursorLoader(context, getContentUri(), projection, selection, selectionArgs, sortOrder);
    }
}