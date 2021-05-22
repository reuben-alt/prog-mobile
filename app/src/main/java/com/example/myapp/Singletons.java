package com.example.myapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapp.data.RandmApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsoninstance;
    private static RandmApi randmApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsoninstance == null){
            gsoninstance= new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsoninstance;
    }
    public static RandmApi getRandmApi(){
        if(randmApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            randmApiInstance = retrofit.create(RandmApi.class);

        } return randmApiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if(sharedPreferencesInstance == null){
sharedPreferencesInstance=context.getSharedPreferences("R&M_application", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
