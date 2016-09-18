package com.example.administrator.electronicproject.PurchaseDetails;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface PurchaseCallBack {
    void addFragment();
    void changNumber();
    void addShoppingCar(String productName,String
            productTitle,String productPrice,
            String productImgUrl,String productId);
}
