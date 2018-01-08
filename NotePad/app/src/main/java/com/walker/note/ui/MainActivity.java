package com.walker.note.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.walker.library.ui.AppBarActivity;
import com.walker.library.utils.DateStyle;
import com.walker.library.utils.DateUtils;
import com.walker.library.utils.ToastUtils;
import com.walker.note.Globals;
import com.walker.note.R;
import com.walker.note.adapter.HomeAdapter;
import com.walker.note.bean.BaseJump;
import com.walker.note.bean.Skydiving;
import com.walker.note.db.BaseJumpDatabase;
import com.walker.note.db.SkydivingDatabase;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by walke on 2017/6/4.
 */
public class MainActivity extends AppBarActivity
{
    @Bind(R.id.main_tabs) TabLayout mTabs;
    @Bind(R.id.main_pager) ViewPager mViewPager;

    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);

        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.main_title));
        setShimmerTitleVisible(true);
        setNavigationIcon(0);

        mAdapter = new HomeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.manager_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_language:
            {
                Globals.toggle();

                Configuration config = getResources().getConfiguration();//获取系统的配置
                config.locale = Globals.MODEL_LANGUAGE == Globals.MODEL_CHINA ?
                        Locale.TRADITIONAL_CHINESE : Locale.ENGLISH;//将语言更改为繁体中文
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());//更新配置

                startActivity(new Intent(this, MainActivity.class));
                this.finish();
                break;
            }
            case R.id.action_skydiving:
            {
                startActivity(new Intent(this, SkydivingCreateActivity.class));
                break;
            }
            case R.id.action_base_jump:
            {
                startActivity(new Intent(this, BaseJumpCreateActivity.class));
                break;
            }
            case R.id.action_output_skydiving:
            {
                outPutFile("高空跳伞(" + System.currentTimeMillis() + ").xls");
                break;
            }
            case R.id.action_output_base_jump:
            {
                outPutFile("低空跳伞(" + System.currentTimeMillis() + ").xls");
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private String FILE_PATH = "/sdcard/output/";
    private void outPutFile(final String fileName)
    {
        File mkdirs = new File(FILE_PATH);

        if (!mkdirs.exists())
        {
            mkdirs.mkdirs();
        }
        final File file = new File(FILE_PATH, fileName);
        if (file.exists())
        {
            file.delete();
        }
        try
        {
            file.createNewFile();
        }
        catch (IOException e)
        {
            ToastUtils.showShort(e.toString());
            e.printStackTrace();
            return;
        }

        showLoading("正在导出Excel表");

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    WritableWorkbook book = Workbook.createWorkbook(file);

                    WritableSheet sheet = book.createSheet("第一页", 0);

                    int row = 0;

                    if (fileName.contains("高空"))
                    {
                        Cursor cursor = mAdapter.getSkydivingFragment().getAdapter().getCursor();
                        cursor.moveToFirst();

                        while (!cursor.isAfterLast())
                        {
                            String json = cursor.getString(cursor.getColumnIndex(SkydivingDatabase.Table.JSON));
                            Skydiving skydiving = new Gson().fromJson(json, Skydiving.class);
                            if (skydiving == null) continue;

                            sheet.addCell(new Label(0, row, String.valueOf(skydiving.getId())));
                            sheet.addCell(new Label(1, row, String.valueOf(skydiving.getAircraft())));
                            sheet.addCell(new Label(2, row, String.valueOf(skydiving.getAltitude())));
                            sheet.addCell(new Label(3, row, String.valueOf(skydiving.getCanopyModel())));
                            sheet.addCell(new Label(4, row, String.valueOf(DateUtils.dateToString(new Date(skydiving.getDate()), DateStyle.YYYY_MM_DD))));
                            sheet.addCell(new Label(5, row, String.valueOf(skydiving.getDetail())));
                            sheet.addCell(new Label(6, row, String.valueOf(skydiving.getDistance())));
                            sheet.addCell(new Label(7, row, String.valueOf(skydiving.getLocation())));
                            sheet.addCell(new Label(8, row, String.valueOf(skydiving.getTime())));
                            sheet.addCell(new Label(9, row, String.valueOf(skydiving.getTotalTime())));
                            sheet.addCell(new Label(10, row, String.valueOf(skydiving.getNote())));

                            row ++;
                            cursor.moveToNext();
                        }
                    }
                    else
                    {
                        Cursor cursor = mAdapter.getBaseJumpFragment().getAdapter().getCursor();
                        cursor.moveToFirst();

                        while (!cursor.isAfterLast())
                        {
                            String json = cursor.getString(cursor.getColumnIndex(BaseJumpDatabase.Table.JSON));
                            BaseJump baseJump = new Gson().fromJson(json, BaseJump.class);
                            if (baseJump == null) continue;

                            sheet.addCell(new Label(0, row, String.valueOf(baseJump.getId())));
                            sheet.addCell(new Label(1, row, String.valueOf(baseJump.getObject())));
                            sheet.addCell(new Label(2, row, String.valueOf(baseJump.getAltitude())));
                            sheet.addCell(new Label(3, row, String.valueOf(baseJump.getCanopyModel())));
                            sheet.addCell(new Label(4, row, String.valueOf(DateUtils.dateToString(new Date(baseJump.getDate()), DateStyle.YYYY_MM_DD))));
                            sheet.addCell(new Label(5, row, String.valueOf(baseJump.getDetail())));
                            sheet.addCell(new Label(6, row, String.valueOf(baseJump.getDistance())));
                            sheet.addCell(new Label(7, row, String.valueOf(baseJump.getLocation())));
                            sheet.addCell(new Label(8, row, String.valueOf(baseJump.getTime())));
                            sheet.addCell(new Label(9, row, String.valueOf(baseJump.getTotalTime())));
                            sheet.addCell(new Label(10, row, String.valueOf(baseJump.getPilotChuteModel())));
                            sheet.addCell(new Label(11, row, String.valueOf(baseJump.getContainer())));
                            sheet.addCell(new Label(12, row, String.valueOf(baseJump.getNote())));

                            row ++;
                            cursor.moveToNext();
                        }
                    }
                    book.write();
                    book.close();
                    mHandler.sendEmptyMessage(1);
                }
                catch (IOException e)
                {
                    mHandler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
                catch (RowsExceededException e)
                {
                    mHandler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
                catch (WriteException e)
                {
                    mHandler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler()
    {
        @Override
        public void dispatchMessage(Message msg)
        {
            if (msg.what == 1)
            {
                ToastUtils.showLong("Excel表已导出" + FILE_PATH + "目录下！");
            }
            else if (msg.what == 2)
            {
                ToastUtils.showLong("导出失败");
            }
            hideLoading();
        }

    };
}
