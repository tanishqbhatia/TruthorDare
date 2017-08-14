package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.BindView;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Methods.init(this);
        setupToolbar();
        /*WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://instagram.com");*/
        new FinestWebView.Builder(this).titleDefault("Instagram").show("http://instagram.com");
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
