package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanishq Bhatia on 04-09-2017 at 14:31.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class GetUser {
    private Boolean response;
    @SerializedName("user")
    private User user;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
