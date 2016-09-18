package com.example.administrator.electronicproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.activity.AddAddressActivity;

import java.util.List;

import addressdao.com.example.administrator.electronicproject.Address;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/14.
 * 我的收货地址中listview的适配器
 */
public class AddressAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<Address> addressesLists;
    private static int position = 0;
    private AddressCallBack callBack;
    private static boolean check = true;

    public AddressAdapter(Context context,List<Address> addressesLists,AddressCallBack callBack){
        this.context = context;
        this.addressesLists = addressesLists;
        this.callBack = callBack;
    }

    public interface AddressCallBack{
        void returnMine(int position);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        UserHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.user_address_item, viewGroup, false);
            holder = new UserHolder(view);
        }else {
            holder = (UserHolder) view.getTag();
        }
        Address userAddress = addressesLists.get(i);
        holder.name.setText("收货人："+userAddress.getUserName());
        holder.mobile.setText(userAddress.getUserMobile());
        holder.address.setText("收货地址："+userAddress.getUserAddress()+userAddress.getAddressDetails());


//        holder.userRl.setOnClickListener(this);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.returnMine(i);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("index",i);
                context.startActivity(intent);
            }
        });
        if (i == position){
            holder.box.setChecked(check);
        }
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = i;
                callBack.returnMine(-1);
            }
        });
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
        @BindView(R.id.user_address_image)
        CheckBox box;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_address_rl://点击返回我的界面
                callBack.returnMine(-1);
                break;
//            case R.id.user_address_delete://点击删除地址信息
//                callBack.returnMine(position);
//                break;
        }
    }
}
