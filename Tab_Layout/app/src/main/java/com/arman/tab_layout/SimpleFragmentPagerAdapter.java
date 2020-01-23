package com.arman.tab_layout;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Fragment_A();
        } else if (position == 1){
            return new Fragment_B();
        } else{
            return new Fragment_C();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "TB_1";
            case 1:
                return "TB_2";
            case 2:
                return "TB_3";
            default:
                return null;
        }
    }
}
