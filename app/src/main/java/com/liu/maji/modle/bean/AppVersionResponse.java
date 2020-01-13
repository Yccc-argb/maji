package com.liu.maji.modle.bean;

import com.liu.maji.modle.CommonResponse;

public class AppVersionResponse extends CommonResponse {

    /**
     * data : {"version":"1.0.3","url":"http://qa-static.fork.red/project/admin/apk/upgrade/mj-1.0.3.apk"}
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
         * version : 1.0.3
         * url : http://qa-static.fork.red/project/admin/apk/upgrade/mj-1.0.3.apk
         */

        private String version;
        private String url;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
