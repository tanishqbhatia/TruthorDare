package com.tanishqbhatia.truthordare.interfaces;

import com.tanishqbhatia.truthordare.models.ServerResponse;
import com.tanishqbhatia.truthordare.utils.constants.PrefsCons;
import com.tanishqbhatia.truthordare.utils.constants.WebsiteCons;

import retrofit2.Call;
import retrofit2.http.Field;
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
    Call<ServerResponse> saveUserOnServer(@Field(PrefsCons.ACCESS_TOKEN) String accessToken,
                                          @Field(PrefsCons.ID) String id,
                                          @Field(PrefsCons.USERNAME) String username,
                                          @Field(PrefsCons.FULL_NAME) String fullName,
                                          @Field(PrefsCons.BIO) String bio,
                                          @Field(PrefsCons.PROFILE_PICTURE_URL) String profilePictureUrl);
}
