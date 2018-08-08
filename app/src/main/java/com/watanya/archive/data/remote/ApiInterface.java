package com.watanya.archive.data.remote;


import com.watanya.archive.data.model.ApiDefaultResponse;
import com.watanya.archive.data.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * APIs class
 */
public interface ApiInterface {

    @POST(ApiClient.SERVER_APIS_EXTENSION_URL + "login")
    Observable<User> login(@HeaderMap HashMap<String, String> headers, @Body User user);

    @POST(ApiClient.SERVER_APIS_EXTENSION_URL + "login/facebook")
    Observable<User> facebookLogin(@HeaderMap HashMap<String, String> headers, @Body HashMap<String, String> body);

    @POST(ApiClient.SERVER_APIS_EXTENSION_URL + "register")
    Observable<User> signup(@HeaderMap HashMap<String, String> headers, @Body User user);

    Observable<String> updatePhoto(@HeaderMap HashMap<String, String> headers, @Body Byte[] image);
    //TODO: custom header content-type and detect body type
    @PUT(ApiClient.SERVER_APIS_EXTENSION_URL + "user/photo")

    @GET(ApiClient.SERVER_APIS_EXTENSION_URL + "user/profile")
    Observable<User> getProfile(@HeaderMap HashMap<String, String> headers);

    @PUT(ApiClient.SERVER_APIS_EXTENSION_URL + "user/profile")
    Observable<User> updateProfile(@HeaderMap HashMap<String, String> headers, @Body User user);

    @POST(ApiClient.SERVER_APIS_EXTENSION_URL + "password/forget")
    Observable<ApiDefaultResponse> forgetPassword(@HeaderMap HashMap<String, String> headers, @Body User user);

    @PUT(ApiClient.SERVER_APIS_EXTENSION_URL + "user/change_password")
    Observable<ApiDefaultResponse> changePassword(@HeaderMap HashMap<String, String> headers, @Body HashMap<String, String> body);

}
