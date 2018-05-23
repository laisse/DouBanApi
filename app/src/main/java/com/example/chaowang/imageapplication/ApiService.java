package com.example.chaowang.imageapplication;

import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.bean.MovieItemBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("in_theaters")
    Observable<MovieDataBean> getInTheaters(@Query("start") int start, @Query("count") int count);

    @GET("coming_soon")
    Observable<MovieDataBean> getComingSoon(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<MovieDataBean> getTop250(@Query("start") int start, @Query("count") int count);

    @GET("us_box")
    Observable<MovieDataBean> getUSBox(@Query("apikey") String apikey);

    @GET("weekly")
    Observable<MovieDataBean> getWeekly(@Query("apikey") String apikey);

    @GET("new_movies")
    Observable<MovieDataBean> getNewMovies(@Query("apikey") String apikey);

    @GET("subject/{id}")
    Observable<MovieItemBean> getMovieItem(@Path("id") String moviesId, @Query("apikey") String apikey);

    @GET("subject/{id}/photos")
    Observable<MoviePhotoBean> getMoviePhoto(@Path("id") String moviesId, @Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);
}
