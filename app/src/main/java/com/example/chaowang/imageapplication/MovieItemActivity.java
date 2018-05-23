package com.example.chaowang.imageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaowang.imageapplication.adapter.MovieItemAdapter;
import com.example.chaowang.imageapplication.adapter.MoviePhotoAdapter;
import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.bean.MovieItemBean;
import com.example.chaowang.imageapplication.presenter.MovieItemPresenter;
import com.example.chaowang.imageapplication.utils.DataUtils;
import com.example.chaowang.imageapplication.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MovieItemActivity extends AppCompatActivity implements IView {

    private RecyclerView mRecyclerView;
    private MovieItemAdapter mAdapter;
    private SimpleDraweeView mSimpleDraweeView;
    private TextView mTitle;
    private TextView mYear;
    private TextView mDirector;
    private TextView mCasts;
    private String path = "";

    private int start = 0;
    private int count = 0;
    private MovieItemPresenter mMovieItemPresenter;

    private List<MovieItemBean.PhotoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initView();
        initViewData();
    }

    private void initView() {

        mMovieItemPresenter = new MovieItemPresenter(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.id_recycleview_detail);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);



        mAdapter = new MovieItemAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mSimpleDraweeView = (SimpleDraweeView)findViewById(R.id.id_image);
        mTitle = (TextView)findViewById(R.id.id_detail_title);
        mDirector = (TextView)findViewById(R.id.id_detail_director);
        mYear = (TextView)findViewById(R.id.id_detail_year);
        mCasts = (TextView)findViewById(R.id.id_detail_cast);
    }

    private void initViewData() {

        mSimpleDraweeView.setImageURI(DataUtils.getInstance().getImageUrl());
        mTitle.setText(DataUtils.getInstance().getTitle());
        mDirector.setText(DataUtils.getInstance().getDirector());
        mYear.setText(DataUtils.getInstance().getYear() + " " + DataUtils.getInstance().getSubType());
        mCasts.setText(DataUtils.getInstance().getCasts());
        path = DataUtils.getInstance().getId();

        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMore();
            }
        });

        loadMoviePhoto();
    }

    private void showMore() {
        Intent intent = new Intent(this, MoviePhotoActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
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

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void loadMoviePhoto() {
        mMovieItemPresenter.getMovieInfo(path, getString(R.string.apikey));
    }

    @Override
    public void onSuccess(Object object) {
        MovieItemBean data = (MovieItemBean)object;
        if (data.getPhotos().size() == 0) {
            Toast.makeText(this, R.string.load_data_done, Toast.LENGTH_SHORT).show();
        }
        start += count;
        mList.addAll(data.getPhotos());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void showLoad() {

    }
}
