package com.example.administrator.electronicproject.ClassifyFragment.DetailBrandActivity.BrandDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class BrandDetailTopBean {

    /**
     * code : 0
     * msg : success
     * isNew : 1
     * version : 7.0.0
     * data : {"business":{"appApi":"http://api-v2.mall.hichao.com","business_name":"VIVIDNCO","business_id":"3051","business_brief":"VIVIDNCO品牌女装是结合流行趋势，为性感、浪漫、时尚、优雅且独立的女性而设计。始终坚持展现女性的柔美，勾勒出女性的曲线。产品大多采用高档的时尚面料，肌理细腻柔软，每一件商品都结合了当下最时尚的流行元素，透露出感性的美。","introduce":"","business_image":"http://mxycsku.mingxingyichu.cn/group5/M00/F6/71/wKgBf1dGY5uAThbNAAARMQYL5Pw801.jpg","business_image_width":"200","business_image_height":"200","detailUrl":"http://m.hichao.com/app/templates/brand_detail.html?id=3051","english_name":"VIVIDNCO","search_keyword":"VIVIDNCO;VIVIDNCO","business_banner_url":"http://mxycsku.mingxingyichu.cn/group6/M00/36/48/wKgBjFdGZSSAbVO1AAGKZzqkJEE66.jpeg","business_banner_width":"750","business_banner_height":"350","brand_title":"品牌店","short_title":"","img_falls":"","img_falls_width":"0.01","img_falls_height":"0.01"},"business_promote":[],"business_recommend":[],"rule_promote_name":[],"sort_cate":[{"sort":"1","title":"默认"},{"sort":"5","title":"上新"},{"sort":"3","title":"销量"},{"sort":"4","title":"价格","up":"4","down":"2"},{"sort":"6","title":"分类","two":[{"cid":"17","name":"女式上装"},{"cid":"19","name":"裙子"},{"cid":"20","name":"女裤"},{"cid":"171","name":"其它女装"},{"cid":"250","name":"女式泳衣"}]}]}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private String code;
        private String msg;
        private int isNew;
        private String version;
        /**
         * business : {"appApi":"http://api-v2.mall.hichao.com","business_name":"VIVIDNCO","business_id":"3051","business_brief":"VIVIDNCO品牌女装是结合流行趋势，为性感、浪漫、时尚、优雅且独立的女性而设计。始终坚持展现女性的柔美，勾勒出女性的曲线。产品大多采用高档的时尚面料，肌理细腻柔软，每一件商品都结合了当下最时尚的流行元素，透露出感性的美。","introduce":"","business_image":"http://mxycsku.mingxingyichu.cn/group5/M00/F6/71/wKgBf1dGY5uAThbNAAARMQYL5Pw801.jpg","business_image_width":"200","business_image_height":"200","detailUrl":"http://m.hichao.com/app/templates/brand_detail.html?id=3051","english_name":"VIVIDNCO","search_keyword":"VIVIDNCO;VIVIDNCO","business_banner_url":"http://mxycsku.mingxingyichu.cn/group6/M00/36/48/wKgBjFdGZSSAbVO1AAGKZzqkJEE66.jpeg","business_banner_width":"750","business_banner_height":"350","brand_title":"品牌店","short_title":"","img_falls":"","img_falls_width":"0.01","img_falls_height":"0.01"}
         * business_promote : []
         * business_recommend : []
         * rule_promote_name : []
         * sort_cate : [{"sort":"1","title":"默认"},{"sort":"5","title":"上新"},{"sort":"3","title":"销量"},{"sort":"4","title":"价格","up":"4","down":"2"},{"sort":"6","title":"分类","two":[{"cid":"17","name":"女式上装"},{"cid":"19","name":"裙子"},{"cid":"20","name":"女裤"},{"cid":"171","name":"其它女装"},{"cid":"250","name":"女式泳衣"}]}]
         */

        private DataBean data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * appApi : http://api-v2.mall.hichao.com
             * business_name : VIVIDNCO
             * business_id : 3051
             * business_brief : VIVIDNCO品牌女装是结合流行趋势，为性感、浪漫、时尚、优雅且独立的女性而设计。始终坚持展现女性的柔美，勾勒出女性的曲线。产品大多采用高档的时尚面料，肌理细腻柔软，每一件商品都结合了当下最时尚的流行元素，透露出感性的美。
             * introduce :
             * business_image : http://mxycsku.mingxingyichu.cn/group5/M00/F6/71/wKgBf1dGY5uAThbNAAARMQYL5Pw801.jpg
             * business_image_width : 200
             * business_image_height : 200
             * detailUrl : http://m.hichao.com/app/templates/brand_detail.html?id=3051
             * english_name : VIVIDNCO
             * search_keyword : VIVIDNCO;VIVIDNCO
             * business_banner_url : http://mxycsku.mingxingyichu.cn/group6/M00/36/48/wKgBjFdGZSSAbVO1AAGKZzqkJEE66.jpeg
             * business_banner_width : 750
             * business_banner_height : 350
             * brand_title : 品牌店
             * short_title :
             * img_falls :
             * img_falls_width : 0.01
             * img_falls_height : 0.01
             */

            private BusinessBean business;
            private List<?> business_promote;
            private List<?> business_recommend;
            private List<?> rule_promote_name;
            /**
             * sort : 1
             * title : 默认
             */

            private List<SortCateBean> sort_cate;

            public BusinessBean getBusiness() {
                return business;
            }

            public void setBusiness(BusinessBean business) {
                this.business = business;
            }

            public List<?> getBusiness_promote() {
                return business_promote;
            }

            public void setBusiness_promote(List<?> business_promote) {
                this.business_promote = business_promote;
            }

            public List<?> getBusiness_recommend() {
                return business_recommend;
            }

            public void setBusiness_recommend(List<?> business_recommend) {
                this.business_recommend = business_recommend;
            }

            public List<?> getRule_promote_name() {
                return rule_promote_name;
            }

            public void setRule_promote_name(List<?> rule_promote_name) {
                this.rule_promote_name = rule_promote_name;
            }

            public List<SortCateBean> getSort_cate() {
                return sort_cate;
            }

            public void setSort_cate(List<SortCateBean> sort_cate) {
                this.sort_cate = sort_cate;
            }

            public static class BusinessBean {
                private String appApi;
                private String business_name;
                private String business_id;
                private String business_brief;
                private String introduce;
                private String business_image;
                private String business_image_width;
                private String business_image_height;
                private String detailUrl;
                private String english_name;
                private String search_keyword;
                private String business_banner_url;
                private String business_banner_width;
                private String business_banner_height;
                private String brand_title;
                private String short_title;
                private String img_falls;
                private String img_falls_width;
                private String img_falls_height;

                public String getAppApi() {
                    return appApi;
                }

                public void setAppApi(String appApi) {
                    this.appApi = appApi;
                }

                public String getBusiness_name() {
                    return business_name;
                }

                public void setBusiness_name(String business_name) {
                    this.business_name = business_name;
                }

                public String getBusiness_id() {
                    return business_id;
                }

                public void setBusiness_id(String business_id) {
                    this.business_id = business_id;
                }

                public String getBusiness_brief() {
                    return business_brief;
                }

                public void setBusiness_brief(String business_brief) {
                    this.business_brief = business_brief;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getBusiness_image() {
                    return business_image;
                }

                public void setBusiness_image(String business_image) {
                    this.business_image = business_image;
                }

                public String getBusiness_image_width() {
                    return business_image_width;
                }

                public void setBusiness_image_width(String business_image_width) {
                    this.business_image_width = business_image_width;
                }

                public String getBusiness_image_height() {
                    return business_image_height;
                }

                public void setBusiness_image_height(String business_image_height) {
                    this.business_image_height = business_image_height;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public String getEnglish_name() {
                    return english_name;
                }

                public void setEnglish_name(String english_name) {
                    this.english_name = english_name;
                }

                public String getSearch_keyword() {
                    return search_keyword;
                }

                public void setSearch_keyword(String search_keyword) {
                    this.search_keyword = search_keyword;
                }

                public String getBusiness_banner_url() {
                    return business_banner_url;
                }

                public void setBusiness_banner_url(String business_banner_url) {
                    this.business_banner_url = business_banner_url;
                }

                public String getBusiness_banner_width() {
                    return business_banner_width;
                }

                public void setBusiness_banner_width(String business_banner_width) {
                    this.business_banner_width = business_banner_width;
                }

                public String getBusiness_banner_height() {
                    return business_banner_height;
                }

                public void setBusiness_banner_height(String business_banner_height) {
                    this.business_banner_height = business_banner_height;
                }

                public String getBrand_title() {
                    return brand_title;
                }

                public void setBrand_title(String brand_title) {
                    this.brand_title = brand_title;
                }

                public String getShort_title() {
                    return short_title;
                }

                public void setShort_title(String short_title) {
                    this.short_title = short_title;
                }

                public String getImg_falls() {
                    return img_falls;
                }

                public void setImg_falls(String img_falls) {
                    this.img_falls = img_falls;
                }

                public String getImg_falls_width() {
                    return img_falls_width;
                }

                public void setImg_falls_width(String img_falls_width) {
                    this.img_falls_width = img_falls_width;
                }

                public String getImg_falls_height() {
                    return img_falls_height;
                }

                public void setImg_falls_height(String img_falls_height) {
                    this.img_falls_height = img_falls_height;
                }
            }

            public static class SortCateBean {
                private String sort;
                private String title;

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
