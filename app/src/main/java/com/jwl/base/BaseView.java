package com.jwl.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}
