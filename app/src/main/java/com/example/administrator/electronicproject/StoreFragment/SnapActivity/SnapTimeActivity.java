package com.example.administrator.electronicproject.StoreFragment.SnapActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapBean.SnapTimeBean;
import com.example.administrator.electronicproject.StoreFragment.SnapActivity.SnapUtils.SnapHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SnapTimeActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.snap_time_back_btn)
    Button backBtn;
    @BindView(R.id.snap_time_share_btn)
    Button shareBtn;
    @BindView(R.id.snap_time_title)
    TextView title;
    @BindView(R.id.snap_time_pull_to_list_view)
    PullToRefreshListView mListView;
    private Context context;
    private String  tuan_id;
    private ImageView topImg;
    private TextView descriptionTxt;
    private MyListViewAdapter adapter;
    private SnapTimeBean.DataBean.MainBean main;
    private  List<SnapTimeBean.DataBean.ItemsBean> items;
    public static final String SHARE_URL="http://m.hichao.com/app/templates/brand_detail.html?id=3051";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_time);
        ButterKnife.bind(this);
        backBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);
        context=this;
        initIntent();
        initAdapter();
        initInfo(tuan_id);
    }

    private void initIntent() {
        Intent intent = getIntent();
        tuan_id= intent.getStringExtra("tuan_id");
    }

    private void initAdapter() {
        View view=LayoutInflater.from(context).inflate(R.layout.snap_time_head_layout,null);
        topImg= (ImageView) view.findViewById(R.id.snap_time_head_img);
        descriptionTxt= (TextView) view.findViewById(R.id.snap_time_head_description);
        adapter=new MyListViewAdapter();
        ListView refreshableView = mListView.getRefreshableView();
        refreshableView.addHeaderView(view);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SnapTimeBean.DataBean.ItemsBean.ComponentBean component = items.get(i).getComponent();
                Intent intent=new Intent(context, PurchaseDetails.class);
                intent.putExtra("source_id","2317533");
                startActivity(intent);
            }
        });
    }

    private void initInfo(String tuan_id) {
        SnapHttpUtils.create().querySnapTimeBean(tuan_id).enqueue(new Callback<SnapTimeBean>() {
            @Override
            public void onResponse(Call<SnapTimeBean> call, Response<SnapTimeBean> response) {
                items=response.body().getData().getItems();
                main= response.body().getData().getMain();
                title.setText(main.getTitle());
                Picasso.with(context).load(main.getPicUrl()).into(topImg);
                descriptionTxt.setText(main.getDescription());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SnapTimeBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.snap_time_back_btn:
                finish();
                break;
            case R.id.snap_time_share_btn:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,SHARE_URL);
                sendIntent.setType("text/*");
                startActivity(sendIntent);
                break;
        }
    }
    class MyListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items==null?0:items.size();
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
        public View getView(int i, View view2, ViewGroup viewGroup) {
            SnapTimeBean.DataBean.ItemsBean.ComponentBean component = items.get(i).getComponent();
            View view=view2;
            ViewHolder viewHolder=null;
            if(view==null){
                view= LayoutInflater.from(context).inflate(R.layout.snap_time_list_view_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            Picasso.with(context).load(component.getPicUrl()).into(viewHolder.imageView);
            viewHolder.title.setText(component.getTitle());
            viewHolder.currentPrice.setText(component.getPrice());
            viewHolder.originPrice.setText(component.getPriceOrig());
            viewHolder.originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.accountTxt.setText(component.getSaleNum());
            viewHolder.discountBtn.setText(component.getDiscount()+"折");
            return view;
        }
        class ViewHolder {
            public ImageView imageView;
            public TextView title,currentPrice,originPrice,accountTxt;
            public Button discountBtn;
            public ViewHolder(View view){
                view.setTag(this);
                this.imageView= (ImageView) view.findViewById(R.id.snap_time_list_view_img);
                this.title= (TextView) view.findViewById(R.id.snap_time_list_view_title);
                this.currentPrice= (TextView) view.findViewById(R.id.snap_time_list_view_curreentprice);
                this.originPrice= (TextView) view.findViewById(R.id.snap_time_list_view_ooriginprice);
                this.accountTxt= (TextView) view.findViewById(R.id.snap_time_list_view_account);
                this.discountBtn= (Button) view.findViewById(R.id.snap_time_list_view_discount);
            }
        }
    }
}
