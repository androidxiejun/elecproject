package com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailBean.ClassifyDetailCategoryBean;
import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.DetailHttpUtils.HttpUtils;
import com.example.administrator.electronicproject.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassifyDetiailActivity extends AppCompatActivity implements View.OnClickListener{
    private TabLayout mTablayout;
    private TextView mText;
    private Button backBtn;
    private String query,title,sort,flag;
    private GridViewAdapter mAdapter;
    private PullToRefreshGridView mGridView;
    private int index=1;
    private Context context;
    private  List<ClassifyDetailCategoryBean.DataBean.ItemsBean> items=new ArrayList<>();
    private ClassifyDetailCategoryBean.DataBean.ItemsBean.ComponentBean component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_store_details_layout);
        context=this;
        initIntent();
        initView();
        getInfo(query,"all","");
    }

    private void initIntent() {
        Intent intent = getIntent();
        query=intent.getStringExtra("query");
        title=intent.getStringExtra("title");
    }
    private void getInfo(String query,String sort,String flag){
        HttpUtils.create().queryBean(query,sort,flag).enqueue(new Callback<ClassifyDetailCategoryBean>() {
            @Override
            public void onResponse(Call<ClassifyDetailCategoryBean> call, Response<ClassifyDetailCategoryBean> response) {
                List<ClassifyDetailCategoryBean.DataBean.ItemsBean> item = response.body().getData().getItems();
                items.addAll(item);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassifyDetailCategoryBean> call, Throwable t) {

            }
        });
    }
    private void initView() {
        mText= (TextView) findViewById(R.id.classify_detail_title);
        mText.setText(title);
        backBtn= (Button) findViewById(R.id.classify_detail_back_btn);
        backBtn.setOnClickListener(this);
        mTablayout= (TabLayout) findViewById(R.id.classify_detail_tab_layout);
        mGridView= (PullToRefreshGridView) findViewById(R.id.classify_detail_puul_to_gridview);
        mAdapter=new GridViewAdapter();
        mGridView.setAdapter(mAdapter);
        mGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("载入中");
                refreshView.getLoadingLayoutProxy().setPullLabel("下拉加载更多");
                refreshView.getLoadingLayoutProxy().setReleaseLabel("松手刷新");
                String label = DateUtils.formatDateTime(
                        getApplicationContext(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                // 显示最后更新的时间
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                flag="s1";
                new GetDataTask().execute();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("载入中");
                refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                refreshView.getLoadingLayoutProxy().setReleaseLabel("松手刷新");
                flag="s"+index++;
                new GetDataTask().execute();
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case 0:
                       sort="all";
                       getInfo(query,sort,flag);
                      break;
                   case 1:
                       sort="new";
                       getInfo(query,sort,flag);
                       break;
                   case 2:
                       sort="sale";
                       getInfo(query,sort,flag);
                       break;
                   case 3:
                       sort="price";
                       getInfo(query,sort,flag);
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

    @Override
    public void onClick(View view) {
        finish();
    }

    private class GetDataTask extends AsyncTask<Void, Void,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... voids) {
           getInfo(query,sort,flag);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean lean) {
            super.onPostExecute(lean);
            mGridView.onRefreshComplete();
        }
    }
    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
         return items.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View containView, ViewGroup viewGroup) {
            component=items.get(i).getComponent();
            View view=containView;
            ViewHolder viewHolder=null;
            if(view==null){
               view= LayoutInflater.from(context).inflate(R.layout.classify_category_detail_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.img.setImageResource(R.mipmap.ic_launcher);
            viewHolder.countryName.setText(component.getCountry());
            viewHolder.discriptionTxt.setText(component.getDescription());
            viewHolder.currentPrice.setText("￥"+component.getPrice());
            viewHolder.originPrice.setText("￥"+component.getOrigin_price());
            viewHolder.originPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            Picasso.with(context).load(component.getPicUrl()).into(viewHolder.img);
            String ciecleUrl=component.getNationalFlag();
            if(ciecleUrl!=""){
                Picasso.with(context).load(component.getNationalFlag()).into(viewHolder.circleImageView);
            }
            return view;
        }
        class ViewHolder {
            public ImageView img;
            public CircleImageView circleImageView;
            public TextView discriptionTxt;
            public TextView currentPrice;
            public TextView originPrice;
            public TextView countryName;
            public ViewHolder(View view){
                view.setTag(this);
                this.img= (ImageView) view.findViewById(R.id.classify_category_detail_img);
                this.circleImageView= (CircleImageView) view.findViewById(R.id.classify_category_detail_circlrcountry);
                this.countryName= (TextView) view.findViewById(R.id.classify_category_detail_countryname);
                this.discriptionTxt= (TextView) view.findViewById(R.id.classify_category_detail_description);
                this.currentPrice= (TextView) view.findViewById(R.id.classify_category_detail_currentprice);
                this.originPrice= (TextView) view.findViewById(R.id.classify_category_detail_originprice);
            }
        }
    }
}
