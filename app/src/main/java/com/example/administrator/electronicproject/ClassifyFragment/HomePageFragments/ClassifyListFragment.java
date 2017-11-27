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
public class ClassifyListFragment extends Fragment{
    private Context context;
    private ListView mListView;
    private CallBack callBack;
    private ListViewAdapter listViewAdapter;
    private ClassifyStoreFragment storeFragment;
    public static ClassifyListFragment newInstance(){
        return new ClassifyListFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    /**
     * 获取callback对象
     * @param callBack
     */
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_category_list_fragment_layout,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView= (ListView) view.findViewById(R.id.classify_category_list_view);
        storeFragment=new ClassifyStoreFragment();
        listViewAdapter=new ListViewAdapter();
        mListView.setAdapter(listViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listViewAdapter.changeSelected(i);//刷新
                if(callBack!=null){
                    callBack.refreshFragment(i);
                }
            }
        });
    }

    /**
     * tab左边的listview适配器
     */
    class  ListViewAdapter extends BaseAdapter {
        int mSelect = 0;   //选中项
        public void changeSelected(int positon){ //刷新方法
            if(positon != mSelect){
                mSelect = positon;
                notifyDataSetChanged();
            }
        }
        @Override
        public int getCount() {

            return CategoryFragment.titleList==null?0:CategoryFragment.titleList.size();
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
            textView.setText(CategoryFragment.titleList.get(i));
            if(mSelect==i){
                textView.setBackgroundResource(R.color.colorWhite);  //选中项背景
            }else{
                textView.setBackgroundResource(R.color.colorGray);  //其他项背景
            }
            return view;
        }
    }
}
