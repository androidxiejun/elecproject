package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
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
    private Messenger messenger;//Service的Messenger

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_iamge_layout);


        intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
        connectService();
    }

    private void connectService() {
        Intent intent = new Intent(this, DownImageService.class);
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

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
                if (url != null){
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = url;
                    try {
                        if (messenger != null){
                            messenger.send(obtain);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
