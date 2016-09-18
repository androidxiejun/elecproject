package com.example.administrator.electronicproject.MineFragment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.electronicproject.Products;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseConfirMationActivity.PurchaseFirmationActivity;
import com.example.administrator.electronicproject.PurchaseDetails.PurchaseDetails;
import com.example.administrator.electronicproject.R;
import com.example.administrator.electronicproject.ShoppingCarGreenDaoUtils.DaoUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunbin on 2016/9/12.
 * 购物车界面
 */
public class ShappingCart extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.shapping_cart_back_btn)
    Button backBtn;
    @BindView(R.id.shopping_cart_message)
    ImageView messageIv;
    @BindView(R.id.shopping_cart_editing)
    TextView editTv;
    @BindView(R.id.shopping_cart_expand_list)
    PullToRefreshListView expandableListView;
    @BindView(R.id.shopping_car_pay_btn)
    Button payBtn;
    @BindView(R.id.shopping_car_before_edit_linear)
    LinearLayout beforeEditLinear;
    @BindView(R.id.shopping_car_edit_linear)
    LinearLayout editLinear;
    @BindView(R.id.shopping_car_delete_btn)
    Button deleteBtn;
    @BindView(R.id.shopping_car_check_box_main)
    CheckBox beforeEditBox;
    @BindView(R.id.shopping_car_check_box_edit)
    CheckBox editBox;
    @BindView(R.id.shopping_car_count_price)
    TextView countPrice;
    private boolean isEdit=true;
    private boolean topCheck=false,bottomCheck=false;
    private float countPriceNum;
    private Context context;
    private List<Products>productList=new ArrayList<>();
    private RefreshAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shapping_cart_layout);
        ButterKnife.bind(this);
        context=this;
        productList= DaoUtils.getDao(this).loadAll();
        initView();
    }

    private void initView() {
        backBtn.setOnClickListener(this);
        messageIv.setOnClickListener(this);
        editTv.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        beforeEditBox.setOnClickListener(this);
        editBox.setOnClickListener(this);
        payBtn.setOnClickListener(this);
        mAdapter=new RefreshAdapter();
        expandableListView.setAdapter(mAdapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Products product = productList.get(i-1);
                Intent intent=new Intent(context, PurchaseDetails.class);
                intent.putExtra("source_id",product.getProductId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shapping_cart_back_btn://返回
                finish();
                break;
            case R.id.shopping_cart_message://消息
//                Intent intent1=new Intent(context, MainActivity.class);
//                intent1.putExtra("message","message");
//                startActivity(intent1);
                break;
            case R.id.shopping_cart_editing://编辑
                if(isEdit){
                    isEdit=false;
                    editTv.setText("完成");
                    beforeEditLinear.setVisibility(View.INVISIBLE);
                }else{
                    isEdit=true;
                    editTv.setText("编辑");
                    beforeEditLinear.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.shopping_car_delete_btn:
                if(topCheck||bottomCheck){
                    DaoUtils.getDao(context).delete(productList.get(0));
                    productList= DaoUtils.getDao(this).loadAll();
                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(ShappingCart.this, "请选择商品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shopping_car_pay_btn:
                if(topCheck||bottomCheck){
                    Intent intent=new Intent(context, PurchaseFirmationActivity.class);
                    intent.putExtra("source_id", productList.get(0).getProductId());
                    startActivity(intent);
                }else{
                    Toast.makeText(ShappingCart.this, "请选择商品", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    class RefreshAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return productList.size();
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
        public View getView(int i, View view2, ViewGroup viewGroup) {
            final Products product = productList.get(i);
            View view=view2;
            ViewHolder viewHolder=null;
            if(view==null){
                view= LayoutInflater.from(context).inflate(R.layout.shopping_car_item,viewGroup,false);
                viewHolder=new ViewHolder(view);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.brandName.setText(product.getProductName());
            viewHolder.brandTitle.setText(product.getProductTitle());
            viewHolder.brandPrice.setText("￥"+product.getProductPrice());
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.checkBoxTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   if(b){
                       finalViewHolder.checkBoxBottom.setChecked(true);
                       countPriceNum+=Float.valueOf(product.getProductPrice());
                       countPrice.setText(countPriceNum/2+"");
                       topCheck=true;
                       bottomCheck=true;
                   }else{
                       finalViewHolder.checkBoxBottom.setChecked(false);
                       countPriceNum-=Float.valueOf(product.getProductPrice());
                       countPrice.setText(countPriceNum/2+"");
                       topCheck=false;
                   }
               }
           });
            final ViewHolder finalViewHolder1 = viewHolder;
            viewHolder.checkBoxBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        countPriceNum+=Float.valueOf(product.getProductPrice());
                        finalViewHolder1.checkBoxTop.setChecked(true);
                        countPrice.setText(countPriceNum/2+"");
                        bottomCheck=true;
                        topCheck=true;
                    }else{
                        finalViewHolder1.checkBoxTop.setChecked(false);
                        countPriceNum-=Float.valueOf(product.getProductPrice());
                        countPrice.setText(countPriceNum/2+"");
                        topCheck=false;
                        bottomCheck=false;
                    }
                }
            });
            Picasso.with(context).load(product.getProductImgUrl()).into(viewHolder.brandImg);
            return view;
        }

    }
     class ViewHolder {
        public ImageView brandImg;
        public  CheckBox checkBoxTop,checkBoxBottom;
        public TextView brandName,brandTitle,brandPrice;
        public ViewHolder(View view){
            view.setTag(this);
            this.brandImg= (ImageView) view.findViewById(R.id.shopping_car_brand_img);
            this.brandName= (TextView) view.findViewById(R.id.shopping_car_brand_name);
            this.brandTitle= (TextView) view.findViewById(R.id.shopping_car_brand_title);
            this.brandPrice= (TextView) view.findViewById(R.id.shopping_car_brand_price);
            this.checkBoxTop= (CheckBox) view.findViewById(R.id.shopping_car_check_box);
            this.checkBoxBottom= (CheckBox) view.findViewById(R.id.shopping_car_check_box_bottom);

        }
    }
}
