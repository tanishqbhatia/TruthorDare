package com.tanishqbhatia.truthordare.abstracts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.instagram.instagramapi.activities.InstagramAuthActivity;
import com.instagram.instagramapi.engine.InstagramEngine;
import com.instagram.instagramapi.engine.InstagramKitConstants;
import com.instagram.instagramapi.objects.IGSession;
import com.instagram.instagramapi.utils.InstagramKitLoginScope;
import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.constants.RequestCons;
import com.tanishqbhatia.truthordare.utils.prefs.PrefsMethods;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 16:42.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public abstract class Identify extends AppCompatActivity {

    public void login() {
        String[] scopes = {InstagramKitLoginScope.BASIC,
                InstagramKitLoginScope.COMMENTS,
                InstagramKitLoginScope.FOLLOWER_LIST,
                InstagramKitLoginScope.LIKES,
                InstagramKitLoginScope.PUBLIC_ACCESS,
                InstagramKitLoginScope.RELATIONSHIP};
        Intent intent = new Intent(App.get().getCurrentActivity(), InstagramAuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(InstagramEngine.TYPE, InstagramEngine.TYPE_LOGIN);
        intent.putExtra(InstagramEngine.SCOPE, scopes);
        startActivityForResult(intent, RequestCons.INSTAGRAM_LOGIN_REQUEST_CODE);
    }

    public abstract void onIdentified();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCons.INSTAGRAM_LOGIN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle.containsKey(InstagramKitConstants.kSessionKey)) {
                        IGSession session = (IGSession) bundle.getSerializable(InstagramKitConstants.kSessionKey);
                        if(session != null && session.getAccessToken() != null) {
                            PrefsMethods.setIdentified();
                            PrefsMethods.saveAccessToken(session.getAccessToken());
                            onIdentified();
                            return;
                        }
                    }
                }
                Methods.cleanSlateProtocol();
                break;
        }
    }
}