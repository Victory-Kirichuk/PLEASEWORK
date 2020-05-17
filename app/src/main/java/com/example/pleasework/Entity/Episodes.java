package com.example.pleasework.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Episodes implements Comparable<Episodes> {

    @SerializedName("seriesId")
    @Expose
    private Integer seriesId;
    @SerializedName("Id")
    @Expose
    private Integer Id;


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("number")
    @Expose
    private Integer number;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("season")
    @Expose
    private Integer season;

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public int compareTo(Episodes episodes) {
        return date.compareTo(episodes.date);

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

}
