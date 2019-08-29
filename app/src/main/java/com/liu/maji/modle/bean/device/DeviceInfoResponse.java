package com.liu.maji.modle.bean.device;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class DeviceInfoResponse extends CommonResponse {


    /**
     * data : {"page":1,"pageSize":10,"count":17,"records":[{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1323,"createDate":1561184725000,"updateDate":1561424440000,"createUserId":242,"updateUserId":242,"cd":"869300039954684","name":"刷卡盘---洋葱","simCardId":506,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"17","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_swipe","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false,"renewFlag":"Y"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1322,"createDate":1561174523000,"updateDate":1561424493000,"createUserId":242,"updateUserId":242,"cd":"860344041931784","name":"充值机---豆豆","equipTypeId":148,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"19","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/271/20190624/6F12CBABE0A5475499E2DD691DC9DA19-1156137011.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1320,"createDate":1560257762000,"updateDate":1561424412000,"createUserId":242,"updateUserId":242,"cd":"869300039957851","name":"充值机---超音速飞机","simCardId":500,"equipTypeId":148,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"2","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/271/20190624/6F12CBABE0A5475499E2DD691DC9DA19-1156137011.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","isOnline":true,"isReplenisher":"N","sigIntensity":"ZERO","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-0.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1316,"createDate":1559916614000,"updateDate":1561424303000,"createUserId":242,"updateUserId":242,"cd":"869300039960301","name":"椰子","simCardId":503,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":2,"productUnit":"分钟","fee":"2.00","sig":"16","taoCanId":552,"taocanName":"2元2分钟","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1315,"createDate":1559914473000,"updateDate":1561455564000,"createUserId":242,"updateUserId":242,"cd":"869300039968775","name":"香蕉","simCardId":507,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"11","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"LOW","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-2.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1314,"createDate":1559914032000,"updateDate":1561424210000,"createUserId":242,"updateUserId":242,"cd":"869300039967132","name":"黄桃","equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"27","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"HIGH","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-4.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1312,"createDate":1559912841000,"updateDate":1561424178000,"createUserId":242,"updateUserId":242,"cd":"869300039954643","name":"葡萄","equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"17","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1310,"createDate":1559740867000,"updateDate":1561424532000,"createUserId":242,"updateUserId":242,"cd":"869300039967124","name":"提子","simCardId":501,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"16","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"刘秦刚","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1304,"createDate":1559401981000,"updateDate":1561424965000,"createUserId":242,"updateUserId":242,"cd":"869300039967314","name":"榴莲","simCardId":499,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"20","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_swipe","isOnline":true,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1303,"createDate":1559186566000,"updateDate":1561424743000,"createUserId":242,"updateUserId":242,"cd":"869300039967272X","name":"充值机---战斗机","simCardId":215,"equipTypeId":149,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"18","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"15658853435","status":"stop","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","renewFlag":"Y","isOnline":true,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false}],"pages":2,"recordStart":1,"recordEnd":10,"merchantId":272,"agentId":476,"unSoldQuantity":0,"swift":false}
     * totalNum : 17
     * onlineNum : 7
     * offlineNum : 10
     */

    private DataBean data;
    private int totalNum;
    private int onlineNum;
    private int offlineNum;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public int getOfflineNum() {
        return offlineNum;
    }

    public void setOfflineNum(int offlineNum) {
        this.offlineNum = offlineNum;
    }

    public static class DataBean {
        /**
         * page : 1
         * pageSize : 10
         * count : 17
         * records : [{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1323,"createDate":1561184725000,"updateDate":1561424440000,"createUserId":242,"updateUserId":242,"cd":"869300039954684","name":"刷卡盘---洋葱","simCardId":506,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"17","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_swipe","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1322,"createDate":1561174523000,"updateDate":1561424493000,"createUserId":242,"updateUserId":242,"cd":"860344041931784","name":"充值机---豆豆","equipTypeId":148,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"19","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/271/20190624/6F12CBABE0A5475499E2DD691DC9DA19-1156137011.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1320,"createDate":1560257762000,"updateDate":1561424412000,"createUserId":242,"updateUserId":242,"cd":"869300039957851","name":"充值机---超音速飞机","simCardId":500,"equipTypeId":148,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"2","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/271/20190624/6F12CBABE0A5475499E2DD691DC9DA19-1156137011.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","isOnline":true,"isReplenisher":"N","sigIntensity":"ZERO","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-0.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1316,"createDate":1559916614000,"updateDate":1561424303000,"createUserId":242,"updateUserId":242,"cd":"869300039960301","name":"椰子","simCardId":503,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":2,"productUnit":"分钟","fee":"2.00","sig":"16","taoCanId":552,"taocanName":"2元2分钟","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1315,"createDate":1559914473000,"updateDate":1561455564000,"createUserId":242,"updateUserId":242,"cd":"869300039968775","name":"香蕉","simCardId":507,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"11","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"LOW","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-2.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1314,"createDate":1559914032000,"updateDate":1561424210000,"createUserId":242,"updateUserId":242,"cd":"869300039967132","name":"黄桃","equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"27","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"HIGH","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-4.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1312,"createDate":1559912841000,"updateDate":1561424178000,"createUserId":242,"updateUserId":242,"cd":"869300039954643","name":"葡萄","equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"17","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1310,"createDate":1559740867000,"updateDate":1561424532000,"createUserId":242,"updateUserId":242,"cd":"869300039967124","name":"提子","simCardId":501,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":60,"productUnit":"分钟","fee":"10.00","sig":"16","taoCanId":550,"taocanName":"10元一小时","agentId":476,"agentPhone":"18058423179","contactUser":"刘秦刚","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"kiddie_ride","isOnline":false,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1304,"createDate":1559401981000,"updateDate":1561424965000,"createUserId":242,"updateUserId":242,"cd":"869300039967314","name":"榴莲","simCardId":499,"equipTypeId":149,"equipTypeName":"刷卡盘001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"20","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"18058423179","status":"normal","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_swipe","isOnline":true,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":1303,"createDate":1559186566000,"updateDate":1561424743000,"createUserId":242,"updateUserId":242,"cd":"869300039967272X","name":"充值机---战斗机","simCardId":215,"equipTypeId":149,"equipTypeName":"充值机001","isCommer":"Y","merchantId":272,"productNumber":1,"productUnit":"局","fee":"2.00","sig":"18","taoCanId":549,"taocanName":"2元一局","agentId":476,"agentPhone":"18058423179","contactUser":"客服","contactMobilePhone":"15658853435","status":"stop","serviceType":"renewDivide","renewPrice":0.01,"oldRenewPrice":0.01,"showImg":"http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg","isFirstBuyout":"Y","isFirstRenew":"Y","isFirstRenewDivide":"N","equipPort":"ALL","baseType":"mj_recharge","renewFlag":"Y","isOnline":true,"isReplenisher":"N","sigIntensity":"MEDIUM","sigIntensityPath":"http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png","agentType":"renewer","unSoldQuantity":0,"swift":false}]
         * pages : 2
         * recordStart : 1
         * recordEnd : 10
         * merchantId : 272
         * agentId : 476
         * unSoldQuantity : 0
         * swift : false
         */

        private int page;
        private int pageSize;
        private int count;
        private int pages;
        private int recordStart;
        private int recordEnd;
        private int merchantId;
        private int agentId;
        private int unSoldQuantity;
        private boolean swift;
        private List<RecordsBean> records;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getRecordStart() {
            return recordStart;
        }

        public void setRecordStart(int recordStart) {
            this.recordStart = recordStart;
        }

        public int getRecordEnd() {
            return recordEnd;
        }

        public void setRecordEnd(int recordEnd) {
            this.recordEnd = recordEnd;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public int getUnSoldQuantity() {
            return unSoldQuantity;
        }

        public void setUnSoldQuantity(int unSoldQuantity) {
            this.unSoldQuantity = unSoldQuantity;
        }

        public boolean isSwift() {
            return swift;
        }

        public void setSwift(boolean swift) {
            this.swift = swift;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            public long getRemainTime() {
                return remainTime;
            }

            public void setRemainTime(long remainTime) {
                this.remainTime = remainTime;
            }

            /**
             * page : 1
             * pageSize : 10
             * count : 0
             * records : []
             * pages : 0
             * id : 1323
             * createDate : 1561184725000
             * updateDate : 1561424440000
             * createUserId : 242
             * updateUserId : 242
             * cd : 869300039954684
             * name : 刷卡盘---洋葱
             * simCardId : 506
             * equipTypeId : 149
             * equipTypeName : 刷卡盘001
             * isCommer : Y
             * merchantId : 272
             * productNumber : 1
             * productUnit : 局
             * fee : 2.00
             * sig : 17
             * taoCanId : 549
             * taocanName : 2元一局
             * agentId : 476
             * agentPhone : 18058423179
             * contactUser : 客服
             * contactMobilePhone : 18058423179
             * status : normal
             * serviceType : renewDivide
             * renewPrice : 0.01
             * oldRenewPrice : 0.01
             * showImg : http://qa-user.fork.red/image/262/20190625/D7C9183515E7433380A563933F7A8AB81918177926.jpg
             * isFirstBuyout : Y
             * isFirstRenew : Y
             * isFirstRenewDivide : N
             * equipPort : ALL
             * baseType : mj_swipe
             * isOnline : false
             * isReplenisher : N
             * sigIntensity : MEDIUM
             * sigIntensityPath : http://qa-static.fork.red/project/wechart/img/ivem/gangsong/signal-3.png
             * agentType : renewer
             * unSoldQuantity : 0
             * swift : false
             * renewFlag : Y
             */
            private long remainTime;

            public long getTimingDeadLine() {
                    return timingDeadLine;
            }

            public void setTimingDeadLine(long timingDeadLine) {
                this.timingDeadLine = timingDeadLine;
            }

            private long timingDeadLine;   //截止时间
            private int page;
            private int pageSize;
            private int count;
            private int pages;
            private int id;
            private long createDate;
            private long updateDate;
            private int createUserId;
            private int updateUserId;
            private String cd;
            private String name;
            private int simCardId;
            private int equipTypeId;
            private String equipTypeName;
            private String isCommer;
            private int merchantId;
            private int productNumber;
            private String productUnit;
            private String fee;
            private String sig;
            private int taoCanId;
            private String taocanName;
            private int agentId;
            private String agentPhone;
            private String contactUser;
            private String contactMobilePhone;
            private String status;
            private String serviceType;
            private double renewPrice;
            private double oldRenewPrice;
            private String showImg;
            private String isFirstBuyout;
            private String isFirstRenew;
            private String isFirstRenewDivide;
            private String equipPort;
            private String baseType;
            private boolean isOnline;
            private String isReplenisher;
            private String sigIntensity;
            private String sigIntensityPath;
            private String agentType;
            private int unSoldQuantity;
            private boolean swift;
            private String renewFlag;
            private List<?> records;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public int getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(int updateUserId) {
                this.updateUserId = updateUserId;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSimCardId() {
                return simCardId;
            }

            public void setSimCardId(int simCardId) {
                this.simCardId = simCardId;
            }

            public int getEquipTypeId() {
                return equipTypeId;
            }

            public void setEquipTypeId(int equipTypeId) {
                this.equipTypeId = equipTypeId;
            }

            public String getEquipTypeName() {
                return equipTypeName;
            }

            public void setEquipTypeName(String equipTypeName) {
                this.equipTypeName = equipTypeName;
            }

            public String getIsCommer() {
                return isCommer;
            }

            public void setIsCommer(String isCommer) {
                this.isCommer = isCommer;
            }

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public int getProductNumber() {
                return productNumber;
            }

            public void setProductNumber(int productNumber) {
                this.productNumber = productNumber;
            }

            public String getProductUnit() {
                return productUnit;
            }

            public void setProductUnit(String productUnit) {
                this.productUnit = productUnit;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getSig() {
                return sig;
            }

            public void setSig(String sig) {
                this.sig = sig;
            }

            public int getTaoCanId() {
                return taoCanId;
            }

            public void setTaoCanId(int taoCanId) {
                this.taoCanId = taoCanId;
            }

            public String getTaocanName() {
                return taocanName;
            }

            public void setTaocanName(String taocanName) {
                this.taocanName = taocanName;
            }

            public int getAgentId() {
                return agentId;
            }

            public void setAgentId(int agentId) {
                this.agentId = agentId;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getContactUser() {
                return contactUser;
            }

            public void setContactUser(String contactUser) {
                this.contactUser = contactUser;
            }

            public String getContactMobilePhone() {
                return contactMobilePhone;
            }

            public void setContactMobilePhone(String contactMobilePhone) {
                this.contactMobilePhone = contactMobilePhone;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public double getRenewPrice() {
                return renewPrice;
            }

            public void setRenewPrice(double renewPrice) {
                this.renewPrice = renewPrice;
            }

            public double getOldRenewPrice() {
                return oldRenewPrice;
            }

            public void setOldRenewPrice(double oldRenewPrice) {
                this.oldRenewPrice = oldRenewPrice;
            }

            public String getShowImg() {
                return showImg;
            }

            public void setShowImg(String showImg) {
                this.showImg = showImg;
            }

            public String getIsFirstBuyout() {
                return isFirstBuyout;
            }

            public void setIsFirstBuyout(String isFirstBuyout) {
                this.isFirstBuyout = isFirstBuyout;
            }

            public String getIsFirstRenew() {
                return isFirstRenew;
            }

            public void setIsFirstRenew(String isFirstRenew) {
                this.isFirstRenew = isFirstRenew;
            }

            public String getIsFirstRenewDivide() {
                return isFirstRenewDivide;
            }

            public void setIsFirstRenewDivide(String isFirstRenewDivide) {
                this.isFirstRenewDivide = isFirstRenewDivide;
            }

            public String getEquipPort() {
                return equipPort;
            }

            public void setEquipPort(String equipPort) {
                this.equipPort = equipPort;
            }

            public String getBaseType() {
                return baseType;
            }

            public void setBaseType(String baseType) {
                this.baseType = baseType;
            }

            public boolean isIsOnline() {
                return isOnline;
            }

            public void setIsOnline(boolean isOnline) {
                this.isOnline = isOnline;
            }

            public String getIsReplenisher() {
                return isReplenisher;
            }

            public void setIsReplenisher(String isReplenisher) {
                this.isReplenisher = isReplenisher;
            }

            public String getSigIntensity() {
                return sigIntensity;
            }

            public void setSigIntensity(String sigIntensity) {
                this.sigIntensity = sigIntensity;
            }

            public String getSigIntensityPath() {
                return sigIntensityPath;
            }

            public void setSigIntensityPath(String sigIntensityPath) {
                this.sigIntensityPath = sigIntensityPath;
            }

            public String getAgentType() {
                return agentType;
            }

            public void setAgentType(String agentType) {
                this.agentType = agentType;
            }

            public int getUnSoldQuantity() {
                return unSoldQuantity;
            }

            public void setUnSoldQuantity(int unSoldQuantity) {
                this.unSoldQuantity = unSoldQuantity;
            }

            public boolean isSwift() {
                return swift;
            }

            public void setSwift(boolean swift) {
                this.swift = swift;
            }

            public String getRenewFlag() {
                return renewFlag;
            }

            public void setRenewFlag(String renewFlag) {
                this.renewFlag = renewFlag;
            }

            public List<?> getRecords() {
                return records;
            }

            public void setRecords(List<?> records) {
                this.records = records;
            }
        }
    }
}
