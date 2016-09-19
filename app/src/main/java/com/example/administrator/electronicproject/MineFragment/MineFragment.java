package com.example.administrator.electronicproject.MineFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.electronicproject.FashionFragment.view.activity.ExpertFansActivity;
import com.example.administrator.electronicproject.MineFragment.activity.ShappingCart;
import com.example.administrator.electronicproject.Products;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.ShoppingCarGreenDaoUtils.DaoUtils;
import com.example.administrator.electronicproject.activity.AddressActivity;
import com.example.administrator.electronicproject.activity.CouponActivity;
import com.example.administrator.electronicproject.activity.LoginActivity;
import com.example.administrator.electronicproject.activity.MyCollectActivity;
import com.example.administrator.electronicproject.activity.MyOrderActivity;
import com.example.administrator.electronicproject.activity.RegisterActivity;
import com.example.administrator.electronicproject.activity.SettingActivity;
import com.example.administrator.electronicproject.activity.UserBrandActivity;
import com.example.administrator.electronicproject.activity.UserInfoActivity;
import com.example.administrator.electronicproject.activity.UserPostActivity;
import com.example.administrator.electronicproject.activity.VIPActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import user.com.example.administrator.electronicproject.User;
import user.com.example.administrator.electronicproject.UserUtils;

/**
 * Created by Administrator on 2016/9/5.
 * 我的界面
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.mine_shopping_car_num)
    TextView shoppingCarNum;
    @BindView(R.id.mine_user_name)
    TextView userName;
    @BindView(R.id.mine_shapping_chart)
    ImageView shappCart;
    @BindView(R.id.mine_photo_circle_iv)
    CircleImageView avatar;//头像
    @BindView(R.id.mine_member_level)
    TextView level; //会员等级
    @BindView(R.id.mine_login)
    TextView login;//登录
    @BindView(R.id.mine_register)
    TextView register;//注册
    @BindView(R.id.mine_attention_rl)
    RelativeLayout attention;//关注
    @BindView(R.id.mine_attention_number)
    TextView attentionNum;
    @BindView(R.id.mine_fans_rl)
    RelativeLayout fans;//粉丝
    @BindView(R.id.mine_fans_number)
    TextView fansNum;
    @BindView(R.id.mine_collect_rl)
    RelativeLayout collect;//收藏
    @BindView(R.id.mine_collect_number)
    TextView collectNum;
    @BindView(R.id.mine_order)
    RelativeLayout order;//我的订单
    @BindView(R.id.mine_payment)
    TextView payment;//待付款
    @BindView(R.id.mine_send)
    TextView send;//待发货
    @BindView(R.id.mine_tack)
    TextView tack;//待收货
    @BindView(R.id.mine_pingjia)
    TextView pingjia;//待评价
    @BindView(R.id.mine_return)
    TextView returned;//退货
    @BindView(R.id.mine_welfare)
    RelativeLayout welfare;//我的福利
    @BindView(R.id.mine_welfare_rl)
    RelativeLayout memberRl;//会员专享
    @BindView(R.id.mine_welfare_conpon_rl)
    RelativeLayout conpon;//优惠券
    @BindView(R.id.mine_welfare_red_rl)
    RelativeLayout red;//我的红包
    @BindView(R.id.mine_address)
    RelativeLayout address;//收货地址
    @BindView(R.id.mine_post)
    RelativeLayout post;//发帖
    @BindView(R.id.mine_brand)
    RelativeLayout brand;//我关注的品牌
    @BindView(R.id.mine_lable_rl)
    RelativeLayout lable;//我关注的标签
    @BindView(R.id.mine_setting)
    RelativeLayout setting;//设置
    @BindView(R.id.mine_help)
    RelativeLayout help;//帮助
    @BindView(R.id.mine_service)
    RelativeLayout mineService;//客服
    private SharedPreferences mSp;
    private SharedPreferences.Editor editor;
    private Context context;
    private int userId = 2904927;//默认一个用户id
    private List<Products> productList=new ArrayList<>();
    private User user;
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        mSp=getActivity().getSharedPreferences("star",Context.MODE_PRIVATE);
        editor= mSp.edit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        ButterKnife.bind(this, view);
        productList= DaoUtils.getDao(context).loadAll();
        shoppingCarNum.setText(productList.size()+"");
        initListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        productList= DaoUtils.getDao(context).loadAll();
        shoppingCarNum.setText(productList.size()+"");

        List<User> users = UserUtils.getDao(context).loadAll();
        if (users != null && users.size() > 0){
            user = users.get(0);
            avatar.setImageBitmap(BitmapFactory.decodeFile(user.getUserImage()));
        }
    }

    private void initListener() {
        shappCart.setOnClickListener(this);
        avatar.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        attention.setOnClickListener(this);
        fans.setOnClickListener(this);
        collect.setOnClickListener(this);
        order.setOnClickListener(this);
        payment.setOnClickListener(this);
        send.setOnClickListener(this);
        tack.setOnClickListener(this);
        pingjia.setOnClickListener(this);
        returned.setOnClickListener(this);
        welfare.setOnClickListener(this);
        memberRl.setOnClickListener(this);
        conpon.setOnClickListener(this);
        red.setOnClickListener(this);
        address.setOnClickListener(this);
        post.setOnClickListener(this);
        brand.setOnClickListener(this);
        lable.setOnClickListener(this);
        setting.setOnClickListener(this);
        help.setOnClickListener(this);
        mineService.setOnClickListener(this);

        level.setVisibility(View.INVISIBLE);//当用户登录时，才显示会员等级，同时登陆和注册不可见
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_shapping_chart://购物车
                Intent intent = new Intent(context, ShappingCart.class);
                startActivity(intent);
                break;
            case R.id.mine_photo_circle_iv://用户信息
                Intent userinfo = new Intent(context, UserInfoActivity.class);
                startActivity(userinfo);
                break;
            case R.id.mine_login://登陆
                Intent intentLogin = new Intent(context, LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.mine_register://注册
                Intent intentRegis = new Intent(context,RegisterActivity.class);
                startActivity(intentRegis);
                break;
            case R.id.mine_attention_rl://关注 ,使用默认的用户id
                Intent intentAttr = new Intent(context, ExpertFansActivity.class);
                intentAttr.putExtra("id",userId);
                intentAttr.putExtra("type",0);
                startActivity(intentAttr);
                break;
            case R.id.mine_fans_rl://粉丝,使用默认的用户id
                Intent intentFans = new Intent(context, ExpertFansActivity.class);
                intentFans.putExtra("id",userId);
                intentFans.putExtra("type",1);
                startActivity(intentFans);
                break;
            case R.id.mine_collect_rl://收藏
                Intent collect = new Intent(context, MyCollectActivity.class);
                startActivity(collect);
                break;
            case R.id.mine_order://全部订单
                Intent order = new Intent(context, MyOrderActivity.class);
                order.putExtra("num",0);
                startActivity(order);
                break;
            case R.id.mine_payment://待付款
                Intent pay = new Intent(context, MyOrderActivity.class);
                pay.putExtra("num",1);
                startActivity(pay);
                break;
            case R.id.mine_send://待发送
                Intent send = new Intent(context, MyOrderActivity.class);
                send.putExtra("num",2);
                startActivity(send);
                break;
            case R.id.mine_tack://待收货
                Intent tack = new Intent(context, MyOrderActivity.class);
                tack.putExtra("num",3);
                startActivity(tack);
                break;
            case R.id.mine_pingjia://待评价
                Intent pingjia = new Intent(context, MyOrderActivity.class);
                pingjia.putExtra("num",4);
                startActivity(pingjia);
                break;
            case R.id.mine_return://退换货
                Intent returnGoods = new Intent(context, MyOrderActivity.class);
                returnGoods.putExtra("num",5);
                startActivity(returnGoods);
                break;
            case R.id.mine_welfare_rl://会员专享
                Intent vip = new Intent(context, VIPActivity.class);
                vip.putExtra("uid",userId);//使用的默认用户
                startActivity(vip);
                break;
            case R.id.mine_welfare_conpon_rl://优惠券
                Intent coupon = new Intent(context, CouponActivity.class);
                startActivity(coupon);
                break;
            case R.id.mine_welfare_red_rl://我的红包
                break;
            case R.id.mine_address://地址
                Intent address = new Intent(context, AddressActivity.class);
                startActivity(address);
                break;
            case R.id.mine_post://我的发帖
                Intent post = new Intent(context, UserPostActivity.class);
                startActivity(post);
                break;
            case R.id.mine_brand://我关注的品牌
                Intent brand = new Intent(context, UserBrandActivity.class);
                startActivity(brand);
                break;
            case R.id.mine_lable_rl://我关注的标签
                break;
            case R.id.mine_setting://设置
                Intent set = new Intent(context, SettingActivity.class);
                startActivity(set);
                break;
            case R.id.mine_help://帮助和反馈
                break;
            case R.id.mine_service://客服
                //已用超链接
                break;
        }
    }
}
