package com.example.administrator.electronicproject.PurchaseDetails.PurchaseConfirMationActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsBean.PurchaseDetailsBean;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetailsUtils.HttpPurchaseUtils;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseFirmationActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.firmation_circle_img)
    ImageView ciecleImg;
    @BindView(R.id.firmation_img)
    ImageView bigImage;
    @BindView(R.id.firmation_title)
    TextView title;
    @BindView(R.id.firmation_price)
    TextView price;
    @BindView(R.id.firmation_brand_name)
    TextView brandName;
    @BindView(R.id.firmation_country_name)
    TextView countryName;
    @BindView(R.id.firmation_send_money)
    TextView sendMoney;
    @BindView(R.id.firmation_all_money)
    TextView brandMoney;
    @BindView(R.id.firmation_brand_all_money)
    TextView allMoney;
    @BindView(R.id.firmation_edit_text)
    EditText mEditText;
    @BindView(R.id.firmation_text_num)
    TextView textNum;
    @BindView(R.id.firmation_add_monry)
    TextView addMoney;
    @BindView(R.id.firmation_go_to_pay)
    Button gotoPayBtn;
    @BindView(R.id.firmation_back_btn)
    Button backBtn;
    private Context context;
    private int num = 30;
    private int selectionStart;
    private int selectionEnd;
    private String source_id;
    private CharSequence wordNum;
    private PurchaseDetailsBean.ResponseBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_firmation);
        context=this;
        ButterKnife.bind(this);
        mEditText.setOnClickListener(this);
        gotoPayBtn.setOnClickListener(this);
        initIntent();
        initInfo(source_id);
        initListener();
    }

    private void initListener() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                wordNum=charSequence;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number =s.length();
                //TextView显示剩余字数
                textNum.setText(number+"/30");
                selectionStart=mEditText.getSelectionStart();
                selectionEnd = mEditText.getSelectionEnd();
                if (wordNum.length() >num) {
                    Toast.makeText(PurchaseFirmationActivity.this, "字数超过限制", Toast.LENGTH_SHORT).show();
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    mEditText.setText(s);
                    mEditText.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    private void initIntent() {
        Intent intent = getIntent();
        source_id= intent.getStringExtra("source_id");
    }

    private void initInfo(String source_id){
        HttpPurchaseUtils.create().queryBean(source_id).enqueue(new Callback<PurchaseDetailsBean>() {
            @Override
            public void onResponse(Call<PurchaseDetailsBean> call, Response<PurchaseDetailsBean> response) {
                data = response.body().getResponse().getData();
                brandName.setText(data.getBusiness_name());
//                Picasso.with(context).load(data.getCountryInfo().getCountry_img()).into(ciecleImg);
                countryName.setText(data.getCountryInfo().getCountry_title());
                Picasso.with(context).load(data.getGoods_img()).into(bigImage);
                title.setText(data.getGoods_name());
                price.setText(data.getShop_price());
                sendMoney.setText(data.getWarehouse());
                brandMoney.setText("￥"+data.getShop_price());
                allMoney.setText("￥"+data.getShop_price());
                addMoney.setText("￥"+data.getShop_price());
            }

            @Override
            public void onFailure(Call<PurchaseDetailsBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.firmation_edit_text:

                break;
            case R.id.firmation_go_to_pay:
                break;
            case R.id.firmation_back_btn:
                finish();
                break;
        }
    }
}
