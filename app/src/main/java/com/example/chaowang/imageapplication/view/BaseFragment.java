package com.example.chaowang.imageapplication.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaowang.imageapplication.ImageViewActivity;
import com.example.chaowang.imageapplication.MovieItemActivity;
import com.example.chaowang.imageapplication.R;
import com.example.chaowang.imageapplication.adapter.MoviesDataAdapter;
import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.presenter.MovieDataPresenter;
import com.example.chaowang.imageapplication.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements IView{
    private StringBuilder mSB = null;
    private GridLayoutManager mLayoutManager = null;

    public int retryCount = 0;
    public int start = 0;
    public int count = 20;

    public RecyclerView mRecyclerView;
    public ProgressBar mProgressBar;
    public TextView mTextView;
    public Button mButton;

    public MoviesDataAdapter mAdapter;
    public MovieDataPresenter mMovieDataPresenter;
    public List<MovieDataBean.SubjectsBean> mList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieDataPresenter = new MovieDataPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recycleview_fm);
        mProgressBar = (ProgressBar)view.findViewById(R.id.id_progressbar_fm);
        mTextView = (TextView)view.findViewById(R.id.id_textview);
        mButton = (Button) view.findViewById(R.id.id_button);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MoviesDataAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        addViewClickListener();

        loadMoreData();
    }

    private void addViewClickListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    int lastPosition = mLayoutManager.findLastVisibleItemPosition();
                    if (lastPosition >= mLayoutManager.getItemCount() - 1)
                    {
                        loadMoreData();
                    }
                }
            }
        });

        mAdapter.setOnItemClickListener(new MoviesDataAdapter.OnItemClickInterface() {
            @Override
            public void onItemClick(View view, int position) {

                MovieDataBean.SubjectsBean data = mList.get(position);

                DataUtils.getInstance().setId(data.getId());
                DataUtils.getInstance().setTitle(data.getOriginal_title());
                DataUtils.getInstance().setImageUrl(data.getImages().getMedium());
                DataUtils.getInstance().setYear(data.getYear());
                DataUtils.getInstance().setSubType(data.getSubtype());

                if (mSB == null)
                {
                    mSB = new StringBuilder();
                }
                mSB.setLength(0);

                for (int i = 0; i < data.getDirectors().size(); i++)
                {
                    mSB.append(data.getDirectors().get(i).getName());
                    mSB.append(" ");
                }
                DataUtils.getInstance().setDirector(mSB.toString());
                mSB.setLength(0);

                for (int i = 0; i < data.getCasts().size(); i++)
                {
                    mSB.append(data.getCasts().get(i).getName());
                    mSB.append(" ");
                }
                DataUtils.getInstance().setCasts(mSB.toString());

                showDetailScreen();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startImageView();
            }
        });
    }

    private void showDetailScreen() {
        Intent intent = new Intent(getActivity(), MovieItemActivity.class);
        startActivity(intent);
    }

    public abstract void loadMoreData();

    public void startImageView() {
        Intent intent = new Intent(getActivity(), ImageViewActivity.class);
        startActivity(intent);
    }


    @Override
    public void onSuccess(Object object) {
        MovieDataBean data = (MovieDataBean)object;
        if (data.getSubjects().size() == 0) {
            Toast.makeText(getActivity(), R.string.load_data_done, Toast.LENGTH_SHORT).show();
        }
        start += count;

        mList.addAll(data.getSubjects());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Throwable e) {
        Toast.makeText(getActivity(), R.string.load_data_error, Toast.LENGTH_SHORT).show();
        hideLoad();

        tryAgain();
    }

    private void tryAgain() {

        if (retryCount <= 3)
        {
            retryCount++;
            mTextView.setText(R.string.read_data_error);
            mTextView.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.VISIBLE);
        }
        else
        {
            mTextView.setText(R.string.read_data_counted);
            mTextView.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoad() {
        if (mProgressBar != null)
        {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoad() {
        if (mProgressBar != null)
        {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        mTextView.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
    }
}
