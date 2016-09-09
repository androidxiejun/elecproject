package com.example.administrator.electronicproject.FashionFragment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.electronicproject.FashionFragment.bean.TableBean;
import com.example.administrator.electronicproject.FashionFragment.http.HttpUtils;
import com.example.administrator.electronicproject.FashionFragment.view.adapter.FashionMiddleTableAdapter;
import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sunbin on 2016/9/8.
 * 时尚圈中，全部的标签显示界面
 */
public class FashionMiddleTableActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.fashion_table_bar_back_btn)
    Button backBtn;

    private ListView tableListView;
    private List<TableBean.ResponseBean.DataBean.ItemsBean> tableListDatas = new ArrayList<>();
    private FashionMiddleTableAdapter fashionMiddleTableAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fashion_middle_table);
        ButterKnife.bind(this);
        tableListView = (ListView) findViewById(R.id.fashion_middle_table_list);

        initView();
    }

    private void initView() {

        backBtn.setOnClickListener(this);

        fashionMiddleTableAdapter = new FashionMiddleTableAdapter(tableListDatas,this);
        tableListView.setAdapter(fashionMiddleTableAdapter);

        initData();
    }

    private void initData() {
        /**
         * 时尚圈中部标签的数据请求
         */
        HttpUtils.create().fashionMiddleTableDatas().enqueue(new Callback<TableBean>() {

            @Override
            public void onResponse(Call<TableBean> call, Response<TableBean> response) {
                if (tableListDatas != null){
                    tableListDatas.clear();
                }
                tableListDatas.addAll(response.body().getResponse().getData().getItems());
                fashionMiddleTableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TableBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fashion_table_bar_back_btn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
