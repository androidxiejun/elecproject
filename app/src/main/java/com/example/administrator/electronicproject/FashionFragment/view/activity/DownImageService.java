package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunbin on 2016/9/16.
 */
public class DownImageService extends Service {

    private Messenger messenger;
    //声明ActivityA的Messenger对象
    private Messenger activityMessenger= null;
    //声明ActivityB的Messenger对象
    private static ExecutorService service;
    private String urlString;
    private Context context;
    private final static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/download_image/";


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    urlString = (String) msg.obj;
                    initUrl(urlString);
                    break;
                case 2:
                    Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        service = Executors.newFixedThreadPool(3);
        messenger = new Messenger(handler);
        return messenger.getBinder();
    }


    /**
     * 获取apk的文件保存路径，通过获取url地址的最后一段作为保存路径
     * @param urlString 文件下载url
     */
    private void initUrl(String urlString) {
        int indexOf = urlString.lastIndexOf("/")+1;
        String path = urlString.substring(indexOf, urlString.length());
        downImage(urlString,path);
    }

    private void downImage(final String urlString,final String path){
        File dirFile = new File(ALBUM_PATH);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        final File myCaptureFile = new File(ALBUM_PATH + path);
        if (myCaptureFile.exists()){
            Toast.makeText(context,"图片已存在",Toast.LENGTH_SHORT).show();
            return;
        }
        service.execute(new Runnable() {
            InputStream inputStream;
            FileOutputStream outputStream;

            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        inputStream = urlConnection.getInputStream();
                        outputStream = new FileOutputStream(myCaptureFile);
                        int ln = 0;
                        byte[] buffer = new byte[1024];
                        while ((ln = inputStream.read(buffer)) != -1){
                            outputStream.write(buffer,0,ln);
                        }
                        outputStream.flush();
                        close(inputStream);
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        message.sendToTarget();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    close(inputStream);
                    close(outputStream);
                }
            }
        });
    }

    private void close(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
