package com.example.drawnav;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {
    private String title;
    private String poster_path;
    private String original_title;
    private String release_date;
    private String original_language;
    private String overview;

    public Movie() {
    }

    public Movie(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;

    }

    public Movie(String original_title, String release_date, String original_language, String overview) {
        this.original_title = original_title;
        this.release_date = release_date;
        this.original_language = original_language;
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Movie(String title) {
        this.title = title;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        original_title = in.readString();
        release_date = in.readString();
        original_language = in.readString();
        overview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(release_date);
        dest.writeString(original_language);
        dest.writeString(overview);
    }
}
