package com.webber.demos.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.gson.Gson;
import com.webber.demos.MainActivity;
import com.webber.demos.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class WebViewActivity extends AppCompatActivity {

    private String url = "file:///android_asset/testlink.html";
    private WebView mWv;
    private xWebChromeClient xwebchromeclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWv = (WebView) findViewById(R.id.m_test_wv);
        // shouldOverrideUrlLoading
        //demo01();
        // 打开浏览器
        //demo02();
        // embed iframe
        //demo03();
        //google 27 safeResponse test
        demo04();
       /* mWv.getSettings().setJavaScriptEnabled(true);
        mWv.setWebViewClient(new WebViewClient());
        mWv.setWebChromeClient(new WebChromeClient());*/
        this.mWv.loadUrl("file:///android_asset/consentform.html");
    }

    private void demo04() {
        mWv.getSettings().setJavaScriptEnabled(true);
        mWv.setWebViewClient(
                new WebViewClient() {

                    boolean isInternalRedirect;

                    private boolean isConsentFormUrl(String url) {
                        return !TextUtils.isEmpty(url) && url.startsWith("consent://");
                    }

                    private void handleUrl(String url) {
                        if (!isConsentFormUrl(url)) {
                            return;
                        }

                        isInternalRedirect = true;
                        Uri uri = Uri.parse(url);
                        String action = uri.getQueryParameter("action");
                        String status = uri.getQueryParameter("status");
                        String browserUrl = uri.getQueryParameter("url");

                        switch (action) {
                            case "load_complete":
                                handleLoadComplete(status);
                                break;
                            case "dismiss":
                                isInternalRedirect = false;
                                handleDismiss(status);
                                break;
                            case "browser":
                                handleOpenBrowser(browserUrl);
                                break;
                            default: // fall out
                        }
                    }

                    @Override
                    public void onLoadResource (WebView view, String url) {
                        handleUrl(url);
                    }

                    @TargetApi(Build.VERSION_CODES.N)
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        String url = request.getUrl().toString();
                        if (isConsentFormUrl(url)) {
                            handleUrl(url);
                            return true;
                        }
                        return false;
                    }

                    @SuppressWarnings("deprecation")
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (isConsentFormUrl(url)) {
                            handleUrl(url);
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if (!isInternalRedirect) {
                            updateDialogContent(view);
                        }
                        super.onPageFinished(view, url);
                    }

                    @Override
                    public void onReceivedError(
                            WebView view, WebResourceRequest request, WebResourceError error) {
                        super.onReceivedError(view, request, error);
                    }
                });

    }

    private void handleLoadComplete(String status) {

    }

    private void handleOpenBrowser(String urlString) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        //context.startActivity(browserIntent);
    }

    private void handleDismiss(String status) {
        //dialog.dismiss();

       /* if (TextUtils.isEmpty(status)) {
            listener.onConsentFormError("No information provided.");
            return;
        }

        if (status.contains("Error")) {
            listener.onConsentFormError(status);
            return;
        }
*/
        boolean userPrefersAdFree = false;
        ConsentStatus consentStatus;
        switch (status) {
            case "personalized":
                consentStatus = ConsentStatus.PERSONALIZED;
                break;
            case "non_personalized":
                consentStatus = ConsentStatus.NON_PERSONALIZED;
                break;
            case "ad_free":
                userPrefersAdFree = true;
                consentStatus = ConsentStatus.UNKNOWN;
                break;
            default:
                consentStatus = ConsentStatus.UNKNOWN;
        }

       /* ConsentInformation.getInstance(this).setConsentStatus(consentStatus, "form");
        listener.onConsentFormClosed(consentStatus, userPrefersAdFree);*/
    }

    private static String getAppIconURIString(Context context) {
        Drawable iconDrawable = context.getPackageManager().getApplicationIcon(context
                .getApplicationInfo());
        Bitmap bitmap = Bitmap.createBitmap(iconDrawable.getIntrinsicWidth(),
                iconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        iconDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        iconDrawable.draw(canvas);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return "data:image/png;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void updateDialogContent(WebView webView) {
        HashMap<String, Object> formInfo = new HashMap < > ();
        formInfo.put("app_name", this.getApplicationInfo().loadLabel(this.getPackageManager()).toString());
        formInfo.put("app_icon", getAppIconURIString(this));
        formInfo.put("offer_personalized", true);
        formInfo.put("offer_non_personalized", true);
        formInfo.put("offer_ad_free", true);
        formInfo.put("is_request_in_eea_or_unknown",
                ConsentInformation.getInstance(this).isRequestLocationInEeaOrUnknown());
        formInfo.put("app_privacy_url", "file:///android_asset/consentform.html");
        /*ConsentData consentData = ConsentInformation.getInstance(this).loadConsentData();

        formInfo.put("plat", consentData.getSDKPlatformString());
        formInfo.put("consent_info", consentData);*/

        String argumentsJSON = new Gson().toJson(formInfo);
        String javascriptCommand = createJavascriptCommand("setUpConsentDialog",
                argumentsJSON);
        webView.loadUrl(javascriptCommand);
    }

    private static String createJavascriptCommand(String command, String argumentsJSON) {
        HashMap <String, Object> args = new HashMap < > ();
        args.put("info", argumentsJSON);
        HashMap <String, Object> wrappedArgs = new HashMap < > ();
        wrappedArgs.put("args", args);
        return String.format("javascript:%s(%s)", command, new Gson().toJson(wrappedArgs));
    }

    private void demo03() {
        // https://www.jianshu.com/p/4aed5c1230dc 例子
        //demo03_1();
        demo03_2();
        //demo03_3();
    }

    private void demo03_3() {

    }

    String web = "<html>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "<iframe src=\"/i/eg_landscape.jpg\"></iframe>\n" +
            "\n" +
            "<iframe width=\"500\" height=\"300\" frameborder=\"0\" allowfullscreen rolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"http://ditu.google.cn/maps?f=q&amp;source=s_q&amp;hl=zh-CN&amp;geocode=&amp;q=北京市朝阳区天辰东路7号&amp;aq=&amp;sll=40.001238,116.398001&amp;sspn=0.009599,0.022724&amp;brcurrent=3,0x35f054d017a3d83b:0xbf992b5a04c9c667,0,0x35f1abee23736947:0xd7bb8b3026d0813a;5,0,0&amp;ie=UTF8&amp;hq=&amp;hnear=北京市朝阳区天辰东路&amp;ll=39.999566,116.391402&amp;spn=0.0096,0.022724&amp;t=m&amp;z=14&amp;output=embed\"></iframe><br /><small><a href=\"http://ditu.google.cn/maps?f=q&amp;source=embed&amp;hl=zh-CN&amp;geocode=&amp;q=北京市朝阳区天辰东路7号&amp;aq=&amp;sll=40.001238,116.398001&amp;sspn=0.009599,0.022724&amp;brcurrent=3,0x35f054d017a3d83b:0xbf992b5a04c9c667,0,0x35f1abee23736947:0xd7bb8b3026d0813a;5,0,0&amp;ie=UTF8&amp;hq=&amp;hnear=北京市朝阳区天辰东路&amp;ll=39.999566,116.391402&amp;spn=0.0096,0.022724&amp;t=m&amp;z=14\" style=\"color:#0000FF;text-align:left\" target=\"_blank\">查看大图</a></small>\n" +
            "\n" +
            "<iframe id=\"ytplayer\" allowfullscreen  mozallowfullscreen webkitallowfullscreen type=\"text/html\" width=\"640\" height=\"360\"\n" +
            "  src=\"http://www.youtube.com/embed/M7lc1UVf-VE?autoplay=1&origin=http://example.com\"\n" +
            "  frameborder=\"0\"/>\n" +
            "<img width \n" +
            "<p>一些老的浏览器不支持 iframe。</p>\n" +
            "<p>如果得不到支持，iframe 是不可见的。</p>\n" +
            "\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";

    private void demo03_2() {
        mWv.getSettings().setJavaScriptEnabled(true);

        mWv.setWebChromeClient(new CustomWebViewChromeClient());
        mWv.setWebViewClient(new CustomWebClient());
      //initWebView();

        mWv.addJavascriptInterface(new JsFullObject(),"onClick");
        mWv.loadData(web,"text/html","utf-8");
    }

    private class JsFullObject{

        @JavascriptInterface
        public void fullscreen(){
            //监听到用户点击全屏按钮
            Log.d("picher","点击了全屏");
        }
    }
    @Override
    public void onBackPressed() {
        if (mWv.canGoBack()){
            mWv.goBack();
        }else {
            super.onBackPressed();
        }
    }

    private class CustomWebClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("picher","onPageFinished");
        }
    }

    private class CustomWebViewChromeClient extends WebChromeClient{

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            Log.d("picher","onHideCustomView");
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            Log.d("picher","onHideCustomView");
            super.onHideCustomView();
        }
    }

    private void demo03_1() {
        initWebView();
        mWv.loadUrl("http://v.qq.com/iframe/player.html?vid=o0318tp1ddw&tiny=0&auto=0");
        mWv.addJavascriptInterface(new JsObject(WebViewActivity.this), "console");
    }

    private void initWebView() {
        WebSettings ws = mWv.getSettings();
        /**
         * setAllowFileAccess 启用或禁止WebView访问文件数据 setBlockNetworkImage 是否显示网络图像
         * setBuiltInZoomControls 设置是否支持缩放 setCacheMode 设置缓冲的模式
         * setDefaultFontSize 设置默认的字体大小 setDefaultTextEncodingName 设置在解码时使用的默认编码
         * setFixedFontFamily 设置固定使用的字体 setJavaSciptEnabled 设置是否支持Javascript
         * setLayoutAlgorithm 设置布局方式 setLightTouchEnabled 设置用鼠标激活被选项
         * setSupportZoom 设置是否支持变焦
         * */
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        // settings.setPluginsEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setLoadWithOverviewMode(true);
        ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        mWv.setSaveEnabled(false);
        ws.setSaveFormData(false);
        // 下面的一句话是必须的，必须要打开javaScript不然所做一切都是徒劳的
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(true);
        xwebchromeclient = new xWebChromeClient();
        //setWebChromeClient主要处理解析，渲染网页等浏览器做的事情
        //这个方法必须有，就算类中没有函数也可以，不然视频播放不了
        mWv.setWebChromeClient(xwebchromeclient);
        //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWv.setWebViewClient(new xWebViewClientent());
    }

    /**
     * 处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
     *
     * @author
     */
    public class xWebChromeClient extends WebChromeClient {
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }
    }

    /**
     * 设置监听事件
     * 处理各种通知、请求等事件
     *
     * @author
     */
    public class JsObject {
        Context mContext;

        JsObject(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void log() {
            System.out.println("返回结果");
            setFullScreen();
        }
    }

    /**
     * 设置全屏
     */
    private void setFullScreen() {
        Log.i("视频全屏-->", "竖屏切换到横屏");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 全屏下的状态码：1098974464
        // 窗口下的状态吗：1098973440
    }

    public class xWebViewClientent extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            if(true){
                return;
            }

            view.loadUrl(BrowserJsInject.fullScreenByJs(url));
            System.out.println("url1:" + BrowserJsInject.fullScreenByJs(url));
            System.out.println("url2" + url);

            //view.loadData("", "text/html", "UTF-8");
                        // my_web.loadUrl("http://v.qq.com/iframe/player.html?vid=o0318tp1ddw&tiny=0&auto=0");
            //view.loadUrl("javascript:alert('123')");
                        //view.loadUrl(BrowserJsInject.fullScreenByJs(url));

            //点击链接时：
            view.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    view.loadUrl(url); //在当前的webview中跳转到新的url
                    System.out.println("链接--》" + url);
                    return true;
                }
            });

            view.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    return true;
                }
            });

            view.addJavascriptInterface(new Object(){

                @JavascriptInterface
                public void playing(){
                    System.out.println("返回结果");
                    setFullScreen();
                }

            }, "local_obj");
            view.loadUrl(BrowserJsInject.fullScreenByJs(url));
            System.out.println("url1:"+BrowserJsInject.fullScreenByJs(url));
            System.out.println("url2"+url);

        }


        private void demo02() {
            openByBroswer(url);
        }

        private void demo01() {
            WebViewClient webViewClient;

            mWv.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Uri uri = Uri.parse(url);
                    Log.d("picher", "" + url + "scheme:" + uri.getScheme() + "path:" + uri.getAuthority());
                    if (url.contains("mailto:")) {
                        openEmailClient(getApplicationContext(), url.substring(url.indexOf("mailto:") + 1, url.length()));
                        Toast.makeText(WebViewActivity.this, "打开邮件：" + url.substring(url.indexOf("mailto:") + 1, url.length()), Toast.LENGTH_SHORT).show();
                        return true;
                    } else if (url.startsWith("http")) {
                        view.loadUrl(url);
                        return true;
                    }
                    return shouldOverrideUrlLoading(view, url);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    Log.d("picher", url + "onPageStarted");
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    Log.d("picher", url + "onPageFinished");
                }
            });

            mWv.loadUrl(url);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("picher", "" + mWv.canGoBack());
        if (keyCode == KeyEvent.KEYCODE_BACK && mWv.canGoBack()) {
            mWv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openByBroswer(String url) {
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
