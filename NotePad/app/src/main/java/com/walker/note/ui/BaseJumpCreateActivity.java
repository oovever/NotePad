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
import com.walker.note.bean.BaseJump;
import com.walker.note.bean.Skydiving;
import com.walker.note.db.BaseJumpDatabase;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by walke on 2017/6/4.
 */
public class BaseJumpCreateActivity extends AppBarActivity
{
    @Bind(R.id.create_no) EditText mNo;
    @Bind(R.id.create_date) TextView mDate;
    @Bind(R.id.create_location) EditText mLocation;
    @Bind(R.id.create_object) TextView mObject;
    @Bind(R.id.create_container) TextView mContainer;
    @Bind(R.id.create_pilot_chute) TextView mPilotChute;
    @Bind(R.id.create_canopy_model) EditText mCanopy;
    @Bind(R.id.create_altitude) EditText mAltitude;
    @Bind(R.id.create_time) EditText mTime;
    @Bind(R.id.create_total_time) EditText mTotalTime;
    @Bind(R.id.create_distance) TextView mDistance;
    @Bind(R.id.create_detail) TextView mDetail;
    @Bind(R.id.create_note) EditText mNote;

    private BaseJumpDatabase mDatabase;
    private BaseJump mPreviousBaseJump;
    @Override
    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);

        setContentView(R.layout.activity_base_jump_create);
        setTitle(getString(R.string.baseJump));

        objects[0] = getResources().getString(R.string.object_Building);
        objects[1] = getResources().getString(R.string.object_Antenna);
        objects[2] = getResources().getString(R.string.object_Span);
        objects[3] = getResources().getString(R.string.object_Earth);

        initData();
        initView();
    }

    private void initData()
    {
        mDatabase = new BaseJumpDatabase(this);
        Cursor query = mDatabase.query();
        if (query != null && query.getCount() > 0)
        {
            query.moveToFirst();
            String json = query.getString(query.getColumnIndex(BaseJumpDatabase.Table.JSON));
            mPreviousBaseJump = new Gson().fromJson(json, BaseJump.class);
        }
    }

    private void initView()
    {
        mDate.setText(DateUtils.dateToString(new Date(), DateStyle.YYYY_MM_DD));
        if (mPreviousBaseJump != null)
        {
            mNo.setText("" + (mPreviousBaseJump.getNo() + 1));
            mLocation.setText(mPreviousBaseJump.getLocation());
            mCanopy.setText(mPreviousBaseJump.getCanopyModel());
            mContainer.setText(mPreviousBaseJump.getContainer());
            mPilotChute.setText(mPreviousBaseJump.getPilotChuteModel());
            mAltitude.setText(mPreviousBaseJump.getAltitude());
            mTotalTime.setText("" + new BaseJumpDatabase(this).totalTime());
        }
    }

    String[] objects = new String[]{"", "", "", ""};

    @OnClick(R.id.create_object) void onSelectObject()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(objects, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mObject.setText(objects[which]);
            }
        });
        builder.show();
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
        BaseJump baseJump = new BaseJump();
        baseJump.setNo(MathUtil.str2Int(mNo.getText().toString()));
        baseJump.setAltitude(mAltitude.getText().toString());
        baseJump.setCanopyModel(mCanopy.getText().toString());
        baseJump.setObject(mObject.getText().toString());
        baseJump.setPilotChuteModel(mPilotChute.getText().toString());
        baseJump.setContainer(mContainer.getText().toString());
        baseJump.setDate(System.currentTimeMillis());
        baseJump.setDetail(mDetail.getText().toString());
        baseJump.setDistance(mDistance.getText().toString());
        baseJump.setLocation(mLocation.getText().toString());
        baseJump.setTime(MathUtil.str2Int(mTime.getText().toString()));
        baseJump.setTotalTime(MathUtil.str2Int(mTotalTime.getText().toString()));
        baseJump.setNote(mNote.getText().toString());
        Uri insert = mDatabase.insert(baseJump);
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
