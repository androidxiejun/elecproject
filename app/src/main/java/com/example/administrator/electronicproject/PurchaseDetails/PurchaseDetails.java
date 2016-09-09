package com.example.administrator.electronicproject.PurchaseDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseDetails extends AppCompatActivity {
    private PurchaseDetailFragment fragment;
    private FragmentManager manager;
    private Toolbar toolbar;
    @BindView(R.id.purchase_detail_kefu)
    Button serviceBtn;
    @BindView(R.id.purchase_detail_brand)
    Button branBtn;
    @BindView(R.id.purchase_detail_collect)
    Button collectBtn;
    public static String imgUrl,currentPrice,originPrice,title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);
        ButterKnife.bind(this);
        initIntent();
        initFragment();
        toolbar= (Toolbar) findViewById(R.id.purchase_detail_toolbar);
    }

    private void initIntent() {
        Intent intent = getIntent();
        currentPrice=intent.getStringExtra("currentPrice");
        originPrice=intent.getStringExtra("originPrice");
        title=intent.getStringExtra("title");
        imgUrl=intent.getStringExtra("picUrl");
    }

    private void initFragment() {
        manager=getSupportFragmentManager();
        fragment=PurchaseDetailFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.purchase_detaiL_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}
