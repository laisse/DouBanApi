package com.example.chaowang.imageapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaowang.imageapplication.R;
import com.example.chaowang.imageapplication.bean.MoviePhotoBean;
import com.example.chaowang.imageapplication.bean.MovieItemBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    private Context context;
    private List<MovieItemBean.PhotoBean> mList;

    public MovieItemAdapter(List<MovieItemBean.PhotoBean> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.detail_item, parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MovieItemBean.PhotoBean data = mList.get(position);

        if (null != data.getImage())
        {
            holder.mDraweeView.setImageURI(Uri.parse(data.getImage()));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView mDraweeView;

        public ViewHolder(View itemView) {
            super(itemView);

            mDraweeView = (SimpleDraweeView)itemView.findViewById(R.id.id_detail_image);
        }
    }
}
