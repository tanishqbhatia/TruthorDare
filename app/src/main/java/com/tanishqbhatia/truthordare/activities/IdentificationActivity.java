package com.tanishqbhatia.truthordare.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.tanishqbhatia.instagramauthorization.engine.InstagramEngine;
import com.tanishqbhatia.instagramauthorization.exceptions.InstagramException;
import com.tanishqbhatia.instagramauthorization.interfaces.InstagramAPIResponseCallback;
import com.tanishqbhatia.instagramauthorization.objects.IGPagInfo;
import com.tanishqbhatia.instagramauthorization.objects.IGUser;
import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.abstracts.Identify;
import com.tanishqbhatia.truthordare.abstracts.Request;
import com.tanishqbhatia.truthordare.models.ServerResponse;
import com.tanishqbhatia.truthordare.models.User;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;
import com.tanishqbhatia.truthordare.utils.dialogs.Loading;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.PrefsMethods;
import com.tanishqbhatia.truthordare.utils.toast.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class IdentificationActivity extends Identify {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.continueBtn)
    Button continueBtn;
    private Loading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Methods.init(this);
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.continueBtn)
    public void onContinueBtnClicked() {
        disable();
        super.login();
    }

    private void disable() {
        Methods.changeBackgroundColor(continueBtn, ColorCons.GREY_900, 0);
        Methods.disable(continueBtn);
    }

    private void enable() {
        Methods.enable(continueBtn);
        Methods.changeBackgroundColor(continueBtn, ColorCons.BLUE_500, 0);
    }

    @Override
    public void onReceiveAccessToken(String accessToken) {
        createLoading();
        getOtherDetails(accessToken);
    }

    private void createLoading() {
        loading = new Loading(this);
    }

    private void showLoading() {
        loading.show();
    }

    private void hideLoading() {
        loading.dismiss();
    }

    private void getOtherDetails(String accessToken) {
        showLoading();
        InstagramEngine.getInstance(this).getUserDetails(instagramAPIResponseCallback);
    }

    InstagramAPIResponseCallback<IGUser> instagramAPIResponseCallback = new InstagramAPIResponseCallback<IGUser>() {
        @Override
        public void onResponse(IGUser responseObject, IGPagInfo pageInfo) {
            hideLoading();
            Methods.showLog("IdentificationActivity", "onResponse()", responseObject.getAccessToken(),
                    responseObject.getId(),
                    responseObject.getUsername(),
                    responseObject.getFullName(),
                    String.valueOf(responseObject.getMediaCount()),
                    String.valueOf(responseObject.getFollowsCount()),
                    String.valueOf(responseObject.getFollowedByCount()),
                    responseObject.getBio(),
                    responseObject.getWebsite(),
                    responseObject.getProfilePictureURL());
            String accessToken = responseObject.getAccessToken();
            if(accessToken == null)
                accessToken = new PrefsMethods().getAccessToken();
            String id = responseObject.getId();
            String username = responseObject.getUsername();
            String fullName = responseObject.getFullName();
            String bio = responseObject.getBio();
            String profilePictureUrl = responseObject.getProfilePictureURL();
            saveUserOnServer(accessToken, id, username, fullName, Methods.encode(bio), profilePictureUrl);
        }

        @Override
        public void onFailure(InstagramException exception) {
            hideLoading();
            new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
            exception.printStackTrace();
        }
    };

    private void saveUserOnServer(final String accessToken, final String id, final String username, final String fullName, final String bio, final String profilePictureUrl) {
        new Request<ServerResponse>(App.get().getServer().saveUserOnServer(accessToken, id, username, fullName, bio, profilePictureUrl), this) {
            @Override
            public void onRequestCompleted() {
                enable();
            }

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Methods.showLog("IdentificationActivity", "onResponse()", response.body().getResponse().toString());
                if (response.body().getResponse()) {
                    User user = new User();
                    user.setId(id);
                    user.setAccessToken(accessToken);
                    user.setUsername(username);
                    user.setFullName(fullName);
                    user.setBio(bio);
                    user.setProfilePictureURL(profilePictureUrl);
                    new PrefsMethods().saveUser(user);
                    Methods.launchOnly(MainActivity.class);
                } else
                    new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                new Toast().colorRed().priorityHigh().message("Unable to connect, please try again later.").show();
                t.printStackTrace();
            }
        };
    }
}