package com.netflix.api.models;

import java.time.LocalDate;
import java.util.Arrays;

public class MovieModel {

    private String showId;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private LocalDate date_added;
    private String release_year;
    private String rating;
    private String duration;
    private String[] listed_in;
    private String description;

    public MovieModel(String showId, String type, String title, String director, String[] cast, String country, LocalDate date_added, String release_year, String rating, String duration, String[] listed_in, String description) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
        this.description = description;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "showId='" + showId + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", cast=" + Arrays.toString(cast) +
                ", country='" + country + '\'' +
                ", date_added=" + date_added +
                ", release_year='" + release_year + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", listed_in=" + Arrays.toString(listed_in) +
                ", description='" + description + '\'' +
                '}';
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate_added() {
        return date_added;
    }

    public void setDate_added(LocalDate date_added) {
        this.date_added = date_added;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String[] getListed_in() {
        return listed_in;
    }

    public void setListed_in(String[] listed_in) {
        this.listed_in = listed_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
