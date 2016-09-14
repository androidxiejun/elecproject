package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.bean.RecommendTopDetailsCommentBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.FashionTopDetailsCommentAdapter;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.RecommendAdapter;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunbin on 2016/9/11.
 */
public class RecommendActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.recommend_bar_back_btn)
    Button backBtn;
    @BindView(R.id.recommend_comment_et)
    EditText editText;
    @BindView(R.id.recommend_comment_grid)
    PullToRefreshListView recommend;

    private static int id;
    private Intent intent;
    private List<RecommendTopDetailsCommentBean.DataBean.ItemsBean> commentDatas = new ArrayList<>();
    private RecommendAdapter commentAdapter;
    private ListView refreshableView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_layout);
        ButterKnife.bind(this);

        intent = getIntent();
        id = intent.getIntExtra("id",0);

        initView();

    }

    private void initView() {

        backBtn.setOnClickListener(this);

        refreshableView = recommend.getRefreshableView();
        View footView = LayoutInflater.from(this).inflate(R.layout.recommend_foot, null);
        refreshableView.addFooterView(footView);
        commentAdapter = new RecommendAdapter(this,commentDatas);
        recommend.setAdapter(commentAdapter);

        initDatas();
    }

    private void initDatas() {
        /**
         * 网络请求详情foot中的评论的数据
         */
        HttpUtils.create().fashionRecommendTopDetailsCommentDatas(id).enqueue(new Callback<RecommendTopDetailsCommentBean>() {
            @Override
            public void onResponse(Call<RecommendTopDetailsCommentBean> call, Response<RecommendTopDetailsCommentBean> response) {
                RecommendTopDetailsCommentBean.DataBean data = response.body().getData();
                if (commentDatas != null) {
                    commentDatas.clear();
                }
                commentDatas.addAll(data.getItems());
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RecommendTopDetailsCommentBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.recommend_bar_back_btn:
                finish();
                break;
        }
    }
}
