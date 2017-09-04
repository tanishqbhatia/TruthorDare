package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tanishq Bhatia on 04-09-2017 at 14:32.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class UserPosts {
    private Boolean response;
    @SerializedName("user_posts")
    private List<Post> userPostsList;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public List<Post> getUserPostsList() {
        return userPostsList;
    }

    public void setUserPostsList(List<Post> userPostsList) {
        this.userPostsList = userPostsList;
    }
}
