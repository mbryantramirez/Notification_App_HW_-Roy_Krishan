package com.example.krishanroy.notification_app_hw_roy_krishan;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.krishanroy.notification_app_hw_roy_krishan.controller.ImageViewAdapter;
import com.example.krishanroy.notification_app_hw_roy_krishan.model.Hit;
import com.example.krishanroy.notification_app_hw_roy_krishan.model.Pixabey;
import com.example.krishanroy.notification_app_hw_roy_krishan.network.GetImageApi;
import com.example.krishanroy.notification_app_hw_roy_krishan.network.RetrofitSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    /**
     * does this need to be public
     */
    public List<Hit> hits;
    /**
     * should be made private
     */
    RecyclerView recyclerView;
    private static final String TAG = "onResponse";
    public SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_KEY = "import com.squareup.picasso.Picasso";
    /**
     *  you should remove any unused field variables especially if theyre public
     */
    public static final String SHARED_PREF_IMAGE_KEY = "image key";
    public static final String SHARED_PREF_USER_NAME_KEY = "user name key";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.image_recyclerview);
        /**
         * this shared prefrences is never used
         */
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_KEY, MODE_PRIVATE);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        GetImageApi getImageApi = retrofit.create(GetImageApi.class);
        Call<Pixabey> pixabeyCall = getImageApi.getPixabey("11260322-81f2eff70199283f574ff0e68", "water+lilies");
        Log.d(TAG, "onCreate: " + pixabeyCall.request());
        pixabeyCall.enqueue(new Callback<Pixabey>() {
            @Override
            public void onResponse(Call<Pixabey> call, Response<Pixabey> response) {
                /**
                 * Dont ingore the linter, Account for the possibility that this response can be null otherwise your
                 * app might crash
                 */
                hits = response.body().getHits();
                Log.d(TAG, "onResponse: " + hits);
                ImageViewAdapter imageViewAdapter = new ImageViewAdapter(hits);
                /**
                 * Cool I like that you thought about extracting variables into resources
                 * but this isnt really necessary here thats more for text than for gridlayoutcolumn count
                 */
                int gridLayoutColumnCount;
                gridLayoutColumnCount = getResources().getInteger(R.integer.grid_column_count);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), gridLayoutColumnCount);
                recyclerView.setAdapter(imageViewAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(Call<Pixabey> call, Throwable t) {
                /**
                 * Great!! logging failure is important
                 */
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });

    }
}
