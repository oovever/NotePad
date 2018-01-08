package com.walker.note.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.walker.note.Globals;
import com.walker.note.fragment.BaseJumpFragment;
import com.walker.note.fragment.SkydivingFragment;

public class HomeAdapter extends FragmentStatePagerAdapter
{
    private String[] mTitles = {"高空跳伞", "低空跳伞"};


    private SkydivingFragment mSkydivingFragment;

    private BaseJumpFragment mBaseJumpFragment;

    public HomeAdapter(FragmentManager fm) {
        super(fm);

        if (Globals.MODEL_LANGUAGE != Globals.MODEL_CHINA)
        {
            mTitles[0] = "Skydiving";
            mTitles[1] = "BaseJump";
        }
    }

    public SkydivingFragment getSkydivingFragment()
    {
        return mSkydivingFragment;
    }

    public BaseJumpFragment getBaseJumpFragment()
    {
        return mBaseJumpFragment;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
            {
                if (mSkydivingFragment == null)
                {
                    mSkydivingFragment = new SkydivingFragment();
                }
                return mSkydivingFragment;
            }
            case 1:
            {
                if (mBaseJumpFragment == null)
                {
                    mBaseJumpFragment = new BaseJumpFragment();
                }
                return mBaseJumpFragment;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
