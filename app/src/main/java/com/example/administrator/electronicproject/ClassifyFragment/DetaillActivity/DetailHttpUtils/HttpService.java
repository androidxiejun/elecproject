package com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailHttpUtils;

import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailBean.ClassifyDetailCategoryBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/7.
 */
public interface HttpService {
    @GET("/search/skus?ga=%2Fsearch%2Fskus&cat=&asc=1&sort=all")
    Call<ClassifyDetailCategoryBean>queryBean(@Query("query") String query,@Query("sort")String sort,@Query("flag")String flag);
}
