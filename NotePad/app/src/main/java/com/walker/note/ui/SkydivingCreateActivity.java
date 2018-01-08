package com.walker.note.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.walker.library.ui.AppBarActivity;
import com.walker.library.utils.DateStyle;
import com.walker.library.utils.DateUtils;
import com.walker.library.utils.MathUtil;
import com.walker.library.utils.ToastUtils;
import com.walker.note.R;
import com.walker.note.bean.Skydiving;
import com.walker.note.db.SkydivingDatabase;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by walke on 2017/6/4.
 */
public class SkydivingCreateActivity extends AppBarActivity
{
    @Bind(R.id.create_no) EditText mNo;
    @Bind(R.id.create_date) TextView mDate;
    @Bind(R.id.create_location) EditText mLocation;
    @Bind(R.id.create_canopy_model) EditText mCanopy;
    @Bind(R.id.create_aircraft) EditText mAircraft;
    @Bind(R.id.create_altitude) EditText mAltitude;
    @Bind(R.id.create_time) EditText mTime;
    @Bind(R.id.create_total_time) EditText mTotalTime;
    @Bind(R.id.create_distance) TextView mDistance;
    @Bind(R.id.create_detail) TextView mDetail;
    @Bind(R.id.create_note) EditText mNote;

    private SkydivingDatabase mDatabase;
    private Skydiving mPreviousSkydiving;
    @Override
    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);

        setContentView(R.layout.activity_skydiving_create);
        setTitle(getString(R.string.skydiving));

        initData();
        initView();
    }

    private void initData()
    {
        mDatabase = new SkydivingDatabase(this);
        Cursor query = mDatabase.query();
        if (query != null && query.getCount() > 0)
        {
            query.moveToFirst();
            String json = query.getString(query.getColumnIndex(SkydivingDatabase.Table.JSON));
            mPreviousSkydiving = new Gson().fromJson(json, Skydiving.class);
        }
    }

    private void initView()
    {
        mDate.setText(DateUtils.dateToString(new Date(), DateStyle.YYYY_MM_DD));
        if (mPreviousSkydiving != null)
        {
            mNo.setText("" + (mPreviousSkydiving.getNo() + 1));
            mLocation.setText(mPreviousSkydiving.getLocation());
            mCanopy.setText(mPreviousSkydiving.getCanopyModel());
            mAircraft.setText(mPreviousSkydiving.getAircraft());
            mAltitude.setText(mPreviousSkydiving.getAltitude());
            mTotalTime.setText("" + new SkydivingDatabase(this).totalTime());
        }
    }

    String[] distances = new String[]{"0-10 m", "10-20m", ">20m"};

    @OnClick(R.id.create_distance) void onSelectDistance()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(distances, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mDistance.setText(distances[which]);
            }
        });
        builder.show();
    }

    String[] detail = new String[]{"Belly", "Freefly", "Tracking", "Wingsuit", "Hop-N-Pop"};

    @OnClick(R.id.create_detail
    ) void onSelectDetail()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(detail, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mDetail.setText(detail[which]);
            }
        });
        builder.show();
    }

    @OnClick(R.id.create_layout_submit) void onSubmit()
    {
        Skydiving skydiving = new Skydiving();
        skydiving.setNo(MathUtil.str2Int(mNo.getText().toString()));
        skydiving.setAircraft(mAircraft.getText().toString());
        skydiving.setAltitude(mAltitude.getText().toString());
        skydiving.setCanopyModel(mCanopy.getText().toString());
        skydiving.setDate(System.currentTimeMillis());
        skydiving.setDetail(mDetail.getText().toString());
        skydiving.setDistance(mDistance.getText().toString());
        skydiving.setLocation(mLocation.getText().toString());
        skydiving.setTime(MathUtil.str2Int(mTime.getText().toString()));
        skydiving.setTotalTime(MathUtil.str2Int(mTotalTime.getText().toString()));
        skydiving.setNote(mNote.getText().toString());
        Uri insert = mDatabase.insert(skydiving);
        if (insert != null)
        {
            ToastUtils.showShort(getString(R.string.success));
            finish();
        }
        else
        {
            ToastUtils.showShort(getString(R.string.fail));
        }
    }
}
