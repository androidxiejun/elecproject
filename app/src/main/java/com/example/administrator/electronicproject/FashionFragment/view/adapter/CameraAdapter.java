package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.electronicproject.FashionFragment.bean.DatasUtils;
import com.example.administrator.electronicproject.FashionFragment.view.fragment.CameraFragmentOne;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/16.
 */
public class CameraAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private CameraFragmentOne.CameraCallBack cameraCallBack;

    public CameraAdapter(Context context,List<String> list,CameraFragmentOne.CameraCallBack cameraCallBack){
        this.context = context;
        this.list = list;
        this.cameraCallBack = cameraCallBack;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CameraHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.camera_fragment_one_item,viewGroup,false);
            holder = new CameraHolder(view);
        }else {
            holder = (CameraHolder) view.getTag();
        }
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        //第一次采样
//        BitmapFactory.decodeFile(list.get(i),options);
//        int radio = options.outHeight / 60;
//        if (radio <= 0){
//            radio = 30;
//        }
//        options.inSampleSize = radio;
//        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeFile(list.get(i), options);
//        holder.image.setImageBitmap(bitmap);
//        ImageLoader.init(context).load(list.get(i),holder.image);
        if (i == 0){
            holder.image.setImageResource(R.drawable.ic_image_from_camera);
            holder.chose.setVisibility(View.INVISIBLE);
        }else {
            Picasso.with(context).load(new File(list.get(i))).into(holder.image);
        }
        holder.chose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (DatasUtils.cameraCount.contains(list.get(i))){
                    DatasUtils.cameraCount.remove(list.get(i));
                }else {
                    if (DatasUtils.cameraCount.size() < 6){
                        DatasUtils.cameraCount.add(list.get(i));
                    }else {
                        compoundButton.setChecked(false);
                        Toast.makeText(context,"最多只能选择6张图片",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.i("CameraSize",DatasUtils.cameraCount.size()+"");
                cameraCallBack.count();
            }
        });
        return view;
    }

    class CameraHolder{
        public CameraHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.camera_fragment_one_item_iv)
        ImageView image;
        @BindView(R.id.camera_fragment_one_item_btn)
        CheckBox chose;
    }

}
