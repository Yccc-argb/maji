package com.liu.maji.modle.bean.vip;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class VipInfoResponse extends CommonResponse {


    /**
     * data : {"amount":1074,"majiangVOList":[{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":112,"createDate":1560947457000,"updateDate":1561189955000,"createUserId":476,"updateUserId":476,"newCd":"18058423179","cd":"A72FCD1B5E","name":"简宇杰","mobile":"17326030813","onlineAmount":0,"offlineAmount":374,"giveAmount":0,"amount":374,"merchantId":272,"consumerId":241,"agentId":476,"status":"normal","enabledFlag":"Y","frozenFunds":0,"nmFrozenFunds":0,"statusStr":"正常","totalFundsAmount":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":107,"createDate":1558855073000,"updateDate":1558862591000,"createUserId":476,"updateUserId":476,"newCd":"13601010101","cd":"3762CC1B82","name":"杨cc","mobile":"15157172719","onlineAmount":0,"offlineAmount":700,"giveAmount":0,"amount":700,"merchantId":272,"consumerId":240,"agentId":476,"status":"normal","enabledFlag":"Y","frozenFunds":0,"nmFrozenFunds":0,"statusStr":"正常","totalFundsAmount":0}],"onlinemount":0,"offlineaAmount":1074,"majiangVOListSize":2}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 1074.0
         * majiangVOList : [{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":112,"createDate":1560947457000,"updateDate":1561189955000,"createUserId":476,"updateUserId":476,"newCd":"18058423179","cd":"A72FCD1B5E","name":"简宇杰","mobile":"17326030813","onlineAmount":0,"offlineAmount":374,"giveAmount":0,"amount":374,"merchantId":272,"consumerId":241,"agentId":476,"status":"normal","enabledFlag":"Y","frozenFunds":0,"nmFrozenFunds":0,"statusStr":"正常","totalFundsAmount":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":107,"createDate":1558855073000,"updateDate":1558862591000,"createUserId":476,"updateUserId":476,"newCd":"13601010101","cd":"3762CC1B82","name":"杨cc","mobile":"15157172719","onlineAmount":0,"offlineAmount":700,"giveAmount":0,"amount":700,"merchantId":272,"consumerId":240,"agentId":476,"status":"normal","enabledFlag":"Y","frozenFunds":0,"nmFrozenFunds":0,"statusStr":"正常","totalFundsAmount":0}]
         * onlinemount : 0.0
         * offlineaAmount : 1074.0
         * majiangVOListSize : 2
         */

        private double amount;
        private double onlinemount;
        private double offlineaAmount;
        private int majiangVOListSize;
        private List<MajiangVOListBean> majiangVOList;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getOnlinemount() {
            return onlinemount;
        }

        public void setOnlinemount(double onlinemount) {
            this.onlinemount = onlinemount;
        }

        public double getOfflineaAmount() {
            return offlineaAmount;
        }

        public void setOfflineaAmount(double offlineaAmount) {
            this.offlineaAmount = offlineaAmount;
        }

        public int getMajiangVOListSize() {
            return majiangVOListSize;
        }

        public void setMajiangVOListSize(int majiangVOListSize) {
            this.majiangVOListSize = majiangVOListSize;
        }

        public List<MajiangVOListBean> getMajiangVOList() {
            return majiangVOList;
        }

        public void setMajiangVOList(List<MajiangVOListBean> majiangVOList) {
            this.majiangVOList = majiangVOList;
        }

        public static class MajiangVOListBean extends CommonResponse{
            /**
             * page : 1
             * pageSize : 10
             * count : 0
             * records : []
             * pages : 0
             * id : 112
             * createDate : 1560947457000
             * updateDate : 1561189955000
             * createUserId : 476
             * updateUserId : 476
             * newCd : 18058423179
             * cd : A72FCD1B5E
             * name : 简宇杰
             * mobile : 17326030813
             * onlineAmount : 0.0
             * offlineAmount : 374.0
             * giveAmount : 0.0
             * amount : 374.0
             * merchantId : 272
             * consumerId : 241
             * agentId : 476
             * status : normal
             * enabledFlag : Y
             * frozenFunds : 0.0
             * nmFrozenFunds : 0.0
             * statusStr : 正常
             * totalFundsAmount : 0.0
             */

            private int page;
            private int pageSize;
            private int count;
            private int pages;
            private int id;
            private long createDate;
            private long updateDate;
            private int createUserId;
            private int updateUserId;
            private String newCd;
            private String cd;
            private String name;
            private String mobile;
            private double onlineAmount;
            private double offlineAmount;
            private double giveAmount;
            private double amount;
            private int merchantId;
            private int consumerId;
            private int agentId;
            private String status;
            private String enabledFlag;
            private double frozenFunds;
            private double nmFrozenFunds;
            private String statusStr;
            private double totalFundsAmount;
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

            public String getNewCd() {
                return newCd;
            }

            public void setNewCd(String newCd) {
                this.newCd = newCd;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public double getOnlineAmount() {
                return onlineAmount;
            }

            public void setOnlineAmount(double onlineAmount) {
                this.onlineAmount = onlineAmount;
            }

            public double getOfflineAmount() {
                return offlineAmount;
            }

            public void setOfflineAmount(double offlineAmount) {
                this.offlineAmount = offlineAmount;
            }

            public double getGiveAmount() {
                return giveAmount;
            }

            public void setGiveAmount(double giveAmount) {
                this.giveAmount = giveAmount;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public int getConsumerId() {
                return consumerId;
            }

            public void setConsumerId(int consumerId) {
                this.consumerId = consumerId;
            }

            public int getAgentId() {
                return agentId;
            }

            public void setAgentId(int agentId) {
                this.agentId = agentId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEnabledFlag() {
                return enabledFlag;
            }

            public void setEnabledFlag(String enabledFlag) {
                this.enabledFlag = enabledFlag;
            }

            public double getFrozenFunds() {
                return frozenFunds;
            }

            public void setFrozenFunds(double frozenFunds) {
                this.frozenFunds = frozenFunds;
            }

            public double getNmFrozenFunds() {
                return nmFrozenFunds;
            }

            public void setNmFrozenFunds(double nmFrozenFunds) {
                this.nmFrozenFunds = nmFrozenFunds;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public double getTotalFundsAmount() {
                return totalFundsAmount;
            }

            public void setTotalFundsAmount(double totalFundsAmount) {
                this.totalFundsAmount = totalFundsAmount;
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
