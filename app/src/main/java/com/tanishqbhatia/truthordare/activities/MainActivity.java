package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.tanishqbhatia.backstack.FragNavController;
import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.fragments.HomeFragment;
import com.tanishqbhatia.truthordare.fragments.UserFragment;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;
import com.tanishqbhatia.truthordare.utils.constants.Cons;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.PrefsMethods;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigationBnv)
    AHBottomNavigation navigationBnv;

    private Bundle savedInstanceState;
    private FragNavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Methods.init(this);
        if (!new PrefsMethods().isIdentified()) {
            Methods.launchOnly(IntroductionActivity.class);
        }
        setToolbar();
        setBottomNavigation();
        setListeners();
        initHome();
    }

    private void setBottomNavigation() {
        AHBottomNavigationAdapter bottomNavigationAdapter = new AHBottomNavigationAdapter(this, R.menu.navigation);
        bottomNavigationAdapter.setupWithBottomNavigation(navigationBnv);

//        navigationBnv.manageFloatingActionButtonBehavior(floatingActionButton);
        navigationBnv.setDefaultBackgroundColor(ColorCons.GREY_50);
//        navigationBnv.setBehaviorTranslationEnabled(false);
        navigationBnv.setAccentColor(ColorCons.BLACK);
        navigationBnv.setInactiveColor(ColorCons.GREY_500);
//        navigationBnv.setForceTint(true);
        navigationBnv.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        navigationBnv.setCurrentItem(0);
//        navigationBnv.setNotificationBackgroundColor(ColorCons.BLUE_500);
    }

    private void initHome() {
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setListeners() {
        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(new FragNavController.TransactionListener() {
                    @Override
                    public void onTabTransaction(Fragment fragment, int index) {
                        // If we have a backstack, show the back button
                        if (toolbar != null && mNavController != null) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
                        }
                    }

                    @Override
                    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
                        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
                        // If we have a backstack, show the back button
                        if (toolbar != null && mNavController != null) {
                            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
                        }
                    }
                })
                .rootFragmentListener(new FragNavController.RootFragmentListener() {
                    @Override
                    public Fragment getRootFragment(int index) {
                        switch (index) {
                            case Cons.NAVIGATION_HOME:
                                return new HomeFragment();
                            case Cons.NAVIGATION_SEARCH:
                                return new HomeFragment();
                            case Cons.NAVIGATION_DASHBOARD:
                                return new HomeFragment();
                            case Cons.NAVIGATION_NOTIFICATIONS:
                                return new HomeFragment();
                            case Cons.NAVIGATION_USER:
                                return new UserFragment();
                        }
                        throw new IllegalStateException("Invalid index.");
                    }
                }, Cons.NAVIGATION_TOTAL)
                .build();

        navigationBnv.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected)
                    mNavController.switchTab(position);
                else mNavController.clearStack();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }
}