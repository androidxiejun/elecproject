package com.example.administrator.electronicproject.SweepQrCode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
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

public class TakePictureActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        initViews();
    }

    private void initViews() {
        //1，初始化Surfaceview
        mSurfaceView = (SurfaceView)findViewById(R.id.preview_surface);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }

    public void onTakePictureClick(View view) {
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                //拍照
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        //对照片数据进行处理
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        saveBitmap(bitmap);
                        //暂停并重新预览
                        mCamera.stopPreview();
                        mCamera.startPreview();
                    }
                });
            }
        });
    }

    //保存bitmap到磁盘
    private void saveBitmap(Bitmap bitmap){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "pictures";
//        String dirPath2=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        File picFile = new File(dir,"pic.jpg");
        try {
            FileOutputStream outputStream = new FileOutputStream(picFile);
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
    public void surfaceCreated(SurfaceHolder holder) {
        //2，打开摄像头
        mCamera = Camera.open();
        //3，设置摄像头的参数
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.set("jpeg-quality",100);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        parameters.setPictureSize(display.getWidth(),display.getHeight());
        mCamera.setParameters(parameters);
        //4,设置摄像头的预览界面
        try {
            mCamera.setPreviewDisplay(mHolder);
            //5，开始预览
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //释放摄像头资源
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
        }
    }
}
