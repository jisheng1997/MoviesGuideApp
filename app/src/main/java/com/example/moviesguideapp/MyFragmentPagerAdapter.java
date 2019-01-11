/**
 * projectName:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:MyFragmentPagerAdapter
 * date:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * version:1.12
 * author:
 * className:MyFragmentPagerAdapter
 * date:2019/1/10 15:19
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

}
