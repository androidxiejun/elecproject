package com.example.administrator.electronicproject.ClassifyFragment.HomePageFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidxx.yangjw.httplibrary.HttpUtils;
import com.androidxx.yangjw.httplibrary.ICallback;
import com.example.administrator.electronicproject.ClassifyFragment.HomePageInfo;
import com.example.administrator.electronicproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BrandFragment extends Fragment implements ICallback{
    private Context context;
    private BrandLeftFragment leftFragment;
    private BrandRightFragment rightFragment;
    private FragmentManager manager;
    public static List<String>brandList=new ArrayList<>();
    public static List<HomePageInfo>infoList;
    private HomePageInfo pageInfo;
    public static Map<Integer,List<HomePageInfo>>brandMap=new HashMap<>();
    public static final String URL_PATH="http://api-v2.mall.hichao.com/region/brands/list?ga=%2Fregion%2Fbrands%2Flist";

    public static BrandFragment newInstance(){
        return new BrandFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_tablayout_fragment_right_layout,container,false);
        getInfo();
        return view;
    }

    /**
     * 进行网络数据请求
     */
    private void getInfo(){
        HttpUtils.load(URL_PATH).callback(this,3);
    }

    /**
     * 初始化fragment，并将fragment显示在界面
     */
    private void initFragment() {
        manager=getFragmentManager();
        leftFragment=BrandLeftFragment.newInstance();
        rightFragment=BrandRightFragment.newInstance();
        leftFragment.setCallBack(rightFragment);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.classify_tablayout_framelayout_brand_left,leftFragment);
        fragmentTransaction.add(R.id.classify_tablayout_framelayout_brand_right,rightFragment);
        fragmentTransaction.commit();
    }

    /**
     * 数据请求成功，进行JSON解析
     * @param result
     * @param requestCode
     */
    @Override
    public void success(String result, int requestCode) {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(result);
            String dataString=jsonObject.getString("data");
            JSONObject jsonObjectData=new JSONObject(dataString);
            JSONArray jsonArray=jsonObjectData.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                infoList=new ArrayList<>();
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                JSONObject jsonObject2=jsonObject1.getJSONObject("component");
                String title=jsonObject2.getString("title");
                brandList.add(title);
                JSONArray jsonArray1=jsonObject2.getJSONArray("items");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    pageInfo=new HomePageInfo();
                    JSONObject jsonObject3=jsonArray1.getJSONObject(j);
                    JSONObject jsonObject4=jsonObject3.getJSONObject("component");
                    pageInfo.storeUrl=jsonObject4.getString("picUrl");
                    pageInfo.storeId=jsonObject4.getString("id");
                    infoList.add(pageInfo);
                }
                brandMap.put(i,infoList);
            }
            initFragment();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
