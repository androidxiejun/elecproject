package com.example.administrator.electronicproject.PurchaseDetails.CommonActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.electronicproject.PurchaseDetails.JavascriptInterface;
import com.example.administrator.electronicproject.R;

public class CommonActivity extends AppCompatActivity {
//    public static final String URL_PATH="http://fed.hichao.com/templates/webview/evaluate.html?gsv=4.2.2&uid=13895988&mxyc_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&mxyc_id=133524489692433&gc=hichao&gf=android&gn=mxyc_adr&id=28019&timestamp=1473299584384&token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&gi=133524489692433&p=GT-P5210&gv=700&gos=7.0.0&gs=576x1024&access_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM";
   public static final String URL_PATH="http://fed.hichao.com/templates/webview/evaluate.html?gsv=4.2.1&mxyc_id=860623022675159&gc=hichao&gf=android&gn=mxyc_adr&timestamp=1473728480862&token=&gi=860623022675159&p=HUAWEI%2BG610-U00&gv=701&gos=7.0.1&gs=540x960&access_token=&id=";
    private WebView mWebView;
    private String goods_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initIntent();
        initView();
    }

    private void initIntent() {
        Intent intent = getIntent();
        goods_id=intent.getStringExtra("goods_id");
    }

    public void onClick(View view){
       finish();
   }
    private void initView() {
        mWebView= (WebView) findViewById(R.id.common_web_view);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JavascriptInterface(this),"jsObj");
        mWebView.loadUrl(URL_PATH+goods_id);
        WebViewClient webViewClient = new WebViewClient() {
            /**
             * WebView每次加载地址，都会经过此方法
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);
    }
}
