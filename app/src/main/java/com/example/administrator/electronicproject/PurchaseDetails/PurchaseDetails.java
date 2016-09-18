package com.example.administrator.electronicproject.PurchaseDetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.DetailBrandActivity;
import com.example.administrator.electronicproject.MineFragment.activity.ShappingCart;
import com.example.administrator.electronicproject.Products;
import com.example.administrator.electronicproject.PurchaseDetails.ImageAndText.ImageAndTextFragment;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseConfirMationActivity.PurchaseFirmationActivity;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.ShoppingCarGreenDaoUtils.DaoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseDetails extends AppCompatActivity implements PurchaseCallBack,View.OnClickListener{
    private PurchaseDetailFragment fragment;
    private FragmentManager manager;
    private ImageAndTextFragment imageFragment;
    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction2;
    private SharedPreferences mSp;
    private SharedPreferences.Editor editor;
    private List<Products> productList=new ArrayList<>();
    private int number;
    private boolean isLast=true;
    private boolean isBuyNow=false;
    private Context context;
    public static boolean isAddCar=false;//判断是加入购物车还是进入购买界面
    @BindView(R.id.purchase_detail_shooping_back_btn)
    Button backBtn;
    @BindView(R.id.purchase_detail_kefu)
    Button serviceBtn;
    @BindView(R.id.purchase_detail_brand)
    Button branBtn;
    @BindView(R.id.purchase_detail_collect)
    Button collectBtn;
    @BindView(R.id.purchase_detail_add_car)
    Button addCarBtn;
    @BindView(R.id.purchase_detail_buy_now)
    Button buyBtn;
    @BindView(R.id.purchase_detail_shooping_number)
    TextView shoopingNum;
    @BindView(R.id.purchase_detail_shooping_car)
    Button shoppingCarBtn;
   public static String source_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);
        ButterKnife.bind(this);
        productList=DaoUtils.getDao(this).loadAll();
//        mSp=getSharedPreferences("star",Context.MODE_PRIVATE);
//        editor=mSp.edit();
//        number= mSp.getInt("number", 0);
        shoopingNum.setText(productList.size()+"");
        context=this;
        backBtn.setOnClickListener(this);
        addCarBtn.setOnClickListener(this);
        buyBtn.setOnClickListener(this);
        branBtn.setOnClickListener(this);
        collectBtn.setOnClickListener(this);
        shoppingCarBtn.setOnClickListener(this);
        manager=getSupportFragmentManager();
        initIntent();
        initFragment();
        toolbar= (Toolbar) findViewById(R.id.purchase_detail_toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        productList=DaoUtils.getDao(this).loadAll();
        shoopingNum.setText(productList.size()+"");
    }

    private void initIntent() {
        Intent intent = getIntent();
        source_id=intent.getStringExtra("source_id");
    }

    private void initFragment() {
        fragment=PurchaseDetailFragment.newInstance();
        imageFragment=ImageAndTextFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.purchase_detaiL_frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void addFragment() {
        isLast=false;
        fragmentTransaction2= manager.beginTransaction();
        fragmentTransaction2.replace(R.id.purchase_detaiL_frame_layout,imageFragment);
        fragmentTransaction2.commit();

    }

    @Override
    public void changNumber() {
//        int number = mSp.getInt("number", 0);
        productList=DaoUtils.getDao(this).loadAll();
        shoopingNum.setText(productList.size()+"");
    }

    @Override
    public void addShoppingCar(String productName,String productTitle,String productPrice,String productImgUrl,String productId) {
        Products product=new Products();
        product.setProductName(productName);
        product.setProductTitle(productTitle);
        product.setProductPrice(productPrice);
        product.setProductImgUrl(productImgUrl);
        product.setProductId(productId);
        DaoUtils.getDao(context).insert(product);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            if(isLast){
                finish();
            }else {
                isLast=true;
                fragmentTransaction2= manager.beginTransaction();
                fragmentTransaction2.replace(R.id.purchase_detaiL_frame_layout,fragment);
                fragmentTransaction2.commit();
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.purchase_detail_shooping_back_btn:
                if(isLast){
                    finish();
                }else {
                    isLast=true;
                    fragmentTransaction2= manager.beginTransaction();
                    fragmentTransaction2.replace(R.id.purchase_detaiL_frame_layout,fragment);
                    fragmentTransaction2.commit();
                }
                break;
            case R.id.purchase_detail_add_car:
                isAddCar=true;
                fragment.showPopup(view);
                break;
            case R.id.purchase_detail_buy_now:
                isAddCar=false;
                if(!isBuyNow){
                    isBuyNow=true;
                    fragment.showPopup(view);
                }else{
                    Intent intent=new Intent(context, PurchaseFirmationActivity.class);
                    intent.putExtra("source_id",source_id);
                    startActivity(intent);
                    isBuyNow=false;
                }
                break;
            case R.id.purchase_detail_brand:
                String business_id = fragment.data.getBusiness_id();
                Intent intent = new Intent(this, DetailBrandActivity.class);
                intent.putExtra("business_id",business_id);
                startActivity(intent);
                break;
            case R.id.purchase_detail_collect:

                break;
            case R.id.purchase_detail_shooping_car:
                Intent intent1=new Intent(context, ShappingCart.class);
                startActivity(intent1);
                break;
        }

    }
}
