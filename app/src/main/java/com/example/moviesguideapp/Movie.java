package com.example.moviesguideapp;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id_movie;
    private int movie_pic;
    private String movie_type;
    private String name;
    private String director;
    private String staring;
    private int year;
    private String description;
    private float rating_douban;
    private float rating_IMDB;

    Movie(String name, int imageId){
        this.name = name;
        this.movie_pic = imageId;
    }

    Movie(){}

    public Movie(int movieId, int imageId, String movieType, String name, String directorName, String staring, int year, String description, float db_Rates, float imdb_Rates){
        this.id_movie = movieId;
        this.movie_pic = imageId;
        this.movie_type = movieType;
        this.name = name;
        this.director = directorName;
        this.staring = staring;
        this.year = year;
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

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public void setMovie_pic(int movie_pic) {
        this.movie_pic = movie_pic;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setStaring(String staring) {
        this.staring = staring;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating_douban(float rating_douban) {
        this.rating_douban = rating_douban;
    }

    public void setRating_IMDB(float rating_IMDB) {
        this.rating_IMDB = rating_IMDB;
    }

    public String getStaring() {
        return staring;
    }

    public int getYear() {
        return year;
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