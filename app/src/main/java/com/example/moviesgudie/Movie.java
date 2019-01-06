package com.example.moviesgudie;

public class Movie {
    private String name;
    private int imageId;
    private String directorName;
    private int years;
    private String staring;
    private float db_Rates;
    private float imdb_Rates;

    public Movie(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public Movie(String name, int imageId, String directorName, int years, String staring, float db_Rates, float imdb_Rates){
        this.db_Rates = db_Rates;
        this.name = name;
        this.imageId=imageId;
        this.directorName = directorName;
        this.imdb_Rates = imdb_Rates;
        this.years = years;
        this.staring = staring;
    }

    public String getDirectorName() { return directorName; }

    public int getYears() { return years; }

    public String getStaring() { return staring; }

    public float getDb_Rates() { return db_Rates; }

    public float getImdb_Rates() { return imdb_Rates; }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }


}
