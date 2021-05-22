package com.example.myapp.presentation.view.model;

import java.util.List;

public class RestRickandMortyResponse {

    private Integer count;
    private String pages;
    private String next;
    private String prev;
    private List<Rickandmorty> results;

    public Integer getCount() {
        return count;
    }
    public String getPages() {
        return pages;
    }
    public String getNext() {
        return next;
    }
    public String getPrev() {
        return prev;
    }
    public List<Rickandmorty> getResults() {
        return results;
    }
}
