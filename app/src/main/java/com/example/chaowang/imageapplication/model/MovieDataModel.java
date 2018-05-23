package com.example.chaowang.imageapplication.model;

import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.bean.MovieItemBean;
import com.example.chaowang.imageapplication.presenter.OnGetDataListener;
import com.example.chaowang.imageapplication.utils.RetrofitUtils;

import rx.Observable;

public class MovieDataModel implements IModel {

    private OnGetDataListener mListener;
    private Observable<MovieDataBean> mObservable;

    public MovieDataModel(OnGetDataListener onGetDataListener)
    {
        this.mListener = onGetDataListener;
    }

    @Override
    public void getInTheaters(int start, int count) {
        mObservable = RetrofitUtils.getInstance().getApiService().getInTheaters(start, count);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }

    }

    @Override
    public void getComingSoon(int start, int count) {
        mObservable = RetrofitUtils.getInstance().getApiService().getComingSoon(start, count);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }
    }

    @Override
    public void getTop250(int start, int count) {
        mObservable = RetrofitUtils.getInstance().getApiService().getTop250(start, count);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }
    }

    @Override
    public void getUSBox(String apikey) {
        mObservable = RetrofitUtils.getInstance().getApiService().getUSBox(apikey);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }
    }

    @Override
    public void getWeekly(String apikey) {
        mObservable = RetrofitUtils.getInstance().getApiService().getWeekly(apikey);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }
    }

    @Override
    public void getNewMovies(String apikey) {
        mObservable = RetrofitUtils.getInstance().getApiService().getNewMovies(apikey);
        if (null != mListener)
        {
            mListener.onSuccess(mObservable);
        }
    }

    @Override
    public void getMoviePhoto(String path, String apikey, int start, int count) {
        Observable<MoviePhotoBean> observable = RetrofitUtils.getInstance().getApiService().getMoviePhoto(path, apikey, start, count);
        if (null != mListener)
        {
            mListener.onSuccess(observable);
        }
    }

    @Override
    public void getMovieInfo(String path, String apikey) {
        Observable<MovieItemBean> observable = RetrofitUtils.getInstance().getApiService().getMovieItem(path, apikey);
        if (null != mListener)
        {
            mListener.onSuccess(observable);
        }
    }
}
