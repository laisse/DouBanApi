package com.example.chaowang.imageapplication.model;


public interface IModel {

    void getInTheaters(int start, int count);

    void getComingSoon(int start, int count);

    void getTop250(int start, int count);

    void getUSBox(String apikey);

    void getWeekly(String apikey);

    void getNewMovies(String apikey);

    void getMoviePhoto(String path, String apikey, int start, int count);

    void getMovieInfo(String path, String apikey);
}
