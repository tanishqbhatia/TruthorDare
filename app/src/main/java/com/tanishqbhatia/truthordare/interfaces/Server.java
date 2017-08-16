package com.tanishqbhatia.truthordare.interfaces;

import com.tanishqbhatia.truthordare.models.Response;
import com.tanishqbhatia.truthordare.utils.constants.WebsiteCons;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 14:04.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public interface Server {
    @FormUrlEncoded
    @POST(WebsiteCons.IDENTIFY)
    Call<Response> isIdentified();
}
