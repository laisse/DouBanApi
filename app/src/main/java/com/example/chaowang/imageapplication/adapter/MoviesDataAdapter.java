package com.example.chaowang.imageapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chaowang.imageapplication.bean.MovieDataBean;
import com.example.chaowang.imageapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MoviesDataAdapter extends RecyclerView.Adapter<MoviesDataAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<MovieDataBean.SubjectsBean> mList;
    private OnItemClickInterface mOnItemClickListener ;

    public MoviesDataAdapter(List<MovieDataBean.SubjectsBean> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MovieDataBean.SubjectsBean data = mList.get(position);
        holder.mTitle.setText(data.getOriginal_title());

        if (null != data.getImages())
        {
            holder.mDraweeView.setImageURI(Uri.parse(data.getImages().getMedium()));
//            Glide.with(context).load(data.getImages().getMedium()).into(holder.mDraweeView);
        }

        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null)
        {
            mOnItemClickListener.onItemClick(view, (int)view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickInterface listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickInterface {
        void onItemClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView mTitle;
        SimpleDraweeView mDraweeView;

        public ViewHolder(View itemView) {
            super(itemView);

            mDraweeView = (SimpleDraweeView)itemView.findViewById(R.id.id_image);
            mTitle = (TextView)itemView.findViewById(R.id.id_orignal_title);
            cardView =(CardView)itemView.findViewById(R.id.cardview);

        }
    }

}
