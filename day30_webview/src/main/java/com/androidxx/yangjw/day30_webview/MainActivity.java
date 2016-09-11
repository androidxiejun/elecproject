package com.androidxx.yangjw.day30_webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
     public static final String YRL_PATH="http://fed.hichao.com/templates/webview/evaluate.html?gsv=4.2.2&uid=13895988&mxyc_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&mxyc_id=133524489692433&gc=hichao&gf=android&gn=mxyc_adr&id=28019&timestamp=1473299584384&token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&gi=133524489692433&p=GT-P5210&gv=700&gos=7.0.0&gs=576x1024&access_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM";
    private WebView mWebView,mWebView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView2= (WebView) findViewById(R.id.web_view2);
        //设置WebView支持Javascript脚本
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new JavascriptInterface(this),"android");
        mWebView.addJavascriptInterface(new JavascriptInterface(this),"jsObj");
        mWebView2.addJavascriptInterface(new JavascriptInterface(this),"jsOb");
        //加载html文件
        mWebView.loadUrl(YRL_PATH);
        mWebView2.loadUrl("http://www.zhibo8.com");
//        mWebView.loadUrl("file:///android_asset/login.html");
//        mWebView.loadUrl("http://www.u17.com/z/zt/appspecial/special_comic_list_v3.html?is_comment=1&special_id=40&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia");
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);
        mWebView2.setWebViewClient(webViewClient);
    }
}
