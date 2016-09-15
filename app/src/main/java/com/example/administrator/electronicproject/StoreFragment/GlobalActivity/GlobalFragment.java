package com.example.administrator.electronicproject.StoreFragment.GlobalActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.DetailBrandActivity;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.StoreFragment.CustomGridView.CustomGridView;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.FirCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.GlobalTopImageBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.RecyclerBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.SecCustomGridBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean.TabBean;
import com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalUtils.GlobalHttpUtils;
import com.example.administrator.electronicproject.StoreFragment.SubjectActvity.ProjectActivity;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/11.
 */
public class GlobalFragment extends Fragment {
    private Context context;
    private TabLayout firTab, secTab;
    private RecyclerView mRecyclerView;
    private CustomGridView firCustomGridView;
    private ImageView topImage;
    private String select_id;
    private int integer;
    private StringAdapter stringAdapter;
    private UltimateRecyclerView mUltimate;
    private MyListViewAdapter listViewAdapter;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private List<TabBean.DataBean.ItemsBean> tabItemList;
    private List<RecyclerBean.DataBean.ItemsBean> recyclerItemList;
    private List<FirCustomGridBean.DataBean.ItemsBean> firGridList;
    private List<SecCustomGridBean.DataBean.ItemsBean> secGridList;
    private List<GlobalTopImageBean.DataBean.ItemsBean> topImageList;
    public static final int JAPAN_ID = 1462, MAKEUP_ID = 5599, CHINA_ID = 1458, GLOBAL_ID = 5634, KOREA_ID = 1460, EUROPE_ID = 1459;

    public static GlobalFragment newInstance() {
        return new GlobalFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_global_frame_layout, container, false);
        initView(view);
        initTabSelceted();
        initTabInfo(integer);
        initRecyclerInfo(integer);
        initListInfo(integer);
        initGridInfo("", integer);
        initTopImageInfo(integer);
        return view;
    }

    private void initTabSelceted() {
        select_id=GlobalActivity.china_id;
        integer= Integer.valueOf(select_id);
        switch (integer){
            case JAPAN_ID:
                TabLayout.Tab tabAt = firTab.getTabAt(0);
                tabAt.select();
                break;
            case MAKEUP_ID:
                TabLayout.Tab tabAt1 = firTab.getTabAt(1);
                tabAt1.select();
                break;
            case CHINA_ID:
                TabLayout.Tab tabAt2 = firTab.getTabAt(2);
                tabAt2.select();
                break;
            case GLOBAL_ID:
                TabLayout.Tab tabAt3 = firTab.getTabAt(3);
                tabAt3.select();
                break;
            case KOREA_ID:
                TabLayout.Tab tabAt4 = firTab.getTabAt(4);
                tabAt4.select();
                break;
            case EUROPE_ID:
                TabLayout.Tab tabAt5= firTab.getTabAt(5);
                tabAt5.select();
                break;

        }
    }


    private void initSecTab() {
        secTab.removeAllTabs();
        for (int i = 0; i < tabItemList.size(); i++) {
            final TabBean.DataBean.ItemsBean itemsBean = tabItemList.get(i);
            secTab.addTab(secTab.newTab().setText(itemsBean.getNav_name()));
        }
    }
   private void initGridViewChange(final int id){
       secTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               int position = tab.getPosition();
               initGridInfo(tabItemList.get(position).getCate_names(),id);
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
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
               recyclerViewAdapter.notifyDataSetChanged();
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
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FirCustomGridBean> call, Throwable t) {

            }
        });
    }

    private void initGridInfo(String cat, int id) {
        GlobalHttpUtils.create().querySecGridBean(cat, id).enqueue(new Callback<SecCustomGridBean>() {
            @Override
            public void onResponse(Call<SecCustomGridBean> call, Response<SecCustomGridBean> response) {
                secGridList = response.body().getData().getItems();
                stringAdapter=new StringAdapter(secGridList);
                mUltimate.setAdapter(stringAdapter);
            }

            @Override
            public void onFailure(Call<SecCustomGridBean> call, Throwable t) {

            }
        });
    }

    private void initTopImageInfo(int id) {
        GlobalHttpUtils.create().queryImageBean(id).enqueue(new Callback<GlobalTopImageBean>() {
            @Override
            public void onResponse(Call<GlobalTopImageBean> call, Response<GlobalTopImageBean> response) {
                topImageList = response.body().getData().getItems();
                String picUrl = topImageList.get(0).getComponent().getPicUrl();
                Picasso.with(context).load(picUrl).into(topImage);
            }

            @Override
            public void onFailure(Call<GlobalTopImageBean> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        mUltimate= (UltimateRecyclerView) view.findViewById(R.id.store_global_ultimate);
        firCustomGridView = (CustomGridView) view.findViewById(R.id.store_global_gridview);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.store_global_recycleview);
        mUltimate.setLayoutManager(new GridLayoutManager(context,2));
        topImage = (ImageView) view.findViewById(R.id.store_global_topimag);
        firTab = (TabLayout) view.findViewById(R.id.store_global_first_tab);
        secTab = (TabLayout) view.findViewById(R.id.store_global_second_tab);
        recyclerViewAdapter = new MyRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        listViewAdapter = new MyListViewAdapter();
        firCustomGridView.setAdapter(listViewAdapter);
        firTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        integer=JAPAN_ID;
                        initTopImageInfo(JAPAN_ID);
                        initTabInfo(JAPAN_ID);
                        initRecyclerInfo(JAPAN_ID);
                        initListInfo(JAPAN_ID);
                        initGridInfo("", JAPAN_ID);
                        initGridViewChange(JAPAN_ID);
                        break;
                    case 1:
                        integer=MAKEUP_ID;
                        initTabInfo(MAKEUP_ID);
                        initTopImageInfo(MAKEUP_ID);
                        initRecyclerInfo(MAKEUP_ID);
                        initListInfo(MAKEUP_ID);
                        initGridInfo("", MAKEUP_ID);
                        initGridViewChange(MAKEUP_ID);
                        break;
                    case 2:
                        integer=CHINA_ID;
                        initTabInfo(CHINA_ID);
                        initTopImageInfo(CHINA_ID);
                        initRecyclerInfo(CHINA_ID);
                        initListInfo(CHINA_ID);
                        initGridInfo("", CHINA_ID);
                        initGridViewChange(CHINA_ID);
                        break;
                    case 3:
                        integer=GLOBAL_ID;
                        initTabInfo(GLOBAL_ID);
                        initTopImageInfo(GLOBAL_ID);
                        initRecyclerInfo(GLOBAL_ID);
                        initGridInfo("", GLOBAL_ID);
                        initGridViewChange(GLOBAL_ID);
                        initListInfo(0);
                        break;
                    case 4:
                        integer=KOREA_ID;
                        initTabInfo(KOREA_ID);
                        initTopImageInfo(KOREA_ID);
                        initGridInfo("", KOREA_ID);
                        initGridViewChange(KOREA_ID);
                        initListInfo(0);
                        initRecyclerInfo(0);
                        break;
                    case 5:
                        integer=EUROPE_ID;
                        initTabInfo(EUROPE_ID);
                        initTopImageInfo(EUROPE_ID);
                        initListInfo(EUROPE_ID);
                        initGridInfo("", EUROPE_ID);
                        initGridViewChange(EUROPE_ID);
                        initRecyclerInfo(0);
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

    /**
     * recyclerview的适配器初始化
     */
    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.global_recycler_view_img);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.global_recycler_view_layout, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            final RecyclerBean.DataBean.ItemsBean.ComponentBean component = recyclerItemList.get(position).getComponent();
            Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //只有中国馆的recyclerview跳转至品牌详情界面
                    //其他品牌跳转
                    if(integer==MAKEUP_ID||integer==GLOBAL_ID){
                        Intent intent = new Intent(context, ProjectActivity.class);
                        String id = component.getAction().getId();
                        intent.putExtra("topic_id",id);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(context, DetailBrandActivity.class);
                        String id = component.getAction().getId();
                        intent.putExtra("business_id",id);
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return recyclerItemList == null ? 0 : recyclerItemList.size();
        }
    }

    /**
     * 中间的LISTVIEW的适配器
     */
    class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return firGridList==null?0:firGridList.size();
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
            final FirCustomGridBean.DataBean.ItemsBean.ComponentBean component = firGridList.get(i).getComponent();
            View view =view2;
            ViewHolder viewHolder=null;
            if(view==null){
                view=LayoutInflater.from(context).inflate(R.layout.global_list_view_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            Picasso.with(context).load(component.getPicUrl()).into(viewHolder.imageView);
            viewHolder.title.setText(component.getTitle());
            viewHolder.currentPrice.setText("￥"+component.getPrice());
            viewHolder.originPrice.setText("￥"+component.getOrigin_price());
            viewHolder.originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PurchaseDetails.class);
                    intent.putExtra("currentPrice", component.getPrice());
                    intent.putExtra("originPrice", component.getOrigin_price());
                    intent.putExtra("title", component.getTitle());
                    intent.putExtra("picUrl", component.getPicUrl());
                    intent.putExtra("source_id",component.getAction().getSourceId());
                    startActivity(intent);
                }
            });
            return view;
        }
        class ViewHolder {
            public ImageView imageView;
            public TextView title,currentPrice,originPrice;
            public ViewHolder(View view){
                view.setTag(this);
                this.imageView= (ImageView) view.findViewById(R.id.global_list_view_img);
                this.title= (TextView) view.findViewById(R.id.global_list_view_title);
                this.currentPrice= (TextView) view.findViewById(R.id.global_list_view_currentprice);
                this.originPrice= (TextView) view.findViewById(R.id.global_list_view_originprice);
            }
        }
    }
    public class StringViewHolder extends UltimateRecyclerviewViewHolder {
        public ImageView imageView;
        public TextView title, currentPrice, originPrice, countryName;
        public CircleImageView circleImageView;

        public StringViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                imageView = (ImageView) itemView.findViewById(R.id.brand_detail_ultimate_img);
                title = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_title);
                currentPrice = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_currentprice);
                originPrice = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_originprice);
                circleImageView = (CircleImageView) itemView.findViewById(R.id.brand_detail_ultimate_circlrcountry);
                countryName = (TextView) itemView.findViewById(R.id.brand_detail_ultimate_countryname);
            }
        }
    }

    /**
     * 最下面的ultimaterecyclerview的适配器
     */
    class StringAdapter extends UltimateViewAdapter<StringViewHolder> {
        private List<SecCustomGridBean.DataBean.ItemsBean> items;

        public StringAdapter(List<SecCustomGridBean.DataBean.ItemsBean> items) {
            this.items = items;
        }

        @Override
        public StringViewHolder newFooterHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder newHeaderHolder(View view) {
            return null;
        }

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item, null);
            return new StringViewHolder(view, true);
        }

        @Override
        public int getAdapterItemCount() {
            return items == null ? 0 : items.size();
            //这里返回的是你的item的个数  不包括头部和加载view
        }

        @Override
        public long generateHeaderId(int position) {
            return -1;
        }

        @Override
        public void onBindViewHolder(final StringViewHolder holder, final int position) {
            SecCustomGridBean.DataBean.ItemsBean.ComponentBean component = secGridList.get(position).getComponent();
            //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
            if (position < getItemCount() && (customHeaderView != null ? position <= items.size() : position < items.size()) && (customHeaderView != null ? position > 0 : true)) {
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
                holder.title.setText(component.getDescription());
                holder.currentPrice.setText("￥" + component.getPrice());
                holder.originPrice.setText("￥" + component.getOrigin_price());
                holder.countryName.setText(component.getCountry());
                holder.originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                Picasso.with(context).load(component.getPicUrl()).into(holder.imageView);
                if(!component.getNationalFlag().equals("")){
                    Picasso.with(context).load(component.getNationalFlag()).into(holder.circleImageView);
                }
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position1 = holder.getPosition();
                        SecCustomGridBean.DataBean.ItemsBean.ComponentBean component1 = secGridList.get(position1).getComponent();
                        Intent intent = new Intent(context, PurchaseDetails.class);
                        intent.putExtra("currentPrice", component1.getPrice());
                        intent.putExtra("originPrice", component1.getOrigin_price());
                        intent.putExtra("title", component1.getDescription());
                        intent.putExtra("picUrl", component1.getPicUrl());
                        intent.putExtra("source_id",component1.getAction().getSourceId());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.brand_detail_ultimatefresh_item, parent,false);
            return new StringViewHolder(view, true);
            //初始化item的头部布局  这里为了方便 就直接用StringViewHolder,实际使用可以使用不同于item的布局
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (customHeaderView != null) {
                position -= 1;
            }
        }
    }

}
