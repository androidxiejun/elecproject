package com.example.administrator.electronicproject.PurchaseDetails.ImageAndText;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.electronicproject.PurchaseDetails.JavascriptInterface;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ImageAndTextFragment extends Fragment {
    private Context context;
    private WebView mWebView;
    private TabLayout mTabLayout;
    private String type,id,gi;
    public  String URL_PATH="http://m.hichao.com/lib/interface.php?m=";
    private String content="&sid=";
    private String content2="&gi=";
    public static ImageAndTextFragment newInstance(){
        return new ImageAndTextFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_image_and_text,container,false);
        type="goodsdetail";
        id=PurchaseDetails.sourceId;
        initView(view);
        initWeb();
        return view;
    }

    private void initWeb() {
        String path=URL_PATH+type+content+id+content2;
        WebSettings settings = mWebView.getSettings();
       settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JavascriptInterface(context),"jsObj");
        mWebView.loadUrl(path);
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

    private void initView(View view) {
        mWebView= (WebView) view.findViewById(R.id.purchase_detal_imageandtext_web);
        mTabLayout= (TabLayout) view.findViewById(R.id.purchase_detal_imageandtext_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("详情") );
        mTabLayout.addTab(mTabLayout.newTab().setText("尺码") );
        mTabLayout.addTab(mTabLayout.newTab().setText("售后") );
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        type="goodsdetail";
                        initWeb();
                        break;
                    case 1:
                        type="goodssize";
                        initWeb();
                        break;
                    case 2:
                        type="aftersaleservice";
                        gi="gi=1";
                        initWeb();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
