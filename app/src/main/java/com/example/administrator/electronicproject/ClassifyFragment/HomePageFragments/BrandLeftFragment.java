package com.example.administrator.electronicproject.ClassifyFragment.HomePageFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.electronicproject.ClassifyFragment.CallBack;
import com.example.administrator.electronicproject.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BrandLeftFragment extends Fragment {
    private Context context;
    private ListView brandListView;
    private BrandListAdapter listAdapter;
    private int index;
    private CallBack callBack;
    public static BrandLeftFragment newInstance(){
        return new BrandLeftFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_category_list_fragment_layout,container,false);
        initView(view);
        return view;
    }
   public void setCallBack(CallBack callBack){
       this.callBack=callBack;
   }
    private void initView(View view) {
        brandListView= (ListView) view.findViewById(R.id.classify_category_list_view);
        listAdapter=new BrandListAdapter();
        brandListView.setAdapter(listAdapter);
        brandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listAdapter.changeSelected(i);//刷新
                if(callBack!=null){
                    callBack.refreshFragment(i);
                }

            }
        });
    }


    class BrandListAdapter extends BaseAdapter{
        int mSelect = 0;   //选中项
        public void changeSelected(int positon){ //刷新方法
            if(positon != mSelect){
                mSelect = positon;
                notifyDataSetChanged();
            }
        }
        @Override
        public int getCount() {
            return BrandFragment.brandList==null?0:BrandFragment.brandList.size();
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
            if(view==null){
                view=LayoutInflater.from(context).inflate(R.layout.classfy_tablayout_list_view_item,viewGroup,false);
            }
            TextView textView= (TextView) view.findViewById(R.id.classify_list_view_txt);
            textView.setText(BrandFragment.brandList.get(i));
            if(mSelect==i){
                textView.setBackgroundResource(R.color.colorWhite);  //选中项背景
            }else{
                textView.setBackgroundResource(R.color.colorGray);  //其他项背景
            }
            return view;
        }
    }
}
