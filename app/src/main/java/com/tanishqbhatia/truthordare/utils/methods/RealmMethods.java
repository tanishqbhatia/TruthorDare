package com.tanishqbhatia.truthordare.utils.methods;

import com.tanishqbhatia.truthordare.realm_models.User;
import com.tanishqbhatia.truthordare.utils.constants.RealmCons;

import io.realm.Realm;

/**
 * Created by Tanishq Bhatia on 21-08-2017 at 14:54.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class RealmMethods {
    public void saveUser(final String accessToken, final String id, final String username, final String fullname, final Integer media, final Integer followers, final Integer following, final String bio, final String website, final String profilePictureUrl) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User checkUser = realm.where(User.class).equalTo(RealmCons.ID, id).findFirst();
                if (checkUser == null) {
                    User user = realm.createObject(User.class, id);
                    user.setAccessToken(accessToken);
                    user.setUsername(username);
                    user.setFullName(fullname);
                    user.setMedia(media);
                    user.setFollowers(followers);
                    user.setFollowing(following);
                    user.setBio(bio);
                    user.setWebsite(website);
                    user.setProfilePictureURL(profilePictureUrl);
                } else {
                    checkUser.setAccessToken(accessToken);
                    checkUser.setUsername(username);
                    checkUser.setFullName(fullname);
                    checkUser.setMedia(media);
                    checkUser.setFollowers(followers);
                    checkUser.setFollowing(following);
                    checkUser.setBio(bio);
                    checkUser.setWebsite(website);
                    checkUser.setProfilePictureURL(profilePictureUrl);
                }
            }
        });
        realm.close();
    }

    public User getUser() {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        if (user != null)
            return user;
        else return null;
    }
}
