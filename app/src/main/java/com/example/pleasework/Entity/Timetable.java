package com.example.pleasework.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Timetable {

    @SerializedName("seriesId")
    @Expose
    private Integer seriesId;

@SerializedName("episodeId")
@Expose
private Integer episodeId;

    @SerializedName("seriesName")
    @Expose
    private String seriesName;

    @SerializedName("episodesNumber")
    @Expose
    private Integer episodesNumber;

    @SerializedName("episodesSeason")
    @Expose
    private Integer episodesSeason;

    @SerializedName("episodesDate")
    @Expose
    private Date episodesDate;

    @SerializedName("episodeName")
    @Expose
    private String episodeName;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Integer getEpisodesNumber() {
        return episodesNumber;
    }

    public void setEpisodesNumber(Integer episodesNumber) {
        this.episodesNumber = episodesNumber;
    }

    public Integer getEpisodesSeason() {
        return episodesSeason;
    }

    public void setEpisodesSeason(Integer episodesSeason) {
        this.episodesSeason = episodesSeason;
    }

    public Date getEpisodesDate() {
        return episodesDate;
    }

    public void setEpisodesDate(Date episodesDate) {
        this.episodesDate = episodesDate;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }
}
