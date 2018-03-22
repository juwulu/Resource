package com.jwl.module.webview;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class WebViewPresenter implements WebViewContract.Presenter {

    private final WebViewContract.View mView;

    public WebViewPresenter(WebViewContract.View view){
        mView = view;
        mView.setPresenter(this);
    }
    @Override
    public void start() {

    }

    @Override
    public boolean checkNetworkAvaible() {
        return false;
    }
}
