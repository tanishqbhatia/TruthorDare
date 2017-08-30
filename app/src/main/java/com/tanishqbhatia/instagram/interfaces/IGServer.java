package com.tanishqbhatia.instagram.interfaces;

import com.tanishqbhatia.instagram.models.IGAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tanishq Bhatia on 30-08-2017 at 11:01.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public interface IGServer {
    @GET("users/self")
    Call<IGAPIResponse> getUser(@Query("access_token") String accessToken);
}