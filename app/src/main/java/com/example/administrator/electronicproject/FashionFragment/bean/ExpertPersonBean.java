package com.example.administrator.electronicproject.FashionFragment.bean;

/**
 * Created by sunbin on 2016/9/10.
 */
public class ExpertPersonBean {


    /**
     * message :
     * data : {"userId":"12951489","userName":"Burqa Ange","userAvatar":"http://wx.qlogo.cn/mmopen/DNGh0ujJaR7Nfvr11UnpD2DzkiakTCssjHrfibxKSQnTjAIfpGdNiaSb7zdUZkRBibgaWic6I5e7ibTicSlyYEARVC5Wr8aafd3Y5Mg/0","userLevel":"1","score":"0","needScore":"0","userRankIcon":"http://m6.mingxingyichu.cn/images/images/20150414/2f613e66-e831-47f8-879b-27cde14c0874.png","userRank":"V0会员","starUserIcon":"http://m6.mingxingyichu.cn/images/images/20140505/5a82f938-8c0c-4921-a857-ae304bda55da.png","userTypeName":"设计师","description":"80后全能艺人","banwuIcon":"","followedNum":"0","followingNum":"0","userFollowNum":"0","isFollow":"0","brandNum":"0","favoriteNum":"0","followType":"","background":"http://s0.mingxingyichu.cngroup2/M00/1C/A1/wKgBWVRXJMOAZJsTAAIUUSu_oI4424.jpg","background_id":"11","userFansNum":"7534","appApi":"/user/info"}
     */

    private String message;
    /**
     * userId : 12951489
     * userName : Burqa Ange
     * userAvatar : http://wx.qlogo.cn/mmopen/DNGh0ujJaR7Nfvr11UnpD2DzkiakTCssjHrfibxKSQnTjAIfpGdNiaSb7zdUZkRBibgaWic6I5e7ibTicSlyYEARVC5Wr8aafd3Y5Mg/0
     * userLevel : 1
     * score : 0
     * needScore : 0
     * userRankIcon : http://m6.mingxingyichu.cn/images/images/20150414/2f613e66-e831-47f8-879b-27cde14c0874.png
     * userRank : V0会员
     * starUserIcon : http://m6.mingxingyichu.cn/images/images/20140505/5a82f938-8c0c-4921-a857-ae304bda55da.png
     * userTypeName : 设计师
     * description : 80后全能艺人
     * banwuIcon :
     * followedNum : 0
     * followingNum : 0
     * userFollowNum : 0
     * isFollow : 0
     * brandNum : 0
     * favoriteNum : 0
     * followType :
     * background : http://s0.mingxingyichu.cngroup2/M00/1C/A1/wKgBWVRXJMOAZJsTAAIUUSu_oI4424.jpg
     * background_id : 11
     * userFansNum : 7534
     * appApi : /user/info
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
        private int userId;
        private String userName;
        private String userAvatar;
        private String userLevel;
        private String score;
        private String needScore;
        private String userRankIcon;
        private String userRank;
        private String starUserIcon;
        private String userTypeName;
        private String description;
        private String banwuIcon;
        private String followedNum;
        private String followingNum;
        private String userFollowNum;
        private String isFollow;
        private String brandNum;
        private String favoriteNum;
        private String followType;
        private String background;
        private int background_id;
        private String userFansNum;
        private String appApi;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getNeedScore() {
            return needScore;
        }

        public void setNeedScore(String needScore) {
            this.needScore = needScore;
        }

        public String getUserRankIcon() {
            return userRankIcon;
        }

        public void setUserRankIcon(String userRankIcon) {
            this.userRankIcon = userRankIcon;
        }

        public String getUserRank() {
            return userRank;
        }

        public void setUserRank(String userRank) {
            this.userRank = userRank;
        }

        public String getStarUserIcon() {
            return starUserIcon;
        }

        public void setStarUserIcon(String starUserIcon) {
            this.starUserIcon = starUserIcon;
        }

        public String getUserTypeName() {
            return userTypeName;
        }

        public void setUserTypeName(String userTypeName) {
            this.userTypeName = userTypeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBanwuIcon() {
            return banwuIcon;
        }

        public void setBanwuIcon(String banwuIcon) {
            this.banwuIcon = banwuIcon;
        }

        public String getFollowedNum() {
            return followedNum;
        }

        public void setFollowedNum(String followedNum) {
            this.followedNum = followedNum;
        }

        public String getFollowingNum() {
            return followingNum;
        }

        public void setFollowingNum(String followingNum) {
            this.followingNum = followingNum;
        }

        public String getUserFollowNum() {
            return userFollowNum;
        }

        public void setUserFollowNum(String userFollowNum) {
            this.userFollowNum = userFollowNum;
        }

        public String getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(String isFollow) {
            this.isFollow = isFollow;
        }

        public String getBrandNum() {
            return brandNum;
        }

        public void setBrandNum(String brandNum) {
            this.brandNum = brandNum;
        }

        public String getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(String favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getFollowType() {
            return followType;
        }

        public void setFollowType(String followType) {
            this.followType = followType;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getBackground_id() {
            return background_id;
        }

        public void setBackground_id(int background_id) {
            this.background_id = background_id;
        }

        public String getUserFansNum() {
            return userFansNum;
        }

        public void setUserFansNum(String userFansNum) {
            this.userFansNum = userFansNum;
        }

        public String getAppApi() {
            return appApi;
        }

        public void setAppApi(String appApi) {
            this.appApi = appApi;
        }
    }
}
