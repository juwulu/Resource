package com.jwl.module.home;

import android.view.View;

import com.jwl.base.BasePresenter;
import com.jwl.base.BaseView;
import com.jwl.entity.GankData;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public interface HomeContract {
    interface Presenter extends BasePresenter{

    }

    interface View extends BaseView<Presenter>{

        void initTabLayout(String title);
        void initFragments(String title);
        void setDatas();

    }

    interface FragmentView extends BaseView<Presenter>{
        void loadGankDatas(android.view.View view);
    }
}
