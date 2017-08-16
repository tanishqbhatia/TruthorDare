package com.tanishqbhatia.truthordare.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tanishqbhatia.truthordare.fragments.HomeFragment;
import com.tanishqbhatia.truthordare.utils.constants.Cons;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 13:57.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class MainAdapter extends FragmentPagerAdapter {
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Cons.NAVIGATION_HOME:
                return new HomeFragment();
            case Cons.NAVIGATION_SEARCH:
                return new HomeFragment();
            case Cons.NAVIGATION_DASHBOARD:
                return new HomeFragment();
            case Cons.NAVIGATION_NOTIFICATIONS:
                return new HomeFragment();
            case Cons.NAVIGATION_PROFILE:
                return new HomeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return Cons.NAVIGATION_TOTAL;
    }
}
