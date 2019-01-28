package com.example.krishanroy.notification_app_hw_roy_krishan.network;

/**
 * you should remove unused imports
 */

import com.example.krishanroy.notification_app_hw_roy_krishan.model.Hit;
import com.example.krishanroy.notification_app_hw_roy_krishan.model.Pixabey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetImageApi {

    //https://pixabay.com/api/?key=11260322-81f2eff70199283f574ff0e68&q=water+lilies&image_type=photo&pretty=true
    @GET("api/")
    /**
     * @Query parameters should be marked by their keys
     * One would be q the next should be image_type the next should be pretty
     */
    Call<Pixabey> getPixabey(@Query("key") String apiKey , @Query("q") String imageKey);
}
