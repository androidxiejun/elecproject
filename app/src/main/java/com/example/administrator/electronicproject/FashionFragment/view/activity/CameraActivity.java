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

import com.example.administrator.electronicproject.FashionFragment.bean.DatasUtils;
import com.example.administrator.electronicproject.FashionFragment.view.fragment.CameraFragmentOne;
import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/6.
 * 照片界面
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener,CameraFragmentOne.CameraCallBack{

    @BindView(R.id.camera_tool_bar_back_btn)
    Button backBtn;
    @BindView(R.id.fashion_tool_bar_camera)
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
    private Map<String,List<String>> parentMap = new HashMap<>();
    private List<String> childList;
    private boolean mode = false;//用来判断显示模式
    private CameraFragmentOne cameraFragmentOne;


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
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = MediaStore.Images.Media.query(contentResolver,MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection);
        while (cursor.moveToNext()){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String parentPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//            File partentFile = new File(path).getParentFile();
//            String absolutePath = partentFile.getAbsolutePath();
            cameraList.add(path);//手机中所有的图片路径
            if (!parentMap.containsKey(parentPath)){
                childList = new ArrayList<>();
                childList.add(path);
                parentMap.put(parentPath,childList);
            }else {
                childList.add(path);
            }
        }
        cameraList.add(0,"ic_image_from_camera");
        initView();
    }

    private void initView() {
        cameraFragmentOne = CameraFragmentOne.newInstances(cameraList,this);


        chooseFragment(cameraFragmentOne);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera_tool_bar_back_btn:
                finish();
                break;
            case R.id.fashion_tool_bar_camera:
                if (!mode){

                }else {

                }
                mode = !mode;
                break;
            case R.id.camera_tool_bar_content_tv:
                break;
        }
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

    @Override
    public void count() {
        ensureTv.setText("("+ DatasUtils.cameraCount.size()+"/6)确定");
    }
}
