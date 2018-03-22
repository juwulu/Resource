package com.jwl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jwl.entity.GankData;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<GankData.ResultsBean> mResults;
    private Context context;

    public ItemAdapter(Context context, List<GankData.ResultsBean> results) {
        this.context = context;
        mResults = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GankData.ResultsBean resultsBean = mResults.get(position);
        holder.mItemTimeTv.setText(resultsBean.getPublishedAt().substring(0,10));
        holder.mItemTitleTv.setText(resultsBean.getDesc());
        holder.mItemPublisherTv.setText(resultsBean.getWho());
        if (resultsBean.getImages() != null) {
            Glide.with(context).load(resultsBean.getImages().get(0)).into(holder.mItemImgIv);
        }

    }

    @Override
    public int getItemCount() {
        return mResults == null ? 0 : mResults.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView mItemTitleTv;
        private AppCompatTextView mItemPublisherTv;
        private AppCompatTextView mItemTimeTv;
        private AppCompatImageView mItemImgIv;
        private LinearLayout mItemLl;
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(@NonNull final View itemView) {
            mItemTitleTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_title);
            mItemPublisherTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_publisher);
            mItemTimeTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_time);
            mItemImgIv = (AppCompatImageView) itemView.findViewById(R.id.iv_item_img);
            mItemLl = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }
}
