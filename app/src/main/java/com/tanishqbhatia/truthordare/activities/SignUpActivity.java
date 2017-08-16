package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.abstracts.Identify;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends Identify {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.continueBtn)
    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Methods.init(this);
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.continueBtn)
    public void onContinueBtnClicked() {
        disable();
        super.login();
    }

    private void disable() {
        Methods.disable(continueBtn);
        Methods.changeBackground(continueBtn, ColorCons.GREY_900);
    }

    private void enable() {
        Methods.enable(continueBtn);
        Methods.changeBackground(continueBtn, ColorCons.BLUE_500);
    }

    @Override
    public void onIdentified() {
        enable();
    }
}
