package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.DatasUtils;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.R;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by sunbin on 2016/9/10.
 */
public class TopDetailsThreeImageAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.ImgsBean> imgs;

    public TopDetailsThreeImageAdapter(Context context, List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.ImgsBean> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ThreeHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fashion_recommend_top_details_item_two, viewGroup, false);
            holder = new ThreeHolder();
            holder.imageView = (GifImageView) view.findViewById(R.id.recommend_top_details_iv);
            view.setTag(holder);
        } else {
            holder = (ThreeHolder) view.getTag();
        }

        String picUrl = imgs.get(position).getComponent().getPicUrl();
        int height = Integer.valueOf(imgs.get(position).getHeight());
        int width = Integer.valueOf(imgs.get(position).getWidth());
        int setHeight = holder.imageView.getHeight();


        if ((DatasUtils.windowWight/width)*height > setHeight){
            ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
            layoutParams.height = (width/DatasUtils.windowWight)*height;
            holder.imageView.setLayoutParams(layoutParams);
        }
        if (picUrl != null){
            int gif = picUrl.indexOf(".gif");
            if (gif > 0){
                //大于0表示是gif图
                downGif(picUrl,holder.imageView);
            }else {
                //小于0表示不是gif图
                ImageLoader.init(context).load(picUrl,holder.imageView);
            }
        }
        return view;
    }

    class ThreeHolder {
        private GifImageView imageView;
    }

    private void downGif(final String urlString, final GifImageView imageView) {

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                GifDrawable bytes = (GifDrawable) msg.obj;
                imageView.setImageDrawable(bytes);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                //网络请求加载图片
                InputStream inputStream = null;
                ByteArrayOutputStream baos = null;
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = urlConnection.getInputStream();
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        baos = new ByteArrayOutputStream();
                        while ((len = inputStream.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        baos.flush();
                        //流转换成Bitmap对象
                        byte[] bytes = baos.toByteArray();
                        GifDrawable gifDrawable = new GifDrawable(bytes);
                        Message message = mHandler.obtainMessage();
                        message.obj = gifDrawable;
                        mHandler.sendMessage(message);

                    }

                    close(inputStream);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(inputStream);
                    close(baos);
                }
            }
        }).start();
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
