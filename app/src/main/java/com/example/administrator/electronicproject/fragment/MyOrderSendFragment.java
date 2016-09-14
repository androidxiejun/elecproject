package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.electronicproject.R;

/**
 * Created by sunbin on 2016/9/12.
 * 我的商品订单，待发货
 */
public class MyOrderSendFragment extends Fragment {

    private Context context;

    public static MyOrderSendFragment newInstance(){
        return new MyOrderSendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //暂时代替
        View inflate = inflater.inflate(R.layout.mycollection_fragment_one, container, false);
        return inflate;
    }
}
