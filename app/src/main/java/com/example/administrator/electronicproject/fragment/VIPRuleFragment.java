package com.example.administrator.electronicproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.electronicproject.R;

/**
 * Created by sunbin on 2016/9/13.
 */
public class VIPRuleFragment extends Fragment {

    private Context context;
    private TextView built;
    private TextView relegation;
    private TextView demotion;

    public static VIPRuleFragment newInstance(){
        return new VIPRuleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.vip_rule_layout, container, false);

        built = (TextView) inflate.findViewById(R.id.vip_rule_custom_built);
        relegation = (TextView) inflate.findViewById(R.id.vip_rule_relegation);
        demotion = (TextView) inflate.findViewById(R.id.vip_rule_demotion);

        initView();
        return inflate;
    }

    private void initView() {
        String strBuilt = "在商城<font color='#FF0000'>注册后</font>,即可成为<font color='#FF0000'>v0</font>会员;<br></br>"
                +"在商城<font color='#FF0000'>第一次消费</font>，交易成功后成为<font color='#FF0000'>v1</font>会员;<br></br>"
                +"累计在商城有效消费满<font color='#FF0000'>1000</font>元升级为<font color='#FF0000'>v2</font>会员;<br></br>"
                +"累计在商城有效消费满<font color='#FF0000'>2000</font>元升级为<font color='#FF0000'>v3</font>会员;<br></br>"
                +"累计在商城有效消费满<font color='#FF0000'>5000</font>元升级为<font color='#FF0000'>v4</font>会员。";
        built.setText(Html.fromHtml(strBuilt));

        String strRele = "以自然季度为期。\n"
                +"第一季度：1月、2月、3月\n"+"第二季度：4月、5月、6月\n"
                +"第三季度：7月、8月、9月\n"+"第四季度：10月、11月、12月";
        relegation.setText(strRele);

        String strDemo = "<font color='#FF0000'>保级：</font>V1-V4会员，每季度在商城消费300元。<br></br>"
                +"<font color='#FF0000'>降级：</font>当季度内，在商城有效消费未满300，则于下季度1日会员等级降至上一级别(至多降一个级别)。直到" +
                "一个自然季内有效消费满300元(确认收货后)，恢复原有VIP级别。";
        demotion.setText(Html.fromHtml(strDemo));
    }
}
