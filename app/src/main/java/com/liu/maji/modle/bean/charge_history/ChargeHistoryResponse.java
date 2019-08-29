package com.liu.maji.modle.bean.charge_history;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class ChargeHistoryResponse extends CommonResponse {


    /**
     * data : {"page":1,"pageSize":10,"count":5,"records":[{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":879,"createDate":1561729353000,"updateDate":1561729353000,"cardId":124,"platAmount":559,"noDepositAmount":100,"operateAmount":200,"reportAmount":559,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":862,"createDate":1561422106000,"updateDate":1561422106000,"cardId":124,"platAmount":579,"noDepositAmount":0,"operateAmount":100,"reportAmount":579,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":856,"createDate":1561395024000,"updateDate":1561395024000,"cardId":124,"platAmount":479,"noDepositAmount":0,"operateAmount":100,"reportAmount":479,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":807,"createDate":1561280246000,"updateDate":1561280246000,"cardId":124,"platAmount":1,"noDepositAmount":0,"operateAmount":500,"reportAmount":1,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":703,"createDate":1561261698000,"updateDate":1561261698000,"cardId":124,"platAmount":0,"noDepositAmount":0,"operateAmount":200,"reportAmount":0,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"}],"pages":1,"recordStart":1,"recordEnd":10,"operateType":"recharge_offline","startTime":1556668800000,"endTime":1561852800000,"cardCd":"","agentId":478,"merchantId":272}
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
         * page : 1
         * pageSize : 10
         * count : 5
         * records : [{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":879,"createDate":1561729353000,"updateDate":1561729353000,"cardId":124,"platAmount":559,"noDepositAmount":100,"operateAmount":200,"reportAmount":559,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":862,"createDate":1561422106000,"updateDate":1561422106000,"cardId":124,"platAmount":579,"noDepositAmount":0,"operateAmount":100,"reportAmount":579,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":856,"createDate":1561395024000,"updateDate":1561395024000,"cardId":124,"platAmount":479,"noDepositAmount":0,"operateAmount":100,"reportAmount":479,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":807,"createDate":1561280246000,"updateDate":1561280246000,"cardId":124,"platAmount":1,"noDepositAmount":0,"operateAmount":500,"reportAmount":1,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"id":703,"createDate":1561261698000,"updateDate":1561261698000,"cardId":124,"platAmount":0,"noDepositAmount":0,"operateAmount":200,"reportAmount":0,"operateType":"recharge_offline","cardCd":"D4C34CE5BE","mobile":"17802976446"}]
         * pages : 1
         * recordStart : 1
         * recordEnd : 10
         * operateType : recharge_offline
         * startTime : 1556668800000
         * endTime : 1561852800000
         * cardCd :
         * agentId : 478
         * merchantId : 272
         */

        private int page;
        private int pageSize;
        private int count;
        private int pages;
        private int recordStart;
        private int recordEnd;
        private String operateType;
        private long startTime;
        private long endTime;
        private String cardCd;
        private int agentId;
        private int merchantId;
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

        public String getOperateType() {
            return operateType;
        }

        public void setOperateType(String operateType) {
            this.operateType = operateType;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getCardCd() {
            return cardCd;
        }

        public void setCardCd(String cardCd) {
            this.cardCd = cardCd;
        }

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * page : 1
             * pageSize : 10
             * count : 0
             * records : []
             * pages : 0
             * id : 879
             * createDate : 1561729353000
             * updateDate : 1561729353000
             * cardId : 124
             * platAmount : 559
             * noDepositAmount : 100
             * operateAmount : 200
             * reportAmount : 559
             * operateType : recharge_offline
             * cardCd : D4C34CE5BE
             * mobile : 17802976446
             */

            private int page;
            private int pageSize;
            private int count;
            private int pages;
            private int id;
            private long createDate;
            private long updateDate;
            private int cardId;
            private int platAmount;
            private int noDepositAmount;
            private int operateAmount;
            private int reportAmount;
            private String operateType;
            private String cardCd;
            private String mobile;
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

            public int getCardId() {
                return cardId;
            }

            public void setCardId(int cardId) {
                this.cardId = cardId;
            }

            public int getPlatAmount() {
                return platAmount;
            }

            public void setPlatAmount(int platAmount) {
                this.platAmount = platAmount;
            }

            public int getNoDepositAmount() {
                return noDepositAmount;
            }

            public void setNoDepositAmount(int noDepositAmount) {
                this.noDepositAmount = noDepositAmount;
            }

            public int getOperateAmount() {
                return operateAmount;
            }

            public void setOperateAmount(int operateAmount) {
                this.operateAmount = operateAmount;
            }

            public int getReportAmount() {
                return reportAmount;
            }

            public void setReportAmount(int reportAmount) {
                this.reportAmount = reportAmount;
            }

            public String getOperateType() {
                return operateType;
            }

            public void setOperateType(String operateType) {
                this.operateType = operateType;
            }

            public String getCardCd() {
                return cardCd;
            }

            public void setCardCd(String cardCd) {
                this.cardCd = cardCd;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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