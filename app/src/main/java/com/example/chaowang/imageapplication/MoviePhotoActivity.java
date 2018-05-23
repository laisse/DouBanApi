package com.example.chaowang.imageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.chaowang.imageapplication.adapter.MoviePhotoAdapter;
import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.presenter.MoviePhotoPresenter;
import com.example.chaowang.imageapplication.utils.DataUtils;
import com.example.chaowang.imageapplication.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MoviePhotoActivity extends AppCompatActivity implements IView {


    private String path = "";
    private int start = 0;
    private int count = 20;

    private RecyclerView mRecyclerView;
    private MoviePhotoAdapter mAdapter;
    private SimpleDraweeView mSimpleDraweeView;
    private NestedScrollView mScrollView;
    private MoviePhotoPresenter mMoviePhotoPresenter;
    private GridLayoutManager mLayoutManager;
    private List<MoviePhotoBean.PhotosBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initView();
        initViewData();
    }


    private void initView() {

        mMoviePhotoPresenter = new MoviePhotoPresenter(this);
        mSimpleDraweeView = (SimpleDraweeView)findViewById(R.id.id_simpleview_detail);
        mScrollView = (NestedScrollView)findViewById(R.id.id_scrollview);
        mRecyclerView = (RecyclerView)findViewById(R.id.id_recycleview_scroll);
        mLayoutManager = new GridLayoutManager(this,2);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setAutoMeasureEnabled(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        mAdapter = new MoviePhotoAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initViewData() {

        mSimpleDraweeView.setImageURI(DataUtils.getInstance().getImageUrl());
        getSupportActionBar().setTitle(DataUtils.getInstance().getTitle());
        path = DataUtils.getInstance().getId();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    int lastPosition = mLayoutManager.findLastVisibleItemPosition();
                    if (lastPosition >= mLayoutManager.getItemCount() - 1)
                    {
                        loadMoviePhoto();
                    }
                }
            }
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    loadMoviePhoto();
                }
            }
        });

        loadMoviePhoto();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        path = DataUtils.getInstance().getId();
    }

    private void loadMoviePhoto() {
        Log.d("wangchao", "start is " + start );
        mMoviePhotoPresenter.getMoviePhoto(path, getString(R.string.apikey), start, count);
    }

    @Override
    public void onSuccess(Object object) {
        MoviePhotoBean data = (MoviePhotoBean)object;
        if (data.getPhotos().size() == 0) {
            Toast.makeText(this, R.string.load_data_done, Toast.LENGTH_SHORT).show();
        }
        start += count;
        mList.addAll(data.getPhotos());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Throwable e) {
        Toast.makeText(this, R.string.load_data_error, Toast.LENGTH_SHORT).show();
        hideLoad();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideLoad() {
    }

    @Override
    public void showLoad() {

    }
}
