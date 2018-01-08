package com.walker.note.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDbOpenHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "note.db";
    private static final int DB_VERSION = 1;

    public BaseDbOpenHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        BaseJumpDatabase.Table.TABLE.create(db);
        SkydivingDatabase.Table.TABLE.create(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

}