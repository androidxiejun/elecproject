package com.example.administrator.electronicproject.ShoppingCarGreenDaoUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.electronicproject.ProductdbDaoMaster;
import com.example.administrator.electronicproject.ProductdbDaoSession;
import com.example.administrator.electronicproject.ProductsDao;

/**
 * Created by Administrator on 2016/8/24.
 */
public class DaoUtils {
    private static ProductsDao productDao;
    public static ProductsDao getDao(Context context){
        if(productDao==null){
        ProductdbDaoMaster.DevOpenHelper devOpenHelper = new ProductdbDaoMaster.DevOpenHelper(context, "androidxx");
        SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
            ProductdbDaoMaster androidxxDaoMaster = new ProductdbDaoMaster(readableDatabase);
        ProductdbDaoSession androidxxDaoSession = androidxxDaoMaster.newSession();
        productDao = androidxxDaoSession.getProductsDao();
        }
        return  productDao;
    }
}
