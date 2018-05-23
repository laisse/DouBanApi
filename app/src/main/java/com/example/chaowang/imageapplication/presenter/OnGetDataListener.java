package com.example.chaowang.imageapplication.presenter;

import com.example.chaowang.imageapplication.bean.MovieDataBean;

import rx.Observable;

public interface OnGetDataListener<T> {
    void onSuccess(Observable<T> observable);

    void onError();

}
