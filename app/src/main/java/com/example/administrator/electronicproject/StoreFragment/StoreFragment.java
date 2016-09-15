package com.example.administrator.electronicproject.StoreFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.SearchActivity.SearchActivity;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageBean;
import com.example.administrator.electronicproject.SweepQrCode.TakePictureActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/5.
 */
public class StoreFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private StoreHomePageFragment storeFragment;
    private FragmentManager manager;
    private Button sweepBtn,searchBtn,shoppingBtn;
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
        initView(view);
        return view;
    }

    private void initView(View view) {
        sweepBtn= (Button) view.findViewById(R.id.store_sweep_btn);
        searchBtn= (Button) view.findViewById(R.id.store_search_btn);
        shoppingBtn= (Button) view.findViewById(R.id.store_shopping_btn);
        sweepBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        shoppingBtn.setOnClickListener(this);
    }

    private void initFragment() {
        manager=getFragmentManager();
        storeFragment=StoreHomePageFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.store_fragment,storeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.store_sweep_btn:
                Intent intent=new Intent(context,TakePictureActivity.class);
                startActivity(intent);
                break;
            case R.id.store_search_btn:
                Intent intent2=new Intent(context, SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.store_shopping_btn:
                //TODO
                break;
        }
    }
}
