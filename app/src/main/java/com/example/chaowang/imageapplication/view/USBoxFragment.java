package com.example.chaowang.imageapplication.view;


import com.example.chaowang.imageapplication.R;

public class USBoxFragment extends BaseFragment {


    @Override
    public void loadMoreData() {
        mMovieDataPresenter.getUSBox(getActivity().getString(R.string.apikey));
    }

}
