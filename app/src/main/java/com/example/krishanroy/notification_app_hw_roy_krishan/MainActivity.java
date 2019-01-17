package com.example.krishanroy.notification_app_hw_roy_krishan;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public List<Hit> hits;
    RecyclerView recyclerView;
    private static final String TAG = "onResponse";
    public SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_KEY = "import com.squareup.picasso.Picasso";
    public static final String SHARED_PREF_IMAGE_KEY = "image key";
    public static final String SHARED_PREF_USER_NAME_KEY = "user name key";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.image_recyclerview);
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_KEY, MODE_PRIVATE);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        GetImageApi getImageApi = retrofit.create(GetImageApi.class);
        Call<Pixabey> pixabeyCall = getImageApi.getPixabey("11260322-81f2eff70199283f574ff0e68");
        Log.d(TAG, "onCreate: " + pixabeyCall.request());
        pixabeyCall.enqueue(new Callback<Pixabey>() {
            @Override
            public void onResponse(Call<Pixabey> call, Response<Pixabey> response) {
                //Command+option+V
                hits = response.body().getHits();

                Log.d(TAG, "onResponse: " + hits);
                ImageViewAdapter imageViewAdapter = new ImageViewAdapter(hits);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setAdapter(imageViewAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<Pixabey> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });

    }
}
