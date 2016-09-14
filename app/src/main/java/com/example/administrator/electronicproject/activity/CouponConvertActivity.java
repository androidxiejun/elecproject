package com.example.administrator.electronicproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/14.
 * 优惠券的兑换界面
 */
public class CouponConvertActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.coupon_convert_back_btn)
    Button backBtn;
    @BindView(R.id.coupon_convert_cdk)
    EditText userCDK;
    @BindView(R.id.coupon_convert_ensure)
    TextView ensure;
    @BindView(R.id.coupon_prompt)
    TextView prompt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_convert_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        ensure.setOnClickListener(this);

        String str = "说明：\n"
                +"1.如果您通过活动获得了明星衣橱优惠券兑换码，您可以在此页面输入兑换码兑换优惠券;\n"
                +"2.线上领取的兑换券无需兑换即可使用;";

        prompt.setText(str);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.coupon_convert_back_btn:
                finish();
                break;
            case R.id.coupon_convert_ensure://确定兑换

                break;
        }
    }
}
