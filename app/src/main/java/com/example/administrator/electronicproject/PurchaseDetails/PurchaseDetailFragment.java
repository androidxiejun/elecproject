package com.example.administrator.electronicproject.PurchaseDetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.DetailBrandActivity;
import com.example.administrator.electronicproject.PurchaseDetails.CommonActivity.CommonActivity;
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
    private int startX, startY;
    private int distanceX;
    private Context context;
    private PurchaseDetailsBean.ResponseBean.DataBean data;
    private NestedScrollView mNestedScrollView;
    public static final String URL_PATH = "http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=1522163";
    private ImageView logoImg, logoImg2;
    private TextView brandTxt, brandTxt2, totalTxt, freshTxt;
    private ImageView bigImg;
    private TextView currentPrice, lookTxt, chooseColor;
    private TextView originPrice;
    private TextView descriptionTxt;
    private WebView mWebView;
    private ImageView freshBtn;
    private LinearLayout linearLayout;
    private PurchaseCallBack callBack;
    private Button lookBtn;
    private boolean isChecked = false;
    private CoordinatorLayout coordinatorLayout;

    public static PurchaseDetailFragment newInstance() {
        return new PurchaseDetailFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PurchaseDetails) {
            callBack = (PurchaseCallBack) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purchase_detail_fragment_layout, container, false);
        initView(view);
        initLookListenner();
        initScrollViewListenner();
        initImageListener();
        getInfo();
        initDefaultData();
        return view;
    }

    /**
     * 对上面的图片进行滑动监听，当滑动到一定距离后，加载网络布局
     */
    private void initImageListener() {

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        linearLayout.scrollTo(0, 0);
                        startX = (int) motionEvent.getX();
                        startY = (int) motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        distanceX = (int) (startX - motionEvent.getX());
                        if (distanceX < 300) {
                            linearLayout.scrollTo(distanceX, 0);
                        }
                        if (distanceX > 100) {
                            freshTxt.setText("拉出以刷新");
                            freshBtn.setImageResource(R.drawable.icon_detail_push);
                        } else {
                            freshTxt.setText("放手以刷新");
                            freshBtn.setImageResource(R.drawable.icon_detail_pull);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        linearLayout.scrollTo(0, 0);
                        callBack.addFragment();
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
                View view = v.getChildAt(v.getChildCount() - 1);
                int d = view.getBottom();
                d -= (v.getHeight() + v.getScrollY());
                if (d == 0) {
//                    Toast.makeText(context, "滑到底部", Toast.LENGTH_SHORT).show();
                    WebSettings settings = mWebView.getSettings();
                    settings.setJavaScriptEnabled(true);
                    mWebView.addJavascriptInterface(new JavascriptInterface(context), "jsObj");
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
                Intent intent = new Intent(context, CommonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.purchase_coordinate);
        chooseColor = (TextView) view.findViewById(R.id.purchase_detail_choose);
        freshBtn = (ImageView) view.findViewById(R.id.brand_detail_pull_arrow);
        freshTxt = (TextView) view.findViewById(R.id.brand_detail_pll_txt);
        linearLayout = (LinearLayout) view.findViewById(R.id.detail_brand_linear_layout);
        mNestedScrollView = (NestedScrollView) view.findViewById(R.id.purchase_detail_nested_scroll_view);
        currentPrice = (TextView) view.findViewById(R.id.purchase_detaiL_current_price);
        logoImg = (ImageView) view.findViewById(R.id.purchase_detail_logo);
        logoImg2 = (ImageView) view.findViewById(R.id.purchase_detail_logo2);
        brandTxt = (TextView) view.findViewById(R.id.purchase_detail_productname);
        bigImg = (ImageView) view.findViewById(R.id.purchase_detail_img);
        originPrice = (TextView) view.findViewById(R.id.purchase_detail_origin_price);
        descriptionTxt = (TextView) view.findViewById(R.id.purchase_details_description);
        totalTxt = (TextView) view.findViewById(R.id.purchase_detail_total_description);
        brandTxt2 = (TextView) view.findViewById(R.id.purchase_details_brand_name);
        lookTxt = (TextView) view.findViewById(R.id.purchase_details_howlook);
        chooseColor = (TextView) view.findViewById(R.id.purchase_detail_choose);
        mWebView = (WebView) view.findViewById(R.id.purchase_detail_web_view);
        lookBtn = (Button) view.findViewById(R.id.purchse_detail_look_btn);
        chooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);

            }
        });
        //对关注按钮进行监听
        lookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked) {
                    isChecked = false;
                    lookBtn.setText("关注品牌");
                } else {
                    isChecked = true;
                    lookBtn.setText("已关注");
                }
            }
        });
        //对最上面的品牌商标进行监听，点击按钮后跳转至品牌详情
        brandTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBrandActivity.class);
                intent.putExtra("imgUrl", PurchaseDetails.imgUrl);
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });
        //对最下面的品牌商标进行监听，点击按钮后跳转至品牌详情
        brandTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBrandActivity.class);
                intent.putExtra("imgUrl", PurchaseDetails.imgUrl);
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });
    }

    //显示购买选择弹出框
    private void showPopup(View view) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_layout, null);
        ImageView popupImage = (ImageView) popupView.findViewById(R.id.popup_image_view);
        TextView priceText = (TextView) popupView.findViewById(R.id.popup_price);
        Button deleteBtn = (Button) popupView.findViewById(R.id.popup_delete);
        Picasso.with(context).load(PurchaseDetails.imgUrl).into(popupImage);
        priceText.setText("￥" + PurchaseDetails.currentPrice);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        //给popup设置一个空背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
//        WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
//        int xpos=manager.getDefaultDisplay().getWidth()/2-popupWindow.getWidth()/2;
        //xoff,yoff基于anchor的左下角进行偏移。
//        popupWindow.showAsDropDown(view,400, 0);
        // 设置好参数之后再show
//        popupWindow.showAsDropDown(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, getStatusBarHeight());
    }

    //获取屏幕像素高度，使得Popup填充整个屏幕
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initDefaultData() {
        Picasso.with(context).load(PurchaseDetails.imgUrl).into(bigImg);
        currentPrice.setText("￥" + PurchaseDetails.currentPrice);
        originPrice.setText("￥" + PurchaseDetails.originPrice);
        originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        descriptionTxt.setText(PurchaseDetails.title);
    }

    private void getInfo() {
        HttpPurchaseUtils.create().queryBean().enqueue(new Callback<PurchaseDetailsBean>() {
            @Override
            public void onResponse(Call<PurchaseDetailsBean> call, Response<PurchaseDetailsBean> response) {
                data = response.body().getResponse().getData();
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
