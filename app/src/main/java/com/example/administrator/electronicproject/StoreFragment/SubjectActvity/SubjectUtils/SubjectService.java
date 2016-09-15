package com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectUtils;

import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectBean.SubjectBean;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.SubjectBean.SubjectCommonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/12.
 */
public interface SubjectService {
    @GET("/topic/view?width=1080&ga=%2Ftopic%2Fview&twm=1")
    Call<SubjectBean>queryBean(@Query("topic_id")int topic_id);
    @GET("/comment/comments?asc=0&rtf=1&ga=%2Fcomment%2Fcomments0&type=topic")
    Call<SubjectCommonBean>queryCommonBean(@Query("flag") int flag,@Query("id")int id);
}
