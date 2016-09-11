package com.example.administrator.electronicproject.StoreFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/5.
 */
public class StoreFragment extends Fragment {
    private Context context;
    private StoreHomePageFragment storeFragment;
    private FragmentManager manager;
    private int index;
    public static Map<Integer,StoreHomePageBean.DataBean>dataMap=new HashMap<>();
    public static StoreFragment newInstance(){
        return new StoreFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.store_fragment_layout,container,false);
        initFragment();
        return view;
    }
    private void initFragment() {
        manager=getFragmentManager();
        storeFragment=StoreHomePageFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.store_fragment,storeFragment);
        fragmentTransaction.commit();
    }

}
