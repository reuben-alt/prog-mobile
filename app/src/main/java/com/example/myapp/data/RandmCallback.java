package com.example.myapp.data;

import com.example.myapp.presentation.view.model.Rickandmorty;

import java.util.List;

public interface RandmCallback {
     void onSuccess(List<Rickandmorty> response);
     void onFailed();
}
