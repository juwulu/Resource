package com.jwl.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/23/0023.
 */

public class WebViewUtil {

    public static void openInBrowser(Context context,String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void sendUrl(Context context,String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_TEXT,url);
        context.startActivity(Intent.createChooser(intent,"分享"));
    }

    public static void copyLink(Context context,String url){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(url);
        Toast.makeText(context, url+"复制成功", Toast.LENGTH_SHORT).show();
    }

}
