package com.walker.note.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import com.google.gson.Gson;
import com.walker.library.db.BaseDatabase;
import com.walker.library.db.Column;
import com.walker.library.db.SQLiteTable;
import com.walker.note.bean.BaseJump;

public class BaseJumpDatabase extends BaseDatabase
{
    public BaseJumpDatabase(Context context)
    {
        super(context);
    }

    @Override
    protected Uri getContentUri()
    {
        return AppProvider.CONTENT_JUMP_URI;
    }

    public int justQuery(int id)
    {
        Cursor cursor = query(
                null, SQLiteTable.ID + "= " + id, null, null);
        return cursor.getCount();
    }

    public Cursor query(int id)
    {
        return query(null, SQLiteTable.ID + "= " + id, null, null);
    }

    public Cursor query()
    {
        return query(null, null, null, SQLiteTable.ID + " DESC");
    }

    public int totalTime()
    {
        BaseDbOpenHelper mDBHelper = AppProvider.getDbHelper();
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        String totalSql="select total(" + Table.TIME + ") from " + Table.TABLE_NAME;
        Cursor cursor = db.rawQuery(totalSql, new String[]{});
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                int total = cursor.getInt(0);
                cursor.close();
                return total;
            }
        }
        return 0;
    }

    public Uri insert(BaseJump model)
    {
       return insert(getValues(model));
    }

    public ContentValues getValues(BaseJump model)
    {
        ContentValues values = new ContentValues();
        values.put(Table.NO, model.getNo());
        values.put(Table.DATE, model.getDate());
        values.put(Table.LOCATION, model.getLocation());
        values.put(Table.OBJECT, model.getObject());
        values.put(Table.ALTITUDE, model.getAltitude());
        values.put(Table.CONTAINER, model.getContainer());
        values.put(Table.CANOPY_MODEL, model.getCanopyModel());
        values.put(Table.PILOT_CHUTE_MODEL, model.getPilotChuteModel());
        values.put(Table.DISTANCE, model.getDistance());
        values.put(Table.TIME, model.getTime());
        values.put(Table.TOTAL_TIME, model.getTotalTime());
        values.put(Table.DETAIL, model.getDetail());
        values.put(Table.NOTE, model.getId());
        values.put(Table.JSON, new Gson().toJson(model));
        return values;
    }

    public int deleteAll()
    {
        synchronized (AppProvider.mObject)
        {
            BaseDbOpenHelper mDBHelper = AppProvider.getDbHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int row = db.delete(Table.TABLE_NAME, null, null);
            getContext().getContentResolver().notifyChange(getContentUri(), null);
            return row;
        }
    }

    public CursorLoader getCursorLoader()
    {
        return new CursorLoader(getContext(), getContentUri(), null, null, null, SQLiteTable.ID + " DESC");
    }

    public static class Table implements BaseColumns
    {
        public Table()
        {
        }

        public static final String TABLE_NAME   = "base_jump";

        public static final String NO             = "no";
        public static final String DATE             = "date";
        public static final String LOCATION             = "location";
        public static final String OBJECT             = "object";
        public static final String ALTITUDE             = "altitude";
        public static final String CONTAINER             = "container";
        public static final String CANOPY_MODEL             = "canopyModel";
        public static final String PILOT_CHUTE_MODEL             = "pilotChuteModel";
        public static final String DISTANCE             = "distance";
        public static final String TIME             = "time";
        public static final String TOTAL_TIME             = "totalTime";
        public static final String DETAIL             = "detail";
        public static final String NOTE             = "note";
        public static final String JSON             = "json";

        public static final SQLiteTable TABLE =
                new SQLiteTable(TABLE_NAME)
                        .addColumn(NO, Column.DataType.INTEGER)
                        .addColumn(DATE, Column.DataType.INTEGER)
                        .addColumn(LOCATION, Column.DataType.TEXT)
                        .addColumn(OBJECT, Column.DataType.TEXT)
                        .addColumn(ALTITUDE, Column.DataType.TEXT)
                        .addColumn(CONTAINER, Column.DataType.TEXT)
                        .addColumn(CANOPY_MODEL, Column.DataType.TEXT)
                        .addColumn(PILOT_CHUTE_MODEL, Column.DataType.TEXT)
                        .addColumn(DISTANCE, Column.DataType.TEXT)
                        .addColumn(TIME, Column.DataType.INTEGER)
                        .addColumn(TOTAL_TIME, Column.DataType.INTEGER)
                        .addColumn(DETAIL, Column.DataType.TEXT)
                        .addColumn(NOTE, Column.DataType.TEXT)
                        .addColumn(JSON, Column.DataType.TEXT);
    }
}
