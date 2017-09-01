package com.tanishqbhatia.truthordare.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tanishq Bhatia on 19-08-2017 at 10:17.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class User {
    private String id;
    private String accessToken;
    private String username;
    @SerializedName("full_name")
    private String fullName;
    private Integer posts;
    private Integer followers;
    private Integer following;
    private String bio;
    @SerializedName("profile_picture_url")
    private String profilePictureURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }
}
