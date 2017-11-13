package com.example.ashish.firebase.DataModel;


/**
 * Created by ashish on 3/11/17.
 */

public class Artists {

    String artistId;
    String artistName;
    String artistGenres;

    public Artists() {

    }

    public Artists(String artistId, String artistName, String artistGenres) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenres = artistGenres;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistGenres() {
        return artistGenres;
    }

    public void setArtistGenres(String artistGenres) {
        this.artistGenres = artistGenres;
    }

}
