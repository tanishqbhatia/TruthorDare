package com.tanishqbhatia.instagramauthorization.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tanishqbhatia.instagramauthorization.R;
import com.tanishqbhatia.instagramauthorization.engine.InstagramEngine;
import com.tanishqbhatia.instagramauthorization.engine.InstagramKitConstants;
import com.tanishqbhatia.instagramauthorization.exceptions.InstagramException;
import com.tanishqbhatia.instagramauthorization.interfaces.InstagramLoginCallbackListener;
import com.tanishqbhatia.instagramauthorization.objects.IGSession;
import com.tanishqbhatia.instagramauthorization.widgets.InstagramWebViewClient;

public class InstagramAuthActivity extends AppCompatActivity {

    WebView instagrramAuthWebView;
    InstagramWebViewClient webViewClient;

    String authURL;
    String redirectURL;
    String[] scopes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instagram_authorization);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Instagram authorization - Truth or Dare");

        instagrramAuthWebView = (WebView) findViewById(R.id.instagramWv);

        authURL = InstagramEngine.getInstance(getApplicationContext()).authorizationURL();
        redirectURL = InstagramEngine.getInstance(getApplicationContext()).getAppRedirectURL();
        instagrramAuthWebView.getSettings().setJavaScriptEnabled(true);
        instagrramAuthWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        webViewClient = new InstagramWebViewClient();

        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {

        Bundle extras = intent.getExtras();

        if (null != extras && extras.containsKey(InstagramEngine.TYPE)) {
            final int type = extras.getInt(InstagramEngine.TYPE);

            switch (type) {

                //Login
                default:
                case InstagramEngine.TYPE_LOGIN:

                    if (extras.containsKey(InstagramEngine.SCOPE)) {
                        scopes = extras.getStringArray(InstagramEngine.SCOPE);
                        authURL = InstagramEngine.getInstance(getApplicationContext()).authorizationURLForScope(scopes);
                    } else {
                        authURL = InstagramEngine.getInstance(getApplicationContext()).authorizationURL();
                    }

                    if (!extras.containsKey(InstagramEngine.IS_LOGIN_BUTTON)) {
                        InstagramEngine.getInstance(InstagramAuthActivity.this).setInstagramLoginButtonCallback(instagramLoginCallbackListener);
                    }

                    instagrramAuthWebView.setWebViewClient(webViewClient);
                    instagrramAuthWebView.loadUrl(authURL);
                    break;

                //Logout
                case InstagramEngine.TYPE_LOGOUT:

                    instagrramAuthWebView.clearCache(true);
                    instagrramAuthWebView.clearHistory();
                    ClearCookies(getApplicationContext());

                    intent = new Intent();
                    setResult(RESULT_OK, intent);

                    finish();
                    break;
            }
        } else {

            throw new RuntimeException("You must provide type key with valid int value in intent");
        }
    }

    class ServerResponse {
        Boolean response;
        @SerializedName("access_token")
        String accessToken;

        public Boolean getResponse() {
            return response;
        }

        public void setResponse(Boolean response) {
            this.response = response;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        public void processHTML(String html) {
            try {
                String response;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    response = String.valueOf(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    response = String.valueOf(Html.fromHtml(html));
                }
                if (response != null) {
                    Gson gson = new Gson();
                    ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);
                    if (serverResponse != null) {
                        if (serverResponse.getResponse()) {
                            response = serverResponse.getAccessToken();
                            if (response != null && response.length() > 0) {
                                IGSession IGSession = new IGSession(response);
                                InstagramEngine.getInstance(InstagramAuthActivity.this).setSession(IGSession);
                                InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onSuccess(IGSession);
                            } else {
                                InstagramException instagramException = new InstagramException("Error: Something went wrong, please try again.");
                                InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onError(instagramException);
                            }
                        } else {
                            Toast.makeText(InstagramAuthActivity.this, "Error while connecting to instagram, please try again.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        InstagramException instagramException = new InstagramException("Error: Something went wrong, please try again.");
                        InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onError(instagramException);
                    }
                    finish();
                } else initAuth();
            } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        private void initAuth() {
            instagrramAuthWebView.reload();
        }

        InstagramLoginCallbackListener instagramLoginCallbackListener = new InstagramLoginCallbackListener() {
            @Override
            public void onSuccess(IGSession session) {
                Bundle responseSession = new Bundle();
                responseSession.putSerializable(InstagramKitConstants.kSessionKey, session);
                Intent intent = new Intent();
                intent.putExtras(responseSession);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onCancel() {
                Bundle responseSession = new Bundle();
                responseSession.putString("message", "Something went wrong, try again.");
                Intent intent = new Intent();
                intent.putExtras(responseSession);
                setResult(RESULT_CANCELED, intent);
                finish();
            }

            @Override
            public void onError(InstagramException error) {

                Bundle responseSession = new Bundle();
                responseSession.putString("message", error.getMessage());
                responseSession.putString("error", error.getErrorType());
                responseSession.putString("error_reason", error.getErrorReason());

                Intent intent = new Intent();
                intent.putExtras(responseSession);

                setResult(RESULT_CANCELED, intent);
                finish();


            }
        };

        @SuppressWarnings("deprecation")
        private static void ClearCookies(Context context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                Log.d("IGAuthActivity", "Using ClearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
            } else {
                Log.d("IGAuthActivity", "Using ClearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
                CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
                cookieSyncMngr.startSync();
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                cookieManager.removeSessionCookie();
                cookieSyncMngr.stopSync();
                cookieSyncMngr.sync();
            }
        }


    }
