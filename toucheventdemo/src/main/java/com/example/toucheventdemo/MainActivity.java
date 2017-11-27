package com.example.toucheventdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends AppCompatActivity {
    private CustomListView mLv;
    private Context context;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        initView();
    }

    private void initView() {
        mLv= (CustomListView) findViewById(R.id.list_view);
        mAdapter=new MyAdapter();
        mLv.setAdapter(mAdapter);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 30;
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
            View view=view2;
            if(view==null){
                view= LayoutInflater.from(context).inflate(R.layout.list_view_item,viewGroup,false);
            }
            return view;
        }
    }
}
