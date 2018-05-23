package com.example.chaowang.imageapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.chaowang.imageapplication.adapter.MyAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImageViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    private NestedScrollView mScrollView;
    private SimpleDraweeView mSimpleDraweeView;

    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        createData();

        mSimpleDraweeView = (SimpleDraweeView)findViewById(R.id.id_simpleview_detail);
        mScrollView = (NestedScrollView)findViewById(R.id.id_scrollview);

        mRecyclerView = (RecyclerView)findViewById(R.id.id_recycleview_scroll);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setAutoMeasureEnabled(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        myAdapter = new MyAdapter(this, mData);
        mRecyclerView.setAdapter(myAdapter);

    }

    private void createData()
    {
        mData = new ArrayList<String>();
        for (int i = 0; i < 30; i++)
        {
            mData.add(" " + i);
        }
    }
}
