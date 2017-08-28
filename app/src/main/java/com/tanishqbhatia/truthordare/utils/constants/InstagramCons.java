package com.tanishqbhatia.truthordare.utils.constants;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 11:46.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class InstagramCons {
    public static final String INSTAGRAM_CLIENT_ID = App.get().getResources().getString(R.string.instagram_client_id);
    public static final String INSTAGRAM_CLIENT_SECRET = App.get().getResources().getString(R.string.instagram_client_secret);
    public static final String INSTAGRAM_CALLBACK_URL = App.get().getResources().getString(R.string.instagram_callback_url);
    public static final String INSTAGRAM_RESPONSE_TYPE = "code";

    public static final String INSTAGRAM_LOGIN_URL_PREFIX = "https://api.instagram.com/oauth/authorize/?";
    public static final String INSTAGRAM_LOGIN_URL_BASIC_SCOPE = InstagramCons.INSTAGRAM_LOGIN_URL_PREFIX
            .concat(InstagramCons.URL_CLIENT_ID)
            .concat(InstagramCons.INSTAGRAM_CLIENT_ID)
            .concat(InstagramCons.AND)
            .concat(InstagramCons.URL_REDIRECT_URI)
            .concat(InstagramCons.INSTAGRAM_CALLBACK_URL)
            .concat(InstagramCons.AND)
            .concat(InstagramCons.URL_RESPONSE_TYPE)
            .concat(InstagramCons.INSTAGRAM_RESPONSE_TYPE)
            .concat(InstagramCons.AND)
            .concat(InstagramCons.URL_SCOPE)
            .concat(InstagramCons.SCOPE_BASIC);

    public static final String AND = "&";
    public static final String PLUS = "+";
    public static final String EQUAL = "=";

    public static final String URL_CLIENT_ID = "client_id=";
    public static final String URL_REDIRECT_URI = "redirect_uri=";
    public static final String URL_RESPONSE_TYPE = "response_type=";
    public static final String URL_SCOPE = "scope=";

    public static final String SCOPE_BASIC = "basic";
    public static final String SCOPE_PUBLIC_CONTENT = "public_content";
    public static final String SCOPE_FOLLOWER_LIST = "follower_list";
    public static final String SCOPE_COMMENTS = "comments";
    public static final String SCOPE_RELATIONSHIPS = "relationships";
    public static final String SCOPE_LIKES = "likes";

    /*public static final String INSTAGRAM_LOGIN_URL = INSTAGRAM_LOGIN_URL_PREFIX
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
            .concat(SCOPE_LIKES);*/
}