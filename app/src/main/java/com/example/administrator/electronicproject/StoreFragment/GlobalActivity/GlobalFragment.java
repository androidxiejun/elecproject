package com.example.administrator.electronicproject.StoreFragment.GlobalActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.CustomGridView.CustomGridView;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.FirCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.GlobalTopImageBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.RecyclerBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.SecCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.TabBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalUtils.GlobalHttpUtils;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/11.
 */
public class GlobalFragment extends Fragment {
    private Context context;
    private TabLayout firTab,secTab;
    private RecyclerView mRecyclerView;
    private CustomGridView customGridView;
    private ImageView topImage;
    private UltimateRecyclerView mUltimate;
    private int id;
    private  List<TabBean.DataBean.ItemsBean> tabItemList;
    private  List<RecyclerBean.DataBean.ItemsBean> recyclerItemList;
    private  List<FirCustomGridBean.DataBean.ItemsBean> firGridList;
    private  List<SecCustomGridBean.DataBean.ItemsBean> secGridList;
    private  List<GlobalTopImageBean.DataBean.ItemsBean> topImageList;

    public static GlobalFragment newInstance(){
        return new GlobalFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.store_global_frame_layout,container,false);
        initView(view);
        initTabInfo(1458);
        initRecyclerInfo(1458);
        initListInfo(1458);
        initGridInfo("",1458);
        initTopImageInfo(1458);
        return view;
    }




    private void initSecTab() {
        secTab.removeAllTabs();
        for (int i = 0; i < tabItemList.size(); i++) {
            TabBean.DataBean.ItemsBean itemsBean = tabItemList.get(i);
            secTab.addTab(secTab.newTab().setText(itemsBean.getNav_name()));
        }
    }
    private void initTabInfo(int region) {
      GlobalHttpUtils.create().queryTabBean(region).enqueue(new Callback<TabBean>() {
          @Override
          public void onResponse(Call<TabBean> call, Response<TabBean> response) {
              tabItemList = response.body().getData().getItems();
              initSecTab();
          }

          @Override
          public void onFailure(Call<TabBean> call, Throwable t) {

          }
      });
    }
    private void initRecyclerInfo(int id) {
      GlobalHttpUtils.create().queryRecyclerBean(id).enqueue(new Callback<RecyclerBean>() {
          @Override
          public void onResponse(Call<RecyclerBean> call, Response<RecyclerBean> response) {
              recyclerItemList = response.body().getData().getItems();
          }

          @Override
          public void onFailure(Call<RecyclerBean> call, Throwable t) {

          }
      });
    }
    private void initListInfo(int id) {
        GlobalHttpUtils.create().queryFirGridBean(id).enqueue(new Callback<FirCustomGridBean>() {
            @Override
            public void onResponse(Call<FirCustomGridBean> call, Response<FirCustomGridBean> response) {
                firGridList = response.body().getData().getItems();
            }

            @Override
            public void onFailure(Call<FirCustomGridBean> call, Throwable t) {

            }
        });
    }
    private void initGridInfo(String cat,int id) {
        GlobalHttpUtils.create().querySecGridBean("",id).enqueue(new Callback<SecCustomGridBean>() {
            @Override
            public void onResponse(Call<SecCustomGridBean> call, Response<SecCustomGridBean> response) {
                secGridList= response.body().getData().getItems();
            }

            @Override
            public void onFailure(Call<SecCustomGridBean> call, Throwable t) {

            }
        });
    }
    private void initTopImageInfo(int id){
        GlobalHttpUtils.create().queryImageBean(id).enqueue(new Callback<GlobalTopImageBean>() {
            @Override
            public void onResponse(Call<GlobalTopImageBean> call, Response<GlobalTopImageBean> response) {
                topImageList= response.body().getData().getItems();
                String picUrl = topImageList.get(0).getComponent().getPicUrl();
                Picasso.with(context).load(picUrl).into(topImage);
            }

            @Override
            public void onFailure(Call<GlobalTopImageBean> call, Throwable t) {

            }
        });
    }
    private void initView(View view) {
        topImage= (ImageView) view.findViewById(R.id.store_global_topimag);
        firTab= (TabLayout) view.findViewById(R.id.store_global_first_tab);
        secTab= (TabLayout) view.findViewById(R.id.store_global_second_tab);
        mUltimate= (UltimateRecyclerView) view.findViewById(R.id.store_global_ultimate);
        firTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                    id=1462;
                        initTabInfo(id);
                        initRecyclerInfo(id);
                        initListInfo(id);
                        initGridInfo("",id);
                        break;
                    case 1:
                        id=5599;
                        initTabInfo(id);
                        break;
                    case 2:
                        id=1458;
                        initTabInfo(id);
                        break;
                    case 3:
                        id=5634;
                        initTabInfo(id);
                        break;
                    case 4:
                        id=1460;
                        initTabInfo(id);
                        break;
                    case 5:
                        id=1459;
                        initTabInfo(id);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
