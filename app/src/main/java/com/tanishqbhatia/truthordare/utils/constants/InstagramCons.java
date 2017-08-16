package com.tanishqbhatia.truthordare.utils.constants;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 11:46.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class InstagramCons {
    private static final String INSTAGRAM_CLIENT_ID = App.get().getResources().getString(R.string.instagram_client_id);
    private static final String INSTAGRAM_CLIENT_SECRET = App.get().getResources().getString(R.string.instagram_client_secret);
    private static final String INSTAGRAM_CALLBACK_URL = App.get().getResources().getString(R.string.instagram_callback_url);

    private static final String INSTAGRAM_LOGIN_URL_PREFIX = "https://api.instagram.com/oauth/authorize/?";

    private static final String AND = "&";
    private static final String PLUS = "+";

    private static final String URL_CLIENT_ID = "client_id=";
    private static final String URL_REDIRECT_URI = "redirect_uri=";
    private static final String URL_RESPONSE_TYPE = "response_type=";
    private static final String URL_SCOPE = "scope=";

    private static final String SCOPE_BASIC = "basic";
    private static final String SCOPE_PUBLIC_CONTENT = "public_content";
    private static final String SCOPE_FOLLOWER_LIST = "follower_list";
    private static final String SCOPE_COMMENTS = "comments";
    private static final String SCOPE_RELATIONSHIPS = "relationships";
    private static final String SCOPE_LIKES = "likes";

    public static final String INSTAGRAM_LOGIN_URL = INSTAGRAM_LOGIN_URL_PREFIX
            .concat(URL_CLIENT_ID)
            .concat(INSTAGRAM_CLIENT_ID)
            .concat(AND)
            .concat(URL_REDIRECT_URI)
            .concat(INSTAGRAM_CALLBACK_URL)
            .concat(AND)
            .concat(URL_RESPONSE_TYPE)
            .concat(RequestCons.INSTAGRAM_LOGIN_REQUEST_CODE)
            .concat(AND)
            .concat(URL_SCOPE)
            .concat(SCOPE_BASIC)
            .concat(PLUS)
            .concat(SCOPE_PUBLIC_CONTENT)
            .concat(PLUS)
            .concat(SCOPE_FOLLOWER_LIST)
            .concat(PLUS)
            .concat(SCOPE_COMMENTS)
            .concat(PLUS)
            .concat(SCOPE_RELATIONSHIPS)
            .concat(PLUS)
            .concat(SCOPE_LIKES);
}