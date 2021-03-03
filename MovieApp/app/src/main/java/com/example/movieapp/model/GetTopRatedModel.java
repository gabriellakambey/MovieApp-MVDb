package com.example.movieapp.model;

import java.util.List;

public class GetTopRatedModel {
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
