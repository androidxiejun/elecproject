package com.example.administrator.electronicproject.ClassifyFragment.HomePageFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.electronicproject.ClassifyFragment.CallBack;
import com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.DetailBrandActivity;
import com.example.administrator.electronicproject.ClassifyFragment.HomePageInfo;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BrandRightFragment extends Fragment implements CallBack{
    private Context context;
    private GridView mGridView;
    private  List<HomePageInfo> homePageInfos;
    private BrandGridAdapter brandGridAdapter;
    private int index;
    public static BrandRightFragment newInstance(){
        return new BrandRightFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_brand_right_grid_view_layout,container,false);
        initView(view);

        return view;
}

    private void initView(View view) {
        mGridView= (GridView) view.findViewById(R.id.classify_grand_grid_view);
        brandGridAdapter=new BrandGridAdapter();
        mGridView.setAdapter(brandGridAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(context, DetailBrandActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void refreshFragment(int index) {
            this.index=index;
           brandGridAdapter.notifyDataSetChanged();
    }

    class BrandGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            homePageInfos = BrandFragment.brandMap.get(index);
            return homePageInfos==null?0:homePageInfos.size();
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
            View view=containView;
            ViewHolder viewHolder=null;
            if(view==null){
                view=LayoutInflater.from(context).inflate(R.layout.classify_brand_right_grid_view_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            Picasso.with(context).load(homePageInfos.get(i).storeUrl).into(viewHolder.imageView);
            return view;
        }
        class ViewHolder {
            public ImageView imageView;
            public ViewHolder(View view){
                this.imageView= (ImageView) view.findViewById(R.id.classify_brand_right_img_view);
                view.setTag(this);
            }
        }
    }
}
