package com.tanishqbhatia.instagramauthorization.widgets;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tanishqbhatia.instagramauthorization.interfaces.InstagramAuthCallbackListener;


public class InstagramWebViewClient extends WebViewClient {

    boolean flag;

    InstagramAuthCallbackListener instagramAuthCallbackListener;

    private InstagramWebViewClient() {
    }

    public InstagramWebViewClient(InstagramAuthCallbackListener instagramAuthCallbackListener) {
        this.instagramAuthCallbackListener = instagramAuthCallbackListener;
    }

//    @TargetApi(Build.VERSION_CODES.N)
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        if (!instagramAuthCallbackListener.onRedirect(request.getUrl().toString())) {
//            view.loadUrl(request.getUrl().toString());
//        }
//        return true;
//    }
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if (!instagramAuthCallbackListener.onRedirect(url)) {
//            view.loadUrl(url);
//        }
//        return true;
//    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("url", "onPageFinished: " + url);
        view.setVisibility(View.VISIBLE);
        view.bringToFront();
        flag = url.contains("code");
        if (url.startsWith("http://tanishqbhatia.epizy.com/identify.php") && flag) {
//            if (!instagramAuthCallbackListener.onRedirect(url)) {
//            view.loadUrl(url);
            view.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
//            }
        }
        super.onPageFinished(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        view.setVisibility(View.GONE);
    }
}
