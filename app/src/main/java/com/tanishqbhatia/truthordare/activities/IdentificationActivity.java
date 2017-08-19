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
import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class IdentificationActivity extends Identify {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.continueBtn)
    Button continueBtn;

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
    public void onIdentified(String accessToken) {
        saveIdentificationOnServer(accessToken);
    }

    private void saveIdentificationOnServer(String accessToken) {
        InstagramEngine.getInstance(this).getUserDetails(instagramAPIResponseCallback);
        new Request<ServerResponse>(App.get().getServer().isIdentified(accessToken), this) {
            @Override
            public void onRequestCompleted() {
                enable();
            }

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Methods.showLog("IdentificationActivity", "onResponse()", response.body().getResponse().toString());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                t.printStackTrace();
            }
        };
    }

    InstagramAPIResponseCallback<IGUser> instagramAPIResponseCallback = new InstagramAPIResponseCallback<IGUser>() {
        @Override
        public void onResponse(IGUser responseObject, IGPagInfo pageInfo) {
            Methods.showLog("IdentificationActivity", "onResponse()", responseObject.getAccessToken(),
                    responseObject.getBio(),
                    responseObject.getFullName(),
                    responseObject.getId(),
                    responseObject.getProfilePictureURL(),
                    responseObject.getUsername(),
                    responseObject.getWebsite(),
                    String.valueOf(responseObject.getFollowedByCount()),
                    String.valueOf(responseObject.getFollowsCount()),
                    String.valueOf(responseObject.getMediaCount()));
        }

        @Override
        public void onFailure(InstagramException exception) {

        }
    };
}