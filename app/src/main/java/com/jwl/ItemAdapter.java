package com.jwl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jwl.entity.GankData;
import com.jwl.module.webview.WebViewActivity;
import com.jwl.module.webview.WebViewContract;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private String mCategory;
    private List<GankData.ResultsBean> mResults;
    private Context context;


    public ItemAdapter(Context context, List<GankData.ResultsBean> results,String category) {
        this.context = context;
        mResults = results;
        mCategory = category;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
            view=LayoutInflater.from(context).inflate(R.layout.item_image,null,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item, null, false);
        }
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GankData.ResultsBean resultsBean = mResults.get(position);
        if (!GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
            holder.mItemTimeTv.setText(resultsBean.getPublishedAt().substring(0,10));
            holder.mItemTitleTv.setText(resultsBean.getDesc());
            holder.mItemPublisherTv.setText(resultsBean.getWho());
            holder.mItemRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url",resultsBean.getUrl());
                    context.startActivity(intent);
                }
            });
            if (resultsBean.getImages() != null) {
                Glide.with(context).load(resultsBean.getImages().get(0)).into(holder.mItemImgIv);
            }else{
                holder.mItemImgIv.setVisibility(View.GONE);
            }
        }else{
            Glide.with(context).load(resultsBean.getUrl()).into(holder.mItemImageView);
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
        private ImageView mItemImageView;
        private RelativeLayout mItemRl;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(@NonNull final View itemView) {
            if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
                mItemImageView = ((ImageView) itemView.findViewById(R.id.item_img));
            }else{
                mItemTitleTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_title);
                mItemPublisherTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_publisher);
                mItemTimeTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_time);
                mItemImgIv = (AppCompatImageView) itemView.findViewById(R.id.iv_item_img);
                mItemRl = ((RelativeLayout) itemView.findViewById(R.id.rl_item));
            }

        }
    }
}
