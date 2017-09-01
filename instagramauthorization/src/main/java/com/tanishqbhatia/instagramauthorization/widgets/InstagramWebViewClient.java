package com.tanishqbhatia.instagramauthorization.widgets;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class InstagramWebViewClient extends WebViewClient {

    public InstagramWebViewClient() {
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("url", "onPageFinished: " + url);
        view.setVisibility(View.VISIBLE);
        view.bringToFront();
        boolean flag = url.contains("code") || url.contains("error");
        if (url.startsWith("http://www.truthordare.ml/TruthorDare/identify.php") && flag) {
            view.setVisibility(View.GONE);
            view.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        view.setVisibility(View.GONE);
    }
}
