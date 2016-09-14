package com.example.administrator.electronicproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.adapter.bean.UserAddress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/14.
 * 我的收货地址中listview的适配器
 */
public class AddressAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<UserAddress> addressesLists;
    private static int position;

    public AddressAdapter(Context context,List<UserAddress> addressesLists){
        this.context = context;
        this.addressesLists = addressesLists;
    }

    @Override
    public int getCount() {
        return addressesLists == null ? 0 : addressesLists.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.user_address_item, viewGroup, false);
            holder = new UserHolder(view);
        }else {
            holder = (UserHolder) view.getTag();
        }
        UserAddress userAddress = addressesLists.get(i);
        holder.name.setText("收货人："+userAddress.name);
        holder.mobile.setText(userAddress.mobile);
        holder.address.setText("收货地址："+userAddress.address);

        position = i;
        holder.userRl.setOnClickListener(this);
        holder.edit.setOnClickListener(this);
        holder.delete.setOnClickListener(this);

        return view;
    }

    class UserHolder{
        public UserHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
        @BindView(R.id.user_address_rl)
        RelativeLayout userRl;
        @BindView(R.id.user_address_name)
        TextView name;
        @BindView(R.id.user_address_mobile)
        TextView mobile;
        @BindView(R.id.user_address_address)
        TextView address;
        @BindView(R.id.user_address_edit)
        Button edit;
        @BindView(R.id.user_address_delete)
        Button delete;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_address_rl://点击返回我的界面

                break;
            case R.id.user_address_edit://点击编辑地址信息
                break;
            case R.id.user_address_delete://点击删除地址信息
                break;
        }
    }
}
