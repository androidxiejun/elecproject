package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class FirCustomGridBean {

    /**
     * message :
     * data : {"items":[{"component":{"componentType":"region_today_cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/D5/97/wKgBf1cQiYCAZjr-AAFJdxIVC4I600.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"detail","type":"sku","id":"6579145","source":"ecshop","sourceId":"2411621","width":"451","height":"453","main_image":"0","post_id":"245","banner_id":"14632","bannerId":"14632","title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","tab_id":"20"},"title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","width":"750","height":"480","price":"1788","origin_price":"3700"},"width":"750","height":"480"}],"appApi":"/region/detail/daily-goods"}
     */

    private String message;
    /**
     * items : [{"component":{"componentType":"region_today_cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/D5/97/wKgBf1cQiYCAZjr-AAFJdxIVC4I600.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"detail","type":"sku","id":"6579145","source":"ecshop","sourceId":"2411621","width":"451","height":"453","main_image":"0","post_id":"245","banner_id":"14632","bannerId":"14632","title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","tab_id":"20"},"title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","width":"750","height":"480","price":"1788","origin_price":"3700"},"width":"750","height":"480"}]
     * appApi : /region/detail/daily-goods
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
         * component : {"componentType":"region_today_cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/D5/97/wKgBf1cQiYCAZjr-AAFJdxIVC4I600.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"detail","type":"sku","id":"6579145","source":"ecshop","sourceId":"2411621","width":"451","height":"453","main_image":"0","post_id":"245","banner_id":"14632","bannerId":"14632","title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","tab_id":"20"},"title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","width":"750","height":"480","price":"1788","origin_price":"3700"}
         * width : 750
         * height : 480
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
            /**
             * componentType : region_today_cell
             * picUrl : http://s0.mingxingyichu.cn/group5/M00/D5/97/wKgBf1cQiYCAZjr-AAFJdxIVC4I600.jpg?imageMogr2?imageMogr2?imageMogr2
             * action : {"actionType":"detail","type":"sku","id":"6579145","source":"ecshop","sourceId":"2411621","width":"451","height":"453","main_image":"0","post_id":"245","banner_id":"14632","bannerId":"14632","title":"Michael Kors/MK女包 女士水桶包真皮单肩手提包","tab_id":"20"}
             * title : Michael Kors/MK女包 女士水桶包真皮单肩手提包
             * width : 750
             * height : 480
             * price : 1788
             * origin_price : 3700
             */

            private ComponentBean component;
            private String width;
            private String height;

            public ComponentBean getComponent() {
                return component;
            }

            public void setComponent(ComponentBean component) {
                this.component = component;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public static class ComponentBean {
                private String componentType;
                private String picUrl;
                /**
                 * actionType : detail
                 * type : sku
                 * id : 6579145
                 * source : ecshop
                 * sourceId : 2411621
                 * width : 451
                 * height : 453
                 * main_image : 0
                 * post_id : 245
                 * banner_id : 14632
                 * bannerId : 14632
                 * title : Michael Kors/MK女包 女士水桶包真皮单肩手提包
                 * tab_id : 20
                 */

                private ActionBean action;
                private String title;
                private String width;
                private String height;
                private String price;
                private String origin_price;

                public String getComponentType() {
                    return componentType;
                }

                public void setComponentType(String componentType) {
                    this.componentType = componentType;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public ActionBean getAction() {
                    return action;
                }

                public void setAction(ActionBean action) {
                    this.action = action;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOrigin_price() {
                    return origin_price;
                }

                public void setOrigin_price(String origin_price) {
                    this.origin_price = origin_price;
                }

                public static class ActionBean {
                    private String actionType;
                    private String type;
                    private String id;
                    private String source;
                    private String sourceId;
                    private String width;
                    private String height;
                    private String main_image;
                    private String post_id;
                    private String banner_id;
                    private String bannerId;
                    private String title;
                    private String tab_id;

                    public String getActionType() {
                        return actionType;
                    }

                    public void setActionType(String actionType) {
                        this.actionType = actionType;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getSource() {
                        return source;
                    }

                    public void setSource(String source) {
                        this.source = source;
                    }

                    public String getSourceId() {
                        return sourceId;
                    }

                    public void setSourceId(String sourceId) {
                        this.sourceId = sourceId;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getMain_image() {
                        return main_image;
                    }

                    public void setMain_image(String main_image) {
                        this.main_image = main_image;
                    }

                    public String getPost_id() {
                        return post_id;
                    }

                    public void setPost_id(String post_id) {
                        this.post_id = post_id;
                    }

                    public String getBanner_id() {
                        return banner_id;
                    }

                    public void setBanner_id(String banner_id) {
                        this.banner_id = banner_id;
                    }

                    public String getBannerId() {
                        return bannerId;
                    }

                    public void setBannerId(String bannerId) {
                        this.bannerId = bannerId;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getTab_id() {
                        return tab_id;
                    }

                    public void setTab_id(String tab_id) {
                        this.tab_id = tab_id;
                    }
                }
            }
        }
    }
}
