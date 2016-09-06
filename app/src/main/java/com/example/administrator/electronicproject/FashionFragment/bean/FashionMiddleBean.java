package com.example.administrator.electronicproject.FashionFragment.bean;

import java.util.List;

/**
 * Created by sunbin on 2016/9/6.
 */
public class FashionMiddleBean {

    /**
     * code : 0
     * msg :
     * isNew : 1
     * version :
     * data : {"items":[{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s3.mingxingyichu.cn/group6/M00/82/40/wKgBjFe-UbyAJTVMAAB9JflLdBU532.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"526","tag":"潮流搭配","title":""},"title":"#搭配","width":"230","height":"144"}},{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s5.mingxingyichu.cn/group6/M00/82/4A/wKgBjVe-UdCADOJAAACAZwUpNpw043.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"527","tag":"衣橱杂志","title":""},"title":"#专题","width":"230","height":"144"}},{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s2.mingxingyichu.cn/group6/M00/82/4E/wKgBjVe-VWeAOEhLAABXIB3i_Dg982.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"586","tag":"复古","title":""},"title":"#复古","width":"230","height":"144"}},{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s1.mingxingyichu.cn/group5/M00/3E/D0/wKgBf1e-U6CAL5eiAABJJOvK6HI558.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"391","tag":"性感","title":""},"title":"#性感","width":"230","height":"144"}},{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s5.mingxingyichu.cn/group5/M00/69/2E/wKgBfVfOeJuACzx5AABSLJWQrrg314.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"760","tag":"帆布鞋","title":""},"title":"#帆布鞋","width":"230","height":"144"}},{"width":"230","height":"144","component":{"componentType":"labelCell","picUrl":"http://s2.mingxingyichu.cn/group6/M00/AC/1B/wKgBjFfD63KAJP1sAABW1LeJNw4903.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"668","tag":"夹克","title":""},"title":"#夹克","width":"230","height":"144"}}]}
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
             * width : 230
             * height : 144
             * component : {"componentType":"labelCell","picUrl":"http://s3.mingxingyichu.cn/group6/M00/82/40/wKgBjFe-UbyAJTVMAAB9JflLdBU532.jpg?imageMogr2?imageMogr2?imageMogr2","action":{"actionType":"tag","type":"forum","id":"526","tag":"潮流搭配","title":""},"title":"#搭配","width":"230","height":"144"}
             */

            private List<ItemsBean> items;

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                private String width;
                private String height;
                /**
                 * componentType : labelCell
                 * picUrl : http://s3.mingxingyichu.cn/group6/M00/82/40/wKgBjFe-UbyAJTVMAAB9JflLdBU532.jpg?imageMogr2?imageMogr2?imageMogr2
                 * action : {"actionType":"tag","type":"forum","id":"526","tag":"潮流搭配","title":""}
                 * title : #搭配
                 * width : 230
                 * height : 144
                 */

                private ComponentBean component;

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

                public ComponentBean getComponent() {
                    return component;
                }

                public void setComponent(ComponentBean component) {
                    this.component = component;
                }

                public static class ComponentBean {
                    private String componentType;
                    private String picUrl;
                    /**
                     * actionType : tag
                     * type : forum
                     * id : 526
                     * tag : 潮流搭配
                     * title :
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
                        private String actionType;
                        private String type;
                        private String id;
                        private String tag;
                        private String title;

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

                        public String getTag() {
                            return tag;
                        }

                        public void setTag(String tag) {
                            this.tag = tag;
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
    }
}
