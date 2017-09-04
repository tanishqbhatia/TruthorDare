package com.tanishqbhatia.truthordare.utils.server;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.abstracts.Request;
import com.tanishqbhatia.truthordare.models.GetUser;
import com.tanishqbhatia.truthordare.models.UserPosts;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.PrefsMethods;
import com.tanishqbhatia.truthordare.utils.toast.Toast;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Tanishq Bhatia on 01-09-2017 at 10:36.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class ServerRequest {

    public void getUserHeader() {
        new Request<GetUser>(App.get().getServer().getUserHeader(new PrefsMethods().getId(), new PrefsMethods().getAccessToken()), App.get().getCurrentActivity()) {
            @Override
            public void onRequestCompleted() {
            }

            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                if (response != null && response.body() != null) {
                    Methods.showLog("IdentificationActivity", "onResponse()", response.body().getResponse());
                    if (response.body().getResponse()) {
                        GetUser getUser = response.body();
                        Methods.showLog("ServerRequest", "onResponse()", "getUserHeader()");
                        EventBus.getDefault().postSticky(getUser);
                    } else
                        new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                t.printStackTrace();
            }
        };
    }


    public void getUserPosts() {
        new Request<UserPosts>(App.get().getServer().getUserPosts(new PrefsMethods().getId(), new PrefsMethods().getAccessToken()), App.get().getCurrentActivity()) {
            @Override
            public void onRequestCompleted() {}

            @Override
            public void onResponse(Call<UserPosts> call, Response<UserPosts> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getResponse()) {
                        Methods.showLog("ServerRequest", "onResponse()", "getUserPosts()");
                        EventBus.getDefault().postSticky(response.body());
                    } else
                        new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                }
            }

            @Override
            public void onFailure(Call<UserPosts> call, Throwable t) {
                new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                t.printStackTrace();
            }
        };
    }
}
