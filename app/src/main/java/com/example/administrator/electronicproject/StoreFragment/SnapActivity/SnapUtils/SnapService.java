package com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapUtils;

import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapBean.SnapTimeBean;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapBean.SnapingBean;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapBean.StartingBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/13.
 */
public interface SnapService {
    @GET("/tuanlist_new?cateId=0&ga=%2Ftuanlist_new&visit=&flag=")
    Call<SnapingBean>querySnapingBean();
    @GET("/tuanlist_new?cateId=0&ga=%2Ftuanlist_new&visit=starting&flag=")
    Call<StartingBean>queryStartingBean();
    @GET("/tuan?ga=%2Ftuan&flag=&with_main=1")
    Call<SnapTimeBean>querySnapTimeBean(@Query("tuan_id") String tuan_id);
}
