package com.walker.note.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.walker.library.app.App;

public class AppProvider extends ContentProvider
{
    public static final String SCHEME		        = "content://";
    public static final String AUTHORITY            = "com.broke.piece.provider";
    public static final String TYPE                 = "vnd.android.cursor.dir/vnd.piece.work.";

    public static final int CONTENT_JUMP_IDX = 0;
    public static final String CONTENT_JUMP_TAG = "base_jump";
    public static final Uri CONTENT_JUMP_URI = Uri.parse(SCHEME + AUTHORITY + "/" + CONTENT_JUMP_TAG);
    public static final String CONTENT_JUMP_TYPE = TYPE + CONTENT_JUMP_TAG;

    public static final int CONTENT_SKYDIVING_IDX = 1;
    public static final String CONTENT_SKYDIVING_TAG = "skydiving";
    public static final Uri CONTENT_SKYDIVING_URI = Uri.parse(SCHEME + AUTHORITY + "/" + CONTENT_SKYDIVING_TAG);
    public static final String CONTENT_SKYDIVING_TYPE = TYPE + CONTENT_SKYDIVING_TAG;

    private static final UriMatcher mUriMatcher;
    private static BaseDbOpenHelper mDBHelper;

    static
    {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        mUriMatcher.addURI(AUTHORITY, CONTENT_JUMP_TAG, CONTENT_JUMP_IDX);
        mUriMatcher.addURI(AUTHORITY, CONTENT_SKYDIVING_TAG, CONTENT_SKYDIVING_IDX);
    }

    @Override
    public boolean onCreate()
    {
        return true;
    }

    @Override
    public String getType(Uri uri)
    {
        switch (mUriMatcher.match(uri))
        {
            case CONTENT_JUMP_IDX:
            {
                return CONTENT_JUMP_TYPE;
            }
            case CONTENT_SKYDIVING_IDX:
            {
                return CONTENT_SKYDIVING_TYPE;
            }
            default:
            {
                throw new IllegalArgumentException("Unknown Uri" + uri);
            }
        }
    }

    public static BaseDbOpenHelper getDbHelper()
    {
        if (mDBHelper == null)
        {
            mDBHelper = new BaseDbOpenHelper(App.getAppContext());
        }
        return mDBHelper;
    }

    public static Object mObject = new Object();

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        synchronized (mObject)
        {
            String table = matchTable(uri);
            SQLiteDatabase db = getDbHelper().getWritableDatabase();
            long rowId = 0;
            db.beginTransaction();
            try
            {
                rowId = db.insert(table, null, values);
                db.setTransactionSuccessful();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                db.endTransaction();
            }
            if (rowId > 0)
            {
                Uri resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return resultUri;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        synchronized (mObject)
        {
            SQLiteDatabase db = getDbHelper().getWritableDatabase();

            String table = matchTable(uri);
            int result = 0;
            db.beginTransaction();
            try
            {
                result = db.delete(table, selection, selectionArgs);
                db.setTransactionSuccessful();
            }
            catch (IllegalStateException e)
            {
                e.printStackTrace();
            }
            finally
            {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return result;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        synchronized (mObject)
        {
            SQLiteDatabase db = getDbHelper().getWritableDatabase();

            String table = matchTable(uri);
            int result = 0;
            db.beginTransaction();
            try
            {
                result = db.update(table, values, selection, selectionArgs);
                db.setTransactionSuccessful();
            }
            catch (IllegalStateException e)
            {
                e.printStackTrace();
            }
            finally
            {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return result;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String table = matchTable(uri);
        queryBuilder.setTables(table);

        SQLiteDatabase db = getDbHelper().getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, // The database to queryFromDB
                projection, // The columns to return from the queryFromDB
                selection, // The columns for the where clause
                selectionArgs, // The values for the where clause
                null, // don't group the rows
                null, // don't filter by row groups
                sortOrder // The sort order
        );
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    protected String matchTable(Uri uri)
    {
        switch (mUriMatcher.match(uri))
        {
            case CONTENT_JUMP_IDX:
            {
                return BaseJumpDatabase.Table.TABLE_NAME;
            }
            case CONTENT_SKYDIVING_IDX:
            {
                return SkydivingDatabase.Table.TABLE_NAME;
            }
            default:
            {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }
}
