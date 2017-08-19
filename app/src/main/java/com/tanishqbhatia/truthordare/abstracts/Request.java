package com.tanishqbhatia.truthordare.abstracts;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.tanishqbhatia.truthordare.utils.dialogs.Loading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harsh_jatinder on 16-08-2017.
 */

public abstract class Request<T> {

    private Call<T> call;
    private Activity activity;
    private Loading loading;

    protected Request(Call<T> call, Activity activity) {
        this.call = call;
        this.activity = activity;
        createLoading();
        request();
    }

    private void createLoading() {
        loading = new Loading(activity);
    }

    private void request() {
        showLoading();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                hideLoading();
                Request.this.onRequestCompleted();
                Request.this.onResponse(call, response);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                hideLoading();
                Request.this.onRequestCompleted();
                Request.this.onFailure(call, t);
            }
        });
    }

    private void showLoading() {
        loading.show();
    }

    private void hideLoading() {
        loading.dismiss();
    }

    public abstract void onRequestCompleted();

    public abstract void onResponse(Call<T> call, Response<T> response);

    public abstract void onFailure(Call<T> call, Throwable t);
}
