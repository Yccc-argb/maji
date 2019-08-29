package com.liu.maji.modle.bean.login;

import com.liu.maji.modle.CommonResponse;

public class LoginResponse extends CommonResponse {

    /**
     * data : {"merchantId":98,"agentId":361}
     */
    public LoginResponse(){
        super();
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * merchantId : 98
         * agentId : 361
         */

        private int merchantId;
        private int agentId;
        private String agentName;

        public void setAgentName(String agentName){
            this.agentName = agentName;
        }

        public String getAgentName(){
            return this.agentName;
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
    }
}
