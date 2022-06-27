package com.example.expandablerecyclerview;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApi {
    @FormUrlEncoded
    @POST("users")
    Call<DataModal> createPost(@Field("name") String name, @Field("job") String job);
    @POST("/api/users")
    Call<DataModal> getdata(@Body DataModal dataModal);


}
