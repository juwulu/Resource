package com.jwl.module.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwl.GlobalConfig;
import com.jwl.ItemAdapter;
import com.jwl.R;
import com.jwl.api.GankService;
import com.jwl.entity.GankData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class HomeFragment extends Fragment implements HomeContract.FragmentView {

    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ItemAdapter mItemAdapter;
    private int page=1;
    private GankService gankService;
    private LinearLayoutManager mLinearLayoutManager;
    private int mLastItemPosition;
    private List<GankData.ResultsBean> mResults;
    private String mCategory;
    private GridLayoutManager mGridLayoutManager;

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }



    public static HomeFragment newInstance(String category) {

        Bundle args = new Bundle();
        args.putString("category",category);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = getArguments().getString("category");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag_layout, null, false);
        if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
            view.setBackgroundColor(Color.BLACK);
            view.postInvalidate();
        }
        loadGankDatas(view);
        return view;
    }


    @Override
    public void loadGankDatas(final View view) {
        mRecyclerView = ((RecyclerView) view.findViewById(R.id.recyclerview));
        if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
            mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
        }else{
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gankService = retrofit.create(GankService.class);
        Call<GankData> gankData = gankService.getGankData(mCategory, 10, page);
        gankData.enqueue(new Callback<GankData>() {
            @Override
            public void onResponse(Call<GankData> call, Response<GankData> response) {
                mResults = response.body().getResults();
                mItemAdapter = new ItemAdapter(getActivity(), mResults,mCategory);
                mRecyclerView.setAdapter(mItemAdapter);

            }

            @Override
            public void onFailure(Call<GankData> call, Throwable t) {

            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //如果RecyclerView正在滑动且已经快要到达底部
                int itemCount=0;
                if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
                    itemCount = mGridLayoutManager.getItemCount();
                }else{
                    itemCount = mLinearLayoutManager.getItemCount();
                }
                if (RecyclerView.SCROLL_STATE_IDLE==newState&&mLastItemPosition+2>itemCount) {
                    Call<GankData> gankData1 = gankService.getGankData(mCategory, 10, ++page);
                    gankData1.enqueue(new Callback<GankData>() {
                        @Override
                        public void onResponse(Call<GankData> call, Response<GankData> response) {
                            mResults.addAll(response.body().getResults());
                            mRecyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    mItemAdapter.notifyDataSetChanged();
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<GankData> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (GlobalConfig.CATEGORY_FULI.equals(mCategory)) {
                    mLastItemPosition = mGridLayoutManager.findLastVisibleItemPosition();
                }else{
                    mLastItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                }

            }
        });
    }





}
