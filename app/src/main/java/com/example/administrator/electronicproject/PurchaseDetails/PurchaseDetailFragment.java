package com.example.administrator.electronicproject.PurchaseDetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsBean.PurchaseDetailsBean;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsUtils.HttpPurchaseUtils;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/8.
 */
public class PurchaseDetailFragment extends Fragment {
    private int startX,startY;
    private int distanceX;
    private Context context;
    private PurchaseDetailsBean.ResponseBean.DataBean data;
    private NestedScrollView mNestedScrollView;
    public static final String URL_PATH="http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=1522163";
    private ImageView logoImg,logoImg2;
    private TextView brandTxt,brandTxt2,totalTxt;
    private ImageView bigImg;
    private TextView currentPrice,lookTxt,chooseColor;
    private TextView originPrice;
    private TextView descriptionTxt;
    private  WebView mWebView;
    private LinearLayout linearLayout;


    public static PurchaseDetailFragment newInstance(){
        return new PurchaseDetailFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.purchase_detail_fragment_layout,container,false);
        initView(view);
        initLookListenner();
        initScrollViewListenner();
        initImageListener();
        getInfo();
        initDefaultData();
        return view;
    }

    private void initImageListener() {

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(view==null){
                            view=bigImg;
                        }
                        startX = (int) motionEvent.getX();
                        startY = (int) motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        distanceX = (int) (startX-motionEvent.getX());
                        if (distanceX >50& distanceX <300) {
                            view.scrollTo(distanceX,0);

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        view.scrollTo(0,0);
                        if(distanceX>200){
                            Toast.makeText(context, ".............", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void initScrollViewListenner() {
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                View view = v.getChildAt(v.getChildCount()-1);
                int d = view.getBottom();
                d -= (v.getHeight()+v.getScrollY());
                if(d==0)
                {
//                    Toast.makeText(context, "滑到底部", Toast.LENGTH_SHORT).show();
                    WebSettings settings = mWebView.getSettings();
                    settings.setJavaScriptEnabled(true);
                    mWebView.addJavascriptInterface(new JavascriptInterface(context),"jsObj");
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
        });
    }

    private void initLookListenner() {
        lookTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,CommonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        linearLayout= (LinearLayout) view.findViewById(R.id.detail_brand_linear_layout);
        mNestedScrollView= (NestedScrollView) view.findViewById(R.id.purchase_detail_nested_scroll_view);
        currentPrice= (TextView) view.findViewById(R.id.purchase_detaiL_current_price);
        logoImg= (ImageView) view.findViewById(R.id.purchase_detail_logo);
        logoImg2= (ImageView) view.findViewById(R.id.purchase_detail_logo2);
        brandTxt= (TextView) view.findViewById(R.id.purchase_detail_productname);
        bigImg= (ImageView) view.findViewById(R.id.purchase_detail_img);
        originPrice= (TextView) view.findViewById(R.id.purchase_detail_origin_price);
        descriptionTxt= (TextView) view.findViewById(R.id.purchase_details_description);
        totalTxt= (TextView) view.findViewById(R.id.purchase_detail_total_description);
        brandTxt2= (TextView) view.findViewById(R.id.purchase_details_brand_name);
        lookTxt= (TextView) view.findViewById(R.id.purchase_details_howlook);
        chooseColor= (TextView) view.findViewById(R.id.purchase_detail_choose);
        mWebView= (WebView) view.findViewById(R.id.purchase_detail_web_view);
    }

    private void initDefaultData() {
        Picasso.with(context).load(PurchaseDetails.imgUrl).into(bigImg);
        currentPrice.setText("￥"+PurchaseDetails.currentPrice);
        originPrice.setText("￥"+PurchaseDetails.originPrice);
        originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        descriptionTxt.setText(PurchaseDetails.title);
    }

    private void getInfo(){
        HttpPurchaseUtils.create().queryBean().enqueue(new Callback<PurchaseDetailsBean>() {
            @Override
            public void onResponse(Call<PurchaseDetailsBean> call, Response<PurchaseDetailsBean> response) {
                data= response.body().getResponse().getData();
                Picasso.with(context).load(data.getBusiness_image()).into(logoImg);
                Picasso.with(context).load(data.getBusiness_image()).into(logoImg2);
                brandTxt.setText(data.getBusiness_name());
                brandTxt2.setText(data.getBusiness_name());
                totalTxt.setText(data.getBusiness_brief());
            }

            @Override
            public void onFailure(Call<PurchaseDetailsBean> call, Throwable t) {

            }
        });
    }
}
