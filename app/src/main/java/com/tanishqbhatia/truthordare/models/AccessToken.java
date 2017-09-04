package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanishq Bhatia on 04-09-2017 at 14:30.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class AccessToken {
    private Boolean response;
    @SerializedName("access_token")
    private String accessToken;

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
