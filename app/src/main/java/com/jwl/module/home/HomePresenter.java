package com.jwl.module.home;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view){
        mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void start() {

    }

}
