package com.liu.maji.modle.bean;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class AdResponse extends CommonResponse {


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
         * id : 33
         * createDate : 1566482507000
         * updateDate : 1566482507000
         * createUserId : 242
         * updateUserId : 242
         * merchantId : 272
         * imgUrl : /image/272/20190822/32D42820A5BF4B8E9FAF5D552AA53C88-1016833257.png
         * addUrl : http://qa-merchant.fork.red/web-admin/login
         * remarks : hello
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
        private int merchantId;
        private String imgUrl;
        private String addUrl;
        private String remarks;

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

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAddUrl() {
            return addUrl;
        }

        public void setAddUrl(String addUrl) {
            this.addUrl = addUrl;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public List<?> getRecords() {
            return records;
        }

        public void setRecords(List<?> records) {
            this.records = records;
        }

    }
}
