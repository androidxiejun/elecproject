package com.example.administrator.electronicproject.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/14.
 * 设置界面
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.set_back_btn)
    Button backBtn;
    @BindView(R.id.seting_reputation)
    TextView reputation;
    @BindView(R.id.seting_recommend)
    TextView recommend;
    @BindView(R.id.seting_clear)
    TextView clear;
    @BindView(R.id.seting_declaration)
    TextView declaration;
    @BindView(R.id.seting_updata)
    TextView updata;
    @BindView(R.id.seting_modified_pass)
    TextView modifiedPass;
    @BindView(R.id.setting_exit)
    TextView exit;
    private TextView weiChat;
    private TextView frends;
    private TextView qq;
    private TextView qqZome;
    private TextView weibo;
    private TextView cancle;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        reputation.setOnClickListener(this);
        recommend.setOnClickListener(this);
        clear.setOnClickListener(this);
        declaration.setOnClickListener(this);
        updata.setOnClickListener(this);
        modifiedPass.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.set_back_btn://返回
                finish();
                break;
            case R.id.seting_reputation://评价
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                startActivity(intent);
                break;
            case R.id.seting_recommend://推荐
                initPopup();
                break;
            case R.id.seting_clear://清除
                break;
            case R.id.seting_declaration://声明
                break;
            case R.id.seting_updata://更新
                break;
            case R.id.seting_modified_pass://修改密码
                break;
            case R.id.setting_exit://退出登录
                break;
        }
    }

    private void initPopup(){
        View popup = LayoutInflater.from(this).inflate(R.layout.setting_popup, null);

        weiChat = (TextView) popup.findViewById(R.id.setting_wechat);
        frends = (TextView) popup.findViewById(R.id.setting_friends);
        qq = (TextView) popup.findViewById(R.id.setting_qq);
        qqZome = (TextView) popup.findViewById(R.id.setting_qq_rome);
        weibo = (TextView) popup.findViewById(R.id.setting_weibo);
        cancle = (TextView) popup.findViewById(R.id.popup_cancle);

        weiChat.setOnClickListener(popupListener);
        frends.setOnClickListener(popupListener);
        qq.setOnClickListener(popupListener);
        qqZome.setOnClickListener(popupListener);
        weibo.setOnClickListener(popupListener);
        cancle.setOnClickListener(popupListener);

        popupWindow = new PopupWindow(popup, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(popup, Gravity.BOTTOM,0,0);
        popupWindow.setOutsideTouchable(true);
    }

    private View.OnClickListener popupListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.setting_wechat:

                    break;
                case R.id.setting_friends:
                    break;
                case R.id.setting_qq:
                    break;
                case R.id.setting_qq_rome:
                    break;
                case R.id.setting_weibo:
                    break;
                case R.id.popup_cancle:
                    popupWindow.dismiss();
                    break;
            }
        }
    };
}
