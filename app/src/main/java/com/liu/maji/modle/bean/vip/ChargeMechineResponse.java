package com.liu.maji.modle.bean.vip;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class ChargeMechineResponse extends CommonResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page : 1
         * pageSize : 10
         * count : 0
         * records : []
         * pages : 0
         * id : 1287
         * createDate : 1556522108000
         * updateDate : 1558790899000
         * createUserId : 242
         * updateUserId : 242
         * cd : 869300036309767
         * name : 土豆
         * equipTypeId : 149
         * equipTypeName : 刷卡盘001
         * isCommer : Y
         * merchantId : 272
         * contactUser : 大神1
         * contactMobilePhone : 15658853435
         * status : normal
         * serviceType : renewDivide
         * renewPrice : 0.01
         * oldRenewPrice : 0.01
         * showImg : /image/272/20190525/EED0FDB1FC714F719B55BC1F4EBD7FDC1696699730.png
         * isFirstBuyout : Y
         * isFirstRenew : Y
         * isFirstRenewDivide : N
         * equipPort : ALL
         * baseType : mj_recharge
         * isOnline : true
         * isReplenisher : N
         * agentId : 468
         * sigIntensity : HIGH
         * agentType : renewer
         * unSoldQuantity : 0
         * swift : false
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
        private String cd;
        private String name;
        private int equipTypeId;
        private String equipTypeName;
        private String isCommer;
        private int merchantId;
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
        private int agentId;
        private String sigIntensity;
        private String agentType;
        private int unSoldQuantity;
        private boolean swift;
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

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public String getSigIntensity() {
            return sigIntensity;
        }

        public void setSigIntensity(String sigIntensity) {
            this.sigIntensity = sigIntensity;
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

        public List<?> getRecords() {
            return records;
        }

        public void setRecords(List<?> records) {
            this.records = records;
        }
    }
}
