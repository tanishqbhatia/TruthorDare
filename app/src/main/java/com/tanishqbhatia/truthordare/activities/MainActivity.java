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
import com.tanishqbhatia.truthordare.utils.constants.Cons;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.PrefsMethods;

import java.lang.reflect.Field;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigationBnv)
    BottomNavigationView navigationBnv;
    @BindView(R.id.contentVp)
    ViewPager contentVp;
    private MainAdapter mainAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    contentVp.setCurrentItem(Cons.NAVIGATION_HOME);
                    return true;
                case R.id.navigation_search:
                    contentVp.setCurrentItem(Cons.NAVIGATION_SEARCH);
                    return true;
                case R.id.navigation_dashboard:
                    contentVp.setCurrentItem(Cons.NAVIGATION_DASHBOARD);
                    return true;
                case R.id.navigation_notifications:
                    contentVp.setCurrentItem(Cons.NAVIGATION_NOTIFICATIONS);
                    return true;
                case R.id.navigation_user:
                    contentVp.setCurrentItem(Cons.NAVIGATION_USER);
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
                    navigationBnv.setSelectedItemId(R.id.navigation_home);
                    break;
                case Cons.NAVIGATION_SEARCH:
                    navigationBnv.setSelectedItemId(R.id.navigation_search);
                    break;
                case Cons.NAVIGATION_DASHBOARD:
                    navigationBnv.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case Cons.NAVIGATION_NOTIFICATIONS:
                    navigationBnv.setSelectedItemId(R.id.navigation_notifications);
                    break;
                case Cons.NAVIGATION_USER:
                    navigationBnv.setSelectedItemId(R.id.navigation_user);
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
        if(! new PrefsMethods().isIdentified()) {
            Methods.launchOnly(IntroductionActivity.class);
        }
        setToolbar();
        setListeners();
        setAdapters();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setAdapters() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        contentVp.setAdapter(mainAdapter);
    }

    private void setListeners() {
        navigationBnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(navigationBnv);
        contentVp.addOnPageChangeListener(mOnPageChangeListener);
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
