package com.tanishqbhatia.truthordare.abstracts;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.models.ServerResponse;
import com.tanishqbhatia.truthordare.models.User;
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
        new Request<ServerResponse>(App.get().getServer().getUserHeader(new PrefsMethods().getId(), new PrefsMethods().getAccessToken()), App.get().getCurrentActivity()) {
            @Override
            public void onRequestCompleted() {
            }

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response != null && response.body() != null) {
                    Methods.showLog("IdentificationActivity", "onResponse()", String.valueOf(response.body().getResponse()));
                    if (response.body().getResponse()) {
                        User user = response.body().getUser();
                        if (user != null) {
                            Methods.showLog("ServerRequest", "onResponse()", "getUserHeader()");
                            EventBus.getDefault().postSticky(user);
                        }
                        else
                            new Toast().colorRed().priorityHigh().message("Unable to fetch the details, please try again later.").show();
                    } else
                        new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                t.printStackTrace();
            }
        };
    }
}
