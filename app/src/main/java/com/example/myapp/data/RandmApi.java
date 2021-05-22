package com.example.myapp.data;

import com.example.myapp.presentation.view.model.RestRickandMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandmApi {
    @GET("/api/character/")
    Call<RestRickandMortyResponse> getRickandMortyResponse();

}
