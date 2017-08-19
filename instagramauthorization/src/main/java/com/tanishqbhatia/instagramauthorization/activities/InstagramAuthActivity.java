package com.tanishqbhatia.instagramauthorization.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import com.tanishqbhatia.instagramauthorization.R;
import com.tanishqbhatia.instagramauthorization.engine.InstagramEngine;
import com.tanishqbhatia.instagramauthorization.engine.InstagramKitConstants;
import com.tanishqbhatia.instagramauthorization.exceptions.InstagramException;
import com.tanishqbhatia.instagramauthorization.interfaces.InstagramAuthCallbackListener;
import com.tanishqbhatia.instagramauthorization.interfaces.InstagramLoginCallbackListener;
import com.tanishqbhatia.instagramauthorization.objects.IGSession;
import com.tanishqbhatia.instagramauthorization.utils.Utils;
import com.tanishqbhatia.instagramauthorization.widgets.InstagramWebViewClient;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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

        webViewClient = new InstagramWebViewClient(instagramAuthCallbackListener);

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


    InstagramAuthCallbackListener instagramAuthCallbackListener = new InstagramAuthCallbackListener() {
        @Override
        public boolean onRedirect(String actualRedirectURL) {
            Log.v("IntagramAuthActivity", "URL: " + redirectURL);

            Uri actualRedirectURI = Uri.parse(actualRedirectURL);
            Uri redirectURI = Uri.parse(redirectURL);

            try {

                if (actualRedirectURI.getScheme().equals(redirectURI.getScheme()) && actualRedirectURI.getHost().equals(redirectURI.getHost())) {

                    //Fragment contains the access token i.e. http://your-redirect-uri#access_token=ACCESS-TOKEN
                    String fragment = actualRedirectURI.getFragment();

                    if (null != fragment && !fragment.isEmpty()) {

                        Map<String, String> tokenHash = Utils.splitQuery(fragment);

                        if (tokenHash.size() > 0 && tokenHash.containsKey("access_token")) {
                            //TODO Save access token and return success callback

                            IGSession IGSession = new IGSession(tokenHash.get("access_token"));

                            InstagramEngine.getInstance(InstagramAuthActivity.this).setSession(IGSession);

                            InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onSuccess(IGSession);
                            Log.v("IntagramAuthActivity", "Access Token: " + tokenHash.get("access_token"));

                        } else {
                            //TODO Show error dialog OR return failure callback
                            Log.v("IntagramAuthActivity", "Oh Crap....");

                            InstagramException instagramException = new InstagramException("Error: Something went wrong, please try again.");

                            InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onError(instagramException);

                        }
                    } else {
                        //TODO Show error dialog OR return failure callback

                        String errorDescription = actualRedirectURI.getQueryParameter("error_description");
                        String error = actualRedirectURI.getQueryParameter("error");
                        String errorReason = actualRedirectURI.getQueryParameter("errorReason");

                        Log.v("IntagramAuthActivity", "Oh Crap...." + " Error:" + error + " Description: " + errorDescription);

                        InstagramException instagramException = new InstagramException(error, errorReason);

                        InstagramEngine.getInstance(InstagramAuthActivity.this).getInstagramLoginButtonCallback().onError(instagramException);

                    }
                    finish();
                    return true;
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return false;
        }
    };

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
