package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.adapters.MainAdapter;
import com.tanishqbhatia.truthordare.utils.Cons;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.PrefsMethods;

import java.lang.reflect.Field;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigationBnv)
    BottomNavigationView mNavigationBnv;
    @BindView(R.id.contentVp)
    ViewPager mContentVp;
    private MainAdapter mainAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mContentVp.setCurrentItem(Cons.NAVIGATION_HOME);
                    return true;
                case R.id.navigation_search:
                    mContentVp.setCurrentItem(Cons.NAVIGATION_SEARCH);
                    return true;
                case R.id.navigation_dashboard:
                    mContentVp.setCurrentItem(Cons.NAVIGATION_DASHBOARD);
                    return true;
                case R.id.navigation_notifications:
                    mContentVp.setCurrentItem(Cons.NAVIGATION_NOTIFICATIONS);
                    return true;
                case R.id.navigation_profile:
                    mContentVp.setCurrentItem(Cons.NAVIGATION_PROFILE);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener
            = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case Cons.NAVIGATION_HOME:
                    mNavigationBnv.setSelectedItemId(R.id.navigation_home);
                    break;
                case Cons.NAVIGATION_SEARCH:
                    mNavigationBnv.setSelectedItemId(R.id.navigation_search);
                    break;
                case Cons.NAVIGATION_DASHBOARD:
                    mNavigationBnv.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case Cons.NAVIGATION_NOTIFICATIONS:
                    mNavigationBnv.setSelectedItemId(R.id.navigation_notifications);
                    break;
                case Cons.NAVIGATION_PROFILE:
                    mNavigationBnv.setSelectedItemId(R.id.navigation_profile);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Methods.init(this);
        if(!PrefsMethods.isIdentified()) {
            Methods.cleanSlateProtocol();
        }
        setSupportActionBar(toolbar);
        setListeners();
        setAdapters();
    }

    private void setAdapters() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(mainAdapter);
    }

    private void setListeners() {
        mNavigationBnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(mNavigationBnv);
        mContentVp.addOnPageChangeListener(mOnPageChangeListener);
    }

    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Methods.showLog("Methods", "disableShiftMode()", "Unable to get shift mode field", e.getMessage());
        } catch (IllegalAccessException e) {
            Methods.showLog("Methods", "disableShiftMode()", "Unable to change value of shift mode", e.getMessage());
        }
    }

}
