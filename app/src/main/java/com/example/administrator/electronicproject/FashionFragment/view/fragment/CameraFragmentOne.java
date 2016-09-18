package com.example.administrator.electronicproject.FashionFragment.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.electronicproject.FashionFragment.bean.DatasUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.CameraAdapter;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.activity.TackPhotoActivity;

import java.util.List;

/**
 * Created by sunbin on 2016/9/16.
 */
public class CameraFragmentOne extends Fragment {

    private Context context;
    private GridView cameraGrid;
    private static List<String> list;
    private CameraAdapter cameraAdapter;
    private static CameraCallBack cameraCallBack;

    public interface CameraCallBack{
        void count();
    }

    public static CameraFragmentOne newInstances(List<String> lists,CameraCallBack callBack){
        list = lists;
        cameraCallBack = callBack;
        return new CameraFragmentOne();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.camera_fragment_one, container, false);
        cameraGrid = (GridView) inflate.findViewById(R.id.camera_grid_layout);
        cameraAdapter = new CameraAdapter(context,list,cameraCallBack);
        cameraGrid.setAdapter(cameraAdapter);

        initListener();
        return inflate;
    }

    private void initListener() {
        cameraGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    //调用系统相机
                    Intent intent = new Intent(context, TackPhotoActivity.class);
                    startActivity(intent);
                }else {
                    //展开大图
                }
            }
        });
    }
}
