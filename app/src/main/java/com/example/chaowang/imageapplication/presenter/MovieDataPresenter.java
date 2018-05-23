package com.example.chaowang.imageapplication.presenter;


import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.model.MovieDataModel;
import com.example.chaowang.imageapplication.view.IView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDataPresenter implements IPresenter, OnGetDataListener {

    public IView mView;
    public MovieDataModel mMovieDataModel;
    public MovieDataPresenter(IView iView)
    {
        this.mView = iView;
        mMovieDataModel = new MovieDataModel(this);
    }

    @Override
    public void onSuccess(Observable observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDataBean>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFailed(e);
                    }

                    @Override
                    public void onNext(MovieDataBean data) {

                        mView.onSuccess(data);

                    }
                });
    }

    @Override
    public void onError() {

    }

    @Override
    public void getInTheaters(int start, int count) {
        mView.showLoad();
        mMovieDataModel.getInTheaters(start, count);
    }

    @Override
    public void getComingSoon(int start, int count) {
        mView.showLoad();
        mMovieDataModel.getComingSoon(start, count);
    }

    @Override
    public void getTop250(int start, int count) {
        mView.showLoad();
        mMovieDataModel.getTop250(start, count);
    }

    @Override
    public void getUSBox(String apikey) {
        mView.showLoad();
        mMovieDataModel.getUSBox(apikey);
    }

    @Override
    public void getWeekly(String apikey) {
        mView.showLoad();
        mMovieDataModel.getWeekly(apikey);
    }

    @Override
    public void getNewMovies(String apikey) {
        mView.showLoad();
        mMovieDataModel.getNewMovies(apikey);
    }

    @Override
    public void getMoviePhoto(String path, String apikey, int start, int count) {

    }

    public void clear()
    {
        mView = null;
        mMovieDataModel = null;
    }

}
