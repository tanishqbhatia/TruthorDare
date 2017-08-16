package com.tanishqbhatia.truthordare.abstracts;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.interfaces.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harsh_jatinder on 16-08-2017.
 */

public abstract class Request<T> {

    private Server server;
    private Call<T> call;
    private Activity activity;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    public Request(Server server, Call<T> call, Activity activity) {
        this.server = server;
        this.call = call;
        this.activity = activity;
        createLoadingView();
        request(activity);
    }

    private void createLoadingView() {
        alertDialog = new AlertDialog(activity.getBaseContext());
        builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.create();
    }

    private void request(final Context context) {
        showLoading();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Toast.makeText(context, "Response received..", Toast.LENGTH_SHORT).show();
                onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Toast.makeText(context, "Failed..", Toast.LENGTH_SHORT).show();
                onFailure(call, t);
            }
        });
    }

    private void showLoading() {
        alertDialog.show();
    }

    private void hideLoading() {
        alertDialog.dismiss();
    }

    public abstract void onResponse(Call<T> call, Response<T> response);

    public abstract void onFailure(Call<T> call, Throwable t);


}
