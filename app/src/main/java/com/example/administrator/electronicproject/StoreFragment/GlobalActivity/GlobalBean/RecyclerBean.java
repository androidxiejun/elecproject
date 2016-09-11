package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class RecyclerBean {

    /**
     * message :
     * data : {"items":[{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/ED/EC/wKgBjFbmYDeAaIhHAAGP6tGach4751.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1457934544,"actionType":"ecshopSearch","id":"2546","bannerId":"11671","title":"LIP-SERVICE","post_id":"681","banner_id":"11671","tab_id":"55"},"title":"LIP-SERVICE","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/F5/69/wKgBjVbqg_aAdbLaAAGsABoCn4I354.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209768,"actionType":"ecshopSearch","id":"2552","bannerId":"12020","title":"BACKS","post_id":"684","banner_id":"12020","tab_id":"55"},"title":"BACKS","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/B6/F9/wKgBf1bqhA6ATl4KAAGrp8-lx5U887.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209798,"actionType":"ecshopSearch","id":"2573","bannerId":"12021","title":"Ravijour","post_id":"686","banner_id":"12021","tab_id":"55"},"title":"Ravijour","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/EE/01/wKgBjVbmYs2ACcEAAACk-b39L1Y370.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1457939144,"actionType":"ecshopSearch","id":"2525","bannerId":"11681","title":"suteteko","post_id":"688","banner_id":"11681","tab_id":"55"},"title":"suteteko","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/F5/69/wKgBjVbqhDiAIkwxAAGLJH2sHBE218.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209841,"actionType":"ecshopSearch","id":"2554","bannerId":"12022","title":"F-i.n.t","post_id":"689","banner_id":"12022","tab_id":"55"},"title":"F-i.n.t","width":"280","height":"320"},"width":"280","height":"320"}],"appApi":"/region/detail/brands"}
     */

    private String message;
    /**
     * items : [{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/ED/EC/wKgBjFbmYDeAaIhHAAGP6tGach4751.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1457934544,"actionType":"ecshopSearch","id":"2546","bannerId":"11671","title":"LIP-SERVICE","post_id":"681","banner_id":"11671","tab_id":"55"},"title":"LIP-SERVICE","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/F5/69/wKgBjVbqg_aAdbLaAAGsABoCn4I354.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209768,"actionType":"ecshopSearch","id":"2552","bannerId":"12020","title":"BACKS","post_id":"684","banner_id":"12020","tab_id":"55"},"title":"BACKS","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/B6/F9/wKgBf1bqhA6ATl4KAAGrp8-lx5U887.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209798,"actionType":"ecshopSearch","id":"2573","bannerId":"12021","title":"Ravijour","post_id":"686","banner_id":"12021","tab_id":"55"},"title":"Ravijour","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/EE/01/wKgBjVbmYs2ACcEAAACk-b39L1Y370.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1457939144,"actionType":"ecshopSearch","id":"2525","bannerId":"11681","title":"suteteko","post_id":"688","banner_id":"11681","tab_id":"55"},"title":"suteteko","width":"280","height":"320"},"width":"280","height":"320"},{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/F5/69/wKgBjVbqhDiAIkwxAAGLJH2sHBE218.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1458209841,"actionType":"ecshopSearch","id":"2554","bannerId":"12022","title":"F-i.n.t","post_id":"689","banner_id":"12022","tab_id":"55"},"title":"F-i.n.t","width":"280","height":"320"},"width":"280","height":"320"}]
     * appApi : /region/detail/brands
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
         * component : {"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group6/M00/ED/EC/wKgBjFbmYDeAaIhHAAGP6tGach4751.png?imageMogr2?imageMogr2?imageMogr2","action":{"unixtime":1457934544,"actionType":"ecshopSearch","id":"2546","bannerId":"11671","title":"LIP-SERVICE","post_id":"681","banner_id":"11671","tab_id":"55"},"title":"LIP-SERVICE","width":"280","height":"320"}
         * width : 280
         * height : 320
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
             * componentType : cell
             * picUrl : http://s0.mingxingyichu.cn/group6/M00/ED/EC/wKgBjFbmYDeAaIhHAAGP6tGach4751.png?imageMogr2?imageMogr2?imageMogr2
             * action : {"unixtime":1457934544,"actionType":"ecshopSearch","id":"2546","bannerId":"11671","title":"LIP-SERVICE","post_id":"681","banner_id":"11671","tab_id":"55"}
             * title : LIP-SERVICE
             * width : 280
             * height : 320
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
                 * unixtime : 1457934544
                 * actionType : ecshopSearch
                 * id : 2546
                 * bannerId : 11671
                 * title : LIP-SERVICE
                 * post_id : 681
                 * banner_id : 11671
                 * tab_id : 55
                 */

                private ActionBean action;
                private String title;
                private String width;
                private String height;

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

                public static class ActionBean {
                    private int unixtime;
                    private String actionType;
                    private String id;
                    private String bannerId;
                    private String title;
                    private String post_id;
                    private String banner_id;
                    private String tab_id;

                    public int getUnixtime() {
                        return unixtime;
                    }

                    public void setUnixtime(int unixtime) {
                        this.unixtime = unixtime;
                    }

                    public String getActionType() {
                        return actionType;
                    }

                    public void setActionType(String actionType) {
                        this.actionType = actionType;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
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
