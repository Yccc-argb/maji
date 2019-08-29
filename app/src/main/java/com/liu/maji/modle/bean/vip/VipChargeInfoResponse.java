package com.liu.maji.modle.bean.vip;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class VipChargeInfoResponse extends CommonResponse {


    /**
     * data : {"majiangVOList":[{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":124,"createDate":1561261586000,"updateDate":1561280246000,"createUserId":478,"updateUserId":478,"newCd":"17802976446","cd":"D4C34CE5BE","name":"杨炎炎","mobile":"17802976446","onlineAmount":0,"offlineAmount":479,"giveAmount":0,"amount":479,"merchantId":272,"agentId":478,"status":"normal","enabledFlag":"Y","frozenFunds":0,"nmFrozenFunds":0,"statusStr":"正常","totalFundsAmount":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MajiangVOListBean> majiangVOList;

        public List<MajiangVOListBean> getMajiangVOList() {
            return majiangVOList;
        }

        public void setMajiangVOList(List<MajiangVOListBean> majiangVOList) {
            this.majiangVOList = majiangVOList;
        }

        public static class MajiangVOListBean {
            /**
             * page : 1
             * pageSize : 10
             * count : 0
             * records : []
             * pages : 0
             * id : 124
             * createDate : 1561261586000
             * updateDate : 1561280246000
             * createUserId : 478
             * updateUserId : 478
             * newCd : 17802976446
             * cd : D4C34CE5BE
             * name : 杨炎炎
             * mobile : 17802976446
             * onlineAmount : 0
             * offlineAmount : 479
             * giveAmount : 0
             * amount : 479
             * merchantId : 272
             * agentId : 478
             * status : normal
             * enabledFlag : Y
             * frozenFunds : 0
             * nmFrozenFunds : 0
             * statusStr : 正常
             * totalFundsAmount : 0
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
            private int onlineAmount;
            private int offlineAmount;
            private int giveAmount;
            private int amount;
            private int merchantId;
            private int agentId;
            private String status;
            private String enabledFlag;
            private int frozenFunds;
            private int nmFrozenFunds;
            private String statusStr;
            private int totalFundsAmount;
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

            public int getOnlineAmount() {
                return onlineAmount;
            }

            public void setOnlineAmount(int onlineAmount) {
                this.onlineAmount = onlineAmount;
            }

            public int getOfflineAmount() {
                return offlineAmount;
            }

            public void setOfflineAmount(int offlineAmount) {
                this.offlineAmount = offlineAmount;
            }

            public int getGiveAmount() {
                return giveAmount;
            }

            public void setGiveAmount(int giveAmount) {
                this.giveAmount = giveAmount;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
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

            public int getFrozenFunds() {
                return frozenFunds;
            }

            public void setFrozenFunds(int frozenFunds) {
                this.frozenFunds = frozenFunds;
            }

            public int getNmFrozenFunds() {
                return nmFrozenFunds;
            }

            public void setNmFrozenFunds(int nmFrozenFunds) {
                this.nmFrozenFunds = nmFrozenFunds;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public int getTotalFundsAmount() {
                return totalFundsAmount;
            }

            public void setTotalFundsAmount(int totalFundsAmount) {
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
