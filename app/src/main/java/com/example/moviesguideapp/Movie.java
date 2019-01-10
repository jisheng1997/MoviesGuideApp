package com.example.moviesguideapp;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id_movie;
    private int movie_pic;
    private String movie_type;
    private String name;
    private String director;
    private String staring;
    private int years;
    private String description;
    private float rating_douban;
    private float rating_IMDB;

    Movie(String name, int imageId){
        this.name = name;
        this.movie_pic = imageId;
    }

    Movie(){}

    public Movie(int movieId, int imageId, String movieType, String name, String directorName, String staring, int years, String description, float db_Rates, float imdb_Rates){
        this.id_movie = movieId;
        this.movie_pic = imageId;
        this.movie_type = movieType;
        this.name = name;
        this.director = directorName;
        this.staring = staring;
        this.years = years;
        this.description =description;
        this.rating_douban = db_Rates;
        this.rating_IMDB = imdb_Rates;
    }

    public int getId_movie() {
        return id_movie;
    }

    public int getMovie_pic() {
        return movie_pic;
    }

    public String getMovietype() {
        return movie_type;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getStaring() {
        return staring;
    }

    public int getYears() {
        return years;
    }

    public String getDescription() {
        return description;
    }

    public float getRating_douban() {
        return rating_douban;
    }

    public float getRating_IMDB() {
        return rating_IMDB;
    }

}