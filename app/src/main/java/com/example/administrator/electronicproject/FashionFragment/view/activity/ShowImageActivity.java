package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sunbin on 2016/9/10.
 */
public class ShowImageActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private ImageView saveIv;
    private Intent intent;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_iamge_layout);


        intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.show_big_image_view);
        saveIv = (ImageView) findViewById(R.id.show_big_down_iv);

        if (!url.equals("") && url != null){
            Picasso.with(this).load(url).into(imageView);
        }
        imageView.setOnClickListener(this);
        saveIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_big_image_view:
                finish();
                break;
            case R.id.show_big_down_iv:
                //点击下载图片
                break;
        }
    }
}
