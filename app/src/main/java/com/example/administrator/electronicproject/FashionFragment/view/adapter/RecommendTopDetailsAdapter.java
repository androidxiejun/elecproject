package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.R;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by sunbin on 2016/9/7.
 */
public class RecommendTopDetailsAdapter extends BaseAdapter{

    private List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean> detailsLists ;
    private Context context;
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    public RecommendTopDetailsAdapter(Context context,List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean> detailsLists){
        this.context = context;
        this.detailsLists = detailsLists;
    }


    @Override
    public int getCount() {
        return detailsLists == null ? 0 : detailsLists.size();
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
        View itemView = view;
        switch (getItemViewType(position)){
            case TYPE_ONE:
                TopDetailsHolderOne holderOne = null;
//                if (itemView == null){
                    itemView = LayoutInflater.from(context).inflate(R.layout.fashion_recommend_top_details_item_one,viewGroup,false);
                    holderOne = new TopDetailsHolderOne();
                    holderOne.textView = (TextView) itemView.findViewById(R.id.recommend_top_details_title_item);
                    itemView.setTag(holderOne);
//                }else {
//                    holderOne = (TopDetailsHolderOne) itemView.getTag();
//                }
                String text = detailsLists.get(position).getComponent().getText();
                if (text != null){
                    holderOne.textView.setText(text);
                }
                break;
            case TYPE_TWO:
                TopDetailsHolderTwo holderTwo = null;
//                if (itemView == null){
                    itemView = LayoutInflater.from(context).inflate(R.layout.fashion_recommend_top_details_item_two,viewGroup,false);
                    holderTwo = new TopDetailsHolderTwo();
                    holderTwo.imageView = (GifImageView) itemView.findViewById(R.id.recommend_top_details_iv);
                    itemView.setTag(holderTwo);
//                }else {
//                    holderOne = (TopDetailsHolderOne) itemView.getTag();
//                }
                String picUrl = detailsLists.get(position).getComponent().getPicUrl();
                String height = detailsLists.get(position).getHeight();
                int setHeight = holderTwo.imageView.getHeight();
                if (Integer.valueOf(height) > setHeight){
                    ViewGroup.LayoutParams layoutParams = holderTwo.imageView.getLayoutParams();
                    layoutParams.height = Integer.valueOf(height);
                    holderTwo.imageView.setLayoutParams(layoutParams);
                }
                if (picUrl != null){
                    int gif = picUrl.indexOf(".gif");
                    if (gif > 0){
                        //大于0表示是gif图
                        downGif(picUrl,holderTwo.imageView);
                    }else {
                        //小于0表示不是gif图
                        ImageLoader.init(context).load(picUrl,holderTwo.imageView);
                    }
                }
                break;
        }
        return itemView;
    }

    class TopDetailsHolderOne{
       private TextView textView;
        private GifImageView imageView;
    }

    class TopDetailsHolderTwo{
        private GifImageView imageView;
    }

    @Override
    public int getItemViewType(int position) {
        String componentType = detailsLists.get(position).getComponent().getComponentType();
        if (componentType.equals("msgText")){
            return TYPE_ONE;
        }else if (componentType.equals("cell")){
            return TYPE_TWO;
        }
        return super.getItemViewType(position);
    }



    private void downGif(final String urlString, final GifImageView imageView){

        final Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                GifDrawable  bytes = (GifDrawable ) msg.obj;
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
                        while((len = inputStream.read(buffer)) != -1) {
                            baos.write(buffer,0,len);
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
