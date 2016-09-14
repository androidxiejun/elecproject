package com.example.administrator.electronicproject.FashionFragment.bean;

import java.util.List;

/**
 * Created by sunbin on 2016/9/7.
 */
public class RecommendTopDetailsCommentBean {


    /**
     * message :
     * data : {"items":[{"component":{"componentType":"postSubThreadCell","id":"1410170","userAvatar":"http://api.upload1.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","userName":"夕夏未薰","userTypeName":"时尚博主","userId":"3467486","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"回复24楼砂糖味道大熊猫乐乐 ：宝宝  好好回帖   哈哈  期待你凹妆","action":{"actionType":"detail","type":"user","id":"3467486","userAvatar":"http://api.upload2.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","useName":"夕夏未薰","useId":"3467486"}}},{"component":{"componentType":"postSubThreadCell","id":"1410157","userAvatar":"http://tp4.sinaimg.cn/5680668031/180/5740793029/0","userName":"砂糖味道大熊猫乐乐","userTypeName":"美妆达人","userId":"11566255","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"洗刷大礼包 绝对需要呀","action":{"actionType":"detail","type":"user","id":"11566255","userAvatar":"http://tp4.sinaimg.cn/5680668031/180/5740793029/0","useName":"砂糖味道大熊猫乐乐","useId":"11566255"}}},{"component":{"componentType":"postSubThreadCell","id":"1410155","userAvatar":"http://api.upload3.pimg.cn/user_upload/3ff092cd1794490bb2a4872365abe29e.jpeg_thumb_120x%3E_.jpeg","userName":"😘羽彤hyt*^_^*","userTypeName":"","userId":"8722298","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"腮红刷，最喜欢用了，但我是用洗面奶或是洗洁精来洗的","action":{"actionType":"detail","type":"user","id":"8722298","userAvatar":"http://api.upload4.pimg.cn/user_upload/3ff092cd1794490bb2a4872365abe29e.jpeg_thumb_120x%3E_.jpeg","useName":"😘羽彤hyt*^_^*","useId":"8722298"}}}],"comment_count":"22","flag":"3","appApi":"/forum/post-comments"}
     */

    private String message;
    /**
     * items : [{"component":{"componentType":"postSubThreadCell","id":"1410170","userAvatar":"http://api.upload1.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","userName":"夕夏未薰","userTypeName":"时尚博主","userId":"3467486","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"回复24楼砂糖味道大熊猫乐乐 ：宝宝  好好回帖   哈哈  期待你凹妆","action":{"actionType":"detail","type":"user","id":"3467486","userAvatar":"http://api.upload2.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","useName":"夕夏未薰","useId":"3467486"}}},{"component":{"componentType":"postSubThreadCell","id":"1410157","userAvatar":"http://tp4.sinaimg.cn/5680668031/180/5740793029/0","userName":"砂糖味道大熊猫乐乐","userTypeName":"美妆达人","userId":"11566255","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"洗刷大礼包 绝对需要呀","action":{"actionType":"detail","type":"user","id":"11566255","userAvatar":"http://tp4.sinaimg.cn/5680668031/180/5740793029/0","useName":"砂糖味道大熊猫乐乐","useId":"11566255"}}},{"component":{"componentType":"postSubThreadCell","id":"1410155","userAvatar":"http://api.upload3.pimg.cn/user_upload/3ff092cd1794490bb2a4872365abe29e.jpeg_thumb_120x%3E_.jpeg","userName":"😘羽彤hyt*^_^*","userTypeName":"","userId":"8722298","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"腮红刷，最喜欢用了，但我是用洗面奶或是洗洁精来洗的","action":{"actionType":"detail","type":"user","id":"8722298","userAvatar":"http://api.upload4.pimg.cn/user_upload/3ff092cd1794490bb2a4872365abe29e.jpeg_thumb_120x%3E_.jpeg","useName":"😘羽彤hyt*^_^*","useId":"8722298"}}}]
     * comment_count : 22
     * flag : 3
     * appApi : /forum/post-comments
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
        private int comment_count;
        private String flag;
        private String appApi;
        /**
         * component : {"componentType":"postSubThreadCell","id":"1410170","userAvatar":"http://api.upload1.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","userName":"夕夏未薰","userTypeName":"时尚博主","userId":"3467486","publishDate":"3小时前","roleIcon":[],"contentHead":"","content":"回复24楼砂糖味道大熊猫乐乐 ：宝宝  好好回帖   哈哈  期待你凹妆","action":{"actionType":"detail","type":"user","id":"3467486","userAvatar":"http://api.upload2.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","useName":"夕夏未薰","useId":"3467486"}}
         */

        private List<ItemsBean> items;

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

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
             * componentType : postSubThreadCell
             * id : 1410170
             * userAvatar : http://api.upload1.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg
             * userName : 夕夏未薰
             * userTypeName : 时尚博主
             * userId : 3467486
             * publishDate : 3小时前
             * roleIcon : []
             * contentHead :
             * content : 回复24楼砂糖味道大熊猫乐乐 ：宝宝  好好回帖   哈哈  期待你凹妆
             * action : {"actionType":"detail","type":"user","id":"3467486","userAvatar":"http://api.upload2.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg","useName":"夕夏未薰","useId":"3467486"}
             */

            private ComponentBean component;

            public ComponentBean getComponent() {
                return component;
            }

            public void setComponent(ComponentBean component) {
                this.component = component;
            }

            public static class ComponentBean {
                private String componentType;
                private String id;
                private String userAvatar;
                private String userName;
                private String userTypeName;
                private String userId;
                private String publishDate;
                private String contentHead;
                private String content;
                /**
                 * actionType : detail
                 * type : user
                 * id : 3467486
                 * userAvatar : http://api.upload2.pimg.cn/user_upload/fc1e08a7ddde438c8f126a3e55fe0e1d.jpeg_thumb_120x%3E_.jpeg
                 * useName : 夕夏未薰
                 * useId : 3467486
                 */

                private ActionBean action;
                private List<?> roleIcon;

                public String getComponentType() {
                    return componentType;
                }

                public void setComponentType(String componentType) {
                    this.componentType = componentType;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserAvatar() {
                    return userAvatar;
                }

                public void setUserAvatar(String userAvatar) {
                    this.userAvatar = userAvatar;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getUserTypeName() {
                    return userTypeName;
                }

                public void setUserTypeName(String userTypeName) {
                    this.userTypeName = userTypeName;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getPublishDate() {
                    return publishDate;
                }

                public void setPublishDate(String publishDate) {
                    this.publishDate = publishDate;
                }

                public String getContentHead() {
                    return contentHead;
                }

                public void setContentHead(String contentHead) {
                    this.contentHead = contentHead;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public ActionBean getAction() {
                    return action;
                }

                public void setAction(ActionBean action) {
                    this.action = action;
                }

                public List<?> getRoleIcon() {
                    return roleIcon;
                }

                public void setRoleIcon(List<?> roleIcon) {
                    this.roleIcon = roleIcon;
                }

                public static class ActionBean {
                    private String actionType;
                    private String type;
                    private String id;
                    private String userAvatar;
                    private String useName;
                    private String useId;

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

                    public String getUserAvatar() {
                        return userAvatar;
                    }

                    public void setUserAvatar(String userAvatar) {
                        this.userAvatar = userAvatar;
                    }

                    public String getUseName() {
                        return useName;
                    }

                    public void setUseName(String useName) {
                        this.useName = useName;
                    }

                    public String getUseId() {
                        return useId;
                    }

                    public void setUseId(String useId) {
                        this.useId = useId;
                    }
                }
            }
        }
    }
}
