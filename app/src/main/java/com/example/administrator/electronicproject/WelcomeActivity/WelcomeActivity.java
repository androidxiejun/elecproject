package com.example.administrator.electronicproject.WelcomeActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.electronicproject.MainActivity;
import com.example.administrator.electronicproject.R;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private Context context;
    private String name;
    private SharedPreferences mSp;
    private SharedPreferences.Editor editor;
    private List<Integer>dataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        context=this;
        mSp=getSharedPreferences("star",MODE_PRIVATE);
        name=mSp.getString("name","");
        if(name.equals("star")){
            Intent intent=new Intent(this,WelcomeActivityTwo.class);
            startActivity(intent);
            finish();
        }else{
            initData();
            initView();
        }
    }

    private void initData() {
        editor=mSp.edit();
        editor.putString("name","star");
        editor.commit();
        dataList.add(R.drawable.splash_foreground_2);
        dataList.add(R.drawable.splash_foreground_3);
        dataList.add(R.drawable.splash_foreground_4);
        dataList.add(R.drawable.splash_foreground_5);
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.welcome_view_pager);
        mAdapter=new ViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
    }
    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(dataList.get(position));
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position==dataList.size()-1){
                        Intent intent=new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
