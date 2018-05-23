package com.example.chaowang.imageapplication.view;

import com.example.chaowang.imageapplication.R;


public class WeeklyFragment extends BaseFragment {


    @Override
    public void loadMoreData() {
        mMovieDataPresenter.getWeekly(getActivity().getString(R.string.apikey));
    }

}
