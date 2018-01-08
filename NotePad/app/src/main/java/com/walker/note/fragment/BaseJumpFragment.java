package com.walker.note.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.walker.library.ui.BaseFragment;
import com.walker.library.utils.ToastUtils;
import com.walker.note.R;
import com.walker.note.adapter.BaseJumpAdapter;
import com.walker.note.adapter.SkydivingAdapter;
import com.walker.note.db.BaseJumpDatabase;
import com.walker.note.db.SkydivingDatabase;

import butterknife.Bind;

/**
 * Created by walker.sun on 2016/11/4.
 */

public class BaseJumpFragment extends BaseFragment  implements LoaderManager.LoaderCallbacks<Cursor>
{
    @Bind(R.id.main_list) ListView mListView;
    @Bind(R.id.empty_view) View mEmptyView;

    private BaseJumpAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mAdapter = new BaseJumpAdapter(getActivity(), null, false);
        mListView.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_main;
    }

    public BaseJumpAdapter getAdapter()
    {
        return mAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        return new BaseJumpDatabase(getActivity()).getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        if (data.getCount() <= 0)
        {
            mListView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
        else
        {
            mListView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
            mAdapter.changeCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

}
