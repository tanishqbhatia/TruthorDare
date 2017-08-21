package com.tanishqbhatia.truthordare.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.fragments.HomeFragment;
import com.tanishqbhatia.truthordare.fragments.UserFragment;
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
                HomeFragment homeFragment = new HomeFragment();
                App.get().setCurrentFragment(homeFragment);
                return homeFragment;
            case Cons.NAVIGATION_SEARCH:
                HomeFragment searchFragment = new HomeFragment();
                App.get().setCurrentFragment(searchFragment);
                return searchFragment;
            case Cons.NAVIGATION_DASHBOARD:
                HomeFragment dashboardFragment = new HomeFragment();
                App.get().setCurrentFragment(dashboardFragment);
                return dashboardFragment;
            case Cons.NAVIGATION_NOTIFICATIONS:
                HomeFragment notificationsFragment = new HomeFragment();
                App.get().setCurrentFragment(notificationsFragment);
                return notificationsFragment;
            case Cons.NAVIGATION_USER:
                UserFragment userFragment = new UserFragment();
                App.get().setCurrentFragment(userFragment);
                return userFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return Cons.NAVIGATION_TOTAL;
    }
}
