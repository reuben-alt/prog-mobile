package com.example.myapp.presentation.view.controlleur;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.myapp.Constant;
import com.example.myapp.Singletons;
import com.example.myapp.presentation.view.MainActivity;
import com.example.myapp.presentation.view.model.RestRickandMortyResponse;
import com.example.myapp.presentation.view.model.Rickandmorty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainControlleur {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainControlleur(MainActivity mainActivity,Gson gson,SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Rickandmorty> rickandmortyList = getDataFromCache();

        if(rickandmortyList != null){
            view.showList(rickandmortyList);
        }else{
            makeApiCall();
        }

    }
    public void makeApiCall(){

        Call<RestRickandMortyResponse> call = Singletons.getRandmApi().getRickandMortyResponse();
        call.enqueue(new Callback<RestRickandMortyResponse>() {

            @Override
            public void onResponse(Call<RestRickandMortyResponse> call, Response<RestRickandMortyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Rickandmorty> rickandmortyList = response.body().getResults();
                    saveList(rickandmortyList);
                    view.showList(rickandmortyList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestRickandMortyResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Rickandmorty> rickandmortyList) {
        String jsonString = gson.toJson(rickandmortyList);
        sharedPreferences
                .edit()
                .putString("Constant.KEY_CHARACTER_LIST", jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(),"List Saved",Toast.LENGTH_SHORT).show();

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
    public void onItemClick(Rickandmorty rickandmorty){
        view.navigateToDetails(rickandmorty);
    }
}