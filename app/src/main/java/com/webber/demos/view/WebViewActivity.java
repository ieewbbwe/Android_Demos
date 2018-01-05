package com.webber.demos.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.webber.demos.R;

public class WebViewActivity extends AppCompatActivity {

    private String url = "file:///android_asset/testlink.html";
    private WebView mWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWv = (WebView) findViewById(R.id.m_test_wv);
        WebViewClient webViewClient;

        mWv.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                Log.d("picher",""+url +"scheme:"+uri.getScheme()+"path:"+uri.getAuthority());
                if(url.contains("mailto:")){
                    openEmailClient(getApplicationContext(),url.substring(url.indexOf("mailto:")+1,url.length()));
                    Toast.makeText(WebViewActivity.this,"打开邮件："+ url.substring(url.indexOf("mailto:")+1,url.length()),Toast.LENGTH_SHORT).show();
                    return true;
                }else if(url.startsWith("http")){
                    view.loadUrl(url);
                    return true;
                }
                return shouldOverrideUrlLoading(view,url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("picher",url+"onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("picher",url+"onPageFinished");
            }
        });

        mWv.loadUrl(url);

        openByBroswer(url);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("picher",""+mWv.canGoBack());
        if (keyCode == KeyEvent.KEYCODE_BACK && mWv.canGoBack()) {
            mWv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openByBroswer(String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://ml-welcome01.nxtdig.com.tw/uat/payment/faq.html");
        intent.setData(content_url);
        startActivity(intent);
    }

    public void openEmailClient(Context context, String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        //  intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        context.startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
