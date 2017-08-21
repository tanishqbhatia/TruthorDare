package com.tanishqbhatia.truthordare.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.toast.Toast;

import butterknife.BindView;

public class TermsandConditionsActivity extends AppCompatActivity {

    @BindView(R.id.termsAndConditionsTv)
    TextView termsAndConditionsTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.acceptTnCCb)
    AppCompatCheckBox acceptTnCCb;
    @BindView(R.id.acceptTnCCbLl)
    LinearLayout acceptTnCCbLl;
    private boolean areTnCAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        Methods.init(this);
        showHelperToast();
        setupToolbar();
        displayTnC();
        addListeners();
    }

    private void showHelperToast() {
        Toast.priority(Toast.NORMAL_PRIORITY).color(R.color.blue_500).duration(Toast.LENGTH_LONG).message("Please read these and accept the terms and conditions in order to continue.").show();
    }

    private void addListeners() {
        acceptTnCCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                areTnCAccepted = isChecked;
                if(isChecked)
                    Methods.changeBackgroundColor(acceptTnCCbLl, ColorCons.GREEN_500, 700);
                else Methods.changeBackgroundColor(acceptTnCCbLl, ColorCons.RED_500, 700);
                invalidateOptionsMenu();
            }
        });
    }

    private void displayTnC() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            termsAndConditionsTv.setText(Html.fromHtml(getString(R.string.termsAndConditions), Html.FROM_HTML_MODE_LEGACY));
        else termsAndConditionsTv.setText(Html.fromHtml(getString(R.string.termsAndConditions)));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.continue_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_continue);
        menuItem.setVisible(areTnCAccepted);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_continue:
                Methods.launch(IdentificationActivity.class);
                break;
        }
        return true;
    }
}
