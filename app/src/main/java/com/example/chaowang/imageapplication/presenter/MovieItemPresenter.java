package com.example.chaowang.imageapplication.presenter;


import android.graphics.Movie;

import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.bean.MovieItemBean;
import com.example.chaowang.imageapplication.view.IView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieItemPresenter extends MovieDataPresenter {

    public MovieItemPresenter(IView view)
    {
        super(view);
    }

    public void getMovieInfo(String path, String apikey)
    {
        mMovieDataModel.getMovieInfo(path, apikey);
    }

    @Override
    public void onSuccess(Observable observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieItemBean>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFailed(e);
                    }

                    @Override
                    public void onNext(MovieItemBean data) {

                        mView.onSuccess(data);

                    }
                });
    }
}
