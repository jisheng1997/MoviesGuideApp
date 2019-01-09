package com.example.moviesgudieapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Movie implements Parcelable ,Serializable {
    private String name;
    private int imageId;
    private String directorName;
    private int years;
    private String staring;
    private float db_Rates;
    private float imdb_Rates;
    private int movieId;

    public Movie(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public Movie(){

    }

    public Movie(String name, int imageId, String directorName, int years, String staring, float db_Rates, float imdb_Rates, int movieID){
        this.db_Rates = db_Rates;
        this.name = name;
        this.imageId=imageId;
        this.directorName = directorName;
        this.imdb_Rates = imdb_Rates;
        this.years = years;
        this.staring = staring;
        this.movieId = movieID;
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

    public static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            Movie mMovie = new Movie();
            mMovie.name = source.readString();
            mMovie.staring = source.readString();
            mMovie.imageId = source.readInt();
            return mMovie;
        }
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(staring);
        parcel.writeInt(imageId);
    }
}
