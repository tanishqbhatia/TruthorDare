package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanishq Bhatia on 04-09-2017 at 10:45.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Post {
    @SerializedName("post_id")
    private String postId;
    @SerializedName("post_url")
    private String postUrl;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
