package com.example.administrator.electronicproject.PurchaseDetails.CommonActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.electronicproject.PurchaseDetails.JavascriptInterface;
import com.example.administrator.electronicproject.R;

public class CommonActivity extends AppCompatActivity {
    public static final String URL_PATH="http://fed.hichao.com/templates/webview/evaluate.html?gsv=4.2.2&uid=13895988&mxyc_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&mxyc_id=133524489692433&gc=hichao&gf=android&gn=mxyc_adr&id=28019&timestamp=1473299584384&token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM&gi=133524489692433&p=GT-P5210&gv=700&gos=7.0.0&gs=576x1024&access_token=7dmQ_BnaDoJz7JbM2cppELQfVrRlHWNHYU9JHzFnIbM";
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initView();
    }
   public void onClick(View view){
       finish();
   }
    private void initView() {
        mWebView= (WebView) findViewById(R.id.common_web_view);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JavascriptInterface(this),"jsObj");
        mWebView.loadUrl(URL_PATH);
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
