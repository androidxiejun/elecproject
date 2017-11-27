package com.example.administrator.electronicproject.StoreFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.electronicproject.MineFragment.activity.ShappingCart;
import com.example.administrator.electronicproject.Products;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.SearchActivity.SearchActivity;
import com.example.administrator.electronicproject.ShoppingCarGreenDaoUtils.DaoUtils;
import com.example.administrator.electronicproject.StoreFragment.StoreHomePageBean.StoreHomePageBean;
import com.example.administrator.electronicproject.SweepQrCode.TakePictureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/5.
 */
public class StoreFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private StoreHomePageFragment storeFragment;
    private FragmentManager manager;
    private Button sweepBtn,searchBtn,shoppingBtn;
    private ImageView topImg;
    private TextView shoppingNUm;
    private int index;
    private List<Products>productList=new ArrayList<>();
    private SharedPreferences mSp;
    private SharedPreferences.Editor editor;
    public static Map<Integer,StoreHomePageBean.DataBean>dataMap=new HashMap<>();
    public static StoreFragment newInstance(){
        return new StoreFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.store_fragment_layout,container,false);
        productList= DaoUtils.getDao(context).loadAll();
//        mSp=getActivity().getSharedPreferences("star",Context.MODE_PRIVATE);
//        editor=mSp.edit();
        initFragment();
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        productList= DaoUtils.getDao(context).loadAll();
        shoppingNUm.setText(productList.size()+"");
    }

    private void initView(View view) {
        sweepBtn= (Button) view.findViewById(R.id.store_sweep_btn);
        searchBtn= (Button) view.findViewById(R.id.store_search_btn);
        shoppingBtn= (Button) view.findViewById(R.id.store_shopping_btn);
        topImg= (ImageView) view.findViewById(R.id.store_top_img);
        shoppingNUm= (TextView) view.findViewById(R.id.store_shopping_car_num);
//        shoppingNUm.setText(mSp.getInt("number",0)+"");
        shoppingNUm.setText(productList.size()+"");

        sweepBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        shoppingBtn.setOnClickListener(this);
    }

    private void initFragment() {
        manager=getFragmentManager();
        storeFragment=StoreHomePageFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.store_fragment,storeFragment);
        fragmentTransaction.commit();
    }

    /**
     * 对按钮进行监听，跳转至不同界面
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //进入扫描二维码界面
            case R.id.store_sweep_btn:
                Intent intent=new Intent(context,TakePictureActivity.class);
                startActivity(intent);
                break;
//            Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivity(intents);
//            break;
            //进入搜索界面
            case R.id.store_search_btn:
                Intent intent2=new Intent(context, SearchActivity.class);
                startActivity(intent2);
                break;
            //进入购物车界面
            case R.id.store_shopping_btn:
                Intent intent1=new Intent(context, ShappingCart.class);
                startActivity(intent1);
                break;
        }
    }
}
