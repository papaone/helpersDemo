package com.example.helpersDemo;

import com.example.helpersDemo.pojo_models.user.UpdateUserBody;
import com.example.helpersDemo.pojo_models.user.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {

    @GET("users/2")
    Call<User> getUser();

    @FormUrlEncoded
    @POST("users/2")
    Call<UpdateUserBody> updateUser(@Field("name") String name, @Field("job") String job);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @FormUrlEncoded
    @PATCH("users/2")
    Call<UpdateUserBody> updateUserWithPatch(@Field("name") String name, @Field("job") String job);
}