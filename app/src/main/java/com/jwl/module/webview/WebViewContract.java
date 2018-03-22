package com.jwl.module.webview;

import com.jwl.base.BasePresenter;
import com.jwl.base.BaseView;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public class WebViewContract {
    interface View extends BaseView<Presenter>{
        void showNetworkError();
        void showloadingDialog();
    }
    interface Presenter extends BasePresenter{
        boolean checkNetworkAvaible();
    }
}
