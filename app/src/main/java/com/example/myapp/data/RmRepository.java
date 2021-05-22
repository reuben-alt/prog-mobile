package com.example.myapp.data;

import android.content.SharedPreferences;

import com.example.myapp.Constant;
import com.example.myapp.presentation.view.model.RestRickandMortyResponse;
import com.example.myapp.presentation.view.model.Rickandmorty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RmRepository {
    private final Gson gson;
    private RandmApi randmApi;
    private SharedPreferences sharedPreferences;

    public RmRepository(RandmApi randmApi, SharedPreferences sharedPreferences, Gson gson) {
        this.randmApi = randmApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }
    public void getRickandMortyResponse(final RandmCallback callback){
        List<Rickandmorty> list = getDataFromCache();
        if (list != null){
callback.onSuccess(list);
        }else{
            randmApi.getRickandMortyResponse().enqueue(new Callback<RestRickandMortyResponse>() {
                @Override
                public void onResponse(Call<RestRickandMortyResponse> call, Response<RestRickandMortyResponse> response) {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body().getResults());
                    }else{
                        callback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<RestRickandMortyResponse> call, Throwable t) {
                    callback.onFailed();
                 }
            });
        }
    }

    private List<Rickandmorty> getDataFromCache() {
        String jsonCharacter = sharedPreferences.getString(Constant.KEY_CHARACTER_LIST, null);
        if (jsonCharacter == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Rickandmorty>>() {}.getType();
            return gson.fromJson(jsonCharacter, listType);
        }
    }
    private void saveList(List<Rickandmorty> rickandmortyList) {
        String jsonString = gson.toJson(rickandmortyList);
        sharedPreferences
                .edit()
                .putString("Constant.KEY_CHARACTER_LIST", jsonString)
                .apply();


    }
}
