package com.example.administrator.electronicproject.StoreFragment.SubjectActvity.BigImageActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.electronicproject.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BigImageActvity extends AppCompatActivity {
    private ImageView saveImage, bigImage;
    private Context context;
    private String picUrl;
    private Bitmap bitmap;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_actvity);
        context = this;
        initIntent();
        initView();
    }

    private void initIntent() {
        Intent intent = getIntent();
        picUrl = intent.getStringExtra("picUrl");
    }

    /**
     * 进行保存图片的操作
     */
    private void initView() {
        saveImage = (ImageView) findViewById(R.id.save_img);
        bigImage = (ImageView) findViewById(R.id.big_image_view);
        new LoadImageTask().run();
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pictures";
                File file = new File(picPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                int index = picUrl.lastIndexOf("/");
                String urlPath=picUrl.substring(index,picUrl.length());
                File picFile=new File(file,urlPath+".jpg");
                try {
                    FileOutputStream outputStream = new FileOutputStream(picFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    mHandler.sendEmptyMessage(1);
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(BigImageActvity.this, "保存成功", Toast.LENGTH_SHORT).show();
        }
    };
    class LoadImageTask implements Runnable {

        @Override
        public void run() {
            InputStream inputStream = null;
            try {
                URL url = new URL(picUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = url.openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    bigImage.setImageBitmap(bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
