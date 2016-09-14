package com.example.administrator.electronicproject.activity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.adapter.bean.UserAddress;
import com.example.administrator.electronicproject.adapter.bean.UtilsInfo;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/13.
 * 新增地址界面
 */
public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.address_new_back_btn)
    Button backBtn;
    @BindView(R.id.address_new_save)
    TextView save;
    @BindView(R.id.address_new_user_name)
    EditText userName;
    @BindView(R.id.address_new_user_mobile)
    EditText userMobile;
    @BindView(R.id.address_new_user_address)
    TextView userAddress;
    @BindView(R.id.address_new_user_address_details)
    EditText addressDetails;
    @BindView(R.id.address_new_user_id_card)
    EditText userIdCard;
    //省市区联动的弹出框
    private TextView cancle;
    private TextView submit;
    private WheelView province;
    private WheelView city;
    private PopupWindow popupWindow;
    private ArrayWheelAdapter wheelAdapter;
    private List<String> provinceList = new ArrayList<>();
    private HashMap<String,List<String>> cityMap = new HashMap<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private UserAddress userAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_new);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("user_address",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        initView();
        initDatas();
    }

    private void initDatas() {
        provinceList.add("北京市");
        provinceList.add("广东省");
        provinceList.add("山东省");
        provinceList.add("江苏省");
        provinceList.add("河南省");
        provinceList.add("上海市");

        ArrayList<String> cityOne = new ArrayList<>();
        cityOne.add("朝阳区");
        cityOne.add("海淀区");
        cityOne.add("通州区");
        cityOne.add("房山区");
        cityOne.add("丰台区");
        cityOne.add("昌平区");
        cityOne.add("大兴区");
        cityOne.add("顺义区");
        cityOne.add("西城区");
        cityOne.add("延庆县");
        cityOne.add("石景山区");
        ArrayList<String> cityTwo = new ArrayList<>();
        ArrayList<String> cityThree = new ArrayList<>();
        ArrayList<String> cityFour = new ArrayList<>();
        ArrayList<String> cityFive = new ArrayList<>();
        ArrayList<String> citySix = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cityTwo.add(i+"城市");
            cityThree.add(i+"城市");
            cityFour.add(i+"城市");
            cityFive.add(i+"城市");
            citySix.add(i+"城市");
        }
        cityMap.put("北京市",cityOne);
        cityMap.put("广东省",cityTwo);
        cityMap.put("山东省",cityTwo);
        cityMap.put("江苏省",cityTwo);
        cityMap.put("河南省",cityTwo);
        cityMap.put("上海市",cityTwo);
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        save.setOnClickListener(this);
        userAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.address_new_back_btn://返回
                finish();
                break;
            case R.id.address_new_save://保存信息
//                userAdd = new UserAddress();
//                userAdd.name = userName.getText().toString();
//                userAdd.mobile = userMobile.getText().toString();
//                userAdd.address = userAddress.getText().toString()+addressDetails.getText().toString();
//                UtilsInfo.userSet.add(userAdd);

                break;
            case R.id.address_new_user_address://弹出地址选择框
                choseAddress();
                break;
        }
    }

    /**
     * 地址选择
     */
    private void choseAddress(){
        View addressPopup = LayoutInflater.from(this).inflate(R.layout.address_popup, null);

        cancle = (TextView) addressPopup.findViewById(R.id.address_popup_cancel);
        submit = (TextView) addressPopup.findViewById(R.id.address_popup_submit);
        province = (WheelView) addressPopup.findViewById(R.id.wheelview_province);
        city = (WheelView) addressPopup.findViewById(R.id.wheelview_city);

        cancle.setOnClickListener(chose);
        submit.setOnClickListener(chose);

//        wheelAdapter = new ArrayWheelAdapter(this);
//        final ArrayWheelAdapter cityAdapter = new ArrayWheelAdapter(this);
//        province.setWheelAdapter(wheelAdapter);
//        province.setSkin(WheelView.Skin.Holo);
//        province.setWheelData(provinceList);
//        province.setWheelSize(5);
//
//        province.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
//            @Override
//            public void onItemSelected(int position, Object o) {
//
//                String province = provinceList.get(position);
//                city.setWheelAdapter(cityAdapter);
//                city.setSkin(WheelView.Skin.Holo);
//                city.setWheelData(cityMap.get(province));
//                city.setWheelSize(5);
//            }
//        });

        popupWindow = new PopupWindow(addressPopup, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(addressPopup, Gravity.BOTTOM,0,0);
    }

    private View.OnClickListener chose = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.address_popup_cancel://取消
                    popupWindow.dismiss();
                    break;
                case R.id.address_popup_submit://提交信息
                    break;
            }
        }
    };
}
