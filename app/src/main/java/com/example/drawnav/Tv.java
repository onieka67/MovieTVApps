package com.example.drawnav;

import android.os.Parcel;
import android.os.Parcelable;

public class Tv implements Parcelable {
    private String name;
    private String poster_path;
    private String original_name;
    private String first_air_date;
    private String original_language;
    private String overview;

    public Tv() {
    }

    public Tv(String name, String poster_path, String original_name, String first_air_date, String original_language, String overview) {
        this.name = name;
        this.poster_path = poster_path;
        this.original_name = original_name;
        this.first_air_date = first_air_date;
        this.original_language = original_language;
        this.overview = overview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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

    protected Tv(Parcel in) {
        name = in.readString();
        poster_path = in.readString();
        original_name = in.readString();
        first_air_date = in.readString();
        original_language = in.readString();
        overview = in.readString();
    }

    public static final Creator<Tv> CREATOR = new Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel in) {
            return new Tv(in);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(poster_path);
        dest.writeString(original_name);
        dest.writeString(first_air_date);
        dest.writeString(original_language);
        dest.writeString(overview);
    }
}
