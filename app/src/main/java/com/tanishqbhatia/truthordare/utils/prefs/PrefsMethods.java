package com.tanishqbhatia.truthordare.utils.prefs;

import com.google.gson.Gson;
import com.tanishqbhatia.truthordare.models.User;
import com.tanishqbhatia.truthordare.utils.constants.PrefsCons;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 10:40.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class PrefsMethods {
    public void setIdentified() {
        Prefs.putBoolean(PrefsCons.IDENTIFIED, true);
    }

    public void setNotIdentified() {
        Prefs.putBoolean(PrefsCons.IDENTIFIED, false);
    }

    public Boolean isIdentified() {
        return Prefs.getBoolean(PrefsCons.IDENTIFIED, false);
    }


    public void saveAccessToken(String accessToken) {
        Prefs.putString(PrefsCons.ACCESS_TOKEN, accessToken);
    }

    public void removeAccessToken() {
        Prefs.remove(PrefsCons.ACCESS_TOKEN);
    }

    public String getAccessToken() {
        return Prefs.getString(PrefsCons.ACCESS_TOKEN, "");
    }

    public void saveId(String id) {
        Prefs.putString(PrefsCons.ID, id);
    }

    public void removeId() {
        Prefs.remove(PrefsCons.ID);
    }

    public String getId() {
        return Prefs.getString(PrefsCons.ID, "");
    }

    public void saveUser(User user) {
        if (user != null) {
            String id = user.getId();
            saveId(id);
            Gson gson = new Gson();
            Prefs.putString(PrefsCons.USER_PREFIX.concat(user.getId()), gson.toJson(user));
        }
    }

    public User getUser(User user) {
        Gson gson = new Gson();
        return gson.fromJson(Prefs.getString(PrefsCons.USER_PREFIX.concat(user.getId()), null), User.class);
    }

    public User getUser(String id) {
        Gson gson = new Gson();
        return gson.fromJson(Prefs.getString(PrefsCons.USER_PREFIX.concat(id), null), User.class);
    }

    public User getUser() {
        Gson gson = new Gson();
        return gson.fromJson(Prefs.getString(PrefsCons.USER_PREFIX.concat(getId()), null), User.class);
    }
}
