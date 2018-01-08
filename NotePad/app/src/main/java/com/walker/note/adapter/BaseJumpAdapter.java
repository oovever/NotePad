package com.walker.note.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.walker.library.db.SQLiteTable;
import com.walker.note.R;
import com.walker.note.bean.BaseJump;
import com.walker.note.db.BaseJumpDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseJumpAdapter extends CursorAdapter
{
    private Context mContext;

    public BaseJumpAdapter(Context context, Cursor c, boolean autoReQuery)
    {
        super(context, c, autoReQuery);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        ViewHolder holder = (ViewHolder) view.getTag();
        String json = cursor.getString(cursor.getColumnIndex(BaseJumpDatabase.Table.JSON));
        BaseJump item = new Gson().fromJson(json, BaseJump.class);
        if (item != null)
        {
            item.setId(cursor.getInt(cursor.getColumnIndex(SQLiteTable.ID)));
            holder.mContent.setText(item.toString());
        }
    }

    class ViewHolder
    {
        @Bind(R.id.item_content) TextView mContent;

        public ViewHolder(View v)
        {
            ButterKnife.bind(this, v);
        }
    }
}
