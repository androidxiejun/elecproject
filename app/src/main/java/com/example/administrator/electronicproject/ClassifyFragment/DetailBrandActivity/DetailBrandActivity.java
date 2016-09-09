package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.electronicproject.R;

public class DetailBrandActivity extends AppCompatActivity {
    private Context context;
    private BrandDetailsFragment fragment;
    private FragmentManager manager;
    public static final String SHARE_URL="http://m.hichao.com/app/templates/brand_detail.html?id=3051";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_detail_layout);
        context=this;
        initFragment();
    }

    private void initFragment() {
        manager=getSupportFragmentManager();
        fragment=BrandDetailsFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.brand_detail_frame_layout,fragment);
        fragmentTransaction.commit();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.brand_detail_back_btn:
                finish();
                break;
            case R.id.brand_detail_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,SHARE_URL);
                sendIntent.setType("text/*");
                startActivity(sendIntent);
                break;
        }
    }
}
