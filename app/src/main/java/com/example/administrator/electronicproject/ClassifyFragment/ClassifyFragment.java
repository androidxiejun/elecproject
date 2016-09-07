package com.example.administrator.electronicproject.ClassifyFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.electronicproject.ClassifyFragment.HomePageFragments.BrandFragment;
import com.example.administrator.electronicproject.ClassifyFragment.HomePageFragments.CategoryFragment;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.SearchActivity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ClassifyFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private Button searchBtn;
    private BrandFragment brandFragment;
    private CategoryFragment categoryFragment;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment>fragmentList=new ArrayList<>();
    private List<String>tabTitle=new ArrayList<>();
    public static ClassifyFragment newInstance(){
        return new ClassifyFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_layout,container,false);
        View view2=inflater.inflate(R.layout.classify_tablayout_fragment_layout,null);
        initData();
        initFragments();
        initView(view,view2);

        return view;
    }

    private void initFragments() {
        brandFragment=BrandFragment.newInstance();
        categoryFragment=CategoryFragment.newInstance();
        fragmentList.add(categoryFragment);
        fragmentList.add(brandFragment);
    }
    private void initView(View view,View view2) {
        searchBtn= (Button) view.findViewById(R.id.classify_search_btn);
        searchBtn.setOnClickListener(this);
        mViewPager= (ViewPager) view.findViewById(R.id.classify_view_pager);
        mTablayout= (TabLayout) view.findViewById(R.id.classify_tab_layout);
        viewPagerAdapter=new ViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mTablayout.setupWithViewPager(mViewPager);
    }
    private void initData() {
        tabTitle.add("品类");
        tabTitle.add("品牌");
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context, SearchActivity.class);
        startActivity(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList==null?0:fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }
}
