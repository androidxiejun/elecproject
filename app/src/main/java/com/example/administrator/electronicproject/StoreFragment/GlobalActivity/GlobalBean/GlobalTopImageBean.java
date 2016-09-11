package com.example.administrator.electronicproject.StoreFragment.GlobalActivity.GlobalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class GlobalTopImageBean {

    /**
     * message :
     * data : {"items":[{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/99/42/wKgBf1bVY-6Adu4OAAQTWiAKy10196.jpg?imageMogr2?imageMogr2?imageMogr2","action":{},"title":"国内精选","width":"750","height":"350"},"width":"750","height":"350"}],"appApi":""}
     */

    private String message;
    /**
     * items : [{"component":{"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/99/42/wKgBf1bVY-6Adu4OAAQTWiAKy10196.jpg?imageMogr2?imageMogr2?imageMogr2","action":{},"title":"国内精选","width":"750","height":"350"},"width":"750","height":"350"}]
     * appApi :
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
         * component : {"componentType":"cell","picUrl":"http://s0.mingxingyichu.cn/group5/M00/99/42/wKgBf1bVY-6Adu4OAAQTWiAKy10196.jpg?imageMogr2?imageMogr2?imageMogr2","action":{},"title":"国内精选","width":"750","height":"350"}
         * width : 750
         * height : 350
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
             * picUrl : http://s0.mingxingyichu.cn/group5/M00/99/42/wKgBf1bVY-6Adu4OAAQTWiAKy10196.jpg?imageMogr2?imageMogr2?imageMogr2
             * action : {}
             * title : 国内精选
             * width : 750
             * height : 350
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
            }
        }
    }
}
