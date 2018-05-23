package com.example.chaowang.imageapplication.view;



public interface IView<T> {

    void onSuccess(T data);

    void onFailed(Throwable e);

    void hideLoad();

    void showLoad();
}
