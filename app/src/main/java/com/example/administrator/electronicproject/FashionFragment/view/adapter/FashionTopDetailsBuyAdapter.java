package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.RecommendDeatilsBean;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/9.
 */
public class FashionTopDetailsBuyAdapter extends BaseAdapter{

    private Context context;
    private List<RecommendDeatilsBean.ResponseBean.DataBean.EmbedItemsBean> buyDatas;

    public FashionTopDetailsBuyAdapter(Context context,List<RecommendDeatilsBean.ResponseBean.DataBean.EmbedItemsBean> buyDatas){
        this.context = context;
        this.buyDatas = buyDatas;
    }

    @Override
    public int getCount() {
        return buyDatas == null? 0 : buyDatas.size();
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
        BuyHolder buyHolder = null;
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_top_details_buy,viewGroup,false);
            buyHolder = new BuyHolder(view);
        }else {
            buyHolder = (BuyHolder) view.getTag();
        }
        final RecommendDeatilsBean.ResponseBean.DataBean.EmbedItemsBean.CompBeans compBeans = buyDatas.get(i).getComponent();
        Picasso.with(context).load(compBeans.getPicUrl()).into(buyHolder.bigIv);
//        ImageLoader.init(context).load(compBeans.getPicUrl(),buyHolder.bigIv);
        buyHolder.titleTv.setText(compBeans.getDescription());
        buyHolder.priceTv.setText("￥"+compBeans.getPrice());
        buyHolder.smallPrice.setText("￥"+compBeans.getOrigin_price());
//        buyHolder.smallPrice.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        if (!compBeans.getNationalFlag().equals("")){
            Picasso.with(context).load(compBeans.getNationalFlag()).into(buyHolder.localIv);
//            ImageLoader.init(context).load(compBeans.getNationalFlag(),buyHolder.localIv);
        }
        buyHolder.localName.setText(compBeans.getCountry());
        buyHolder.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击跳转到购买界面
                Intent intent = new Intent(context, PurchaseDetails.class);
                intent.putExtra("source_id",compBeans.getAction().getSourceId());
                context.startActivity(intent);
            }
        });
        return view;
    }

    class BuyHolder{

        @BindView(R.id.fashion_top_details_buy_big_iv)
        ImageView bigIv;
        @BindView(R.id.fashion_top_details_buy_btn)
        Button buyBtn;
        @BindView(R.id.fashion_top_details_buy_title)
        TextView titleTv;
        @BindView(R.id.fashion_top_details_buy_price)
        TextView priceTv;
        @BindView(R.id.fashion_top_details_buy_price_small)
        TextView smallPrice;
        @BindView(R.id.fashion_top_details_buy_local)
        ImageView localIv;
        @BindView(R.id.fashion_top_details_buy_local_name)
        TextView localName;

        public BuyHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
