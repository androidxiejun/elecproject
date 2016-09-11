package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class TabBean {

    /**
     * message :
     * data : {"items":[{"cate_names":"","nav_name":"今日上新","nav_cat_ids":""},{"cate_names":"背带裙|连衣裙","nav_name":"连衣裙","nav_cat_ids":"506,39"},{"cate_names":"女装|蕾丝衫|雪纺衫|女式雪纺衫|女雪纺衫|t恤|体恤|女T恤|女式T恤|卫衣|女式卫衣|女卫衣","nav_name":"T恤","nav_cat_ids":"2,38,32,34"},{"cate_names":"长裙|皮裙|半身裙","nav_name":"半身裙","nav_cat_ids":"507,505,40"},{"cate_names":"其他裤子|背带裤|女背带裤|女式背带裤|正装裤|女装裤|女式正装裤|短裤|女短裤|女式短裤|打底裤|女式打底裤|女打底裤|西裤|西装裤|正装裤|女式西裤|女西装裤|牛仔裤|女式牛仔裤|女牛仔裤","nav_name":"裤装","nav_cat_ids":"265,504,502,122,49,45,46"},{"cate_names":"衬衫|衬衣|女式衬衫|女衬衣|衬衫|衬衣|男衬衫|男士衬衫","nav_name":"衬衫","nav_cat_ids":"33,75"},{"cate_names":"牛仔裤|女式牛仔裤|女牛仔裤|牛仔上衣|牛仔外套|女式牛仔上衣|女牛仔外套","nav_name":"牛仔","nav_cat_ids":"46,497"},{"cate_names":"钻石首饰|钻石首饰套装|钻石耳饰|钻石手饰|钻石胸饰|钻石颈饰|其他天然玉石|首饰套装|天然颈饰|珠宝奇石|天然耳饰|天然手饰|铂金|PT|铂金耳饰|铂金颈饰|PT颈饰|珀金手饰|黄金首饰|黄金手饰|黄金脚饰|黄金颈饰|黄金耳饰|投资贵金属|手套|手表","nav_name":"配饰","nav_cat_ids":"387,392,391,390,389,388,372,383,382,381,380,373,367,371,369,368,363,386,375,374,365,364,97,173"}],"appApi":"/region/detail/goods-nav"}
     */

    private String message;
    /**
     * items : [{"cate_names":"","nav_name":"今日上新","nav_cat_ids":""},{"cate_names":"背带裙|连衣裙","nav_name":"连衣裙","nav_cat_ids":"506,39"},{"cate_names":"女装|蕾丝衫|雪纺衫|女式雪纺衫|女雪纺衫|t恤|体恤|女T恤|女式T恤|卫衣|女式卫衣|女卫衣","nav_name":"T恤","nav_cat_ids":"2,38,32,34"},{"cate_names":"长裙|皮裙|半身裙","nav_name":"半身裙","nav_cat_ids":"507,505,40"},{"cate_names":"其他裤子|背带裤|女背带裤|女式背带裤|正装裤|女装裤|女式正装裤|短裤|女短裤|女式短裤|打底裤|女式打底裤|女打底裤|西裤|西装裤|正装裤|女式西裤|女西装裤|牛仔裤|女式牛仔裤|女牛仔裤","nav_name":"裤装","nav_cat_ids":"265,504,502,122,49,45,46"},{"cate_names":"衬衫|衬衣|女式衬衫|女衬衣|衬衫|衬衣|男衬衫|男士衬衫","nav_name":"衬衫","nav_cat_ids":"33,75"},{"cate_names":"牛仔裤|女式牛仔裤|女牛仔裤|牛仔上衣|牛仔外套|女式牛仔上衣|女牛仔外套","nav_name":"牛仔","nav_cat_ids":"46,497"},{"cate_names":"钻石首饰|钻石首饰套装|钻石耳饰|钻石手饰|钻石胸饰|钻石颈饰|其他天然玉石|首饰套装|天然颈饰|珠宝奇石|天然耳饰|天然手饰|铂金|PT|铂金耳饰|铂金颈饰|PT颈饰|珀金手饰|黄金首饰|黄金手饰|黄金脚饰|黄金颈饰|黄金耳饰|投资贵金属|手套|手表","nav_name":"配饰","nav_cat_ids":"387,392,391,390,389,388,372,383,382,381,380,373,367,371,369,368,363,386,375,374,365,364,97,173"}]
     * appApi : /region/detail/goods-nav
     */

    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String appApi;
        /**
         * cate_names :
         * nav_name : 今日上新
         * nav_cat_ids :
         */

        private List<ItemsBean> items;

        public String getAppApi() {
            return appApi;
        }

        public void setAppApi(String appApi) {
            this.appApi = appApi;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String cate_names;
            private String nav_name;
            private String nav_cat_ids;

            public String getCate_names() {
                return cate_names;
            }

            public void setCate_names(String cate_names) {
                this.cate_names = cate_names;
            }

            public String getNav_name() {
                return nav_name;
            }

            public void setNav_name(String nav_name) {
                this.nav_name = nav_name;
            }

            public String getNav_cat_ids() {
                return nav_cat_ids;
            }

            public void setNav_cat_ids(String nav_cat_ids) {
                this.nav_cat_ids = nav_cat_ids;
            }
        }
    }
}
