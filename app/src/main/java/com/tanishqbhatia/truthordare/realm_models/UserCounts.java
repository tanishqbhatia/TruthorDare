package com.tanishqbhatia.truthordare.realm_models;

import io.realm.RealmObject;

/**
 * Created by Tanishq Bhatia on 19-08-2017 at 10:26.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

class UserCounts extends RealmObject {
    private Integer media;
    private Integer following;
    private Integer followers;

    Integer getMedia() {
        return media;
    }

    void setMedia(Integer media) {
        this.media = media;
    }

    Integer getFollowing() {
        return following;
    }

    void setFollowing(Integer following) {
        this.following = following;
    }

    Integer getFollowers() {
        return followers;
    }

    void setFollowers(Integer followers) {
        this.followers = followers;
    }
}
