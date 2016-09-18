package com.example.administrator.electronicproject.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.electronicproject.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sunbin on 2016/9/17.
 * 拍照界面
 */
public class TackPhotoActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tack_photo_layout);

        initView();
    }

    private void initView() {
        //1,初始化SurfaceView
        mSurfaceView = (SurfaceView) findViewById(R.id.picature_surfae_view);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }

    /**
     * 点击拍照按钮进行拍照,并保存照片
     */
    public void takePicature(View view){
        //自动聚焦
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean b, Camera camera) {
                //拍照
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        //保存图片
                        saveBitmap(bitmap);
                        //暂停和重启预览
                        mCamera.stopPreview();
                        mCamera.startPreview();
                    }
                });
            }
        });
    }

    private void saveBitmap(Bitmap bitmap){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "picature";
        File dirFile = new File(path);
        if ( ! dirFile.exists()){
            dirFile.mkdir();
        }
        Date date = new Date();
        File file = new File(dirFile, date+".jpg");

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //2.打开摄像头
        mCamera = Camera.open();
        //3.设置摄像头参数
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.set("jpeg-quality",100);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        parameters.setPictureSize(display.getWidth(),display.getHeight());
        mCamera.setParameters(parameters);
        try {
            //4.设置摄像头的预览界面
            mCamera.setPreviewDisplay(mHolder);
            //5.开始预览
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mCamera != null){
            //释放摄像头的资源
            mCamera.stopPreview();
            mCamera.release();
        }
    }
}
