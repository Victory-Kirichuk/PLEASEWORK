package com.example.pleasework.Network;

import com.example.pleasework.Entity.Episodes;
import com.example.pleasework.Entity.Series;
import com.example.pleasework.Entity.Timetable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @GET("/series/getAll")
    public Call<List<Series>> getAllSeries();


    @GET("/series/getBySearch/{search}")
    public Call<List<Series>> getBySearch(@Path("search") String s);

    @GET("/series/getById/{id}")
    public Call<Series> getById(@Path("id") Integer id);

    @GET("/series/getMy/{email}")
    public Call<List<Series>> getMy(@Path("email") String email);

     @GET("/episodes/getEpById/{episodeId}")
     public Call<Timetable> getEpByEpisodeId(@Path("episodeId") Integer episodeId);

    @GET("/episodes/timeTable/{email}")
    public Call<List<Timetable>> getTimeTable(@Path("email") String email);
    @GET("/episodes/timeTableToday/{email}")
    public Call<List<Timetable>> getTimeTableToday(@Path("email") String email);

    @POST("/notification/add/{id}/{email}")
    public Call<Boolean> addNotification(@Path("id") Integer id, @Path("email") String email);

    @POST("/notification/remove/{id}/{email}")
    public Call<Boolean> removeNotification(@Path("id") Integer id, @Path("email") String email);

    @GET("/notification/isIn/{id}/{email}")
    public Call<Boolean> isInNotification(@Path("id") Integer id, @Path("email") String email);

}
