package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String[] args) {
        buildData();
    }

    public static void buildData(){

        Schema schema = new Schema("addressdb",1,"com.example.administrator.electronicproject");
        //创建表
        Entity attention = schema.addEntity("Address");
        //添加字段
        attention.addIdProperty().primaryKey().autoincrement();
        attention.addStringProperty("userName");
        attention.addStringProperty("userMobile");
        attention.addStringProperty("userAddress");
        attention.addStringProperty("addressDetails");

        //自动生成数据库文件和javabean文件
        try {
            new DaoGenerator().generateAll(schema,"../electricproject/app/src/main/java/addressdao");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
