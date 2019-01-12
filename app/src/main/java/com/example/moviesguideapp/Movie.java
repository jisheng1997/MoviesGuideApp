/**
 * project name:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:Movie
 * data:2019/1/10 15:19
 */

package com.example.moviesguideapp;

import java.io.Serializable;

/**
 * version:1.12
 * author:
 * date:2019/1/10 15:19
 * className:Movie
 */
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

    /**
     * Instantiates a new Movie.
     * @param name    the name
     * @param imageId the image id
     */
    Movie(String name, int imageId){
        this.name = name;
        this.movie_pic = imageId;
    }

    /**
     * Instantiates a new Movie.
     */
    Movie(){
    }

    /**
     * Instantiates a new Movie.
     * @param movieId      the movie id
     * @param imageId      the image id
     * @param movieType    the movie type
     * @param name         the name
     * @param directorName the director name
     * @param staring      the staring
     * @param year         the year
     * @param description  the description
     * @param db_Rates     the douban rates
     * @param imdb_Rates   the IMDB rates
     */
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

    /**
     * Gets id movie.
     * @return the id movie
     */
    public int getId_movie() {
        return id_movie;
    }

    /**
     * Gets movie pic.
     * @return the movie pic
     */
    public int getMovie_pic() {
        return movie_pic;
    }

    /**
     * Gets movietype.
     * @return the movietype
     */
    public String getMovietype() {
        return movie_type;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets director.
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets id movie.
     * @param id_movie the id movie
     */
    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    /**
     * Sets movie pic.
     * @param movie_pic the movie pic
     */
    public void setMovie_pic(int movie_pic) {
        this.movie_pic = movie_pic;
    }

    /**
     * Sets movie type.
     * @param movie_type the movie type
     */
    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets director.
     * @param director the director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets staring.
     * @param staring the staring
     */
    public void setStaring(String staring) {
        this.staring = staring;
    }

    /**
     * Sets year.
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets description.
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets rating douban.
     * @param rating_douban the rating douban
     */
    public void setRating_douban(float rating_douban) {
        this.rating_douban = rating_douban;
    }

    /**
     * Sets rating imdb.
     * @param rating_IMDB the rating imdb
     */
    public void setRating_IMDB(float rating_IMDB) {
        this.rating_IMDB = rating_IMDB;
    }

    /**
     * Gets staring.
     * @return the staring
     */
    public String getStaring() {
        return staring;
    }

    /**
     * Gets year.
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets rating douban.
     * @return the rating douban
     */
    public float getRating_douban() {
        return rating_douban;
    }

    /**
     * Gets rating imdb.
     * @return the rating imdb
     */
    public float getRating_IMDB() {
        return rating_IMDB;
    }

}