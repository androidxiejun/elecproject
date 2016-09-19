package com.example.administrator.electronicproject.FashionFragment.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.imageloader.ImageLoader;
import com.example.administrator.electronicproject.FashionFragment.bean.FashionBottonBean;
import com.example.administrator.electronicproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sunbin on 2016/9/6.
 * 时尚圈下部分GridView的适配器
 */
public class FashionGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private GridCallBack callBack;

    public FashionGridViewAdapter(Context context
            ,List<FashionBottonBean.ResponseBean.DataBean.ItemsBean> itemsBeanList
            ,GridCallBack callBack){
        this.context = context;
        this.itemsBeanList = itemsBeanList;
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return itemsBeanList == null ? 0 : itemsBeanList.size();
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
        View itemView = view;
        BottomGridHolder gridHolder = null;
        if (null == itemView){
            itemView = LayoutInflater.from(context).inflate(R.layout.fashion_bottom_grid_item,viewGroup,false);
            gridHolder = new BottomGridHolder(itemView);
        }else {
            gridHolder = (BottomGridHolder) itemView.getTag();
        }
        FashionBottonBean.ResponseBean.DataBean.ItemsBean itemsBean = itemsBeanList.get(i);
        gridHolder.titleText.setText(itemsBean.getComponent().getContent());
        if (itemsBean.getComponent().getUser()!=null){
            gridHolder.nameText.setText(itemsBean.getComponent().getUser().getUsername());
            Picasso.with(context).load(itemsBean.getComponent().getUser().getUserAvatar()).into(gridHolder.circleImage);
//            ImageLoader.init(context).load(itemsBean.getComponent().getUser().getUserAvatar(),gridHolder.circleImage);
        }
        gridHolder.collectText.setText(itemsBean.getComponent().getCollect_count());
        Picasso.with(context).load(itemsBean.getComponent().getPics()).into(gridHolder.bigImage);
//        ImageLoader.init(context).load(itemsBean.getComponent().getPics(),gridHolder.bigImage);
        return itemView;
    }

    class BottomGridHolder{

        public BottomGridHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
        @BindView(R.id.fashion_bottom_item_big_iv)
        ImageView bigImage;
        @BindView(R.id.fashion_bottom_item_title_tv)
        TextView titleText;
        @BindView(R.id.fashion_bottom_item_circle_iv)
        CircleImageView circleImage;
        @BindView(R.id.fashion_bottom_item_name_tv)
        TextView nameText;
        @BindView(R.id.fashion_bottom_item_collct_tv)
        TextView collectText;
    }

    interface GridCallBack{
        void addMore();
    }
}
