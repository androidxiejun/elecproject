package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/6.
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.camera_tool_bar_back_btn)
    Button backBtn;
    @BindView(R.id.fashion_tool_bar_camera_btn)
    Button cameraModeBtn;
    @BindView(R.id.camera_tool_bar_content_tv)
    TextView ensureTv;
    @BindView(R.id.camera_frame_layout)
    FrameLayout modeFrameLayout;

    private final String TAG = "android--";
    private FragmentManager manager;
    private Fragment mCurrentShowFragment;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private List<String> cameraList = new ArrayList<>();
    private boolean mode = false;//用来判断显示模式


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();
        initListener();
    }

    private void initListener() {

        backBtn.setOnClickListener(this);
        cameraModeBtn.setOnClickListener(this);
        ensureTv.setOnClickListener(this);

        readSystemCamera();
    }

    /**
     * 读取手机相册中的图片路径
     */
    private void readSystemCamera() {
        String[] projection = new String[]{MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
                MediaStore.Images.Media.DATA}; // 图片绝对路径
        
        //根据MediaStore.Images类获取所有图片的路径

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.camera_tool_bar_back_btn:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.fashion_tool_bar_camera_btn:
                if (mode){
                    mode = false;
                }else {
                    mode = true;
                }
                break;
            case R.id.camera_tool_bar_content_tv:
                break;
        }
        startActivity(intent);
    }

    /**
     * 当点击RadioButton时会执行此方法，加载相应的fragment视图
     * @param fragment
     */
    private void chooseFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (mCurrentShowFragment != null && mCurrentShowFragment.isAdded()) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.camera_frame_layout,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        mCurrentShowFragment = fragment;
    }
}
