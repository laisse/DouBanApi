package com.example.chaowang.imageapplication.presenter;

public interface IPresenter {
    void getInTheaters(int start, int count);

    void getComingSoon(int start, int count);

    void getTop250(int start, int count);

    void getUSBox(String apikey);

    void getWeekly(String apikey);

    void getNewMovies(String apikey);

    void getMoviePhoto(String path, String apikey, int start, int count);

}
