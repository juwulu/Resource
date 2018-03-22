package com.jwl.module.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jwl.R;

public class WebViewActivity extends AppCompatActivity implements WebViewContract.View {

    private ImageView mIvClose;
    private ProgressBar mPbWeb;
    private WebView mWbContent;
    private WebViewContract.Presenter mPresenter = new WebViewPresenter(this) ;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url =  getIntent().getStringExtra("url");
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        initView();
    }



    private void initView() {
        mIvClose = (ImageView) findViewById(R.id.close_iv);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mPbWeb = (ProgressBar) findViewById(R.id.web_pb);
        mWbContent = (WebView) findViewById(R.id.content_wb);
        initWebView();
    }

    private void initWebView() {
        mWbContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        WebSettings settings = mWbContent.getSettings();
        settings.setJavaScriptEnabled(true);
        mWbContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100) {
                    mPbWeb.setVisibility(View.GONE);
                }else{
                    mPbWeb.setProgress(newProgress);
                    mPbWeb.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWbContent.loadUrl(url);
    }

    @Override
    public void showNetworkError() {
        if (!mPresenter.checkNetworkAvaible()) {
            Toast.makeText(this, "网络连接错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showloadingDialog() {

    }

    @Override
    public void setPresenter(WebViewContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
