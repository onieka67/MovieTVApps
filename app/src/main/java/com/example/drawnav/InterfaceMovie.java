package com.example.drawnav;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceMovie {
    String JSONURL = "https://api.themoviedb.org/3/discover/";

    @GET("movie?api_key=69d43e3c3581674612df386f09ec1f2c&language=en-US")
    Call<String> getString();
}
