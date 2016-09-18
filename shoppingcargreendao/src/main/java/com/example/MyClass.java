package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args) {
        grennTool();
    }
    private static void grennTool(){
        Schema schema=new Schema("productdb",1,"com.example.administrator.electronicproject");
        Entity entity = schema.addEntity("Products");
        entity.addIdProperty().autoincrement();
        entity.addStringProperty("productName");
        entity.addStringProperty("productTitle");
        entity.addStringProperty("productImgUrl");
        entity.addStringProperty("productPrice");
        entity.addStringProperty("productId");

        try {
            new DaoGenerator().generateAll(schema,"../ElectronicProject/app/src/main/java/greendao");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
