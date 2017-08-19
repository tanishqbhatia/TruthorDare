package com.tanishqbhatia.truthordare.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Tanishq Bhatia on 19-08-2017 at 10:17.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class User extends RealmObject {
    @PrimaryKey
    private String id;
    private String username;
    private String fullName;
    private String profilePictureURL;
    private String bio;
    private String website;
    private UserCounts userCounts;
    private String accessToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public UserCounts getUserCounts() {
        return userCounts;
    }

    public void setUserCounts(UserCounts userCounts) {
        this.userCounts = userCounts;
    }

    public Integer getMedia() {
        return userCounts.getMedia();
    }

    public void setMedia(Integer media) {
        userCounts.setMedia(media);
    }


    public Integer getFollowing() {
        return userCounts.getFollowing();
    }

    public void setFollowing(Integer following) {
        userCounts.setFollowing(following);
    }

    public Integer getFollowers() {
        return userCounts.getFollowers();
    }

    public void setFollowers(Integer followers) {
        userCounts.setFollowers(followers);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
