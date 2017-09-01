package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 14:08.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class ServerResponse {
    private Boolean response;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("user_header")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
