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
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.CallBack;
import com.example.administrator.electronicproject.ClassifyFragment.DetaillActivity.ClassifyDetiailActivity;
import com.example.administrator.electronicproject.ClassifyFragment.HomePageInfo;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class ClassifyStoreFragment extends Fragment implements CallBack{
    private Context context;
    private GridView mGridView;
    private  GridViewAdapter gridViewAdapter;
    private List<HomePageInfo> pageInfos;
    private int index;
    public static final String URL_PATH="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";

    public static ClassifyStoreFragment newInstance(){
        return new ClassifyStoreFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_category_store_fragment_layout,container,false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        mGridView= (GridView) view.findViewById(R.id.classfy_category_store_grid_view);
        gridViewAdapter=new GridViewAdapter();
        mGridView.setAdapter(gridViewAdapter);
        //对gridview进行监听，然后跳转至详情界面
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(context, ClassifyDetiailActivity.class);
                intent.putExtra("query",pageInfos.get(i).storeQuery);
                intent.putExtra("title",pageInfos.get(i).storeTitle);
                startActivity(intent);
            }
        });
    }

    /**
     * 通过回调，刷新适配器
     * @param index
     */
    @Override
    public void refreshFragment(int index) {
        this.index=index;
        gridViewAdapter.notifyDataSetChanged();
    }

    class GridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            pageInfos = CategoryFragment.dataMap.get(index);
            return pageInfos==null?0:pageInfos.size();
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
                view=LayoutInflater.from(context).inflate(R.layout.classify_tablayout_grid_view_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.textView.setText(pageInfos.get(i).storeTitle);
            Picasso.with(context).load(pageInfos.get(i).storeUrl).into(viewHolder.imageView);
            return view;
        }
        class ViewHolder{
            public ImageView imageView;
            public TextView textView;
            public ViewHolder(View view){
                view.setTag(this);
                this.imageView= (ImageView) view.findViewById(R.id.classify_grid_view_img);
                this.textView= (TextView) view.findViewById(R.id.classify_grid_view_txt);
            }
        }
    }

}
