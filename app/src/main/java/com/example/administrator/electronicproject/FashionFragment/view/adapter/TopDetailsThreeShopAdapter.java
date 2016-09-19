package com.example.administrator.electronicproject.FashionFragment.view.adapter;

import android.content.Context;
import android.content.Intent;
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
 * Created by sunbin on 2016/9/10.
 */
public class TopDetailsThreeShopAdapter extends BaseAdapter {

    private Context context;
    private List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.EmbedItemsBean> embedItemsBeen;

    public TopDetailsThreeShopAdapter(Context context,List<RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.EmbedItemsBean> embedItemsBeen){
        this.context = context;
        this.embedItemsBeen = embedItemsBeen;
    }

    @Override
    public int getCount() {
        return embedItemsBeen == null? 0 : embedItemsBeen.size();
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
        BuyHolderTwo buyHolder = null;
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.fashion_top_details_buy,viewGroup,false);
            buyHolder = new BuyHolderTwo(view);
        }else {
            buyHolder = (BuyHolderTwo) view.getTag();
        }
        final RecommendDeatilsBean.ResponseBean.DataBean.ItemsBean.ComponentBean.EmbedItemsBean.ComBeanss comBeanss = embedItemsBeen.get(i).getComponent();
        Picasso.with(context).load(comBeanss.getPicUrl()).into(buyHolder.bigIv);
        buyHolder.titleTv.setText(comBeanss.getDescription());
        buyHolder.priceTv.setText("￥"+comBeanss.getPrice());
//        buyHolder.smallPrice.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        if ((!comBeanss.getNationalFlag().equals("")) && (comBeanss.getNationalFlag() != null)){
            Picasso.with(context).load(comBeanss.getNationalFlag()).into(buyHolder.localIv);
//            ImageLoader.init(context).load(comBeanss.getNationalFlag(),buyHolder.localIv);
        }
        buyHolder.localName.setText(comBeanss.getCountry());
        buyHolder.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击跳转到购买界面
                Intent intent = new Intent(context, PurchaseDetails.class);
                intent.putExtra("source_id",comBeanss.getAction().getSourceId());
                context.startActivity(intent);
            }
        });
        return view;
    }

    class BuyHolderTwo{

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

        public BuyHolderTwo(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
