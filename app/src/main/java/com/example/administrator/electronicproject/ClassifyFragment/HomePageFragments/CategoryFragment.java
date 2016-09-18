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
public class CategoryFragment extends Fragment implements ICallback{
    private Context context;
    private HomePageInfo msgInfo;
    public static  List<String>titleList=new ArrayList<>();
    private List<HomePageInfo>storeList;
    private ClassifyStoreFragment storeFragment;
    private ClassifyListFragment listFragment;
    private FragmentManager manager;
    public static  Map<Integer,List<HomePageInfo>>dataMap=new HashMap<>();
    public static final String URL_PATH="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
    public static CategoryFragment newInstance(){
        return new CategoryFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_tablayout_fragment_layout,container,false);
        getInfo();
        initView(view);
        return view;
    }
    private void initView(View view) {
        manager=getFragmentManager();
        storeFragment=ClassifyStoreFragment.newInstance();
        listFragment=ClassifyListFragment.newInstance();
        listFragment.setCallBack(storeFragment);
    }
    private void getInfo(){
        HttpUtils.load(URL_PATH).callback(this,1);
    }
    @Override
    public void success(String result, int requestCode) {
        try {
            JSONObject jsonObject=new JSONObject(result);
            String dataString=jsonObject.getString("data");
            JSONObject jsonObjectData=new JSONObject(dataString);
            JSONArray jsonArray=jsonObjectData.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                storeList=new ArrayList<>();
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                JSONObject jsonObject2=jsonObject1.getJSONObject("component");
                String title=jsonObject2.getString("title");
                titleList.add(title);
                JSONArray jsonArray1=jsonObject2.getJSONArray("items");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    msgInfo=new HomePageInfo();
                    JSONObject jsonObject3=jsonArray1.getJSONObject(j);
                    JSONObject jsonObject4=jsonObject3.getJSONObject("component");
                    JSONObject jsonObject5=jsonObject4.getJSONObject("action");
                    msgInfo.storeTitle=jsonObject4.getString("word");
                    msgInfo.storeId=jsonObject4.getString("id");
                    msgInfo.storeUrl=jsonObject4.getString("picUrl");
                    msgInfo.storeQuery=jsonObject5.getString("query");
                    storeList.add(msgInfo);
                }
                dataMap.put(i,storeList);
            }
            initFragment();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.classify_tablayout_framelayout,storeFragment);
        fragmentTransaction.add(R.id.classify_tablayout_framelayout_left,listFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
