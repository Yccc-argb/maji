package com.liu.maji.modle.bean.income;

import com.liu.maji.modle.CommonResponse;

import java.util.List;

public class IncomeResponse extends CommonResponse {


    /**
     * data : {"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"success":true,"totalSum":0,"onlineProfit":0,"underLineProfit":0,"ytdOnlineProfit":0,"ytdUnderLineProfit":0,"yesterdayTotalSum":0,"monthTotalSum":0,"monthOnlineTotalSum":0,"monthUnderLineTotalSum":0,"allTotalSum":0,"allOnlineSum":0,"allUnderLineSum":0,"quantity":0,"coinAmount":0,"agentList":[{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559318400000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559404800000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559491200000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559577600000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0}],"monthlyProfitAmt":[0,0],"onlineMonthlyProfitAmt":[0,0],"underLineMonthlyProfitAmt":[0,0],"payType":"all"}
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
         * count : 0
         * records : []
         * pages : 0
         * success : true
         * totalSum : 0
         * onlineProfit : 0
         * underLineProfit : 0
         * ytdOnlineProfit : 0
         * ytdUnderLineProfit : 0
         * yesterdayTotalSum : 0
         * monthTotalSum : 0
         * monthOnlineTotalSum : 0
         * monthUnderLineTotalSum : 0
         * allTotalSum : 0
         * allOnlineSum : 0
         * allUnderLineSum : 0
         * quantity : 0
         * coinAmount : 0
         * agentList : [{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559318400000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559404800000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559491200000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0},{"page":1,"pageSize":10,"count":0,"records":[],"pages":0,"merchantId":272,"agentId":476,"quantity":0,"coinQuantity":0,"cardQuantity":0,"wxQuantity":0,"aliQuantity":0,"fundsQuantity":0,"transDate":1559577600000,"totalAmount":0,"wxAmount":0,"aliAmount":0,"fundsAmount":0,"swipeAmount":0,"coinAmount":0,"cardAmount":0,"totalProfit":0,"wxProfit":0,"aliProfit":0,"fundsProfit":0,"coinProfit":0,"cardProfit":0,"swipeProfit":0}]
         * monthlyProfitAmt : [0,0]
         * onlineMonthlyProfitAmt : [0,0]
         * underLineMonthlyProfitAmt : [0,0]
         * payType : all
         */

        private int page;
        private int pageSize;
        private int count;
        private int pages;
        private boolean successX;
        private double totalSum;
        private int onlineProfit;
        private int underLineProfit;
        private int ytdOnlineProfit;
        private int ytdUnderLineProfit;
        private double yesterdayTotalSum;
        private int monthTotalSum;
        private int monthOnlineTotalSum;
        private int monthUnderLineTotalSum;
        private double allTotalSum;
        private int allOnlineSum;
        private int allUnderLineSum;
        private int quantity;
        private int coinAmount;
        private String payType;
        private List<?> records;
        private List<AgentListBean> agentList;
        private List<Integer> monthlyProfitAmt;
        private List<Integer> onlineMonthlyProfitAmt;
        private List<Integer> underLineMonthlyProfitAmt;

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

        public boolean isSuccessX() {
            return successX;
        }

        public void setSuccessX(boolean successX) {
            this.successX = successX;
        }

        public double getTotalSum() {
            return totalSum;
        }

        public void setTotalSum(double totalSum) {
            this.totalSum = totalSum;
        }

        public int getOnlineProfit() {
            return onlineProfit;
        }

        public void setOnlineProfit(int onlineProfit) {
            this.onlineProfit = onlineProfit;
        }

        public int getUnderLineProfit() {
            return underLineProfit;
        }

        public void setUnderLineProfit(int underLineProfit) {
            this.underLineProfit = underLineProfit;
        }

        public int getYtdOnlineProfit() {
            return ytdOnlineProfit;
        }

        public void setYtdOnlineProfit(int ytdOnlineProfit) {
            this.ytdOnlineProfit = ytdOnlineProfit;
        }

        public int getYtdUnderLineProfit() {
            return ytdUnderLineProfit;
        }

        public void setYtdUnderLineProfit(int ytdUnderLineProfit) {
            this.ytdUnderLineProfit = ytdUnderLineProfit;
        }

        public double getYesterdayTotalSum() {
            return yesterdayTotalSum;
        }

        public void setYesterdayTotalSum(double yesterdayTotalSum) {
            this.yesterdayTotalSum = yesterdayTotalSum;
        }

        public int getMonthTotalSum() {
            return monthTotalSum;
        }

        public void setMonthTotalSum(int monthTotalSum) {
            this.monthTotalSum = monthTotalSum;
        }

        public int getMonthOnlineTotalSum() {
            return monthOnlineTotalSum;
        }

        public void setMonthOnlineTotalSum(int monthOnlineTotalSum) {
            this.monthOnlineTotalSum = monthOnlineTotalSum;
        }

        public int getMonthUnderLineTotalSum() {
            return monthUnderLineTotalSum;
        }

        public void setMonthUnderLineTotalSum(int monthUnderLineTotalSum) {
            this.monthUnderLineTotalSum = monthUnderLineTotalSum;
        }

        public double getAllTotalSum() {
            return allTotalSum;
        }

        public void setAllTotalSum(double allTotalSum) {
            this.allTotalSum = allTotalSum;
        }

        public int getAllOnlineSum() {
            return allOnlineSum;
        }

        public void setAllOnlineSum(int allOnlineSum) {
            this.allOnlineSum = allOnlineSum;
        }

        public int getAllUnderLineSum() {
            return allUnderLineSum;
        }

        public void setAllUnderLineSum(int allUnderLineSum) {
            this.allUnderLineSum = allUnderLineSum;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getCoinAmount() {
            return coinAmount;
        }

        public void setCoinAmount(int coinAmount) {
            this.coinAmount = coinAmount;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public List<?> getRecords() {
            return records;
        }

        public void setRecords(List<?> records) {
            this.records = records;
        }

        public List<AgentListBean> getAgentList() {
            return agentList;
        }

        public void setAgentList(List<AgentListBean> agentList) {
            this.agentList = agentList;
        }

        public List<Integer> getMonthlyProfitAmt() {
            return monthlyProfitAmt;
        }

        public void setMonthlyProfitAmt(List<Integer> monthlyProfitAmt) {
            this.monthlyProfitAmt = monthlyProfitAmt;
        }

        public List<Integer> getOnlineMonthlyProfitAmt() {
            return onlineMonthlyProfitAmt;
        }

        public void setOnlineMonthlyProfitAmt(List<Integer> onlineMonthlyProfitAmt) {
            this.onlineMonthlyProfitAmt = onlineMonthlyProfitAmt;
        }

        public List<Integer> getUnderLineMonthlyProfitAmt() {
            return underLineMonthlyProfitAmt;
        }

        public void setUnderLineMonthlyProfitAmt(List<Integer> underLineMonthlyProfitAmt) {
            this.underLineMonthlyProfitAmt = underLineMonthlyProfitAmt;
        }

        public static class AgentListBean {
            /**
             * page : 1
             * pageSize : 10
             * count : 0
             * records : []
             * pages : 0
             * merchantId : 272
             * agentId : 476
             * quantity : 0
             * coinQuantity : 0
             * cardQuantity : 0
             * wxQuantity : 0
             * aliQuantity : 0
             * fundsQuantity : 0
             * transDate : 1559318400000
             * totalAmount : 0
             * wxAmount : 0
             * aliAmount : 0
             * fundsAmount : 0
             * swipeAmount : 0
             * coinAmount : 0
             * cardAmount : 0
             * totalProfit : 0
             * wxProfit : 0
             * aliProfit : 0
             * fundsProfit : 0
             * coinProfit : 0
             * cardProfit : 0
             * swipeProfit : 0
             */

            private int page;
            private int pageSize;
            private int count;
            private int pages;
            private int merchantId;
            private int agentId;
            private int quantity;
            private int coinQuantity;
            private int cardQuantity;
            private int wxQuantity;
            private int aliQuantity;
            private int fundsQuantity;
            private long transDate;
            private int totalAmount;
            private int wxAmount;
            private int aliAmount;
            private int fundsAmount;
            private int swipeAmount;
            private int coinAmount;
            private int cardAmount;
            private int totalProfit;
            private int wxProfit;
            private int aliProfit;
            private int fundsProfit;
            private int coinProfit;
            private int cardProfit;
            private int swipeProfit;
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

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getCoinQuantity() {
                return coinQuantity;
            }

            public void setCoinQuantity(int coinQuantity) {
                this.coinQuantity = coinQuantity;
            }

            public int getCardQuantity() {
                return cardQuantity;
            }

            public void setCardQuantity(int cardQuantity) {
                this.cardQuantity = cardQuantity;
            }

            public int getWxQuantity() {
                return wxQuantity;
            }

            public void setWxQuantity(int wxQuantity) {
                this.wxQuantity = wxQuantity;
            }

            public int getAliQuantity() {
                return aliQuantity;
            }

            public void setAliQuantity(int aliQuantity) {
                this.aliQuantity = aliQuantity;
            }

            public int getFundsQuantity() {
                return fundsQuantity;
            }

            public void setFundsQuantity(int fundsQuantity) {
                this.fundsQuantity = fundsQuantity;
            }

            public long getTransDate() {
                return transDate;
            }

            public void setTransDate(long transDate) {
                this.transDate = transDate;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getWxAmount() {
                return wxAmount;
            }

            public void setWxAmount(int wxAmount) {
                this.wxAmount = wxAmount;
            }

            public int getAliAmount() {
                return aliAmount;
            }

            public void setAliAmount(int aliAmount) {
                this.aliAmount = aliAmount;
            }

            public int getFundsAmount() {
                return fundsAmount;
            }

            public void setFundsAmount(int fundsAmount) {
                this.fundsAmount = fundsAmount;
            }

            public int getSwipeAmount() {
                return swipeAmount;
            }

            public void setSwipeAmount(int swipeAmount) {
                this.swipeAmount = swipeAmount;
            }

            public int getCoinAmount() {
                return coinAmount;
            }

            public void setCoinAmount(int coinAmount) {
                this.coinAmount = coinAmount;
            }

            public int getCardAmount() {
                return cardAmount;
            }

            public void setCardAmount(int cardAmount) {
                this.cardAmount = cardAmount;
            }

            public int getTotalProfit() {
                return totalProfit;
            }

            public void setTotalProfit(int totalProfit) {
                this.totalProfit = totalProfit;
            }

            public int getWxProfit() {
                return wxProfit;
            }

            public void setWxProfit(int wxProfit) {
                this.wxProfit = wxProfit;
            }

            public int getAliProfit() {
                return aliProfit;
            }

            public void setAliProfit(int aliProfit) {
                this.aliProfit = aliProfit;
            }

            public int getFundsProfit() {
                return fundsProfit;
            }

            public void setFundsProfit(int fundsProfit) {
                this.fundsProfit = fundsProfit;
            }

            public int getCoinProfit() {
                return coinProfit;
            }

            public void setCoinProfit(int coinProfit) {
                this.coinProfit = coinProfit;
            }

            public int getCardProfit() {
                return cardProfit;
            }

            public void setCardProfit(int cardProfit) {
                this.cardProfit = cardProfit;
            }

            public int getSwipeProfit() {
                return swipeProfit;
            }

            public void setSwipeProfit(int swipeProfit) {
                this.swipeProfit = swipeProfit;
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
