package com.example.chaowang.imageapplication.presenter;

import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.view.IView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviePhotoPresenter extends MovieDataPresenter {

    public MoviePhotoPresenter(IView iView) {
        super(iView);
    }

    @Override
    public void onSuccess(Observable observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviePhotoBean>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFailed(e);
                    }

                    @Override
                    public void onNext(MoviePhotoBean data) {

                        mView.onSuccess(data);

                    }
                });
    }

    @Override
    public void getMoviePhoto(String path, String apikey, int start, int count) {
        mMovieDataModel.getMoviePhoto(path, apikey, start, count);
    }

}
